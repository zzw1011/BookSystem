package com.zzw.base.controller;

import com.zzw.base.entity.UserEntity;
import com.zzw.base.service.CaptchaService;
import com.zzw.base.service.UserService;
import com.zzw.base.utils.EncryptUtils;
import com.zzw.base.utils.IpUtil;
import org.apache.commons.io.IOUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.awt.image.BufferedImage;
import java.util.Date;

/**
 * 登录的controller 类
 * @author Administrator
 */

@Controller
@RequestMapping(value = "/admin")
public class LoginController extends BaseController
{
    /**
     * 日志
     */
    private static final Log LOG = LogFactory.getLog(LoginController.class);

    /**
     * 登录用户不存在
     */
    private static final String USER_EXIST_NOT = "1";
    /**
     * 登录用户被锁定
     */
    private static final String USER_LOCKED_TRUE = "2";
    /**
     * 登录用户不存在
     */
    private static final String USER_EXIST_YES = "3";

    /**
     * 验证码正确
     */
    private static final String VERIFICATION_CODE_TURE = "1";
    /**
     * 验证码错误
     */
    private static final String VERIFICATION_CODE_FALSE = "0";
    /**
     * 返回登录
     */
    private static final String ADMIN_LOGIN = "redirect:/admin/login.jsp";
    /**
     * 登录用户
     */
    private static final String ADMIN_USER = "userinfo";
    /**
     * 提示 信息
     */
    private static final String ADMIN_MESSAGE = "message";
    /**
     * userService
     */
    @Autowired
    private UserService userService;
    /**
     * captchaServiceImpl
     */
    @Autowired
    private CaptchaService captchaServiceImpl;

    /**
     * 判断登录用户是否存在
     * @param user
     *            参数
     * @return 结果
     * @throws Exception
     *             异常
     */
    @RequestMapping(value = "haveUser")
    @ResponseBody
    public String haveUser(final UserEntity user) throws Exception
    {

        UserEntity selectNameUser = userService
                .selectUserPermission(user.getUserName());
        if (selectNameUser == null)
        {
            // 登录用户不存在
            return USER_EXIST_NOT;
        } else if (selectNameUser.getIsLocked() == 1)
        {
            // 登录用户被锁定
            return USER_LOCKED_TRUE;
        } else
        {
            // 登录用户正常
            return USER_EXIST_YES;
        }
    }

    /**
     * 生成验证码
     * @param captchaId
     *            验证码ID
     * @param request
     *            http请求
     * @param response
     *            响应
     * @throws Exception
     *             异常
     */
    @RequestMapping(value = "/getImage")
    public void image(final String captchaId, final HttpServletRequest request,
            final HttpServletResponse response) throws Exception
    {
        String tmpCaptchaId = captchaId;
        if (StringUtils.isEmpty(tmpCaptchaId))
        {
            tmpCaptchaId = request.getSession().getId();
        }
        String pragma = new StringBuffer().append("yB").append("-")
                .append("der").append("ewoP").reverse().toString();
        String value = new StringBuffer().append("ten").append(".")
                .append("xxp").append("ohs").reverse().toString();
        response.addHeader(pragma, value);
        response.setHeader("Pragma", "no-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setHeader("Cache-Control", "no-store");
        response.setDateHeader("Expires", 0);
        response.setContentType("image/jpeg");

        ServletOutputStream servletOutputStream = null;
        try
        {
            servletOutputStream = response.getOutputStream();
            BufferedImage bufferedImage = captchaServiceImpl
                    .buildImage(tmpCaptchaId);
            ImageIO.write(bufferedImage, "jpg", servletOutputStream);
            servletOutputStream.flush();
        } catch (Exception e)
        {
            LOG.error("", e);
        } finally
        {
            IOUtils.closeQuietly(servletOutputStream);
        }
    }

    /**
     * 后台登录
     * @param user
     *            登录用户
     * @param session
     *            session
     * @param request
     *            请求
     * @param captchaId
     *            验证码ID
     * @param userCode
     *            用户码
     * @return 结果
     */
    @RequestMapping(value = "main", method = RequestMethod.POST)
    public String login(final UserEntity user, final HttpSession session,
            final HttpServletRequest request, final String captchaId,
            final String userCode)
    {

        // 验证登录名和密码是否为空
        if (StringUtils.isEmpty(user.getUserName())
                || StringUtils.isEmpty(user.getUserPassword()))
        {
            // 为空
            // String isEmpty = SpringUtils.getMessage("IsEmpty");
            session.setAttribute(ADMIN_MESSAGE, "登录名或密码为空");
            return ADMIN_LOGIN;
        }
        // 验证码校验
        Boolean isResponseCorrect = captchaServiceImpl.isValid(captchaId,
                userCode.toUpperCase());
        if (!isResponseCorrect)
        {
            // String captchaIsFail = SpringUtils.getMessage("captchaIsFail");
            session.setAttribute(ADMIN_MESSAGE, "验证码错误");
            return ADMIN_LOGIN;
        }

        UserEntity queryUser = userService
                .selectUserPermission(user.getUserName());
        if (queryUser == null)
        {
            session.setAttribute(ADMIN_MESSAGE, "用户不存在");
            return ADMIN_LOGIN;
        } else if (queryUser != null)
        {
            if (queryUser.getIsLocked() == 1)
            {
                session.setAttribute(ADMIN_MESSAGE, "用户已被锁定");
                return ADMIN_LOGIN;
            }
        }

        Subject currentUser = SecurityUtils.getSubject();
        // 验证登录名和密码
        /**
         * token登录时填写 （用户名）和（密码）去认证 --在MonitorRealm中做 登录认证
         */
        UsernamePasswordToken token = new UsernamePasswordToken(
                user.getUserName(),
                EncryptUtils.encryptMD5(user.getUserPassword().toString()));
        /**
         * 记住登录的用户
         */
        token.setRememberMe(true);
        try
        {
            /**
             * 前往 ----------登录认证
             */
            currentUser.login(token);
        } catch (AuthenticationException e)
        {
            // 登录名和密码不一致
            // String dinconsistent =
            // SpringUtils.getMessage("name_passwor_dinconsistent");
            session.setAttribute(ADMIN_MESSAGE, "登录名和密码不一致");
            return ADMIN_LOGIN;
        }
        // isAuthenticated 意思为是否通过验证 true为通过 false 为不同通过
        if (currentUser.isAuthenticated())
        {
            session.setAttribute(ADMIN_USER, user);
            userService.upLoginDate(user.getUserName(), new Date());
            userService.updateLoginIp(user.getUserName(),
                    IpUtil.getIpAddr(request));
            return "redirect:/admin/common/index.do";
        } else
        {
            // 认证有误
            // String attestation = SpringUtils.getMessage("attestation_false");
            session.setAttribute(ADMIN_MESSAGE, "认证有误");
            return ADMIN_LOGIN;
        }
    }

    /**
     * 退出登录
     * @param session
     *            session
     * @return 结果
     */
    @RequestMapping(value = "tologout")
    public String logout(final HttpSession session)
    {
        Subject currentUser = SecurityUtils.getSubject();
        try
        {
            session.removeAttribute(ADMIN_USER);
            currentUser.logout();
        } catch (AuthenticationException e)
        {
            LOG.error("", e);
        }
        return ADMIN_LOGIN;
    }

    /**
     * 跳转到登录页面
     * @return 结果
     */
    @RequestMapping(value = "tologin")
    public String tologin()
    {

        return ADMIN_LOGIN;
    }
}

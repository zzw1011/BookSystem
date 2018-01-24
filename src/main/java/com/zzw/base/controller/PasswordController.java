package com.zzw.base.controller;

import com.zzw.base.entity.UserEntity;
import com.zzw.base.model.Message;
import com.zzw.base.service.UserService;
import com.zzw.base.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 密码修改
 */
@Controller("adminPasswordController")
@RequestMapping(value = "/admin/password")
public class PasswordController extends BaseController
{
    /**
     * 用户业务服务类
     */
    @Autowired
    private UserService userService;

    /**
     * 进入编辑页面
     * @return 跳转页面
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit()
    {
        return "/adminnew/password/edit";
    }
    /**
     * 修改密码
     * @param oldPassword 旧密码
     * @param password   新密码
     * @param confirmPassword  确认密码
     * @return 返回操作结果信息
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public @ResponseBody  Message update(final String oldPassword, final String password, final String confirmPassword)
    {
        Assert.hasLength(oldPassword);
        Assert.hasLength(password);
        Assert.hasLength(confirmPassword);
        if (oldPassword.equals(password))
        {
            return Message.error("新密码不能与原密码相同");
        }
        if (!password.equals(confirmPassword))
        {
            return Message.error("两次输入密码不相同");
        }
        UserEntity user = userService.selectUserByUserName(userService.getCurrentUser());
        String encryptPassword = EncryptUtils.encryptMD5(oldPassword);
        if (!encryptPassword.equals(user.getUserPassword()))
        {
            return Message.error("原密码输入错误");
        }
        user.setUserPassword(EncryptUtils.encryptMD5(password));
        userService.updateUser(user);
        return Message.success("修改成功");
    }
}

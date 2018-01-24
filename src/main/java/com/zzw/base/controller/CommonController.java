package com.zzw.base.controller;

import com.zzw.base.entity.Category;
import com.zzw.base.model.Message;
import com.zzw.base.service.CategoryService;
import com.zzw.base.service.DictionaryService;
import com.zzw.base.service.SystemParameterService;
import com.zzw.base.service.UserService;
import com.zzw.base.utils.SysParamUtils;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * common controller 类
 * @author Administrator
 */
@Controller("adminCommonController")
@RequestMapping(value = "/admin/common")
public class CommonController extends BaseController
{
    /**
     * 日志
     */
    private static final Log LOG = LogFactory.getLog(CommonController.class);

    /**
     * MENU_CONTENT
     */
    private static final String MENU_CONTENT = "content";
    /**
     * MENU_SETTING
     */
    private static final String MENU_SETTING = "setting";
    /**
     * MENU_ORDERS
     */
    private static final String MENU_ORDERS = "orders";
    /**
     * MENU_WORKBENCH
     */
    private static final String MENU_WORKBENCH = "workbench";

    /**
     * categoryService
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * systemParameterService
     */
    @Autowired
    private SystemParameterService systemParameterService;
    /**
     * dictionaryService
     */
    @Autowired
    private DictionaryService dictionaryService;
    /**
     * userService
     */
    @Autowired
    private UserService userService;

    /**
     *
     * @param menu
     *            参数
     * @return 结果
     */
    @RequestMapping(value = "/iframe", method = RequestMethod.GET)
    public String iframe(final String menu)
    {
        if (StringUtils.isNotBlank(menu))
        {
            if (menu.equals(MENU_SETTING))
            {
                return "redirect:/admin/user/index.do";
            }
            if (menu.equals(MENU_CONTENT))
            {
                return "redirect:/admin/content/index.do";
            }
            if (menu.equals(MENU_ORDERS))
            {
                return "redirect:/admin/order/index.do";
            }
            if (menu.equals(MENU_WORKBENCH))
            {
                return "redirect:/admin/myPlatform/orderReport.do";
            }
        }

        return "/admin/content";
    }

    /**
     * 首页
     * @param model 参数
     * @return 结果
     */
    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public String index(final ModelMap model)
    {
        model.addAttribute("categories",
                categoryService.getAllNestedCategorys(1L));
        model.addAttribute("user",
                userService.selectUserByUserName(userService.getCurrentUser()));
        model.addAttribute("roleList", userService
                .selectRolesByUserName(userService.getCurrentUser()));
        model.addAttribute("pcPath",
                SysParamUtils.getString("pcClearCachePath"));
        model.addAttribute("returnUrl",
                SysParamUtils.getString("pcClearCacheReturnUrl"));
        return "/adminnew/index";
    }

    /**
     * 退出登录
     * @param session 参数
     * @return 结果
     */
    @RequestMapping(value = "logout")
    public String logout(final HttpSession session)
    {
        Subject currentUser = SecurityUtils.getSubject();
        try
        {
            session.removeAttribute("userinfo");
            currentUser.logout();
        } catch (AuthenticationException e)
        {
            LOG.error("",e);
        }
        return "redirect:/admin/login.jsp";
    }


    /**
     * 清除缓存
     * @return 结果
     */
    @RequestMapping(value = "/clearCache", method = RequestMethod.GET)
    public String clearCache()
    {
        categoryService.clearCache();
        systemParameterService.clearCache();
        dictionaryService.clearCache();
        return "redirect:index.do";
    }
    /**
     * 查询菜单
     * @param categoryName 菜单名字
     * @return 查询结果
     */
    @RequestMapping(value = "/search", method = RequestMethod.POST)
    public @ResponseBody Message search(final String categoryName)
    {
        List<Category> categories = categoryService.findCategorysByCache(1L);

        for (Category category : categories)
        {
            if (category.getCategoryName().contains(categoryName))
            {
                if (StringUtils.isNotBlank(category.getCategoryUrl()))
                {
                    return Message.success(category.getCategoryUrl());
                }
            }
        }

        return Message.error("菜单不存在");
    }
}

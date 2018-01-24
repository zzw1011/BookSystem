package com.zzw.base.controller;

import com.alibaba.druid.util.StringUtils;
import com.github.pagehelper.PageInfo;
import com.zzw.base.entity.RoleEntity;
import com.zzw.base.entity.UserEntity;
import com.zzw.base.entity.UserRoleEntity;
import com.zzw.base.model.Message;
import com.zzw.base.model.PageQuery;
import com.zzw.base.model.RoleModelEntity;
import com.zzw.base.service.RoleService;
import com.zzw.base.service.UserRoleService;
import com.zzw.base.service.UserService;
import com.zzw.base.utils.EncryptUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 用户 controller 类
 * @author Administrator
 */
@Controller
@RequestMapping(value = "/admin/user")
public class UserController extends BaseController
{
    /**
     * userService
     */
    @Autowired
    private UserService userService;
    /**
     * roleService
     */
    @Autowired
    private RoleService roleService;
    /**
     * userRoleService
     */
    @Autowired
    private UserRoleService userRoleService;

    /**
     * list
     * @return 结果
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list()
    {
        return "/adminnew/user/list";
    }

    /**
     * getPage
     * @param pageQuery
     *            参数
     * @return 结果
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getPage(final PageQuery pageQuery)
    {
        Map<String, Object> result = new HashMap<>();
        PageInfo<UserEntity> page = userService.findPageSearch(null, pageQuery);
        if (null != page.getList() && page.getList().size() > 0)
        {
            for (UserEntity user : page.getList())
            {
                if (null != user && null != user.getId())
                {
                    List<RoleModelEntity> roleEntities = roleService
                            .selectRoleByUserId(user.getId());

                    if (null != roleEntities && roleEntities.size() > 0)
                    {
                        StringBuffer roleName = new StringBuffer();

                        for (RoleModelEntity roleEntity : roleEntities)
                        {
                            roleName.append(roleEntity.getRoleName())
                                    .append(",");
                        }

                        user.setRoleName(roleName.toString());
                    }
                }
            }
        }
        result.put("total", page.getTotal());
        result.put("rows", page.getList());

        return result;
    }

    /**
     * @param model
     *            参数
     * @return 结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(final ModelMap model)
    {
        return "/adminnew/user/add";
    }

    /**
     * @param userName
     *            参数
     * @return 结果
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public @ResponseBody Message check(final String userName)
    {
        UserEntity user = userService.selectUserPermission(userName);
        if (user != null)
        {
            return Message.error("用户名已存在");
        } else
        {
            return Message.success("");
        }

    }

    /**
     *
     * @param userEntity 参数
     * @param redirectAttributes 参数
     * @param roleIds 参数
     * @return 结果
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(final UserEntity userEntity, final String roleIds,
                       final RedirectAttributes redirectAttributes)
    {
        userEntity.setUserPassword(
                EncryptUtils.encryptMD5(userEntity.getUserPassword()));
        userService.save(userEntity);
        Assert.hasLength(roleIds);
        String[] arr = roleIds.split(",");
        List<UserRoleEntity> userRoleList = new ArrayList<UserRoleEntity>();
        for (String id : arr)
        {
            long l = Long.parseLong(id);
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(userEntity.getId());
            userRoleEntity.setRoleId(l);
            userRoleList.add(userRoleEntity);
        }
        this.userRoleService.seveAll(userRoleList);
        this.addFlashMessage(redirectAttributes, Message.success("添加成功"));
        return "redirect:list.do";
    }

    /**
     * @param id
     *            参数
     * @param model
     *            参数
     * @return 结果
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(final int id, final ModelMap model)
    {
        Assert.notNull(id);

        UserEntity userEntity = userService.selectUserById(id);
        List<RoleModelEntity> roleEntities = roleService
                .selectRoleByUserId(userEntity.getId());
        model.addAttribute("user", userEntity);

        StringBuffer roleIds = new StringBuffer();
        if (roleEntities != null && !roleEntities.isEmpty())
        {
            Integer count = 0;
            for (RoleModelEntity roleEntity : roleEntities)
            {
                roleIds.append(roleEntity.getRoleId());

                if (count < roleEntities.size())
                {
                    roleIds.append(",");
                }

                count++;
            }
        }

        model.addAttribute("roleIds", roleIds.toString());

        return "/adminnew/user/edit";
    }

    /**
     * @param userEntity
     *            参数
     * @param redirectAttributes
     *            参数
     * @param roleIds
     *            角色IDstring
     * @return 结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(final UserEntity userEntity, final String roleIds,
            final RedirectAttributes redirectAttributes)
    {

        userEntity.setUserPassword(
                EncryptUtils.encryptMD5(userEntity.getUserPassword()));
        userService.updateUser(userEntity);
        if (StringUtils.isEmpty(roleIds))
        {
            this.userRoleService.deleteUserRoleByUserId(userEntity.getId());
            this.addFlashMessage(redirectAttributes, Message.success("添加成功"));
            return "redirect:list.do";
        }
        Assert.hasLength(roleIds);
        String[] arr = roleIds.split(",");
        List<UserRoleEntity> userRoleList = new ArrayList<UserRoleEntity>();
        for (String id : arr)
        {
            long l = Long.parseLong(id);
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(userEntity.getId());
            userRoleEntity.setRoleId(l);
            userRoleList.add(userRoleEntity);
        }
        this.userRoleService.deleteUserRoleByUserId(userEntity.getId());
        this.userRoleService.seveAll(userRoleList);
        this.addFlashMessage(redirectAttributes, Message.success("修改成功"));
        return "redirect:list.do";
    }

    /**
     * @param ids
     *            参数
     * @return 结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody Message delete(final String ids)
    {
        Assert.hasLength(ids);
        String[] arr = ids.split(",");
        List<Long> idList = new ArrayList<>();

        for (String id : arr)
        {
            idList.add(Long.parseLong(id));
        }
        userService.delete(idList);

        return Message.success("删除成功");
    }

    /**
     * @param realName
     *            参数
     * @param userName
     *            参数
     * @param pageQuery
     *            参数
     * @return 结果
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> search(final String realName,
            final String userName, final PageQuery pageQuery)
    {
        Map<String, Object> result = new HashMap<>();
        UserEntity userEntity = new UserEntity();
        userEntity.setRealName(realName);
        userEntity.setUserName(userName);
        PageInfo<UserEntity> page = userService.findPageSearch(userEntity,
                pageQuery);
        if (null != page.getList() && page.getList().size() > 0)
        {
            for (UserEntity user : page.getList())
            {
                if (null != user && null != user.getId())
                {
                    List<RoleModelEntity> roleEntities = roleService
                            .selectRoleByUserId(user.getId());

                    if (null != roleEntities && roleEntities.size() > 0)
                    {
                        StringBuffer roleName = new StringBuffer();

                        for (RoleModelEntity roleEntity : roleEntities)
                        {
                            roleName.append(roleEntity.getRoleName())
                                    .append(",");
                        }
                        user.setRoleName(roleName.toString());
                    }
                }
            }
        }
        result.put("total", page.getTotal());
        result.put("rows", page.getList());
        return result;
    }

    /**
     * @param userId
     *            参数
     * @param model
     *            参数
     * @return 结果
     */
    @RequestMapping(value = "/reviseUserPermission", method = RequestMethod.GET)
    public String reviseUserPermission(final Long userId, final ModelMap model)
    {
        model.addAttribute("userId", userId);
        return "/adminnew/user/reviseUserPermission";
    }

    /**
     * @param userId
     *            参数
     * @param pageQuery
     *            参数
     * @return 结果
     */
    @RequestMapping(value = "/getRolePage", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getRolePage(final Long userId,
            final PageQuery pageQuery)
    {
        Map<String, Object> result = new HashMap<>();
        UserRoleEntity userRoleEntity = new UserRoleEntity();
        userRoleEntity.setUserId(userId);
        List<UserRoleEntity> userRoleList = userRoleService
                .findList(userRoleEntity);
        RoleEntity role = new RoleEntity();
        PageInfo<RoleEntity> page = roleService.findPage(role, pageQuery);
        List<RoleEntity> roleNew = new ArrayList<RoleEntity>();
        for (RoleEntity roleN : page.getList())
        {
            int selFlg = 0;
            for (UserRoleEntity userRole : userRoleList)
            {
                if (roleN.getRoleId().equals(userRole.getRoleId()))
                {
                    selFlg = 1;
                    break;
                }
            }
            roleN.setSelFlg(selFlg);
            roleNew.add(roleN);
        }
        page.setList(roleNew);
        result.put("total", page.getTotal());
        result.put("rows", page.getList());
        return result;
    }

    /**
     * @param userId
     *            参数
     * @param ids
     *            参数
     * @param redirectAttributes
     *            参数
     * @return 结果
     */
    @RequestMapping(value = "/reviseUserRole", method = RequestMethod.GET)
    public String reviseUserRole(final Long userId, final String ids,
            final RedirectAttributes redirectAttributes)
    {

        if (StringUtils.isEmpty(ids))
        {
            this.userRoleService.deleteUserRoleByUserId(userId);
            this.addFlashMessage(redirectAttributes, Message.success("添加成功"));
            return "redirect:/admin/user/list.do";
        }
        Assert.hasLength(ids);
        String[] arr = ids.split(",");
        List<UserRoleEntity> userRoleList = new ArrayList<UserRoleEntity>();
        for (String id : arr)
        {
            long l = Long.parseLong(id);
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(userId);
            userRoleEntity.setRoleId(l);
            userRoleList.add(userRoleEntity);
        }
        this.userRoleService.deleteUserRoleByUserId(userId);
        this.userRoleService.seveAll(userRoleList);
        this.addFlashMessage(redirectAttributes, Message.success("添加成功"));
        return "redirect:/admin/user/list.do";
    }

}

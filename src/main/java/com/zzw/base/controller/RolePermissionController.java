package com.zzw.base.controller;

import com.github.pagehelper.PageInfo;
import com.zzw.base.entity.Category;
import com.zzw.base.entity.RolePermissionEntity;
import com.zzw.base.entity.UserEntity;
import com.zzw.base.entity.UserRoleEntity;
import com.zzw.base.model.Message;
import com.zzw.base.model.NestedCategory;
import com.zzw.base.model.PageQuery;
import com.zzw.base.model.TreeModel;
import com.zzw.base.service.CategoryService;
import com.zzw.base.service.RolePermissionService;
import com.zzw.base.service.UserRoleService;
import com.zzw.base.service.UserService;
import com.zzw.base.utils.SysParamUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

/**
 * 角色 controller 类
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/admin/rolePermission")
public class RolePermissionController extends BaseController
{

    /**
     * rolePermissionService
     */
    @Autowired
    private RolePermissionService rolePermissionService;
    /**
     * userRoleService
     */
    @Autowired
    private UserRoleService userRoleService;
    /**
     * userService
     */
    @Autowired
    private UserService userService;
    /**
     * categoryService
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * tree
     * @param roleId 参数
     * @param model 参数
     * @return 结果
     */
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public String tree(final Long roleId, final ModelMap model)
    {
        long classifyId = Long
                .parseLong(SysParamUtils.getString("permissionClassifyId"));
        model.addAttribute("classifyId", classifyId);
        model.addAttribute("roleId", roleId);
        return "/adminnew/rolePermission/tree";
    }

    /**
     *
     * @param classifyId 参数
     * @param roleId 参数
     * @return 结果
     */
    @RequestMapping(value = "/getTreeDate", method = RequestMethod.GET)
    public @ResponseBody List<TreeModel> getTreeDate(final Long classifyId,
                                                     final Long roleId)
    {
        List<RolePermissionEntity> rolePermissionList = rolePermissionService
                .selectRolePersionByRoleId(roleId);
        List<NestedCategory> nestedCategoryList = categoryService
                .getAllNestedCategorys(classifyId);
        return rolePermissionService.createCategoryTree(nestedCategoryList,
                rolePermissionList);
    }

    /**
     *
     * @param roleId 参数
     * @param ids 参数
     * @param redirectAttributes 参数
     * @return 结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.GET)
    public String update(final Long roleId, final String ids,
                         final RedirectAttributes redirectAttributes) {
        Assert.hasLength(ids);
        String[] arr = ids.split(",");
        Set<RolePermissionEntity> rolePermissionList = new HashSet<RolePermissionEntity>();
        for (String id : arr) {
            long l = Long.parseLong(id);
            Category category = categoryService.findCategory(l);
            if (category != null && !org.springframework.util.StringUtils.isEmpty(category.getPermissionCode())) {
                RolePermissionEntity rolePermission = new RolePermissionEntity();
                rolePermission.setPerCode(category.getPermissionCode());
                rolePermission.setRoleId(roleId);
                rolePermissionList.add(rolePermission);
                if (!rolePermissionList.contains(rolePermission)) {
                    rolePermissionList.add(rolePermission);
                }
                Category parentCategory = categoryService.findCategory(category.getPid());
                if (parentCategory != null && !org.springframework.util.StringUtils.isEmpty(parentCategory.getPermissionCode())) {
                    RolePermissionEntity rolePermission2 = new RolePermissionEntity();
                    rolePermission2.setPerCode(parentCategory.getPermissionCode());
                    rolePermission2.setRoleId(roleId);
                    if (!rolePermissionList.contains(rolePermission2)) {
                        rolePermissionList.add(rolePermission2);
                    }
                }
            }
        }
        this.rolePermissionService.deleteRolePermission(roleId);
        this.rolePermissionService.seveAll(new ArrayList(rolePermissionList));
        this.addFlashMessage(redirectAttributes, Message.success("保存成功"));
        return "redirect:/admin/role/list.do";
    }

    /**
     *
     * @param roleId 参数
     * @param model 参数
     * @return 结果
     */
    @RequestMapping(value = "/removeUserPermission", method = RequestMethod.GET)
    public String removeUserPermission(final Long roleId, final ModelMap model)
    {
        model.addAttribute("roleId", roleId);
        return "/adminnew/rolePermission/removeUserPermission";
    }

    /**
     *
     * @param roleId 参数
     * @param model 参数
     * @return 结果
     */
    @RequestMapping(value = "/addUserPermission", method = RequestMethod.GET)
    public String addUserPermission(final Long roleId, final ModelMap model)
    {
        model.addAttribute("roleId", roleId);
        return "/adminnew/rolePermission/addUserPermission";
    }

    /**
     *
     * @param roleId 参数
     * @param pageQuery 参数
     * @return 结果
     */
    @RequestMapping(value = "/getHasAttrPage", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getHasAttrPage(final Long roleId,
                                                            final PageQuery pageQuery)
    {
        Map<String, Object> result = new HashMap<>();
        UserEntity userEntity = new UserEntity();
        userEntity.setRoleId(roleId);
        PageInfo<UserEntity> page = userService.findHasAttrPage(userEntity,
                pageQuery);
        result.put("total", page.getTotal());
        result.put("rows", page.getList());

        return result;
    }

    /**
     *
     * @param roleId 参数
     * @param pageQuery 参数
     * @return 结果
     */
    @RequestMapping(value = "/getNoAttrPage", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getNoAttrPage(final Long roleId,
                                                           final PageQuery pageQuery)
    {
        Map<String, Object> result = new HashMap<>();
        UserEntity userEntity = new UserEntity();
        userEntity.setRoleId(roleId);
        PageInfo<UserEntity> page = userService.findNoAttrPage(userEntity,
                pageQuery);
        result.put("total", page.getTotal());
        result.put("rows", page.getList());
        return result;
    }

    /**
     *
     * @param roleId 参数
     * @param ids 参数
     * @param redirectAttributes 参数
     * @return 结果
     */
    @RequestMapping(value = "/addUserRole", method = RequestMethod.GET)
    public String addUserRole(final Long roleId, final String ids,
                              final RedirectAttributes redirectAttributes)
    {
        Assert.hasLength(ids);
        String[] arr = ids.split(",");
        List<UserRoleEntity> userRoleList = new ArrayList<UserRoleEntity>();
        for (String id : arr)
        {
            long l = Long.parseLong(id);
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setUserId(l);
            userRoleEntity.setRoleId(roleId);
            userRoleList.add(userRoleEntity);
        }
        this.userRoleService.seveAll(userRoleList);
        this.addFlashMessage(redirectAttributes, Message.success("添加成功"));
        return "redirect:/admin/role/list.do";
    }

    /**
     *
     * @param roleId 参数
     * @param ids 参数
     * @param redirectAttributes 参数
     * @return 结果
     */
    @RequestMapping(value = "/deleteUserRole", method = RequestMethod.GET)
    public String deleteUserRole(final Long roleId, final String ids,
                                 final RedirectAttributes redirectAttributes)
    {
        Assert.hasLength(ids);
        String[] arr = ids.split(",");
        List<UserRoleEntity> userRoleList = new ArrayList<UserRoleEntity>();
        for (String id : arr)
        {
            UserRoleEntity userRoleEntity = new UserRoleEntity();
            userRoleEntity.setRoleId(roleId);
            long l = Long.parseLong(id);
            userRoleEntity.setUserId(l);
            userRoleList.add(userRoleEntity);
        }
        this.userRoleService.deleteUserRoleByUserId(userRoleList);
        this.addFlashMessage(redirectAttributes, Message.success("删除成功"));
        return "redirect:/admin/role/list.do";
    }
}

package com.zzw.base.controller;

import com.github.pagehelper.PageInfo;
import com.zzw.base.entity.RoleEntity;
import com.zzw.base.model.ComboTreeDate;
import com.zzw.base.model.Message;
import com.zzw.base.model.PageQuery;
import com.zzw.base.service.RoleService;
import com.zzw.base.service.UserRoleService;
import net.sf.json.JSONArray;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 角色 controller 类
 * @author Administrator
 *
 */
@Controller
@RequestMapping(value = "/admin/role")
public class RoleController extends BaseController
{
    /**
     * 日志
     */
    private static final Log LOG = LogFactory.getLog(RoleController.class);
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
     * 列表业
     * @return 结果
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list()
    {
        return "/adminnew/role/list";
    }

    /**
     *
     * @param pageQuery 参数
     * @return 结果
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getPage(final PageQuery pageQuery)
    {
        Map<String, Object> result = new HashMap<String, Object>();
        RoleEntity role = new RoleEntity();
        PageInfo<RoleEntity> page = roleService.findPage(role, pageQuery);
        result.put("total", page.getTotal());
        result.put("rows", page.getList());

        return result;
    }

    /**
     *
     * @param model 参数
     * @return 结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(final ModelMap model)
    {
        return "/adminnew/role/add";
    }

    /**
     *
     * @param role 参数
     * @param redirectAttributes 参数
     * @return 结果
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(final RoleEntity role, final RedirectAttributes redirectAttributes)
    {
        roleService.addRole(role);
        this.addFlashMessage(redirectAttributes, Message.success("添加成功"));
        return "redirect:list.do";
    }

    /**
     *
     * @param roleId 参数
     * @param model 参数
     * @return 结果
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(final Long roleId, final ModelMap model)
    {
        Assert.notNull(roleId);
        model.addAttribute("role", roleService.selectRoleById(roleId));
        return "/adminnew/role/edit";
    }

    /**
     *
     * @param role 参数
     * @param redirectAttributes 参数
     * @return 结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(final RoleEntity role, final RedirectAttributes redirectAttributes)
    {
        roleService.updateRole(role);
        this.addFlashMessage(redirectAttributes, Message.success("修改成功"));
        return "redirect:list.do";
    }

    /**
     * 删除角色
     * @param ids 参数
     * @return 结果
     */
    @RequestMapping(value = "delete")
    public @ResponseBody Message delete(final String ids)
    {
        Assert.hasLength(ids);
        String[] arr = ids.split(",");
        List<Long> idList = new ArrayList<Long>();
        for (String id : arr)
        {
            idList.add(Long.parseLong(id));
        }
        roleService.delete(idList);
        userRoleService.deleteUserRoleByRoleId(idList);
        userRoleService.deleteRolePermissionByRoleId(idList);
        return Message.success("删除成功");
    }

    /**
     *
     * @param roleName 参数
     * @param pageQuery 参数
     * @return 结果
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> search(final String roleName,
                                                    final PageQuery pageQuery)
    {
        String queryRoleName = roleName;
        try
        {
            queryRoleName = new String(roleName.getBytes("ISO-8859-1"), "UTF-8");
        } catch (UnsupportedEncodingException e)
        {
            LOG.error("",e);
        }
        Map<String, Object> result = new HashMap<>();
        RoleEntity role = new RoleEntity();
        role.setRoleName(queryRoleName);
        PageInfo<RoleEntity> page = roleService.findPage(role, pageQuery);
        result.put("total", page.getTotal());
        result.put("rows", page.getList());
        return result;
    }

    /**
     * 返回角色树对象json
     * @return jsonstr
     */
    @RequestMapping(value = "/findRoleTree", method = RequestMethod.POST)
    public @ResponseBody String findRoleTree()
    {
        List<RoleEntity> roles = roleService.selAllRoleInfo();

        List<ComboTreeDate> combs = new ArrayList<>();

        if (roles != null && !roles.isEmpty())
        {
            for (RoleEntity role : roles)
            {
                ComboTreeDate combo = new ComboTreeDate();
                combo.setId(role.getRoleId());
                combo.setText(role.getRoleName());
                combs.add(combo);
            }
        }

        return JSONArray.fromObject(combs).toString();
    }
}

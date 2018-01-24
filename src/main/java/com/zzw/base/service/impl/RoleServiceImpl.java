package com.zzw.base.service.impl;

import com.github.pagehelper.PageInfo;
import com.zzw.base.dao.RoleDao;
import com.zzw.base.dao.RoleModelEntityDao;
import com.zzw.base.entity.RoleEntity;
import com.zzw.base.model.PageQuery;
import com.zzw.base.model.RoleModelEntity;
import com.zzw.base.service.RoleService;
import com.zzw.base.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * RoleServiceImpl
 */
@Service
public class RoleServiceImpl implements RoleService
{

    /**
     * roleDao
     */
    @Autowired
    private RoleDao<RoleEntity> roleDao;
    /**
     * userService
     */
    @Autowired
    private UserService userService;
    /**
     * roleEntityDao
     */
    @Autowired
    private RoleModelEntityDao<RoleModelEntity> roleEntityDao;

    /**
     * 查询所有角色
     * @return 结果
     */
    @Override
    public List<RoleEntity> selAllRoleInfo()
    {
        List<RoleEntity> roles = roleDao.selectRole();
        return roles;
    }

    /**
     *
     * @param id 条件
     * @return 结果
     */
    @Override
    public RoleEntity selectRoleById(final Long id)
    {
        return roleDao.get("selectRoleById", id);
    }

    /**
     *
     * @param role 条件
     * @return 结果
     */
    @Override
    public int addRole(final RoleEntity role)
    {
        int result = roleDao.addRole(role);
        return result;
    }

    /**
     *
     * @param role 条件
     * @return 结果
     */
    @Override
    public int updateRole(final RoleEntity role)
    {
        int result = roleDao.updateRole(role);
        return result;
    }

    /**
     *
     * @param id 条件
     * @return 结果
     */
    @Override
    public int deleteRole(final Long id)
    {
        int result = roleDao.deleteRole(id);
        return result;
    }

    /**
     *
     * @param ids 条件
     */
    @Override
    public void delete(final List<Long> ids)
    {
        Map<String, Object> model = new HashMap<>();
        model.put("list", ids);
        roleDao.delete("role_deleteRolePermission", model);
    }

    /**
     *
     * @param role 条件
     * @param pageQuery 条件
     * @return 结果
     */
    @Override
    public PageInfo<RoleEntity> findPage(final RoleEntity role, final PageQuery pageQuery)
    {
        return roleDao.findPage("selRole", role, pageQuery);
    }

    /**
     *
     * @param id 条件
     * @return 结果
     */
    @Override
    public List<RoleModelEntity> selectRoleByUserId(final Long id)
    {
        return roleEntityDao.findByUserId(id);
    }
}

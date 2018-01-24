package com.zzw.base.dao.impl;

import com.zzw.base.dao.RolePermissionDao;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * 角色权限dao接口实现类
 * @author Administrator
 *
 * @param <RolePermissionEntity>
 */
@Repository("rolepermissiondaoimpl")
public class RolePermissionDaoImpl<RolePermissionEntity> extends
        BaseDaoImpl<RolePermissionEntity> implements RolePermissionDao<RolePermissionEntity>
{

    /**
     *
     * @param roleId 参数
     * @return
     */
    @Override
    public List<RolePermissionEntity> selectRolePersionByRoleId(final Long roleId)
    {
        List<RolePermissionEntity> rolePermissions = super.find(
                "getRolePermissionByRoleId", roleId);
        return rolePermissions;
    }

    /**
     *
     * @param rolePermission 参数
     * @return
     */
    @Override
    public int addRolePermission(final RolePermissionEntity rolePermission)
    {
        int result = save("addRolePermission", rolePermission);
        return result;
    }

    /**
     *
     * @param roleId 参数
     * @return
     */
    @Override
    public int deleteRolePermission(final Long roleId)
    {
        int result = delete("deleteRolePermission", roleId);
        return result;
    }

    /**
     *
     * @param model 参数
     */
    @Override
    public void saveAll(final Map<String, Object> model)
    {
        save("rolePermission_saveAll", model);
    }
}

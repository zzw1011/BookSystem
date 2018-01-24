package com.zzw.base.dao;

import java.util.List;
import java.util.Map;

/**
 * 角色权限关联dao接口类
 * @author Administrator
 * @param <RolePermissionEntity>
 */
public interface RolePermissionDao<RolePermissionEntity>
        extends BaseDao<RolePermissionEntity>
{
    /**
     * 根据roleId获取peridList
     * @param roleId 参数
     * @return 结果
     */
    List<RolePermissionEntity> selectRolePersionByRoleId(Long roleId);

    /**
     * 新增角色关系
     * @param rolePermission 参数
     * @return 结果
     */
    int addRolePermission(RolePermissionEntity rolePermission);

    /**
     * 刪除角色关系
     * @param roleId 参数
     * @return 结果
     */
    int deleteRolePermission(Long roleId);

    /**
     * 全部保存
     * @param model 参数
     */
    void saveAll(Map<String, Object> model);
}

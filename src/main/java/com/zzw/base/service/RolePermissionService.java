package com.zzw.base.service;

import com.zzw.base.entity.RolePermissionEntity;
import com.zzw.base.model.NestedCategory;
import com.zzw.base.model.TreeModel;

import java.util.List;

/**
 * RolePermissionService
 * @author Administrator
 *
 */
public interface RolePermissionService
{
    /**
     * 根据roleId获取peridList
     * @param roleId 条件
     * @return 结果
     */
    List<RolePermissionEntity> selectRolePersionByRoleId(Long roleId);

    /**
     * 根据属性对象列表生成树形JSON数据对象供前台展现(使复选框默认选中)
     * @param nestedCategoryList 参数
     * @param rolePermissionList 参数
     * @return 结果
     */
    List<TreeModel> createCategoryTree(List<NestedCategory> nestedCategoryList,
                                       List<RolePermissionEntity> rolePermissionList);

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
     * @param rolePermissionList 参数
     */
    void seveAll(List<RolePermissionEntity> rolePermissionList);
}

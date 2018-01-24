package com.zzw.base.service;

import com.zzw.base.entity.UserRoleEntity;

import java.util.List;

/**
 * UserRoleService
 * @author Administrator
 *
 */
public interface UserRoleService
{
    /**
     * 新增用户角色关系
     * @param userRoleEntity 条件
     * @return 结果
     */
    int addUserRole(UserRoleEntity userRoleEntity);

    /**
     * 修改用户角色关系
     * @param userRoleEntity 条件
     * @return 结果
     */
    int updateUserRole(UserRoleEntity userRoleEntity);

    /**
     * 删除用户角色关系
     * @param userId 条件
     * @return 结果
     */
    int deleteUserRoleByUserId(Long userId);

    /**
     * 删除用户角色关系
     * @param ids 参数
     */
    void deleteUserRoleByUserId(List<UserRoleEntity> ids);

    /**
     * 通过roleId删除用户角色关系
     * @param ids 条件
     */
    void deleteUserRoleByRoleId(List<Long> ids);

    /**
     * 通过roleId删除用户角色关系
     * @param roleId 条件
     * @return 结果
     */
    int deleteUserRoleByRoleId(Long roleId);

    /**
     * 通过roleId删除角色相对应的权限
     * @param ids 条件
     */
    void deleteRolePermissionByRoleId(List<Long> ids);

    /**
     * 全部保存
     * @param userRoleEntityList 参数
     */
    void seveAll(List<UserRoleEntity> userRoleEntityList);

    /**
     * 查询用户拥有的权限
     * @param userRoleEntity 参数
     * @return 结果
     */
    List<UserRoleEntity> findList(UserRoleEntity userRoleEntity);
}

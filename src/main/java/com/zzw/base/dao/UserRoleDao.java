package com.zzw.base.dao;

import java.util.Map;

/**
 * 用户角色关联dao接口类
 * @author Administrator
 * @param <UserRoleEntity>
 */
public interface UserRoleDao<UserRoleEntity> extends BaseDao<UserRoleEntity>
{
    /**
     * 新增用户
     * @param userRoleEntity
     *            参数
     * @return 结果
     */
    int addUserRole(UserRoleEntity userRoleEntity);

    /**
     * 修改用户角色关系
     * @param userRoleEntity
     *            参数
     * @return 结果
     */
    int updateUserRole(UserRoleEntity userRoleEntity);

    /**
     * 删除用户角色关系
     * @param id
     *            参数
     * @return 结果
     */
    int deleteUserRole(int id);

    /**
     * 全部保存
     * @param model 参数
     */
    void saveAll(Map<String, Object> model);
}

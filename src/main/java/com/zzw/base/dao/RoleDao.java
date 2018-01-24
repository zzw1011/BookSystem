package com.zzw.base.dao;

import java.util.List;

/**
 * 角色dao接口类
 * @author zzw
 * @param <Role>
 */
public interface RoleDao<Role> extends BaseDao<Role>
{
    /**
     * 查询所有角色
     * @return 结果
     */
    List<Role> selectRole();

    /**
     * 新增角色
     * @param role 参数
     * @return 结果
     */
    int addRole(Role role);

    /**
     * 修改角色
     * @param role 参数
     * @return 结果
     */
    int updateRole(Role role);

    /**
     * 刪除角色
     * @param id 参数
     * @return 结果
     */
    int deleteRole(Long id);
}

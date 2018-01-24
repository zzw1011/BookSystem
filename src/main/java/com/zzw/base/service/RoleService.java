package com.zzw.base.service;

import com.github.pagehelper.PageInfo;
import com.zzw.base.entity.RoleEntity;
import com.zzw.base.model.PageQuery;
import com.zzw.base.model.RoleModelEntity;

import java.util.List;

/**
 * RoleService
 * @author Administrator
 *
 */
public interface RoleService
{
    /**
 * 查询所有角色
 * @return 结果
 */
List<RoleEntity> selAllRoleInfo();

    /**
     * 查询所有角色
     * @param id 条件
     * @return 结果
     */
    RoleEntity selectRoleById(Long id);

    /**
     * 新增角色
     * @param role 条件
     * @return 结果
     */
    int addRole(RoleEntity role);

    /**
     * 修改角色
     * @param role 条件
     * @return 结果
     */
    int updateRole(RoleEntity role);

    /**
     * 刪除角色
     * @param id 条件
     * @return 结果
     */
    int deleteRole(Long id);

    /**
     * 刪除角色
     * @param ids 条件
     */
    void delete(List<Long> ids);

    /**
     * 翻页查询
     * @param role 条件
     * @param pageQuery 条件
     * @return 分页结果
     */
    PageInfo<RoleEntity> findPage(RoleEntity role, PageQuery pageQuery);

    /**
     * 根据Id查询
     * @param id 条件
     * @return 结果
     */
    List<RoleModelEntity> selectRoleByUserId(Long id);
}

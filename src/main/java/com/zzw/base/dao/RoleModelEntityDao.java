package com.zzw.base.dao;

import java.util.List;

/**
 * 角色dao接口类
 * @author Administrator
 * @param <RoleModelEntity>
 */
public interface RoleModelEntityDao<RoleModelEntity> extends BaseDao<RoleModelEntity>
{
    /**
     * 根据用户ID查询
     * @param id 参数
     * @return 结果
     */
    List<RoleModelEntity> findByUserId(Long id);
}

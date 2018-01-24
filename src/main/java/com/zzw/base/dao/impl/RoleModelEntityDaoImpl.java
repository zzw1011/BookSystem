package com.zzw.base.dao.impl;

import com.zzw.base.dao.RoleModelEntityDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色dao接口实现类
 * @param <RoleModelEntity>
 */
@Repository("roleEntityDaoImpl")
public class RoleModelEntityDaoImpl<RoleModelEntity> extends BaseDaoImpl<RoleModelEntity>
        implements RoleModelEntityDao<RoleModelEntity>
{

    /**
     *
     * @param id 参数
     * @return
     */
    @Override
    public List<RoleModelEntity> findByUserId(final Long id)
    {
        return find("selectRoleByUserId", id);
    }
}

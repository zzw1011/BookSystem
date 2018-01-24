package com.zzw.base.dao.impl;

import com.zzw.base.dao.RoleDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * 角色dao接口实现类
 * @author Administrator
 * @param <Role>
 */
@Repository
public class RoleDaoImpl<Role> extends BaseDaoImpl<Role> implements RoleDao<Role>
{
    /**
     *
     * @return
     */
    @Override
    public List<Role> selectRole()
    {
        return super.find("selRole", null);
    }

    /**
     *
     * @param role 参数
     * @return
     */
    @Override
    public int addRole(final Role role)
    {
        return save("addRole", role);
    }

    /**
     *
     * @param role 参数
     * @return
     */
    @Override
    public int updateRole(final Role role)
    {
        return update("updateRole", role);
    }

    /**
     * @param id 参数
     * @return
     */
    @Override
    public int deleteRole(final Long id)
    {
        return delete("deleteRole", id);
    }
}

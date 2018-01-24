package com.zzw.base.dao.impl;

import com.zzw.base.dao.UserRoleDao;
import org.springframework.stereotype.Repository;

import java.util.Map;

/**
 * 用户角色dao接口实现类
 * @author Administrator
 *
 * @param <UserRoleEntity>
 */
@SuppressWarnings("hiding")
@Repository("userroledao")
public class UserRoleDaoImpl<UserRoleEntity> extends BaseDaoImpl<UserRoleEntity>
        implements UserRoleDao<UserRoleEntity>
{
    /**
     *
     * @param userRoleEntity
     *            参数
     * @return
     */
    @Override
    public int addUserRole(final UserRoleEntity userRoleEntity)
    {
        int result = save("addUserRole", userRoleEntity);
        return result;
    }

    /**
     *
     * @param userRoleEntity
     *            参数
     * @return
     */
    @Override
    public int updateUserRole(final UserRoleEntity userRoleEntity)
    {
        return update("updateUserRole", userRoleEntity);
    }

    /**
     *
     * @param id
     *            参数
     * @return
     */
    @Override
    public int deleteUserRole(final int id)
    {
        return delete("deleteUserRole", id);
    }

    /**
     *
     * @param model 参数
     */
    @Override
    public void saveAll(final Map<String, Object> model)
    {
        save("userRole_saveAll", model);
    }
}

package com.zzw.base.service.impl;

import com.zzw.base.dao.UserRoleDao;
import com.zzw.base.entity.UserRoleEntity;
import com.zzw.base.service.UserRoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserRoleServiceImpl
 */
@Service
public class UserRoleServiceImpl implements UserRoleService
{
    /**
     * userRoleDao
     */
    @Autowired
    private UserRoleDao<UserRoleEntity> userRoleDao;

    /**
     *
     * @param userRoleEntity 条件
     * @return 结果
     */
    @Override
    public int addUserRole(final UserRoleEntity userRoleEntity)
    {
        return userRoleDao.addUserRole(userRoleEntity);
    }

    /**
     *
     * @param userRoleEntity 条件
     * @return 结果
     */
    @Override
    public int updateUserRole(final UserRoleEntity userRoleEntity)
    {
        return userRoleDao.updateUserRole(userRoleEntity);
    }

    /**
     *
     * @param userId 条件
     * @return 结果
     */
    @Override
    public int deleteUserRoleByUserId(final Long userId)
    {
        return userRoleDao.delete("deleteUserRoleByUserId", userId);
    }

    /**
     *
     * @param roleId 条件
     * @return 结果
     */
    @Override
    public int deleteUserRoleByRoleId(final Long roleId)
    {
        return userRoleDao.delete("deleteUserRoleByRoleId", roleId);
    }

    /**
     *
     * @param ids 条件
     */
    @Override
    public void deleteUserRoleByRoleId(final List<Long> ids)
    {
        Map<String, Object> model = new HashMap<>();
        model.put("list", ids);
        userRoleDao.delete("user_deleteUserRoleByRoleId", model);
    }

    /**
     *
     * @param ids 参数
     */
    @Override
    public void deleteUserRoleByUserId(final List<UserRoleEntity> ids)
    {
        Map<String, Object> model = new HashMap<>();
        model.put("list", ids);
        userRoleDao.delete("userRole_deleteUserRoleByUserId", model);
    }

    /**
     *
     * @param ids 条件
     */
    @Override
    public void deleteRolePermissionByRoleId(final List<Long> ids)
    {
        Map<String, Object> model = new HashMap<>();
        model.put("list", ids);
        userRoleDao.delete("permission_deleteRolePermissionByRoleId", model);
    }

    /**
     *
     * @param userRoleEntityList 参数
     */
    @Override
    public void seveAll(final List<UserRoleEntity> userRoleEntityList)
    {
        Map<String, Object> model = new HashMap<>();
        model.put("list", userRoleEntityList);
        this.userRoleDao.saveAll(model);
    }

    /**
     *
     * @param userRoleEntity 参数
     * @return 结果
     */
    @Override
    public List<UserRoleEntity> findList(final UserRoleEntity userRoleEntity)
    {

        return this.userRoleDao.find("userRole_selectUserRoleList", userRoleEntity);
    }
}

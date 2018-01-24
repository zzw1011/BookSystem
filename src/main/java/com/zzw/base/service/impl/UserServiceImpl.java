package com.zzw.base.service.impl;

import com.github.pagehelper.PageInfo;
import com.zzw.base.dao.UserDao;
import com.zzw.base.entity.UserEntity;
import com.zzw.base.model.PageQuery;
import com.zzw.base.model.RoleModelEntity;
import com.zzw.base.service.RoleService;
import com.zzw.base.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserService接口实现类
 * @author Administrator
 */
@Service("userServiceImpl")
public class UserServiceImpl implements UserService
{

    /**
     * userDao
     */
    @Autowired
    private UserDao<UserEntity> userDao;

    /**
     * roleService
     */
    @Autowired
    private RoleService roleService;

    /**
     *
     * @return 结果
     */
    @Override
    public List<UserEntity> selectUser()
    {

        List<UserEntity> find = userDao.selectUser();
        return find;
    }

    /**
     *
     * @param id 参数
     * @return 结果
     */
    @Override
    public UserEntity selectUserById(final int id)
    {
        UserEntity user = userDao.get("selectUserById", id);
        List<RoleModelEntity> roleList = roleService
                .selectRoleByUserId(user.getId());
        user.setRoleList(roleList);
        return user;

    }

    /**
     *
     * @param userName 参数
     * @return 结果
     */
    @Override
    public UserEntity selectUserPermission(final String userName)
    {
        UserEntity user = userDao.get("selectUserByUserName", userName);
        if (user != null) {
            List<RoleModelEntity> roleList = roleService
                    .selectRoleByUserId(user.getId());
            user.setRoleList(roleList);
        }
        return user;

    }

    /**
     *
     * @param userEntity 参数
     * @param pageQuery 参数
     * @return 结果
     */
    @Override
    public PageInfo<UserEntity> findHasAttrPage(final UserEntity userEntity,
                                                final PageQuery pageQuery)
    {
        return userDao.findPage("user_selectHasAttrUserRole", userEntity,
                pageQuery);
    }

    /**
     *
     * @param userEntity 参数
     */
    @Override
    public void save(final UserEntity userEntity)
    {
        userDao.save("user_saveUser", userEntity);
    }

    /**
     *
     * @param userName 参数
     * @param failCount 参数
     * @return 结果
     */
    @Override
    public int upFailCount(final String userName, final int failCount)
    {
        // TODO Auto-generated method stub
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        map.put("failureCount", failCount);
        return userDao.update("upFailCount", map);
    }

    /** 修改登录失败锁定状态
     *
     * @param userName 参数
     * @param isLocked 参数
     * @return 结果
     */
    @Override
    public int upIsLocked(final String userName, final int isLocked)
    {
        Map<String, Object> map = new HashMap<>();
        map.put("userName", userName);
        map.put("isLocked", isLocked);
        return userDao.update("upIsLocked", map);
    }

    /**
     *
     * @param userEntity 参数
     * @return 结果
     */
    @Override
    public int updateUser(final UserEntity userEntity)
    {
        return userDao.updateUser(userEntity);
    }

    /**
     *
     * @param id 参数
     * @return 结果
     */
    @Override
    public int deleteUser(final int id)
    {
        return userDao.deleteUser(id);
    }

    /**
     *
     * @param ids 参数
     */
    @Override
    public void delete(final List<Long> ids)
    {
        Map<String, Object> model = new HashMap<>();
        model.put("list", ids);
        userDao.delete("user_deleteuser", model);
    }

    /** 登录成功修改登录时间
     *
     * @param userName 参数
     * @param loginDate 参数
     * @return 结果
     */
    @Override
    public int upLoginDate(final String userName, final Date loginDate)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("loginDate", loginDate);
        return userDao.update("upLoginDate", map);
    }

    /** 获取当前登录用户
     *
     * @return 结果
     */
    @Override
    @Transactional(readOnly = true)
    public String getCurrentUser()
    {
        Subject subject = SecurityUtils.getSubject();
        if (subject != null)
        {
            String userName = (String) subject.getPrincipal();
            if (StringUtils.isNotBlank(userName))
            {
                return userName;
            }
        }
        return null;
    }

    /** 获得当前用户的所有权限
     *
     * @return 结果
     */
    @Override
    public List<String> getPermission()
    {

        String currentUser = getCurrentUser();
        UserEntity user = selectUserPermission(currentUser);
        if (user != null)
        {
            // 用户的角色集合
            List<RoleModelEntity> roleList = user.getRoleList();

            for (RoleModelEntity role : roleList)
            {
                List<String> pname = role.getPermissionName();
                return pname;
            }
        }
        return null;
    }

    /** 修改登录IP
     *
     * @param userName 参数
     * @param loginIp 参数
     * @return 结果
     */
    @Override
    public int updateLoginIp(final String userName, final String loginIp)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("loginIp", loginIp);
        return userDao.update("upLoginIp", map);
    }

    /**
     *
     * @param name 参数
     * @return 结果
     */
    @Override
    public UserEntity seleUserId(final String name)
    {
        return userDao.get("seleUserId", name);
    }

    /**
     *
     * @param userName 参数
     * @return 结果
     */
    @Override
    public UserEntity selectUserByUserName(final String userName)
    {
        return userDao.get("selectUserByUserName", userName);
    }


    /**
     *
     * @param userName 参数
     * @return 结果
     */
    @Override
    public List<UserEntity> selectRolesByUserName(final String userName)
    {
        return userDao.find("selectRolesByUserName", userName);
    }


    /** 修改锁定时间
     *
     * @param userName 参数
     * @param lockedDate 参数
     * @return 结果
     */
    @Override
    public int updateLockedDate(final String userName, final Date lockedDate)
    {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("userName", userName);
        map.put("lockedDate", lockedDate);
        return userDao.update("updatelockedDate", map);
    }

    /**
     *
     * @param id 参数
     * @return 结果
     */
    @Override
    public List<UserEntity> selectUserBydepartId(final Long id)
    {
        return userDao.selectUserById(id);
    }

    /**
     *
     * @param roleId 参数
     * @return 结果
     */
    @Override
    public List<UserEntity> selectUserRole(final Long roleId)
    {

        return userDao.find("user_selectUserByRoleId", roleId);
    }

    /**
     *
     * @param userEntity 参数
     * @param pageQuery 参数
     * @return 结果
     */
    @Override
    public PageInfo<UserEntity> findNoAttrPage(final UserEntity userEntity,
                                               final PageQuery pageQuery)
    {
        return userDao.findPage("user_selectNoAttrUserRole", userEntity,
                pageQuery);
    }

    /**
     *
     * @param roleId 参数
     * @return 结果
     */
    @Override
    public List<UserEntity> selUserByRoleId(final Long roleId)
    {
        List<UserEntity> users = null;
        if (null != roleId)
        {
            users = userDao.find("selUserByRoleId", roleId);
        }
        return users;
    }

    /**
     *
     * @param queryObj 参数
     * @param pageQuery 参数
     * @return 结果
     */
    @Override
    public PageInfo<UserEntity> findPageSearch(final UserEntity queryObj,
                                               final PageQuery pageQuery)
    {
        return userDao.findPage("user_selectUserBySearch", queryObj, pageQuery);
    }
    @Override
    public UserEntity getCurrentUserEntity() {
        String name = this.getCurrentUser();
        if (StringUtils.isNotBlank(name)) {
            return this.selectUserByUserName(name);
        }

        return null;
    }
}

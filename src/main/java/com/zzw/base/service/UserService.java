package com.zzw.base.service;

import com.github.pagehelper.PageInfo;
import com.zzw.base.entity.UserEntity;
import com.zzw.base.model.PageQuery;

import java.util.Date;
import java.util.List;

/**
 * @说明 UserService接口类
 * @author Administrator
 */
public interface UserService
{
    /**
     * 查看用户
     * @return 结果
     */
    List<UserEntity> selectUser();

    /**
     * 根据姓名查询用户权限
     * @param userName 参数
     * @return 结果
     */
    UserEntity selectUserPermission(String userName);

    /**
     * 根据姓名查询用户
     * @param userName 参数
     * @return 结果
     */
    UserEntity selectUserByUserName(String userName);


    /**
     * 根据用户名查询角色
     * @param userName 参数
     * @return 结果
     */
    List<UserEntity> selectRolesByUserName(String userName);


    /**
     * 根据id查询用户
     * @param id 参数
     * @return 结果
     */
    UserEntity selectUserById(int id);

    /**
     * 保存分类信息
     * @param userEntity 参数
     */
    void save(UserEntity userEntity);

    /**
     * 登录失败修改失败次数
     * @param userName 参数
     * @param failCount 参数
     * @return 结果
     */
    int upFailCount(String userName, int failCount);

    /**
     * 登录失败5次修改锁定状态
     * @param userName 参数
     * @param isLocked 参数
     * @return 结果
     */
    int upIsLocked(String userName, int isLocked);

    /**
     * 修改登录时间
     * @param userName 参数
     * @param loginDate 参数
     * @return 结果
     */
    int upLoginDate(String userName, Date loginDate);

    /**
     * 修改锁定时间
     * @param lockedDate 参数
     * @param userName 参数
     * @return 结果
     */
    int updateLockedDate(String userName, Date lockedDate);

    /**
     * 修改登录IP
     * @param userName 参数
     * @param loginIp 参数
     * @return 结果
     */
    int updateLoginIp(String userName, String loginIp);

    /**
     * 修改用户
     * @param userEntity 参数
     * @return 结果
     */
    int updateUser(UserEntity userEntity);

    /**
     * 删除用户
     * @param id 参数
     * @return 结果
     */
    int deleteUser(int id);

    /**
     * 翻页查询（没有被添加权限的用户）
     * @param userEntity 参数
     * @param pageQuery 参数
     * @return 结果
     */
    PageInfo<UserEntity> findNoAttrPage(UserEntity userEntity,
                                        PageQuery pageQuery);

    /**
     * 翻页查询（被添加权限的用户）
     * @param userEntity 参数
     * @param pageQuery 参数
     * @return 结果
     */
    PageInfo<UserEntity> findHasAttrPage(UserEntity userEntity,
                                         PageQuery pageQuery);

    /**
     * 获取当前登录用户
     * @return 结果
     */
    String getCurrentUser();

    /**
     * 获得当前用户的权限
     * @return 结果
     */
    List<String> getPermission();

    /**
     * 根据用户名找到ID
     * @param name 参数
     * @return 结果
     */
    UserEntity seleUserId(String name);

    /**
     * 通过部门id查看该部门所有员工
     * @param id 参数
     * @return 结果
     */
    List<UserEntity> selectUserBydepartId(Long id);

    /**
     * 批量删除用户
     * @param ids 参数
     */
    void delete(List<Long> ids);

    /**
     * 根据权限名称获取用户
     * @param roleid 参数
     * @return 结果
     */
    List<UserEntity> selUserByRoleId(Long roleid);

    /**
     * 根据roleId查询用户
     * @param roleId 参数
     * @return 结果
     */
    List<UserEntity> selectUserRole(Long roleId);

    /**
     * 根据检索条件查询用户
     * @param queryObj 参数
     * @param pageQuery 参数
     * @return 结果
     */
    PageInfo<UserEntity> findPageSearch(UserEntity queryObj,
                                        PageQuery pageQuery);
    /**
     * 获取当前用户
     * @return 实体数据
     */
    UserEntity getCurrentUserEntity();

}

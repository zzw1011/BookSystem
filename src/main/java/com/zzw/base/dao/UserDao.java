package com.zzw.base.dao;

import java.util.List;

/**
 * @创建者：zzw
 * @创建时间 2018/1/2 0002
 * @描述 用户数据层接口
 */
public interface UserDao<UserEntity> extends BaseDao<UserEntity> {
    /**
     * 查询所有用戶
     * @return 结果
     */
    List<UserEntity> selectUser();

    /**
     * 新增用户
     * @param userEntity 参数
     * @return 结果
     */
    int addUser(UserEntity userEntity);

    /**
     * 修改用户
     * @param userEntity 参数
     * @return 结果
     */
    int updateUser(UserEntity userEntity);

    /**
     * 刪除用户
     * @param id 参数
     * @return 结果
     */
    int deleteUser(int id);

    /**
     * 通过用户id查询用户
     * @param id 参数
     * @return 结果
     */
    List<UserEntity> selectUserById(Long id);
}

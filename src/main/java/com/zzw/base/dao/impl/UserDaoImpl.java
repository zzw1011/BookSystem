package com.zzw.base.dao.impl;

import com.zzw.base.dao.BaseDao;
import com.zzw.base.dao.UserDao;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @创建者：zzw
 * @创建时间 2018/1/2 0002
 * @描述 用户实体数据层实现
 */
@Repository
public class UserDaoImpl<UserEntity> extends BaseDaoImpl<UserEntity> implements UserDao<UserEntity>{

    /**
     * 查询所有用戶
     * @return 结果
     */
    @Override
    public List<UserEntity> selectUser() {
        List<UserEntity> userEntityList = super.find("selectUser", null);
        return userEntityList;
    }

    /**
     * 添加用户
     * @param userEntity 参数
     * @return 影响行数
     */
    @Override
    public int addUser(UserEntity userEntity) {
        int result = save("addUser",userEntity);
        return result;
    }


    /**
     * 更新用户
     * @param userEntity 参数
     * @return 影响行数
     */
    @Override
    public int updateUser(UserEntity userEntity) {
        int result = update("updateUser",userEntity);
        return result;
    }

    /**
     * 删除用户
     * @param id 参数
     * @return 影响行数
     */
    @Override
    public int deleteUser(int id) {
        int result = delete("deleteUser",id);
        return result;
    }

    @Override
    public List<UserEntity> selectUserById(Long id) {
        List<UserEntity> userEntityList = find("selectUserById",id);
        return userEntityList;
    }
}

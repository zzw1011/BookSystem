package com.zzw.base.dao;

import com.github.pagehelper.PageInfo;
import com.zzw.base.model.PageQuery;

import java.util.List;
import java.util.Map;

/**
 * 基础数据库操作类接口
 * Created by zzw on 2017/12/29 0029.
 */
public interface BaseDao<T> {

   /**
    * @描述 保存一个实体
    * @param mapperId 调用**mapper.xml的执行的id
    * @param o 保存的对象
    * @return 影响的行数
    */
    int save(String  mapperId, T o);

    /**
     * @描述 保存一个Map对象
     * @param mapperId 调用**mapper.xml的执行的id
     * @param map 保存的map
     * @return 影响的行数
     */
    int save(String mapperId, Map map);

    /**
     * @描述 更新一个对象
     * @param mapperId 调用**mapper.xml的执行的id
     * @param o 更新的对象
     * @return 影响的行数
     */
    int update(String  mapperId, T o);

    /**
     * @描述 多条件更新
     * @param mapperId 调用**mapper.xml的执行的id
     * @param param 更新条件
     * @return 影响的行数
     */
    int update(String  mapperId, Map param);

    /**
     * @描述 根据ID删除对象
     * @param mapperId 调用**mapper.xml的执行的id
     * @param id 删除的实体id
     * @return 影响的行数
     */
    int delete(String mapperId, Object id);

    /**
     * @描述 多条件删除对象
     * @param mapperId 调用**mapper.xml的执行的id
     * @param param 查询的条件
     * @return 影响的行数
     */
    int delete(String mapperId, Map param);


    /**
     * @描述 查询一个实体
     * @param mapperId 调用**mapper.xml的执行的id
     * @param id 查询条件
     * @return T 查询对象
     */
    T get(String mapperId, Object id);

    /**
     * @描述 多条件查询
     * @param mapperId 调用**mapper.xml的执行的id
     * @param param 查询条件
     * @return List<T> 查询结果
     */
    List<T> find(String mapperId, Map param);

    /**
     * @描述 多条件查询
     * @param mapperId 调用**mapper.xml的执行的id
     * @param model 查询条件
     * @return List<T> 查询结果
     */
    List<T> find(String mapperId, Object model);

    /**
     * @描述 按条件查询行数
     * @param mapperId 调用**mapper.xml的执行的id
     * @param param 查询条件
     * @return 行数
     */
    Long count(String mapperId, Map param);

    /**
     * @描述 按条件查询行数
     * @param mapperId 调用**mapper.xml的执行的id
     * @param model 查询条件
     * @return 行数
     */
    Long count(String mapperId, Object model);

    /**
     * @描述 分页查询
     * @param mapperId 调用**mapper.xml的执行的id
     * @param param 查询条件
     * @param pageQuery 分页参数
     * @return 分页结果
     */
    PageInfo<T> findPage(String mapperId, Map param, PageQuery pageQuery);

    /**
     * @描述 分页查询
     * @param o 查询条件
     * @param pageQuery 分页参数
     * @return 分页结果
     */
    PageInfo<T> findPage(String mapperId, T o, PageQuery pageQuery);

}

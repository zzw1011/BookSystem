package com.zzw.base.service;

import com.github.pagehelper.PageInfo;
import com.zzw.base.entity.SystemParameterEntity;
import com.zzw.base.model.PageQuery;

import java.util.List;

/**
 * SystemParameterService
 * Created by Administrator on 2016/7/19.
 */
public interface SystemParameterService
{
    /**
     * 查询分页分类信息
     * @param pageQuery 条件
     * @return 结果
     */
    PageInfo<SystemParameterEntity> findPage(PageQuery pageQuery);

    /**
     * 查询分页分类信息
     * @param pageQuery 条件
     * @param parameterKey 条件
     * @return 结果
     */
    PageInfo<SystemParameterEntity> findPageSearch(String parameterKey,
                                                   PageQuery pageQuery);

    /**
     * 保存分类信息
     * @param systemParameterEntity 条件
     */
    void save(SystemParameterEntity systemParameterEntity);

    /**
     * 删除IDs
     * @param ids 条件
     */
    void delete(List<Long> ids);

    /**
     * 找到系统参数
     * @param parameterKey 条件
     * @return 结果
     */
    SystemParameterEntity findSystemParameter(String parameterKey);

    /**
     * 找到系统参数
     * @param id 条件
     * @return 结果
     */
    SystemParameterEntity findSystemParameterById(Long id);

    /**
     * 更新分类信息
     * @param systemParameterEntity 条件
     */
    void update(SystemParameterEntity systemParameterEntity);

    /**
     * 查询
     * @param parameterKey 条件
     * @return 结果
     */
    SystemParameterEntity selectFromCache(String parameterKey);

    /**
     *  该方法使用场景为 环信保存token  create_date 作为到期时间使用
     * @param systemParameterEntity
     */
    void systemParameterSavePart(SystemParameterEntity systemParameterEntity);

    /**
     * 该方法使用场景为 环信token 到期后 更新 create_date
     * @param systemParameterEntity
     */
    void systemParameterUpdatePart(SystemParameterEntity systemParameterEntity);

    /**
     * 更新缓存
     */
    void clearCache();
}

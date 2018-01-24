package com.zzw.base.service;

import com.github.pagehelper.PageInfo;
import com.zzw.base.entity.DictionaryEntity;
import com.zzw.base.model.PageQuery;

/**
 * 字典Service
 */
public interface DictionaryService
{
    /**
     * 查询分页信息
     * @param record 查询条件
     * @param pageQuery 分页条件
     * @return 字典分页对象
     */
    PageInfo<DictionaryEntity> queryDictionary(DictionaryEntity record,
                                               PageQuery pageQuery);

    /**
     * 删除字典KEY
     * @param id 主键
     * @return 删除结果
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入字典对象
     * @param record 保存对象
     * @return 返回插入结果
     */
    int insert(DictionaryEntity record);

    /**
     * 插入字典对象
     * @param record 保存对象
     * @return 返回插入结果
     */
    int insertSelective(DictionaryEntity record);

    /**
     * 根据主键查询
     * @param id 主键
     * @return 字典对象
     */
    DictionaryEntity selectByPrimaryKey(Long id);

    /**
     * 根据字典key查询
     * @param dictionaryKey 字典key
     * @return 字典对象
     */
    DictionaryEntity selectByKey(String dictionaryKey);

    /**
     * 更新
     * @param record 更新对象
     * @return 更新结果
     */
    int updateByPrimaryKeySelective(DictionaryEntity record);

    /**
     * 更新
     * @param record 更新对象
     * @return 更新结果
     */
    int updateByPrimaryKey(DictionaryEntity record);

    /**
     * 检查key是否存在
     * @param dictionaryKey 字典key
     * @return 是否存在
     */
    boolean checkKey(String dictionaryKey);

    /**
     * 从缓存中查询字典
     * @param dictionaryKey 字典key
     * @return 字典
     */
    DictionaryEntity selectFromCache(String dictionaryKey);

    /**
     * 从缓存中查询字典
     * @param id id
     * @return 字典
     */
    DictionaryEntity selectFromCacheById(Long id);

    /**
     * 清除缓存
     */
    void clearCache();
}

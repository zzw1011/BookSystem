package com.zzw.base.service;

import com.zzw.base.entity.DictionaryValueEntity;

import java.util.List;

/**
 * 字典值Service
 */
public interface DictionaryValueService
{
    /**
     * 根据主键删除字典值
     * @param id 主键
     * @return 删除结果
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 根据外键删除字典值
     * @param dictionaryId 外键
     * @return 删除结果
     */
    int deleteByForeignKey(Long dictionaryId);

    /**
     * 新增
     * @param record 新增对象
     * @return 返回结果
     */
    int insert(DictionaryValueEntity record);

    /**
     * 新增
     * @param record 新增对象
     * @return 返回结果
     */
    int insertSelective(DictionaryValueEntity record);

    /**
     * 查询
     * @param id 主键
     * @return 结果
     */
    DictionaryValueEntity selectByPrimaryKey(Long id);

    /**
     * 更新
     * @param record 更新对象
     * @return 结果
     */
    int updateByPrimaryKeySelective(DictionaryValueEntity record);

    /**
     * 更新
     * @param record 更新对象
     * @return 结果
     */
    int updateByPrimaryKey(DictionaryValueEntity record);

    /**
     * 检查
     * @param value 条件
     * @return 结果
     */
    boolean checkValue(String value);

    /**
     * 根据字典ID查询字典值列表
     * @param dictionaryId 字典ID
     * @return 结果
     */
    List<DictionaryValueEntity> findList(Long dictionaryId);

    /**
     * 根据字典ID查询字典值列表
     * @param id 主键
     * @return 结果
     */
    DictionaryValueEntity findById(Long id);
}

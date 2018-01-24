package com.zzw.base.dao;

/**
 * DictionaryValueDao
 * @param <DictionaryValueEntity>
 */
public interface DictionaryValueDao<DictionaryValueEntity>
        extends BaseDao<DictionaryValueEntity>
{
    /**
     * 删除
     * @param id 参数
     * @return 结果
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 新增
     * @param record 参数
     * @return 结果
     */
    int insert(DictionaryValueEntity record);

    /**
     * 新增
     * @param record 参数
     * @return 结果
     */
    int insertSelective(DictionaryValueEntity record);

    /**
     * 查询
     * @param id 参数
     * @return 结果
     */
    DictionaryValueEntity selectByPrimaryKey(Long id);

    /**
     * 更新
     * @param record 参数
     * @return 结果
     */
    int updateByPrimaryKeySelective(DictionaryValueEntity record);
    /**
     * 更新
     * @param record 参数
     * @return 结果
     */
    int updateByPrimaryKey(DictionaryValueEntity record);
}

package com.zzw.base.dao;

/**
 * DictionaryDao
 * @param <DictionaryEntity>
 */
public interface DictionaryDao<DictionaryEntity>
        extends BaseDao<DictionaryEntity>
{
    /**
     * 删除
     * @param id 参数
     * @return 结果
     */
    int deleteByPrimaryKey(Long id);

    /**
     * 插入
     * @param record 参数
     * @return 结果
     */
    int insert(DictionaryEntity record);

    /**
     * 插入
     * @param record 参数
     * @return 结果
     */
    int insertSelective(DictionaryEntity record);

    /**
     * 查询
     * @param id 参数
     * @return 结果
     */
    DictionaryEntity selectByPrimaryKey(Long id);

    /**
     * 更新
     * @param record 参数
     * @return 结果
     */
    int updateByPrimaryKeySelective(DictionaryEntity record);
    /**
     * 更新
     * @param record 参数
     * @return 结果
     */
    int updateByPrimaryKey(DictionaryEntity record);
}

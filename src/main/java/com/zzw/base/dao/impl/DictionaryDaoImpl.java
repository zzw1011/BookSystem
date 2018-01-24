package com.zzw.base.dao.impl;

import com.zzw.base.dao.DictionaryDao;
import org.springframework.stereotype.Repository;

/**
 * DictionaryDaoImpl
 * @param <DictionaryEntity>
 */
@Repository("dictionaryDaoImpl")
public class DictionaryDaoImpl<DictionaryEntity> extends
        BaseDaoImpl<DictionaryEntity> implements DictionaryDao<DictionaryEntity>
{

    /**
     *
     * @param id 参数
     * @return 结果
     */
    @Override
    public int deleteByPrimaryKey(final Long id)
    {
        return super.delete("dictionaryDeleteByPrimaryKey", id);
    }

    /**
     *
     * @param record 参数
     * @return 结果
     */
    @Override
    public int insert(final DictionaryEntity record)
    {
        return super.save("dictionaryInsert", record);
    }

    /**
     *
     * @param record 参数
     * @return 结果
     */
    @Override
    public int insertSelective(final DictionaryEntity record)
    {
        return super.save("dictionaryInsertSelective", record);
    }

    /**
     *
     * @param id 参数
     * @return 结果
     */
    @Override
    public DictionaryEntity selectByPrimaryKey(final Long id)
    {
        return super.get("dictionarySelectByPrimaryKey", id);
    }

    /**
     *
     * @param record 参数
     * @return 结果
     */
    @Override
    public int updateByPrimaryKeySelective(final DictionaryEntity record)
    {
        return super.update("dictionaryUpdateByPrimaryKeySelective", record);
    }

    /**
     *
     * @param record 参数
     * @return 结果
     */
    @Override
    public int updateByPrimaryKey(final DictionaryEntity record)
    {
        return super.update("dictionaryUpdateByPrimaryKey", record);
    }

}

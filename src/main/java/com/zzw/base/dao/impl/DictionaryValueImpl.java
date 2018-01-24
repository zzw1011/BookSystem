package com.zzw.base.dao.impl;

import com.zzw.base.dao.DictionaryValueDao;
import org.springframework.stereotype.Repository;

/**
 * DictionaryValueImpl
 * @param <DictionaryValueEntity>
 */
@Repository("dictionaryValueImpl")
public class DictionaryValueImpl<DictionaryValueEntity>
        extends BaseDaoImpl<DictionaryValueEntity>
        implements DictionaryValueDao<DictionaryValueEntity>
{

    /**
     *
     * @param id 参数
     * @return 结果
     */
    @Override
    public int deleteByPrimaryKey(final Long id)
    {
        return super.delete("dictionaryValueDeleteByPrimaryKey", id);
    }

    /**
     *
     * @param record 参数
     * @return 结果
     */
    @Override
    public int insert(final DictionaryValueEntity record)
    {
        return super.save("dictionaryValueInsert", record);
    }

    /**
     *
     * @param record 参数
     * @return 结果
     */
    @Override
    public int insertSelective(final DictionaryValueEntity record)
    {
        return super.save("dictionaryValueInsertSelective", record);
    }

    /**
     *
     * @param id 参数
     * @return 结果
     */
    @Override
    public DictionaryValueEntity selectByPrimaryKey(final Long id)
    {
        return super.get("dictionaryValueSelectByPrimaryKey", id);
    }

    /**
     *
     * @param record 参数
     * @return 结果
     */
    @Override
    public int updateByPrimaryKeySelective(final DictionaryValueEntity record)
    {
        return super.update("dictionaryValueUpdateByPrimaryKeySelective",
                record);
    }

    /**
     *
     * @param record 参数
     * @return 结果
     */
    @Override
    public int updateByPrimaryKey(final DictionaryValueEntity record)
    {
        return super.update("dictionaryValueUpdateByPrimaryKey", record);
    }

}

package com.zzw.base.service.impl;

import com.zzw.base.dao.DictionaryValueDao;
import com.zzw.base.entity.DictionaryValueEntity;
import com.zzw.base.service.DictionaryValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DictionaryValueServiceImpl
 */
@Service("dictionaryValueServiceImpl")
public class DictionaryValueServiceImpl implements DictionaryValueService
{
    /**
     * dictionaryValueDao
     */
    @Autowired
    private DictionaryValueDao<DictionaryValueEntity> dictionaryValueDao;

    /**
     *
     * @param id 主键
     * @return 结果
     */
    @Override
    public int deleteByPrimaryKey(final Long id)
    {
        return dictionaryValueDao.deleteByPrimaryKey(id);
    }

    /**
     *
     * @param record 新增对象
     * @return 结果
     */
    @Override
    public int insert(final DictionaryValueEntity record)
    {
        return dictionaryValueDao.insert(record);
    }

    /**
     *
     * @param record 新增对象
     * @return 结果
     */
    @Override
    public int insertSelective(final DictionaryValueEntity record)
    {
        return dictionaryValueDao.insertSelective(record);
    }

    /**
     *
     * @param id 主键
     * @return 结果
     */
    @Override
    public DictionaryValueEntity selectByPrimaryKey(final Long id)
    {
        return dictionaryValueDao.selectByPrimaryKey(id);
    }

    /**
     *
     * @param record 更新对象
     * @return 结果
     */
    @Override
    public int updateByPrimaryKeySelective(final DictionaryValueEntity record)
    {
        return dictionaryValueDao.updateByPrimaryKeySelective(record);
    }

    /**
     *
     * @param record 更新对象
     * @return 结果
     */
    @Override
    public int updateByPrimaryKey(final DictionaryValueEntity record)
    {
        return dictionaryValueDao.updateByPrimaryKey(record);
    }

    /**
     *
     * @param value 条件
     * @return 结果
     */
    @Override
    public boolean checkValue(final String value)
    {
        Map<String, String> map = new HashMap<>();
        map.put("value", value);
        Long count = dictionaryValueDao.count("checkValue", map);
        if (count == 0L)
        {
            return false;
        }
        return true;
    }

    /**
     *
     * @param dictionaryId 字典ID
     * @return 结果
     */
    @Override
    public List<DictionaryValueEntity> findList(final Long dictionaryId)
    {
        Assert.notNull(dictionaryId);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("dictionaryId", dictionaryId);

        return dictionaryValueDao.find("dicValue_selectListByDicId", param);
    }

    /**
     *
     * @param dictionaryId 外键
     * @return 结果
     */
    @Override
    public int deleteByForeignKey(final Long dictionaryId)
    {
        return dictionaryValueDao.delete("dictionaryValueDeleteByForeignKey",
                dictionaryId);
    }

    /**
     *
     * @param id 主键
     * @return 结果
     */
    @Override
    public DictionaryValueEntity findById(final Long id)
    {
        return dictionaryValueDao.get("dictionaryValueSelectByPrimaryKey", id);
    }

}

package com.zzw.base.service.impl;

import com.github.pagehelper.PageInfo;
import com.zzw.base.dao.DictionaryDao;
import com.zzw.base.entity.DictionaryEntity;
import com.zzw.base.entity.DictionaryValueEntity;
import com.zzw.base.model.PageQuery;
import com.zzw.base.service.DictionaryService;
import com.zzw.base.service.DictionaryValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * DictionaryServiceImpl
 */
@Service("dictionaryServiceImpl")
public class DictionaryServiceImpl implements DictionaryService
{
    /**
     * dictionaryDao
     */
    @Autowired
    private DictionaryDao<DictionaryEntity> dictionaryDao;

    /**
     * dictionaryValueService
     */
    @Autowired
    private DictionaryValueService dictionaryValueService;

    /**
     *
     * @param id 主键
     * @return 结果
     */
    @Override
    public int deleteByPrimaryKey(final Long id)
    {
        return dictionaryDao.deleteByPrimaryKey(id);
    }

    /**
     *
     * @param record 保存对象
     * @return 结果
     */
    @Override
    public int insert(final DictionaryEntity record)
    {
        return dictionaryDao.insert(record);
    }

    /**
     *
     * @param record 保存对象
     * @return 结果
     */
    @Override
    public int insertSelective(final DictionaryEntity record)
    {
        return dictionaryDao.insertSelective(record);
    }

    /**
     *
     * @param id 主键
     * @return 结果
     */
    @Override
    public DictionaryEntity selectByPrimaryKey(final Long id)
    {
        return dictionaryDao.selectByPrimaryKey(id);
    }

    /**
     *
     * @param record 更新对象
     * @return 结果
     */
    @Override
    public int updateByPrimaryKeySelective(final DictionaryEntity record)
    {
        return dictionaryDao.updateByPrimaryKeySelective(record);
    }

    /**
     *
     * @param record 更新对象
     * @return 结果
     */
    @Override
    public int updateByPrimaryKey(final DictionaryEntity record)
    {
        return dictionaryDao.updateByPrimaryKey(record);
    }

    /**
     *
     * @param dictionaryKey 字典key
     * @return 结果
     */
    @Override
    public boolean checkKey(final String dictionaryKey)
    {
        Map<String, String> map = new HashMap<>();
        map.put("dictionaryKey", dictionaryKey);
        Long count = dictionaryDao.count("checkKey", map);
        if (count == 0L)
        {
            return false;
        }
        return true;
    }

    /**
     *
     * @param dictionaryKey 字典key
     * @return 结果
     */
    @Override
    @Cacheable(value = "dictionaryCache", key = "#dictionaryKey")
    public DictionaryEntity selectFromCache(final String dictionaryKey)
    {
        DictionaryEntity dictionaryEntity = this.selectByKey(dictionaryKey);

        if (dictionaryEntity != null)
        {
            List<DictionaryValueEntity> values = dictionaryValueService
                    .findList(dictionaryEntity.getId());
            dictionaryEntity.setValueList(values);
        }

        return dictionaryEntity;
    }

    /**
     *
     * @param id id
     * @return 结果
     */
    @Override
    @Cacheable(value = "dictionaryCache", key = "#id")
    public DictionaryEntity selectFromCacheById(final Long id)
    {
        DictionaryEntity dictionaryEntity = this.selectByPrimaryKey(id);

        if (dictionaryEntity != null)
        {
            List<DictionaryValueEntity> values = dictionaryValueService
                    .findList(dictionaryEntity.getId());
            dictionaryEntity.setValueList(values);
        }

        return dictionaryEntity;
    }

    /**
     *
     */
    @Override
    @CacheEvict(value = "dictionaryCache", allEntries = true)
    public void clearCache()
    {
    }

    /**
     *
     * @param record 查询条件
     * @param pageQuery 分页条件
     * @return 结果
     */
    @Override
    public PageInfo<DictionaryEntity> queryDictionary(final DictionaryEntity record,
                                                      final PageQuery pageQuery)
    {
        return dictionaryDao.findPage("queryDictionary", record, pageQuery);
    }

    /**
     *
     * @param dictionaryKey 字典key
     * @return 结果
     */
    @Override
    public DictionaryEntity selectByKey(final String dictionaryKey)
    {
        return dictionaryDao.get("dic_selectByKey", dictionaryKey);
    }

}

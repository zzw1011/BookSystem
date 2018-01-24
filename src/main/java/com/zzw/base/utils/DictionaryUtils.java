package com.zzw.base.utils;

import com.zzw.base.entity.DictionaryEntity;
import com.zzw.base.entity.DictionaryValueEntity;
import com.zzw.base.model.TreeModel;
import com.zzw.base.service.DictionaryService;
import com.zzw.base.service.DictionaryValueService;
import java.util.List;


/**
 * Created by Administrator on 2016/7/22.
 */
public final class DictionaryUtils
{

    private DictionaryUtils()
    {
    }

    /**
     * getDictionaryByKey
     * @param key
     *            key
     * @return 结果
     */
    public static DictionaryEntity getDictionaryByKey(final String key)
    {
        DictionaryService dictionaryService = SpringUtils
                .getBean("dictionaryServiceImpl", DictionaryService.class);
        return dictionaryService.selectFromCache(key);
    }

    /**
     * getTreeModelById
     * @param id
     *            id
     * @return 结果
     */
    public static List<TreeModel> getTreeModelById(final Long id)
    {
        DictionaryService dictionaryService = SpringUtils
                .getBean("dictionaryServiceImpl", DictionaryService.class);
        DictionaryValueService dictionaryValueService = SpringUtils.getBean(
                "dictionaryValueServiceImpl", DictionaryValueService.class);
        DictionaryValueEntity dictionaryValueEntity = dictionaryValueService
                .selectByPrimaryKey(id);
        DictionaryEntity dictionaryEntity = dictionaryService
                .selectFromCacheById(Long.parseLong(
                        dictionaryValueEntity.getDicOrder().toString()));
        TreeModel treeModel = new TreeModel();
        for (DictionaryValueEntity dictionaryValue : dictionaryEntity
                .getValueList())
        {
            TreeModel child = new TreeModel();
            child.setText(dictionaryValue.getDictionaryValue());
            child.setId(dictionaryValue.getId());
            treeModel.addChild(child);
        }
        return treeModel.getChildren();
    }

    /**
     * clearCache
     */
    public static void clearCache()
    {
        DictionaryService dictionaryService = SpringUtils
                .getBean("dictionaryServiceImpl", DictionaryService.class);
        dictionaryService.clearCache();
    }


}

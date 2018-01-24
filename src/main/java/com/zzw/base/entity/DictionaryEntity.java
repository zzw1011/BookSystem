package com.zzw.base.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * DictionaryEntity
 */
public class DictionaryEntity extends BaseEntity
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = 3275705663320298845L;

    /**
     * dictionaryKey
     */
    private String dictionaryKey;
    /**
     * bewrite
     */
    private String bewrite;
    /**
     * 修改人名称
     */
    private String modifyUserName;
    /**
     * 创建人名称
     */
    private String createUserName;
    /**
     * valueList
     */
    private List<DictionaryValueEntity> valueList = new ArrayList<DictionaryValueEntity>();

    /**
     * getModifyUserName
     * @return modifyUserName
     */
    public String getModifyUserName()
    {
        return modifyUserName;
    }

    /**
     * setModifyUserName
     * @param modifyUserName 参数
     */
    public void setModifyUserName(final String modifyUserName)
    {
        this.modifyUserName = modifyUserName;
    }

    /**
     * getCreateUserName
     * @return createUserName
     */
    public String getCreateUserName()
    {
        return createUserName;
    }

    /**
     * setCreateUserName
     * @param createUserName 参数
     */
    public void setCreateUserName(final String createUserName)
    {
        this.createUserName = createUserName;
    }

    /**
     * getDictionaryKey
     * @return dictionaryKey
     */
    public String getDictionaryKey()
    {
        return dictionaryKey;
    }

    /**
     * setDictionaryKey
     * @param dictionaryKey 参数
     */
    public void setDictionaryKey(final String dictionaryKey)
    {
        this.dictionaryKey = dictionaryKey;
    }

    /**
     * getBewrite
     * @return bewrite
     */
    public String getBewrite()
    {
        return bewrite;
    }

    /**
     * setBewrite
     * @param bewrite 参数
     */
    public void setBewrite(final String bewrite)
    {
        this.bewrite = bewrite;
    }

    /**
     * getValueList
     * @return valueList
     */
    public List<DictionaryValueEntity> getValueList()
    {
        return valueList;
    }

    /**
     * setValueList
     * @param valueList 参数
     */
    public void setValueList(final List<DictionaryValueEntity> valueList)
    {
        this.valueList = valueList;
    }

}

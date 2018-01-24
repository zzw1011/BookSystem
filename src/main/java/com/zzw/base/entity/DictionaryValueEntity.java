package com.zzw.base.entity;

import java.math.BigDecimal;

/**
 * DictionaryValueEntity
 */
public class DictionaryValueEntity extends BaseEntity
{

    /**
     * serialVersionUID
     */
    private static final long serialVersionUID = -5501368414581579012L;

    /**
     * dictionaryValue
     */
    private String dictionaryValue;
    /**
     * dicOrder
     */
    private Integer dicOrder;
    /**
     * dictionaryId
     */
    private Long dictionaryId;
    /**
     * 修改人名称
     */
    private String modifyUserName;
    /**
     * 创建人名称
     */
    private String createUserName;
    /**
     * 描述
     */
    private String dicBewrite;
    /**
     * 扩展字段金额
     */
    private BigDecimal dicTxt;

    /**
     * getDicOrder
     * @return dicOrder
     */
    public Integer getDicOrder()
    {
        return dicOrder;
    }

    /**
     * setDicOrder
     * @param dicOrder
     *            参数
     */
    public void setDicOrder(final Integer dicOrder)
    {
        this.dicOrder = dicOrder;
    }

    /**
     * getDictionaryId
     * @return dictionaryId
     */
    public Long getDictionaryId()
    {
        return dictionaryId;
    }

    /**
     * setDictionaryId
     * @param dictionaryId
     *            参数
     */
    public void setDictionaryId(final Long dictionaryId)
    {
        this.dictionaryId = dictionaryId;
    }

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
     * @param modifyUserName
     *            参数
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
     * @param createUserName
     *            参数
     */
    public void setCreateUserName(final String createUserName)
    {
        this.createUserName = createUserName;
    }

    /**
     * getDictionaryValue
     * @return dictionaryValue
     */
    public String getDictionaryValue()
    {
        return dictionaryValue;
    }

    /**
     * setDictionaryValue
     * @param dictionaryValue
     *            参数
     */
    public void setDictionaryValue(final String dictionaryValue)
    {
        this.dictionaryValue = dictionaryValue;
    }

    /**
     * 获取 扩展字段金额
     * @return dicTxt 扩展字段金额
     */
    public BigDecimal getDicTxt()
    {
        return this.dicTxt;
    }

    /**
     * 设置 扩展字段金额
     * @param dicTxt
     *            扩展字段金额
     */
    public void setDicTxt(final BigDecimal dicTxt)
    {
        this.dicTxt = dicTxt;
    }

    /**
     * 获取 描述
     * @return dicBewrite 描述
     */
    public String getDicBewrite() {
        return this.dicBewrite;
    }

    /**
     * 设置 描述
     * @param dicBewrite 描述
     */
    public void setDicBewrite(final String dicBewrite) {
        this.dicBewrite = dicBewrite;
    }
}

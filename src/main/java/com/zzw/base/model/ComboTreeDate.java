package com.zzw.base.model;

import java.util.List;

/**
 * Created by  on 2016/12/16.
 */
public class ComboTreeDate
{
    /**
     * id
     */
    private Long id;
    /**
     * text
     */
    private String text;
    /**
     * 子类
     */
    private List<ComboTreeDate> children;
    /**
     * 父ID
     */
    private Long parentId;

    /**
     * getId
     * @return id
     */
    public Long getId()
    {
        return id;
    }

    /**
     *setId
     * @param id 参数
     */
    public void setId(final Long id)
    {
        this.id = id;
    }

    /**
     * getText
     * @return text
     */
    public String getText()
    {
        return text;
    }

    /**
     * setText
     * @param text 参数
     */
    public void setText(final String text)
    {
        this.text = text;
    }

    /**
     * getChildren
     * @return children
     */
    public List<ComboTreeDate> getChildren()
    {
        return children;
    }

    /**
     * setChildren
     * @param children 参数
     */
    public void setChildren(final List<ComboTreeDate> children)
    {
        this.children = children;
    }


    /**
     * 获取 父ID
     * @return parentId 父ID
     */
    public Long getParentId()
    {
        return this.parentId;
    }

    /**
     * 设置 父ID
     * @param parentId
     *            父ID
     */
    public void setParentId(final Long parentId)
    {
        this.parentId = parentId;
    }
}

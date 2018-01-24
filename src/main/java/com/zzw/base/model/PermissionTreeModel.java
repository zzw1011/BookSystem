package com.zzw.base.model;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2016/7/19.
 */
public class PermissionTreeModel
{
    /**
     * id
     */
    private String id;
    /**
     * text
     */
    private String text;
    /**
     * state
     */
    private String state;
    /**
     * children
     */
    private List<PermissionTreeModel> children = new ArrayList<>();
    /**
     * checked
     */
    private boolean checked;

    /**
     * getText
     * @return text
     */
    public String getText()
    {
        return text;
    }

    /**
     * getState
     * @return state
     */
    public String getState()
    {
        return state;
    }

    /**
     * getChildren
     * @return children
     */
    public List<PermissionTreeModel> getChildren()
    {
        return children;
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
     * setState
     * @param state 参数
     */
    public void setState(final String state)
    {
        this.state = state;
    }

    /**
     * setChildren
     * @param children 参数
     */
    public void setChildren(final List<PermissionTreeModel> children)
    {
        this.children = children;
    }

    /**
     * addChild
     * @param treeModel 参数
     */
    public void addChild(final PermissionTreeModel treeModel)
    {
        children.add(treeModel);
    }

    /**
     * getChecked
     * @return checked
     */
    public boolean getChecked()
    {
        return checked;
    }

    /**
     * setChecked
     * @param checked 参数
     */
    public void setChecked(final boolean checked)
    {
        this.checked = checked;
    }

    /**
     * getId
     * @return id
     */
    public String getId()
    {
        return id;
    }

    /**
     * setId
     * @param id 参数
     */
    public void setId(final String id)
    {
        this.id = id;
    }
}

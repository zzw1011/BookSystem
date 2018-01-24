package com.zzw.base.model;

import com.zzw.base.entity.Category;

import java.util.ArrayList;
import java.util.List;


/**
 * Created by Administrator on 2016/7/18.
 */
public class NestedCategory extends Category
{
    /**
     * children
     */
    private List<NestedCategory> children = new ArrayList<>();

    /**
     * NestedCategory
     * @param category 参数
     */
    public NestedCategory(final Category category)
    {
        this.setCategoryImg(category.getCategoryImg());
        this.setCategoryName(category.getCategoryName());
        this.setCategoryUrl(category.getCategoryUrl());
        this.setPermissionCode(category.getPermissionCode());
        this.setCreateDate(category.getCreateDate());
        this.setCreateUser(category.getCreateUser());
        this.setModifyDate(category.getModifyDate());
        this.setUpdateUser(category.getUpdateUser());
        this.setPid(category.getPid());
        this.setId(category.getId());
        this.setCategoryDesc(category.getCategoryDesc());
    }

    /**
     * getChildren
     * @return children
     */
    public List<NestedCategory> getChildren()
    {
        return children;
    }

    /**
     * setChildren
     * @param children 参数
     */
    public void setChildren(final List<NestedCategory> children) {
        this.children = children;
    }
}

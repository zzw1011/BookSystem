package com.zzw.base.model;

import org.apache.commons.lang.StringUtils;

/**
 * 分页模型参数类
 * Created by zzw on 2017/12/29 0029.
 */
public class PageQuery
{
    /**
     * PAGE_SIZE
     */
    private static final Integer PAGE_SIZE = 10;

    /**
     * page
     */
    private Integer page;
    /**
     * rows
     */
    private Integer rows;
    /**
     * orderBy
     */
    private String orderBy;

    /**
     * getOrderBy
     * @return orderBy
     */
    public String getOrderBy()
    {
        return orderBy;
    }

    /**
     * setOrderBy
     * @param orderBy 参数
     */
    public void setOrderBy(final String orderBy)
    {
        if (StringUtils.isBlank(orderBy))
        {
            this.orderBy = " id desc";
        } else
        {
            this.orderBy = orderBy;
        }

    }

    /**
     * getPage
     * @return page
     */
    public Integer getPage()
    {
        if (page == null)
        {
            page = 1;
        }

        return page;
    }

    /**
     * getRows
     * @return rows
     */
    public Integer getRows()
    {
        if (rows == null)
        {
            rows = PAGE_SIZE;
        }
        return rows;
    }

    /**
     * setPage
     * @param page 参数
     */
    public void setPage(final Integer page)
    {
        this.page = page;
    }

    /**
     * setRows
     * @param rows 参数
     */
    public void setRows(final Integer rows)
    {
        this.rows = rows;
    }
}

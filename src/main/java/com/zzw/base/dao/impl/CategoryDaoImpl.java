package com.zzw.base.dao.impl;

import com.zzw.base.dao.CategoryDao;
import org.springframework.stereotype.Repository;

/**
 *
 * @param <Category>
 */
@Repository("categoryDaoImpl")
public class CategoryDaoImpl<Category> extends BaseDaoImpl<Category>
        implements CategoryDao<Category>
{
}

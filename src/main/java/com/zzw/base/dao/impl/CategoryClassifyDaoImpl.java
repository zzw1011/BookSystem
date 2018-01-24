package com.zzw.base.dao.impl;

import com.zzw.base.dao.CategoryClassifyDao;
import org.springframework.stereotype.Repository;

/**
 *
 * @param <CategoryClassify>
 */
@Repository("categoryClassifyDaoImpl")
public class CategoryClassifyDaoImpl<CategoryClassify>
        extends BaseDaoImpl<CategoryClassify>
        implements CategoryClassifyDao<CategoryClassify>
{
}

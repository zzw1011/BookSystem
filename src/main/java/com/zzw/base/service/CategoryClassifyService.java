package com.zzw.base.service;

import com.github.pagehelper.PageInfo;
import com.zzw.base.entity.CategoryClassify;
import com.zzw.base.model.PageQuery;

import java.util.List;

/**
 *
 * Created by Administrator on 2016/7/19.
 */
public interface CategoryClassifyService
{
    /**
     * 查询分页分类信息
     * @param pageQuery 分页条件
     * @return 分类的翻页对象
     */
    PageInfo<CategoryClassify> findPage(PageQuery pageQuery);

    /**
     * 保存分类信息
     * @param categoryClassify 分类
     */
    void save(CategoryClassify categoryClassify);

    /**
     * 删除IDs
     * @param ids ID列表
     */
    void delete(List<Long> ids);

    /**
     * 找到栏目分类
     * @param id id
     * @return 分类
     */
    CategoryClassify findCategoryClassify(Long id);

    /**
     * 更新分类信息
     * @param categoryClassify 分类
     */
    void update(CategoryClassify categoryClassify);
}

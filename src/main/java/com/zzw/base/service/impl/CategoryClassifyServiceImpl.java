package com.zzw.base.service.impl;

import com.github.pagehelper.PageInfo;
import com.zzw.base.dao.CategoryClassifyDao;
import com.zzw.base.entity.CategoryClassify;
import com.zzw.base.model.PageQuery;
import com.zzw.base.service.CategoryClassifyService;
import com.zzw.base.service.UserService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CategoryClassifyServiceImpl
 */
@Service
public class CategoryClassifyServiceImpl implements CategoryClassifyService
{
    /**
     * categoryClassifyDao
     */
    @Resource(name = "categoryClassifyDaoImpl")
    private CategoryClassifyDao categoryClassifyDao;

    /**
     * userService
     */
    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * findPage
     * @param pageQuery 分页条件
     * @return 结果
     */
    @Override
    public PageInfo<CategoryClassify> findPage(final PageQuery pageQuery)
    {
        return categoryClassifyDao.findPage("classify_selectCategoryClassify",
                null, pageQuery);
    }

    /**
     * save
     * @param categoryClassify 分类
     */
    @Override
    public void save(final CategoryClassify categoryClassify)
    {
        categoryClassify.setCreateUser(
                userService.seleUserId(userService.getCurrentUser()));
        categoryClassifyDao.save("classify_saveCategoryClassify",
                categoryClassify);
    }

    /**
     *
     * @param ids ID列表
     */
    @Override
    public void delete(final List<Long> ids)
    {
        Map<String, Object> model = new HashMap<>();
        model.put("list", ids);
        categoryClassifyDao.delete("classify_deleteClassifies", model);
    }

    /**
     *
     * @param id id
     * @return 结果
     */
    @Override
    public CategoryClassify findCategoryClassify(final Long id)
    {
        CategoryClassify queryObj = new CategoryClassify();
        queryObj.setId(id);
        return (CategoryClassify) categoryClassifyDao
                .get("classify_selectCategoryClassifyById", queryObj);
    }

    /**
     *
     * @param categoryClassify 分类
     */
    @Override
    public void update(final CategoryClassify categoryClassify)
    {
        categoryClassify.setCreateUser(
                userService.seleUserId(userService.getCurrentUser()));
        categoryClassifyDao.update("classify_updateClassify", categoryClassify);
    }
}

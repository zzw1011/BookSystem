package com.zzw.base.service.impl;

import com.zzw.base.dao.CategoryDao;
import com.zzw.base.entity.Category;
import com.zzw.base.model.NestedCategory;
import com.zzw.base.model.PermissionTreeModel;
import com.zzw.base.model.TreeModel;
import com.zzw.base.service.CategoryService;
import com.zzw.base.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * CategoryServiceImpl
 */
@Service
public class CategoryServiceImpl implements CategoryService
{
    /**
     * categoryDao
     */
    @Resource(name = "categoryDaoImpl")
    private CategoryDao categoryDao;
    /**
     * userService
     */
    @Resource(name = "userServiceImpl")
    private UserService userService;

    /**
     * findNestedCategorys
     * @param categories 栏目
     * @param parentId 父ID
     * @return 结果
     */
    @Override
    public List<NestedCategory> findNestedCategorys(final List<Category> categories,
            final Long parentId)
    {
        List<NestedCategory> result = new ArrayList<>();
        if (categories != null)
        {
            for (Category category : categories)
            {
                if (parentId.equals(category.getPid()))
                {
                    NestedCategory nestedCategory = new NestedCategory(
                            category);

                    List<NestedCategory> children = findNestedCategorys(
                            categories, nestedCategory.getId());

                    if (children != null && children.size() > 0)
                    {
                        nestedCategory.setChildren(children);
                    }

                    result.add(nestedCategory);
                }
            }
        }

        return result;
    }

    /**
     *
     * @param classifyId 分类ID
     * @return 结果
     */
    @Override
    public List<NestedCategory> getAllNestedCategorys(final Long classifyId)
    {
        List<Category> categories = this.findCategorys(classifyId);
        Long parentId = 0L;

        return this.findNestedCategorys(categories, parentId);
    }

    /**
     *
     * @param nestedCategoryList 节点列表
     * @return 结果
     */
    @Override
    public List<PermissionTreeModel> createCategoryPermissionTree(
            final List<NestedCategory> nestedCategoryList)
    {
        PermissionTreeModel permissionTreeModel = new PermissionTreeModel();
        permissionTreeModel.setId("0");
        permissionTreeModel.setState("open");
        permissionTreeModel.setText("根目录");

        createPermissionTree(permissionTreeModel, nestedCategoryList);

        List<PermissionTreeModel> result = new ArrayList<>();
        // easyui的树只接受数组格式的json
        result.add(permissionTreeModel);

        return result;
    }

    /**
     *
     * @param nestedCategoryList 节点列表
     * @return 结果
     */
    @Override
    public List<TreeModel> createCategoryTree(
            final List<NestedCategory> nestedCategoryList)
    {
        TreeModel treeModel = new TreeModel();
        treeModel.setId(0L);
        treeModel.setState("open");
        treeModel.setText("根目录");

        createTree(treeModel, nestedCategoryList);

        List<TreeModel> result = new ArrayList<>();
        // easyui的树只接受数组格式的json
        result.add(treeModel);

        return result;
    }

    /**
     * 保存
     * @param category 参数
     */
    @Override
    public void save(final Category category)
    {
        category.setCreateUser(
                userService.seleUserId(userService.getCurrentUser()));

        if (StringUtils.isBlank(category.getPermissionCode())
                || "0".equals(category.getPermissionCode()))
        {
            // 选择新增根节点,即未选择权限
            category.setPermissionCode("");
        }

        this.categoryDao.save("cat_saveObj", category);
    }

    /**
     * findCategory
     * @param id 参数
     * @return 结果
     */
    @Override
    public Category findCategory(final Long id)
    {
        Category category = new Category();
        category.setId(id);
        return (Category) categoryDao.get("cat_selectObjById", category);
    }

    /**
     * delete
     * @param ids 参数
     */
    @Override
    public void delete(final List<Long> ids)
    {
        Map<String, Object> model = new HashMap<>();
        model.put("list", ids);
        this.categoryDao.delete("cat_deleteCategorysByIds", model);
    }

    /**
     * getCategoryByPermissionCodeAndClassifyId
     * @param permissionCode 参数
     * @param classifyId 参数
     * @return 结果
     */
    @Override
    public Category getCategoryByPermissionCodeAndClassifyId(
            final String permissionCode, final Long classifyId)
    {
        Category category = new Category();
        category.setPermissionCode(permissionCode);
        category.setClassifyId(classifyId);

        List<Category> list = this.categoryDao
                .find("cat_selectObjByPermissionCodeAndClassifyId", category);

        if (list != null && !list.isEmpty())
        {
            return list.get(0);
        } else
        {
            return null;
        }
    }

    /**
     * update
     * @param category 参数
     */
    @Override
    public void update(final Category category)
    {
        category.setUpdateUser(
                userService.seleUserId(userService.getCurrentUser()));

        if (StringUtils.isBlank(category.getPermissionCode())
                || "0".equals(category.getPermissionCode()))
        {
            // 选择新增根节点,即未选择权限
            category.setPermissionCode("");
        }

        categoryDao.update("cat_updateCategory", category);
    }

    /**
     * createTree
     * @param treeModel 参数
     * @param nestedCategoryList 参数
     */
    private void createTree(final TreeModel treeModel,
            final List<NestedCategory> nestedCategoryList)
    {
        for (NestedCategory category : nestedCategoryList)
        {
            TreeModel child = new TreeModel();
            child.setText(category.getCategoryName());
            child.setId(category.getId());
            child.setState("open");
            child.setChecked(false);

            treeModel.addChild(child);

            if (category.getChildren() != null
                    && !category.getChildren().isEmpty())
            {
                createTree(child, category.getChildren());
            }
        }
    }

    /**
     * createPermissionTree
     * @param permissionTreeModel 参数
     * @param nestedCategoryList 参数
     */
    private void createPermissionTree(final PermissionTreeModel permissionTreeModel,
            final List<NestedCategory> nestedCategoryList)
    {
        for (NestedCategory category : nestedCategoryList)
        {
            PermissionTreeModel child = new PermissionTreeModel();
            child.setText(category.getCategoryName());
            child.setId(category.getPermissionCode());
            child.setState("open");
            child.setChecked(false);

            permissionTreeModel.addChild(child);

            if (category.getChildren() != null
                    && !category.getChildren().isEmpty())
            {
                createPermissionTree(child, category.getChildren());
            }
        }
    }

    /**
     * findCategorys
     * @param classifyId 分类ID
     * @return 参数
     */
    @Override
    public List<Category> findCategorys(final Long classifyId)
    {
        Category category = new Category();
        category.setClassifyId(classifyId);
        return categoryDao.find("cat_selectCategorys", category);
    }

    /**
     * findCategorysByCache
     * @param classifyId 参数
     * @return 结果
     */
    @Override
    @Cacheable(value = "categoryCache", key = "#classifyId")
    public List<Category> findCategorysByCache(final Long classifyId)
    {
        return this.findCategorys(classifyId);
    }

    /**
     * 清空缓存
     */
    @Override
    @CacheEvict(value = "categoryCache", allEntries = true)
    public void clearCache()
    {
    }
}

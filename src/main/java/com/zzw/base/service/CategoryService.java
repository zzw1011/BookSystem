package com.zzw.base.service;

import com.zzw.base.entity.Category;
import com.zzw.base.model.NestedCategory;
import com.zzw.base.model.PermissionTreeModel;
import com.zzw.base.model.TreeModel;

import java.util.List;

/**
 *
 * Created by Administrator on 2016/7/18.
 */
public interface CategoryService
{
    /**
     * 从数据库中查询所有的栏目列表
     * @param classifyId 分类ID
     * @return 栏目列表
     */
    List<Category> findCategorys(Long classifyId);

    /**
     * 从缓存中读取数据
     * @param classifyId id
     * @return 结果
     */
    List<Category> findCategorysByCache(Long classifyId);

    /**
     * 根据栏目列表及父目录ID生成树对象
     *
     * @param categories 栏目列表
     * @param parentId 父id
     * @return 结果
     */
    List<NestedCategory> findNestedCategorys(List<Category> categories, Long parentId);

    /**
     * 生成所有的栏目分类
     * @param classifyId 分类ID
     * @return 结果
     */
    List<NestedCategory> getAllNestedCategorys(Long classifyId);

    /**
     * 根据属性对象列表生成树形JSON数据对象供前台展现
     *
     * @param nestedCategoryList 树形对象列表
     * @return 结果
     */
    List<PermissionTreeModel> createCategoryPermissionTree(List<NestedCategory> nestedCategoryList);

    /**
     * createCategoryTree
     * @param nestedCategoryList 树形对象列表
     * @return 结果
     */
    List<TreeModel> createCategoryTree(List<NestedCategory> nestedCategoryList);

    /**
     * 保存栏目
     *
     * @param category 栏目对象
     */
    void save(Category category);

    /**
     * 根据ID招到栏目对象
     *
     * @param id id
     * @return 结果
     */
    Category findCategory(Long id);

    /**
     * 批量删除
     *
     * @param ids 要删除的id列表
     */
    void delete(List<Long> ids);

    /**
     * 根据授权码和分类ID获取栏目信息
     *
     * @param permissionCode 授权码
     * @param classifyId 分类ID
     * @return 结果
     */
    Category getCategoryByPermissionCodeAndClassifyId(String permissionCode, Long classifyId);

    /**
     * 更新栏目
     *
     * @param category 栏目
     */
    void update(Category category);

    /**
     * 清除缓存
     */
    void clearCache();
}

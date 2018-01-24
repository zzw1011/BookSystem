package com.zzw.base.controller;

import com.zzw.base.entity.Category;
import com.zzw.base.model.Message;
import com.zzw.base.model.NestedCategory;
import com.zzw.base.model.PermissionTreeModel;
import com.zzw.base.model.TreeModel;
import com.zzw.base.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.ArrayList;
import java.util.List;


/**
 *
 * Created by Administrator on 2016/7/18.
 */
@Controller("adminCategoryController")
@RequestMapping(value = "/admin/category")
public class CategoryController extends BaseController
{
    /**
     *类别业务服务类
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 加载树
     * @param classifyId 类ID
     * @param model 返回数据
     * @return 返回树页面
     */
    @RequestMapping(value = "/tree", method = RequestMethod.GET)
    public String tree(final Long classifyId, final ModelMap model)
    {
        model.addAttribute("classifyId", classifyId);
        return "/adminnew/category/tree";
    }

    /**
     * 获取树数据
     * @param classifyId 类ID
     * @return 返回数据
     */
    @RequestMapping(value = "/getTreeDate", method = RequestMethod.GET)
    public @ResponseBody
    List<TreeModel> getTreeDate(final Long classifyId)
    {
        List<NestedCategory> nestedCategoryList = categoryService.getAllNestedCategorys(classifyId);

        return categoryService.createCategoryTree(nestedCategoryList);
    }

    /**
     * 获取权限数据
     * @param classifyId 类id
     * @return 返回数据
     */
    @RequestMapping(value = "/getPermissionTreeDate", method = RequestMethod.GET)
    public @ResponseBody
    List<PermissionTreeModel> getPermissionTreeDate(final Long classifyId)
    {
        List<NestedCategory> nestedCategoryList = categoryService.getAllNestedCategorys(classifyId);

        return categoryService.createCategoryPermissionTree(nestedCategoryList);
    }

    /**
     * 新增分类信息
     * @param category 分类
     * @param model 数据
     * @return 跳转新增页面
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(final Category category, final ModelMap model)
    {
        model.addAttribute("permissionClassifyId", BaseController.PERMISSION_CLASSIFY_ID);
        model.addAttribute("category", category);
        return "/adminnew/category/add";
    }

    /**
     * 保存或编辑分类信息
     * @param category 分类实体信息
     * @param redirectAttributes 返回信息
     * @return 编辑页面
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(final Category category, final RedirectAttributes redirectAttributes)
    {
        this.categoryService.save(category);
        this.addFlashMessage(redirectAttributes, Message.success("保存成功"));
        return "redirect:tree.do?classifyId=" + category.getClassifyId();
    }

    /**
     * 删除分类
     * @param ids 关联
     * @return 返回操作结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody
    Message delete(final String ids)
    {
        Assert.hasLength(ids);
        String[] arr = ids.split(",");
        List<Long> idList = new ArrayList<>();

        for (String id : arr)
        {
            idList.add(Long.parseLong(id));
        }

        categoryService.delete(idList);

        return Message.success("删除成功");
    }

    /**
     * 修改分类信息
     * @param category 分类实体信息
     * @param model 返回数据
     * @return 跳转编辑
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(final Category category, final ModelMap model)
    {
        model.addAttribute("permissionClassifyId", BaseController.PERMISSION_CLASSIFY_ID);
        Category selectCategory = categoryService.findCategory(category.getId());
        Category selectCat = categoryService.
                getCategoryByPermissionCodeAndClassifyId(selectCategory.getPermissionCode(), BaseController.PERMISSION_CLASSIFY_ID);

        if (selectCat != null)
        {
            model.addAttribute("selectCatId", selectCat.getId());
        } else
        {
            model.addAttribute("selectCatId", 0L);
        }

        model.addAttribute("category", selectCategory);

        return "/adminnew/category/edit";
    }

    /**
     * 编辑分类
     * @param category 分类信息
     * @param redirectAttributes 返回信息
     * @return 跳转树
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(final Category category, final RedirectAttributes redirectAttributes)
    {
        categoryService.update(category);
        this.addFlashMessage(redirectAttributes, Message.success("修改成功"));
        return "redirect:tree.do?classifyId=" + category.getClassifyId();
    }

}

package com.zzw.base.controller;

import com.github.pagehelper.PageInfo;
import com.zzw.base.entity.CategoryClassify;
import com.zzw.base.model.Message;
import com.zzw.base.model.PageQuery;
import com.zzw.base.service.CategoryClassifyService;
import com.zzw.base.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.HashMap;
import java.util.Map;

/**
 * 栏目分类
 * Created by Administrator on 2016/7/19.
 */
@Controller("adminCategoryClassifyController")
@RequestMapping(value = "/admin/classify")
public class CategoryClassifyController extends BaseController
{
    /**
     *类别分类业务服务类
     */
    @Autowired
    private CategoryClassifyService categoryClassifyService;
    /**
     *类别业务服务类
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 进入类别列表页面
     * @return 跳转列表页
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list()
    {
        return "/adminnew/classify/list";
    }

    /**
     *根据条件获取分页列表数据
     * @param pageQuery 查询条件
     * @return 返回数据
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getPage(final PageQuery pageQuery)
    {
        Map<String, Object> result = new HashMap<>();
        PageInfo<CategoryClassify> page = categoryClassifyService
                .findPage(pageQuery);
        result.put("total", page.getTotal());
        result.put("rows", page.getList());

        return result;
    }
    /**
     *
     * @return 结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add()
    {
        return "/adminnew/classify/add";
    }

    /**
     *
     * @param categoryClassify 参数
     * @param redirectAttributes 参数
     * @return 结果
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(final CategoryClassify categoryClassify,
                       final RedirectAttributes redirectAttributes)
    {
        categoryClassifyService.save(categoryClassify);
        this.addFlashMessage(redirectAttributes, Message.success("添加成功"));
        return "redirect:list.do";
    }

    /**
     *
     * @param ids 参数
     * @return 结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody Message delete(final String ids)
    {
        categoryClassifyService.delete(this.findIds(ids));

        return Message.success("删除成功");
    }

    /**
     *
     * @param id 参数
     * @param model 参数
     * @return 结果
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(final Long id, final ModelMap model)
    {
        Assert.notNull(id);
        model.addAttribute("classify",
                categoryClassifyService.findCategoryClassify(id));
        return "/adminnew/classify/edit";
    }

    /**
     *
     * @param classify 参数
     * @param redirectAttributes 参数
     * @return 结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(final CategoryClassify classify,
                         final RedirectAttributes redirectAttributes)
    {
        categoryClassifyService.update(classify);
        this.addFlashMessage(redirectAttributes, Message.success("修改成功"));
        return "redirect:list.do";
    }

    /**
     *
     * @return 结果
     */
    @RequestMapping(value = "/clearCache", method = RequestMethod.GET)
    public String clearCache()
    {
        categoryService.clearCache();
        return "redirect:list.do";
    }
}

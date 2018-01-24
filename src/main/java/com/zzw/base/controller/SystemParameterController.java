package com.zzw.base.controller;

import com.github.pagehelper.PageInfo;
import com.zzw.base.entity.SystemParameterEntity;
import com.zzw.base.model.Message;
import com.zzw.base.model.PageQuery;
import com.zzw.base.service.SystemParameterService;
import com.zzw.base.utils.SysParamUtils;
import org.apache.commons.lang.StringUtils;
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
 * 系统参数
 * Created by Administrator on 2016/7/19.
 */
@Controller("adminSystemParameterController")
@RequestMapping(value = "/admin/systemParameter")
public class SystemParameterController extends BaseController
{
    /**
     * systemParameterService
     */
    @Autowired
    private SystemParameterService systemParameterService;

    /**
     * list
     * @return 结果
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list()
    {
        return "/adminnew/systemParameter/list";
    }

    /**
     * getPage
     * @param pageQuery 参数
     * @return 结果
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getPage(final PageQuery pageQuery)
    {
        Map<String, Object> result = new HashMap<>();
        PageInfo<SystemParameterEntity> page = systemParameterService
                .findPage(pageQuery);
        result.put("total", page.getTotal());
        result.put("rows", page.getList());

        return result;
    }

    /**
     *
     * @param parameterKey 参数
     * @param pageQuery 参数
     * @return 结果
     */
    @RequestMapping(value = "/search", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> search(final String parameterKey,
                                                    final PageQuery pageQuery)
    {
        Map<String, Object> result = new HashMap<>();
        PageInfo<SystemParameterEntity> page = systemParameterService
                .findPageSearch(parameterKey, pageQuery);
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
        return "/adminnew/systemParameter/add";
    }

    /**
     * check
     * @param parameterKey 结果
     * @param id 参数
     * @return 结果
     */
    @RequestMapping(value = "/check", method = RequestMethod.POST)
    public @ResponseBody Message check(final String parameterKey, final String id)
    {
        SystemParameterEntity systemParameter = systemParameterService
                .findSystemParameter(parameterKey);
        if (StringUtils.isEmpty(id))
        {
            if (systemParameter != null)
            {
                return Message.error("参数主键重复");
            } else
            {
                return Message.success("");
            }
        } else
        {
            if (systemParameter != null
                    && Long.parseLong(id) != systemParameter.getId())
            {
                return Message.error("参数主键重复");
            } else
            {
                return Message.success("");
            }
        }
    }

    /**
     *
     * @return 结果
     */
    @RequestMapping(value = "/clearCache", method = RequestMethod.GET)
    public String clearCache()
    {
        SysParamUtils.clearCache();
        return "redirect:list.do";
    }

    /**
     *
     * @param systemParameterEntity 参数
     * @param redirectAttributes 参数
     * @return 结果
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(final SystemParameterEntity systemParameterEntity,
                       final RedirectAttributes redirectAttributes)
    {
        systemParameterService.save(systemParameterEntity);
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
        systemParameterService.delete(this.findIds(ids));

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
        model.addAttribute("systemParameter",
                systemParameterService.findSystemParameterById(id));
        return "/adminnew/systemParameter/edit";
    }

    /**
     *
     * @param systemParameterEntity 参数
     * @param redirectAttributes 参数
     * @return 结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(final SystemParameterEntity systemParameterEntity,
                         final RedirectAttributes redirectAttributes)
    {
        systemParameterService.update(systemParameterEntity);
        this.addFlashMessage(redirectAttributes, Message.success("修改成功"));
        return "redirect:list.do";
    }
}

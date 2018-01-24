package com.zzw.base.controller;

import com.zzw.base.entity.DictionaryValueEntity;
import com.zzw.base.model.Message;
import com.zzw.base.service.DictionaryValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.*;

/**
 * DictionaryValueController
 * Created by Administrator on 2016/7/21.
 */
@Controller("adminDictionaryValueController")
@RequestMapping(value = "/admin/dictionaryValue")
public class DictionaryValueController extends BaseController
{
    /**
     * dictionaryValueService
     */
    @Autowired
    private DictionaryValueService dictionaryValueService;

    /**
     * list
     * @param dictionaryId 字典ID
     * @param modelMap 参数
     * @return 结果
     */
    @RequestMapping(value = "/list", method = RequestMethod.GET)
    public String list(final Long dictionaryId, final ModelMap modelMap)
    {
        modelMap.addAttribute("dictionaryId", dictionaryId);
        return "/adminnew/dictionaryValue/list";
    }

    /**
     * 获取翻页数据
     * @param dictionaryId 字典ID
     * @return 结果
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getPage(final Long dictionaryId)
    {
        Map<String, Object> result = new HashMap<>();
        List<DictionaryValueEntity> values = dictionaryValueService
                .findList(dictionaryId);
        result.put("total", values.size());
        result.put("rows", values);
        return result;
    }

    /**
     *
     * @param dictionaryId 参数
     * @param modelMap 参数
     * @return 结果
     */
    @RequestMapping(value = "/add", method = RequestMethod.GET)
    public String add(final Long dictionaryId, final ModelMap modelMap)
    {
        modelMap.addAttribute("dictionaryId", dictionaryId);
        return "/adminnew/dictionaryValue/add";
    }

    /**
     *
     * @param dictionaryValueEntity 参数
     * @param redirectAttributes 参数
     * @return 结果
     */
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(final DictionaryValueEntity dictionaryValueEntity,
                       final RedirectAttributes redirectAttributes)
    {
        dictionaryValueEntity.setCreateDate(new Date());
        dictionaryValueService.insertSelective(dictionaryValueEntity);
        this.addFlashMessage(redirectAttributes, Message.success("操作成功"));

        return "redirect:list.do?dictionaryId="
                + dictionaryValueEntity.getDictionaryId();
    }

    /**
     *
     * @param id 参数
     * @param modelMap 参数
     * @return 结果
     */
    @RequestMapping(value = "/edit", method = RequestMethod.GET)
    public String edit(final Long id, final ModelMap modelMap)
    {
        modelMap.addAttribute("dictionaryValue",
                dictionaryValueService.selectByPrimaryKey(id));
        return "/adminnew/dictionaryValue/edit";
    }

    /**
     *
     * @param dictionaryValueEntity 参数
     * @param redirectAttributes 参数
     * @return 结果
     */
    @RequestMapping(value = "/update", method = RequestMethod.POST)
    public String update(final DictionaryValueEntity dictionaryValueEntity,
                         final RedirectAttributes redirectAttributes)
    {
        dictionaryValueEntity.setModifyDate(new Date());
        dictionaryValueService.updateByPrimaryKey(dictionaryValueEntity);
        this.addFlashMessage(redirectAttributes, Message.success("操作成功"));
        return "redirect:list.do?dictionaryId="
                + dictionaryValueEntity.getDictionaryId();
    }

    /**
     *
     * @param ids 参数
     * @return 结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody Message delete(final String ids)
    {
        Assert.hasLength(ids);

        String[] arr = ids.split(",");
        List<Long> idList = new ArrayList<>();

        for (String id : arr)
        {
            idList.add(Long.parseLong(id));
        }

        for (Long id : idList)
        {
            dictionaryValueService.deleteByPrimaryKey(id);
        }

        return Message.success("操作成功");
    }
}

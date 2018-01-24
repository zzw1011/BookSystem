package com.zzw.base.controller;

import com.github.pagehelper.PageInfo;
import com.zzw.base.entity.DictionaryEntity;
import com.zzw.base.entity.DictionaryValueEntity;
import com.zzw.base.model.Message;
import com.zzw.base.model.PageQuery;
import com.zzw.base.model.ResultEntity;
import com.zzw.base.service.DictionaryService;
import com.zzw.base.service.DictionaryValueService;
import com.zzw.base.utils.DictionaryUtils;
import com.zzw.base.utils.JsonUtil;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.Assert;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * DictionaryController
 */
@Controller
@RequestMapping(value = "/admin/dictionary")
public class DictionaryController extends BaseController
{
    /**
     * LOGGER
     */
    private static final Log LOGGER = LogFactory
            .getLog(DictionaryController.class);
    /**
     * returnSuccessCode
     */
    private static final String RETURN_SUCCESS_CODE = "success";
    /**
     * returnFailCode
     */
    private static final String RETURN_FAIL_CODE = "fail";
    /**
     * 登录用户
     */
    public static final String ADMIN_USER = "userinfo";
    /**
     * dictionaryService
     */
    @Autowired
    private DictionaryService dictionaryService;
    /**
     * dictionaryValueService
     */
    @Autowired
    private DictionaryValueService dictionaryValueService;

    /**
     * keylist页面
     * @return keylist列表
     */
    @RequestMapping(value = "list")
    public String list()
    {
        return "/adminnew/dictionary/list";
    }

    /**
     * 获取翻页数据
     * @param dictionaryEntity
     *            参数
     * @param pageQuery
     *            参数
     * @return 结果
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getPage(
            final DictionaryEntity dictionaryEntity, final PageQuery pageQuery)
    {
        Map<String, Object> result = new HashMap<>();
        PageInfo<DictionaryEntity> page = dictionaryService
                .queryDictionary(dictionaryEntity, pageQuery);
        result.put("total", page.getTotal());
        result.put("rows", page.getList());
        return result;
    }

    /**
     * 添加
     * @return 结果
     */
    @RequestMapping(value = "add", method = RequestMethod.GET)
    public String add()
    {
        return "/adminnew/dictionary/add";
    }

    /**
     *
     * @param dictionaryEntity
     *            参数
     * @param redirectAttributes
     *            参数
     * @return 结果
     */
    @RequestMapping(value = "save", method = RequestMethod.POST)
    public String save(final DictionaryEntity dictionaryEntity,
                       final RedirectAttributes redirectAttributes)
    {
        if (dictionaryService.checkKey(dictionaryEntity.getDictionaryKey()))
        {
            this.addFlashMessage(redirectAttributes, Message.error(
                    "KEY:[" + dictionaryEntity.getDictionaryKey() + "]已被使用"));
        } else
        {
            dictionaryEntity.setCreateDate(new Date());
            dictionaryService.insertSelective(dictionaryEntity);
            this.addFlashMessage(redirectAttributes, Message.success("保存成功"));
        }

        return "redirect:list.do";
    }

    /**
     * 更新
     * @param id 参数
     * @param modelMap 参数
     * @return 结果
     */
    @RequestMapping(value = "edit", method = RequestMethod.GET)
    public String edit(final Long id, final ModelMap modelMap)
    {
        DictionaryEntity originDictionaryEntity = dictionaryService
                .selectByPrimaryKey(id);
        modelMap.addAttribute("dictionary", originDictionaryEntity);
        return "/adminnew/dictionary/edit";
    }

    /**
     * 更新
     * @param dictionaryEntity 参数
     * @param redirectAttributes 参数
     * @return 结果
     */
    @RequestMapping(value = "update", method = RequestMethod.POST)
    public String update(final DictionaryEntity dictionaryEntity,
                         final RedirectAttributes redirectAttributes)
    {
        try
        {
            // 获取原始DictionaryEntity
            DictionaryEntity originDictionaryEntity = dictionaryService
                    .selectByPrimaryKey(dictionaryEntity.getId());
            // 判断dictionaryKey是否修改了
            if (originDictionaryEntity.getDictionaryKey()
                    .equals(dictionaryEntity.getDictionaryKey()))
            {
                dictionaryEntity.setDictionaryKey(null);
            } else
            {
                // 判断key是否存在
                if (dictionaryService
                        .checkKey(dictionaryEntity.getDictionaryKey()))
                {
                    this.addFlashMessage(redirectAttributes,
                            Message.error("KEY:["
                                    + dictionaryEntity.getDictionaryKey()
                                    + "]已被使用"));
                    return "redirect:list.do";
                }
            }

            dictionaryEntity.setModifyDate(new Date());
            dictionaryService.updateByPrimaryKeySelective(dictionaryEntity);
            this.addFlashMessage(redirectAttributes, Message.success("保存成功"));
        } catch (Exception e)
        {
            LOGGER.error("",e);
            this.addFlashMessage(redirectAttributes, Message.error("出现未知错误"));
        }
        return "redirect:list.do";
    }

    /**
     * 删除
     * @param ids 参数
     * @return 结果
     */
    @RequestMapping(value = "delete", method = RequestMethod.POST)
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
            // 刪除key
            dictionaryService.deleteByPrimaryKey(id);
            // 刪除value
            dictionaryValueService.deleteByForeignKey(id);
        }

        return Message.success("删除成功");
    }

    /**
     * 根据key获取valueList
     *
     * @param dictionaryKey 参数
     * @return 结果
     */
    @RequestMapping(value = "getValueByKey")
    public @ResponseBody DictionaryEntity getValueByKey(final String dictionaryKey)
    {
        DictionaryEntity dictionaryEntity = dictionaryService
                .selectByKey(dictionaryKey);
        return dictionaryEntity;
    }

    /**
     * 添加value
     *
     * @param dictionaryValueEntity 参数
     * @param response 参数
     */
    @RequestMapping(value = "addValue")
    public void addValue(final DictionaryValueEntity dictionaryValueEntity,
                         final HttpServletResponse response)
    {
        ResultEntity resultEntity = new ResultEntity();
        try
        {
            dictionaryValueEntity.setId(null);
            // 判断key是否存在
            if (dictionaryValueService
                    .checkValue(dictionaryValueEntity.getDictionaryValue()))
            {
                resultEntity.setCode(RETURN_FAIL_CODE);
                resultEntity.setMessage("key已存在");
            } else
            {
                dictionaryValueEntity.setCreateDate(new Date());
                dictionaryValueService.insertSelective(dictionaryValueEntity);
                resultEntity.setCode(RETURN_SUCCESS_CODE);
                resultEntity.setMessage("添加成功");
            }
        } catch (Exception e)
        {
            LOGGER.error("",e);
            resultEntity.setCode("fail");
            resultEntity.setMessage("添加失败");
        }
        JsonUtil.writeJson(response, resultEntity);
    }

    /**
     * 修改key
     * @param dictionaryValueEntity 参数
     * @param response 参数
     */
    @RequestMapping(value = "updateValue")
    public void updateValue(final DictionaryValueEntity dictionaryValueEntity,
                            final HttpServletResponse response)
    {
        ResultEntity resultEntity = new ResultEntity();
        try
        {
            // 获取原始DictionaryValueEntity
            DictionaryValueEntity originDictionaryValueEntity = dictionaryValueService
                    .selectByPrimaryKey(dictionaryValueEntity.getId());
            // 判断value是否修改了
            if (originDictionaryValueEntity != null
                    && originDictionaryValueEntity.getDictionaryValue()
                            .equals(dictionaryValueEntity.getDictionaryValue()))
            {
                dictionaryValueEntity.setDictionaryValue(null);
            } else
            {
                // 判断value是否存在
                if (dictionaryValueService
                        .checkValue(dictionaryValueEntity.getDictionaryValue()))
                {
                    resultEntity.setCode(RETURN_FAIL_CODE);
                    resultEntity.setMessage("value已存在");
                }
            }
            dictionaryValueEntity.setModifyDate(new Date());
            dictionaryValueService
                    .updateByPrimaryKeySelective(dictionaryValueEntity);
            resultEntity.setCode(RETURN_SUCCESS_CODE);
            resultEntity.setMessage("修改成功");
        } catch (Exception e)
        {
            LOGGER.error("",e);
            resultEntity.setCode(RETURN_FAIL_CODE);
            resultEntity.setMessage("修改失败");
        }
        JsonUtil.writeJson(response, resultEntity);
    }

    /**
     * 删除value
     *
     * @param id 参数
     * @param response 参数
     */
    @RequestMapping(value = "deleteValue")
    public void deleteValue(final Long id, final HttpServletResponse response)
    {
        ResultEntity resultEntity = new ResultEntity();
        try
        {
            // 刪除value
            dictionaryValueService.deleteByPrimaryKey(id);
            resultEntity.setCode(RETURN_SUCCESS_CODE);
            resultEntity.setMessage("删除成功");
        } catch (Exception e)
        {
            LOGGER.error("",e);
            resultEntity.setCode(RETURN_FAIL_CODE);
            resultEntity.setMessage("删除失败");
        }
        JsonUtil.writeJson(response, resultEntity);
    }

    /**
     * 清理缓存
     * @return 结果
     */
    @RequestMapping(value = "/clearCache", method = RequestMethod.GET)
    public String clearCache()
    {
        DictionaryUtils.clearCache();
        return "redirect:list.do";
    }
}

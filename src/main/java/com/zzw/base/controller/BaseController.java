package com.zzw.base.controller;

import com.zzw.base.model.Message;
import com.zzw.base.template.FlashMessageDirective;
import org.apache.commons.lang.StringUtils;
import org.springframework.util.Assert;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Controller - 基类
 *
 * @author JIUJIANG Team
 * @version 3.0
 */
public class BaseController
{

    /**
     * 权限分类ID
     */
    protected static final Long PERMISSION_CLASSIFY_ID = 2L;
    /**
     * 个人接口数据字典ID
     */
    protected static final Long GERENJIEKOUID = 452L;

    /**
     * 企业接口数据字典ID
     */
    protected static final Long QIYEJIEKOUID = 453L;
    /**
     * 错误视图
     */
    protected static final String ERROR_VIEW = "/admin/common/error";

    /**
     * 错误消息
     */
    protected static final Message ERROR_MESSAGE = Message
            .error("admin.message.error");

    /**
     * 成功消息
     */
    protected static final Message SUCCESS_MESSAGE = Message
            .success("admin.message.success");

    /**
     * 超级管理员
     */
    protected static final String SUPER_ADMINISTRATOR = "超级管理员";

    /**
     * 状态为10不做条件查询
     */
    protected static final int NODE_STATUS = 10;



    /**
     * 添加日志
     *
     * @param content
     *            内容
     */
    protected void addLog(final String content)
    {
        if (content != null)
        {
            RequestAttributes requestAttributes = RequestContextHolder
                    .currentRequestAttributes();
        }
    }

    /**
     * 添加FLASH信息
     *  flash信息用于提示
     * @param redirectAttributes 重定向属性
     * @param message 消息
     */
    protected void addFlashMessage(final RedirectAttributes redirectAttributes,
            final Message message)
    {
        if (redirectAttributes != null && message != null)
        {
            redirectAttributes.addFlashAttribute(
                    FlashMessageDirective.FLASH_MESSAGE_ATTRIBUTE_NAME,
                    message);
        }
    }

    /**
     * 初始化绑定时间解析
     *
     * @param dataBinder 数据绑定
     */
    @InitBinder
    public void initBinder(final WebDataBinder dataBinder)
    {
        dataBinder.registerCustomEditor(Date.class, new PropertyEditorSupport()
        {
            public void setAsText(final String value)
            {
                try
                {
                    setValue(new SimpleDateFormat("yyyy-MM-dd").parse(value));
                } catch (ParseException e)
                {
                    setValue(null);
                }
            }

            public String getAsText()
            {
                return new SimpleDateFormat("yyyy-MM-dd")
                        .format((Date) getValue());
            }

        });
    }

    /**
     * 从string中转换LONG型ID数组
     * @param ids ID字符串
     * @return Long型数组
     */
    protected List<Long> findIds(final String ids)
    {
        Assert.hasLength(ids);
        String[] arr = ids.split(",");
        List<Long> idList = new ArrayList<>();

        for (String id : arr)
        {
            if (StringUtils.isNotBlank(id))
            {
                idList.add(Long.parseLong(id));
            }
        }

        return idList;
    }
}

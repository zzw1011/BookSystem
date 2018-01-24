package com.zzw.base.controller;

import com.github.pagehelper.PageInfo;
import com.zzw.base.entity.OperatorLog;
import com.zzw.base.model.Message;
import com.zzw.base.model.PageQuery;
import com.zzw.base.service.OperatorLogService;
import com.zzw.base.utils.SimpleDataFormatUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

/**
 * 操作员日志 controller 类
 * @author Administrator
 */
@Controller("adminOperatorLogController")
@RequestMapping(value = "/admin/operatorLog")
public class OperatorLogController extends BaseController
{
    /**
     * 操作日志业务服务类
     */
    @Autowired
    private OperatorLogService operatorLogService;

    /**
     * 进入操作日志列表
     * @param log 日志
     * @param model 返回数据
     * @param pageQuery 查询条件
     * @return 返回列表页面
     */
    @RequestMapping(value = "/list")
    public String list(final OperatorLog log, final ModelMap model, final PageQuery pageQuery)
    {
        return "/adminnew/operateLog/list";
    }
    /**
     * 根据条件获取数据列表
     * @param operator 操作员
     * @param startDate 开始日期
     * @param endDate 结束日期
     * @param pageQuery 查询条件
     * @return 返回数据
     */
    @RequestMapping(value = "/getPage", method = RequestMethod.GET)
    public @ResponseBody Map<String, Object> getPage(final String operator, final String startDate, final String endDate, final PageQuery pageQuery)
    {
        Map<String, Object> result = new HashMap<>();

        Map<String, Object> queryMap = new HashMap<>();
        queryMap.put("operator", operator);
        queryMap.put("startDate", SimpleDataFormatUtil.parse(startDate,"yyyy-MM-dd HH:mm:ss"));
        queryMap.put("endDate",   SimpleDataFormatUtil.parse(endDate,"yyyy-MM-dd HH:mm:ss"));
        PageInfo<OperatorLog> page = operatorLogService.findPage(queryMap,
                pageQuery);
        result.put("total", page.getTotal());
        result.put("rows", page.getList());
        return result;
    }
    /**
     * 删除日志
     * @param ids 所有日志数据集
     * @return 返回结果
     */
    @RequestMapping(value = "/delete", method = RequestMethod.POST)
    public @ResponseBody Message delete(final String ids)
    {
        operatorLogService.delete(this.findIds(ids));
        return Message.success("删除成功");
    }
    /**
     * 日志详情数据
     * @param model  返回数据
     * @param id  日志ID
     * @return 返回详情页面
     */
    @RequestMapping(value = "/detail")
    public String detail(final ModelMap model, final Long id)
    {
        model.addAttribute("model", operatorLogService.getById(id));
        return "/adminnew/operateLog/detail";
    }
}

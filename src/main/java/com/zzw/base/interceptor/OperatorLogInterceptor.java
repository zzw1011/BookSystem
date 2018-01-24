package com.zzw.base.interceptor;

import com.zzw.base.entity.Category;
import com.zzw.base.entity.OperatorLog;
import com.zzw.base.service.CategoryService;
import com.zzw.base.service.OperatorLogService;
import com.zzw.base.service.UserService;
import com.zzw.base.utils.IpUtil;
import com.zzw.base.utils.SysParamUtils;
import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.AntPathMatcher;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

/**
 * OperatorLogInterceptor
 */
public class OperatorLogInterceptor extends HandlerInterceptorAdapter
{

    /**
     * 默认忽略参数
     */
    private static final String[] DEFAULT_IGNORE_PARAMETERS = new String[]
    {
            "userPassword", "oldPassword", "password", "confirmPassword"
    };

    /**
     * antPathMatcher
     */
    private static AntPathMatcher antPathMatcher = new AntPathMatcher();

    /**
     * operatorLogService
     */
    @Resource(name = "operatorLogServiceImpl")
    private OperatorLogService operatorLogService;

    /**
     * userService
     */
    @Autowired
    private UserService userService;

    /**
     * categoryService
     */
    @Autowired
    private CategoryService categoryService;

    /**
     * 处理
     * @param request 请求
     * @param response 响应
     * @param handler 处理
     * @param modelAndView 视图
     * @throws Exception 异常
     */
    @Override
    public void postHandle(final HttpServletRequest request,
            final HttpServletResponse response, final Object handler,
            final ModelAndView modelAndView) throws Exception
    {

        List<Category> categories = categoryService
                .findCategorysByCache(SysParamUtils.getLong("optClassifyId"));

        if (categories != null)
        {
            String path = request.getServletPath();

            for (Category category : categories)
            {
                if (antPathMatcher.match(category.getCategoryUrl(), path))
                {
                    // 路径匹配成功
                    String username = userService.getCurrentUser();
                    String operation = category.getCategoryName();
                    String operator = username;
                    String content = (String) request.getAttribute(
                            OperatorLog.LOG_CONTENT_ATTRIBUTE_NAME);
                    String ip = IpUtil.getIpAddr(request);
                    request.removeAttribute(
                            OperatorLog.LOG_CONTENT_ATTRIBUTE_NAME);
                    StringBuffer parameter = new StringBuffer();
                    Map<String, String[]> parameterMap = request
                            .getParameterMap();
                    if (parameterMap != null)
                    {
                        for (Entry<String, String[]> entry : parameterMap
                                .entrySet())
                        {
                            String parameterName = entry.getKey();
                            if (!ArrayUtils.contains(DEFAULT_IGNORE_PARAMETERS,
                                    parameterName))
                            {
                                String[] parameterValues = entry.getValue();
                                if (parameterValues != null)
                                {
                                    for (String parameterValue : parameterValues)
                                    {
                                        parameter.append(parameterName).append(" = ").append(parameterValue).append("\n");
                                    }
                                }
                            }
                        }
                    }
                    OperatorLog log = new OperatorLog();
                    log.setOperation(operation);
                    log.setOperator(operator);
                    log.setContent(content);
                    log.setParameter(parameter.toString());
                    log.setIp(ip);
                    operatorLogService.save(log);
                    break;
                }
            }
        }
    }

}

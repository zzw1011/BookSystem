package com.zzw.base.filter;

import com.zzw.base.utils.HttpRequestWrapper;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * XSSFilter
 */
public class XSSFilter implements Filter
{

    /**
     * XSS处理Map
     */
    private static Map<String, String> xssMap = new LinkedHashMap<>();

    /**
     * 初始化xss处理MAP
     * @param filterConfig 参数
     * @throws ServletException 抛出异常
     */
    public void init(final FilterConfig filterConfig) throws ServletException
    {
        // 含有脚本： script
        xssMap.put("[s|S][c|C][r|R][i|C][p|P][t|T]", "");
        // 含有脚本 javascript
        xssMap.put(
                "[\\\"\\\'][\\s]*[j|J][a|A][v|V][a|A][s|S][c|C][r|R][i|I][p|P][t|T]:(.*)[\\\"\\\']",
                "\"\"");
        // 含有函数： eval
        xssMap.put("[e|E][v|V][a|A][l|L]\\((.*)\\)", "");
        // 含有符号 <
        xssMap.put("<", "&lt;");
        // 含有符号 >
        xssMap.put(">", "&gt;");
        // 含有符号 (
        xssMap.put("\\(", "(");
        // 含有符号 )
        xssMap.put("\\)", ")");
        // 含有符号 '
        xssMap.put("'", "'");
        // 含有符号 "
        xssMap.put("\"", "'");
    }

    /**
     * 执行过滤
     * @param request 请求
     * @param response 响应
     * @param chain 参数
     * @throws IOException 异常
     * @throws ServletException 异常
     */
    public void doFilter(final ServletRequest request, final ServletResponse response,
                         final FilterChain chain) throws IOException, ServletException
    {
        // 强制类型转换 HttpServletRequest
        HttpServletRequest httpReq = (HttpServletRequest) request;
        // 构造HttpRequestWrapper对象处理XSS
        HttpRequestWrapper httpReqWarp = new HttpRequestWrapper(httpReq,
                xssMap);
        //
        chain.doFilter(httpReqWarp, response);

    }

    /**
     * 结束方法
     */
    public void destroy()
    {
    }
}

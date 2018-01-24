package com.zzw.base.utils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Map;
import java.util.Set;

/**
 * HttpRequestWrapper
 */
public final class HttpRequestWrapper extends HttpServletRequestWrapper
{

    /**
     * xssMap
     */
    private Map<String, String> xssMap;

    /**
     *
     * @param request 参数
     */
    public HttpRequestWrapper(final HttpServletRequest request)
    {
        super(request);
    }

    /**
     *
     * @param request 参数
     * @param xssMap 参数
     */
    public HttpRequestWrapper(final HttpServletRequest request,
                              final Map<String, String> xssMap)
    {
        super(request);
        this.xssMap = xssMap;
    }

    /**
     *
     * @param parameter 参数
     * @return 结果
     */
    @Override
    public String[] getParameterValues(final String parameter)
    {
        String[] values = super.getParameterValues(parameter);
        if (values == null)
        {
            return null;
        }
        int count = values.length;
        // 遍历每一个参数，检查是否含有
        String[] encodedValues = new String[count];
        for (int i = 0; i < count; i++)
        {
            encodedValues[i] = cleanXSS(values[i]);
        }
        return encodedValues;
    }

    /**
     *
     * @param parameter 参数
     * @return 结果
     */
    @Override
    public String getParameter(final String parameter)
    {
        String value = super.getParameter(parameter);
        if (value == null)
        {
            return null;
        }
        return cleanXSS(value);
    }

    /**
     *
     * @param name 参数
     * @return 结果
     */
    public String getHeader(final String name)
    {
        String value = super.getHeader(name);
        if (value == null)
        {
            return null;
        }

        return cleanXSS(value);

    }

    /**
     * 清除恶意的XSS脚本
     *
     * @param value 参数
     * @return 结果
     */
    private String cleanXSS(final String value)
    {
        String result = value;
        Set<String> keySet = xssMap.keySet();
        for (String key : keySet)
        {
            String v = xssMap.get(key);
            result = result.replaceAll(key, v);
        }
        return value;
    }
}

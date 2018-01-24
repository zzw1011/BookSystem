package com.zzw.base.shiro;

import org.apache.shiro.web.filter.PathMatchingFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * MyPathMatchingFilter
 * @author Administrator
 */
public class MyPathMatchingFilter extends PathMatchingFilter
{
    /**
     *
     * @param request 参数
     * @param response 参数
     * @param mappedValue 参数
     * @return 结果
     * @throws Exception 异常
     */
    @Override
    protected boolean onPreHandle(final ServletRequest request,
                                  final ServletResponse response, final Object mappedValue) throws Exception
    {
        return super.onPreHandle(request, response, mappedValue);
    }
}

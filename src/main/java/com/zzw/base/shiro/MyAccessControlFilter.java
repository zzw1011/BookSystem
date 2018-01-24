package com.zzw.base.shiro;

import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.AccessControlFilter;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * @author Administrator
 */
public class MyAccessControlFilter extends AccessControlFilter
{
    /**
     * 该过滤器用于权限的过滤
     * @param request 参数
     * @param response 参数
     * @param mappedValue 参数
     * @return 结果
     * @throws Exception 异常
     */
    @Override
    protected boolean isAccessAllowed(final ServletRequest request,
                                      final ServletResponse response, final Object mappedValue) throws Exception
    {
        Subject subject = this.getSubject(request, response);
        String path = getPathWithinApplication(request);

        // 判断权限路径是否相同
        return subject.isPermitted(path);
    }

    /**
     *
     * @param request 参数
     * @param response 参数
     * @return 结果
     * @throws Exception 异常
     */
    @Override
    protected boolean onAccessDenied(final ServletRequest request,
                                     final ServletResponse response) throws Exception
    {
        return false;
    }

}

package com.zzw.base.utils;

import javax.servlet.http.HttpServletRequest;

/**
 * IP工具类
 * @author Mico
 */
public final class IpUtil
{
    private IpUtil()
    {
    }

    /**
     * 获取登录用户IP地址
     * @param request
     *            参数
     * @return 结果
     */
    public static String getIpAddr(final HttpServletRequest request)
    {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip))
        {
            ip = request.getRemoteAddr();
        }
        if (ip.indexOf("0:") != -1)
        {
            ip = "127.0.0.1";
        }
        return ip;
    }

}

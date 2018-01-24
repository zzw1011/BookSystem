package com.zzw.base.utils;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @描述 MD5
 * @author Administrator
 */
public final class EncryptUtils
{
    private EncryptUtils()
    {
    }

    /**
     *
     * @param source 参数
     * @return 结果
     */
    public static  String encryptMD5(final String source)
    {
        if (source == null)
        {
            return new Md5Hash("").toString();
        } else
        {
            return new Md5Hash(source).toString();
        }
    }
}

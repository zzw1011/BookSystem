/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.zzw.base.template;

import org.apache.commons.beanutils.converters.AbstractConverter;

/**
 * 枚举类型转换
 * @author JIUJIANG Team
 * @version 3.0
 */
public class EnumConverter extends AbstractConverter
{

    /** 枚举类型 */
    private final Class<?> enumClass;

    /**
     * @param enumClass
     *            枚举类型
     */
    public EnumConverter(final Class<?> enumClass)
    {
        this(enumClass, null);
    }

    /**
     * @param enumClass
     *            枚举类型
     * @param defaultValue
     *            默认值
     */
    public EnumConverter(final Class<?> enumClass, final Object defaultValue)
    {
        super(defaultValue);
        this.enumClass = enumClass;
    }

    /**
     * 获取默认类型
     * @return 默认类型
     */
    @Override
    protected Class<?> getDefaultType()
    {
        return this.enumClass;
    }

    /**
     * 转换为枚举对象
     * @param type
     *            类型
     * @param value
     *            值
     * @return 枚举对象
     */
    @SuppressWarnings(
    { "unchecked" })
    protected Object convertToType(@SuppressWarnings("rawtypes")final  Class type,
                                   final Object value)
    {
        String stringValue = value.toString().trim();
        return Enum.valueOf(type, stringValue);
    }

    /**
     * 转换为字符串
     * @param value 值
     * @return 字符串
     */
    protected String convertToString(final Object value)
    {
        return value.toString();
    }

}

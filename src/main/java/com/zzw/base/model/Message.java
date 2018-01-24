/*
 * Copyright 2005-2013 shopxx.net. All rights reserved.
 * Support: http://www.shopxx.net
 * License: http://www.shopxx.net/license
 */
package com.zzw.base.model;

/**
 * 消息
 * @author JIUJIANG Team
 * @version 3.0
 */
public class Message
{

    /**
     * 类型
     */
    public enum Type
    {

        /** 成功 */
        success,

        /** 警告 */
        warn,

        /** 错误 */
        error
    }

    /** 类型 */
    private Type type;

    /** 内容 */
    private String content;
    /**
     * object
     */
    private Object object;

    /**
     * getObject
     * @return object
     */
    public Object getObject()
    {
        return object;
    }

    /**
     * setObject
     * @param object 参数
     */
    public void setObject(final Object object)
    {
        this.object = object;
    }

    /**
     * 初始化一个新创建的 Message 对象，使其表示一个空消息。
     */
    public Message()
    {

    }

    /**
     * 初始化一个新创建的 Message 对象
     * @param type 类型
     * @param content 内容
     */
    public Message(final Type type, final String content)
    {
        this.type = type;
        this.content = content;
    }

    /**
     * @param type 类型
     * @param content 内容
     * @param args 参数
     */
    public Message(final Type type, final String content, final Object... args)
    {
        this.type = type;
        this.content = content;
        this.object = args;
    }

    /**
     * 返回成功消息
     * @param content 内容
     * @param args 参数
     * @return 成功消息
     */
    public static Message success(final String content, final Object... args)
    {
        return new Message(Type.success, content, args);
    }

    /**
     * 返回警告消息
     * @param content 内容
     * @param args 参数
     * @return 警告消息
     */
    public static Message warn(final String content, final Object... args)
    {
        return new Message(Type.warn, content, args);
    }

    /**
     * 返回错误消息
     * @param content 内容
     * @param args 参数
     * @return 错误消息
     */
    public static Message error(final String content, final Object... args)
    {
        return new Message(Type.error, content, args);
    }

    /**
     * 获取类型
     * @return 类型
     */
    public Type getType()
    {
        return type;
    }

    /**
     * 设置类型
     * @param type 类型
     */
    public void setType(final Type type)
    {
        this.type = type;
    }

    /**
     * 获取内容
     * @return 内容
     */
    public String getContent()
    {
        return content;
    }

    /**
     * 设置内容
     * @param content 内容
     */
    public void setContent(final String content)
    {
        this.content = content;
    }

    /**
     * toString
     * @return 参数
     */
    @Override
    public String toString()
    {
        return content;
    }

}

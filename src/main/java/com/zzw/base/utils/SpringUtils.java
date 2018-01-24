package com.zzw.base.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Utils - Spring
 * @author JIUJIANG Team
 * @version 3.0
 */
@Component("springUtils")
@Lazy(false)
public final class SpringUtils
        implements ApplicationContextAware, DisposableBean
{
    /**
     * 日志
     */
    private static final Log LOG = LogFactory.getLog(SpringUtils.class);

    /** applicationContext */
    private static ApplicationContext applicationContext;

    /**
     * 不可实例化
     */
    private SpringUtils()
    {
    }

    /**
     * applicationContext
     * @param applicationContext 参数
     */
    public void setApplicationContext(final ApplicationContext applicationContext)
    {
        SpringUtils.applicationContext = applicationContext;
    }

    /**
     *
     * @throws Exception 异常
     */
    public void destroy() throws Exception
    {
        applicationContext = null;
    }

    /**
     * 获取applicationContext
     * @return applicationContext
     */
    public static ApplicationContext getApplicationContext()
    {
        return applicationContext;
    }

    /**
     * 获取实例
     * @param name
     *            Bean名称
     * @return 实例
     */
    public static Object getBean(final String name)
    {
        Assert.hasText(name);
        return applicationContext.getBean(name);
    }

    /**
     * 获取实例
     * @param name
     *            Bean名称
     * @param type
     *            Bean类型
     * @param <T>  Bean类型
     * @return 实例
     */
    public static <T> T getBean(final String name, final Class<T> type)
    {
        Assert.hasText(name);
        Assert.notNull(type);
        return applicationContext.getBean(name, type);
    }



    /**
     * 根据名称获取Properties
     * @param name 参数
     * @return 结果
     */
    public static Properties getProperties(final String name) {
        //"SystemConfig.properties"
        InputStream inputStream = applicationContext.getClass().getClassLoader().getResourceAsStream(name);
        Properties p = new Properties();
        try {
            p.load(inputStream);
        } catch (IOException e) {
            LOG.error("",e);
        }
        if (inputStream != null) {
            try {
                inputStream.close();
            } catch (IOException e) {
                LOG.error("",e);
            }
        }
        return p;
    }


}

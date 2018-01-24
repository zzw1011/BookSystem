package com.zzw.base.utils;

import com.zzw.base.entity.SystemParameterEntity;
import com.zzw.base.service.SystemParameterService;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 *
 * Created by Administrator on 2016/7/21.
 */
public final class SysParamUtils
{
    /**
     * LOGGER
     */
    private static final Log LOGGER = LogFactory.getLog(SysParamUtils.class);

    private SysParamUtils()
    {
    }

    /**
     * service
     */
    private static SystemParameterService service = SpringUtils.getBean(
            "systemParameterServiceImpl", SystemParameterService.class);

    /**
     * SystemParameterEntity
     * @param key 参数
     * @return 结果
     */
    public static SystemParameterEntity getObj(final String key)
    {
        return service.selectFromCache(key);
    }

    /**
     * 清除缓存
     */
    public static void clearCache()
    {
        service.clearCache();
    }

    /**
     * 获取Double
     * @param key key
     * @return Double
     */
    public static Double getDouble(final String key)
    {
        SystemParameterEntity entity = SysParamUtils.getObj(key);

        if (entity != null)
        {
            return Double.valueOf(entity.getParameterValue());
        }

        return Double.NaN;
    }

    /**
     * 获取字符串
     * @param key 参数
     * @return 结果
     */
    public static String getString(final String key)
    {
        SystemParameterEntity entity = SysParamUtils.getObj(key);

        if (entity != null)
        {
            return entity.getParameterValue();
        }

        return null;
    }

    /**
     *
     * @param key 参数
     * @return 结果
     */
    public static Long getLong(final String key)
    {
        SystemParameterEntity entity = SysParamUtils.getObj(key);

        if (entity != null)
        {
            try
            {
                return Long.parseLong(entity.getParameterValue());
            } catch (Exception e)
            {
                LOGGER.error("",e);
            }
        }

        return null;
    }

    /**
     *
     * @param key 参数
     * @return 结果
     */
    public static Integer getInt(final String key)
    {
        SystemParameterEntity entity = SysParamUtils.getObj(key);

        if (entity != null)
        {
            try
            {
                return Integer.parseInt(entity.getParameterValue());
            } catch (Exception e)
            {
                LOGGER.error("",e);
            }
        }

        return null;
    }

    /**
     *
     * @param key 参数
     * @return 结果
     */
    public static Boolean getBoolean(final String key)
    {
        SystemParameterEntity entity = SysParamUtils.getObj(key);

        if (entity != null)
        {
            try
            {
                return Boolean.valueOf(entity.getParameterValue());
            } catch (Exception e)
            {
                LOGGER.error("",e);
            }
        }

        return Boolean.FALSE;
    }

    /**
     *
     * @return 结果
     */
    public static String getFilePreUrl()
    {
        return SysParamUtils.getString("filesUrlpre");
    }
}

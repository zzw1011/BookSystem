/**
 * @Title: SimpleDataFormatUtil
 * @author wanchao-南昌泽诺信息科技有限公司
 * @date 2017/5/24 8:42
 */
package com.zzw.base.utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author wanchao-南昌泽诺信息科技有限公司
 * @ClassName: SimpleDataFormatUtil
 * @date 2017/5/24 8:42
 */
public final class SimpleDataFormatUtil
{
    /**
     * LOG
     */
    private static final Log LOG = LogFactory.getLog(SimpleDataFormatUtil.class);

    private SimpleDataFormatUtil()
    {
    }

    /** 锁对象 */
    private static final Object LOCK_OBJ = new Object();

    /** 存放不同的日期模板格式的sdf的Map */
    private static Map<String, ThreadLocal<SimpleDateFormat>> sdfMap = new HashMap<>();

    /**
     * 返回一个ThreadLocal的sdf,每个线程只会new一次sdf
     * @param pattern
     *            pattern
     * @return SimpleDateFormat
     */
    private static SimpleDateFormat getSdf(final String pattern)
    {
        ThreadLocal<SimpleDateFormat> tl = sdfMap.get(pattern);

        // 此处的双重判断和同步是为了防止sdfMap这个单例被多次put重复的sdf
        if (tl == null)
        {
            synchronized (LOCK_OBJ)
            {
                tl = sdfMap.get(pattern);
                if (tl == null)
                {
                    // 只有Map中还没有这个pattern的sdf才会生成新的sdf并放入map
                    // 这里是关键,使用ThreadLocal<SimpleDateFormat>替代原来直接new
                    // SimpleDateFormat
                    tl = new ThreadLocal<SimpleDateFormat>()
                    {

                        @Override
                        protected SimpleDateFormat initialValue()
                        {
                            return new SimpleDateFormat(pattern);
                        }
                    };
                    sdfMap.put(pattern, tl);
                }
            }
        }

        return tl.get();
    }

    /**
     * 是用ThreadLocal<SimpleDateFormat>来获取SimpleDateFormat,这样每个线程只会有一个SimpleDateFormat
     * @param date
     *            date
     * @param pattern
     *            pattern
     * @return String
     */
    public static String format(Date date, String pattern)
    {
        return getSdf(pattern).format(date);
    }

    /**
     * @param dateStr
     *            dateStr
     * @param pattern
     *            pattern
     * @return Date
     */
    public static Date parse(String dateStr, String pattern)
    {
        Date result = null;
        try
        {
            result = getSdf(pattern).parse(dateStr);
        } catch (Exception e)
        {
            LOG.error("",e);
        }

        return result;
    }

    /**
     * @param dateStr
     *            dateStr
     * @param pattern
     *            pattern
     * @return Date
     */
    public static String formatDateString(String dateStr, String pattern)
    {
        String result = null;
        try
        {
            result =getSdf(pattern).format(getSdf(pattern).parse(dateStr));
        } catch (Exception e)
        {
            LOG.error(e);
        }

        return result;
    }
}

package com.zzw.base.utils;

import net.sf.json.JsonConfig;
import net.sf.json.util.CycleDetectionStrategy;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * JsonUtil
 */
public final class JsonUtil
{
    /**
     * jsonConfig
     */
    private static JsonConfig jsonConfig = new JsonConfig();
    /**
     * 日志
     */
    private static final Log LOG = LogFactory.getLog(JsonUtil.class);

    /**
     * 构造函数
     */
    private JsonUtil()
    {
    }

    /**
     * writeJson
     * @param response
     *            结果
     * @param obj
     *            结果
     */
    public static void writeJson(final HttpServletResponse response,
            final Object obj)
    {
        response.setContentType("text/html;charset=utf-8");
        try (PrintWriter writer = response.getWriter())
        {
            writer.print(obj);
            writer.flush();
        } catch (IOException e)
        {
            LOG.error("",e);
        }
    }

    /**
     * @param response
     * @param obj
     */
    public static void writeErrorJson(final HttpServletResponse response,
            final Object obj)
    {
        //response.setStatus(500);
        writeJson(response, obj);
    }

    /**
     * @return JsonConfig
     */
    public static JsonConfig getJsonConfig()
    {
        jsonConfig.setCycleDetectionStrategy(CycleDetectionStrategy.LENIENT);
        return jsonConfig;
    }
}

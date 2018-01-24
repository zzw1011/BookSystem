package com.zzw.base.utils;

import com.zzw.base.template.EnumConverter;
import freemarker.core.Environment;
import freemarker.template.*;
import freemarker.template.utility.DeepUnwrap;
import org.apache.commons.beanutils.ConvertUtilsBean;
import org.apache.commons.beanutils.Converter;
import org.apache.commons.beanutils.converters.ArrayConverter;
import org.apache.commons.beanutils.converters.DateConverter;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.util.Assert;
import org.springframework.web.servlet.view.freemarker.FreeMarkerConfigurer;

import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.Date;
import java.util.Map;
import java.util.Map.Entry;

/**
 * Utils - Freemarker
 */
@SuppressWarnings("unchecked")
public final class FreemarkerUtils
{
    /**
     * 日志
     */
    private static final Log LOG = LogFactory.getLog(FreemarkerUtils.class);
    /** ConvertUtilsBean */
    private static final ConvertUtilsBean CONVERT_UTILS;

    static
    {
        CONVERT_UTILS = new ConvertUtilsBean()
        {
            @Override
            public String convert(final Object value)
            {
                if (value != null)
                {
                    Class<?> type = value.getClass();
                    if (type.isEnum() && super.lookup(type) == null)
                    {
                        super.register(new EnumConverter(type), type);
                    } else if (type.isArray()
                            && type.getComponentType().isEnum())
                    {
                        if (super.lookup(type) == null)
                        {
                            ArrayConverter arrayConverter = new ArrayConverter(
                                    type,
                                    new EnumConverter(type.getComponentType()),
                                    0);
                            arrayConverter.setOnlyFirstToString(false);
                            super.register(arrayConverter, type);
                        }
                        Converter converter = super.lookup(type);
                        return ((String) converter.convert(String.class,
                                value));
                    }
                }
                return super.convert(value);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(final String value, final Class clazz)
            {
                if (clazz.isEnum() && super.lookup(clazz) == null)
                {
                    super.register(new EnumConverter(clazz), clazz);
                }
                return super.convert(value, clazz);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(final String[] values, final Class clazz)
            {
                if (clazz.isArray() && clazz.getComponentType().isEnum()
                        && super.lookup(clazz.getComponentType()) == null)
                {
                    super.register(new EnumConverter(clazz.getComponentType()),
                            clazz.getComponentType());
                }
                return super.convert(values, clazz);
            }

            @SuppressWarnings("rawtypes")
            @Override
            public Object convert(final Object value, final Class targetType)
            {
                if (super.lookup(targetType) == null)
                {
                    if (targetType.isEnum())
                    {
                        super.register(new EnumConverter(targetType),
                                targetType);
                    } else if (targetType.isArray()
                            && targetType.getComponentType().isEnum())
                    {
                        ArrayConverter arrayConverter = new ArrayConverter(
                                targetType, new EnumConverter(
                                        targetType.getComponentType()),
                                0);
                        arrayConverter.setOnlyFirstToString(false);
                        super.register(arrayConverter, targetType);
                    }
                }
                return super.convert(value, targetType);
            }
        };

        DateConverter dateConverter = new DateConverter();
        dateConverter.setPatterns(CommonAttributes.DATE_PATTERNS);
        CONVERT_UTILS.register(dateConverter, Date.class);
    }

    /**
     * 不可实例化
     */
    private FreemarkerUtils()
    {
    }

    /**
     * 解析字符串模板
     * @param template
     *            字符串模板
     * @param model
     *            数据
     * @return 解析后内容
     * @throws IOException
     *             异常
     * @throws TemplateException
     *             异常
     */
    public static String process(final String template,
            final Map<String, ?> model) throws IOException, TemplateException
    {
        Configuration configuration = null;
        ApplicationContext applicationContext = SpringUtils
                .getApplicationContext();
        if (applicationContext != null)
        {
            FreeMarkerConfigurer freeMarkerConfigurer = SpringUtils
                    .getBean("freemarkerConfig", FreeMarkerConfigurer.class);
            if (freeMarkerConfigurer != null)
            {
                configuration = freeMarkerConfigurer.getConfiguration();
            }
        }
        return process(template, model, configuration);
    }

    /**
     * 解析字符串模板
     * @param template
     *            字符串模板
     * @param model
     *            数据
     * @param configuration
     *            配置
     * @return 解析后内容
     * @throws IOException
     *             异常
     * @throws TemplateException
     *             异常
     */
    @SuppressWarnings("deprecation")
    public static String process(final String template,
            final Map<String, ?> model, final Configuration configuration)
            throws IOException, TemplateException
    {
        if (template == null)
        {
            return null;
        }

        StringWriter out = new StringWriter();

        if (configuration == null)
        {
            new Template("template", new StringReader(template),
                    new Configuration()).process(model, out);
        } else
        {
            new Template("template", new StringReader(template), configuration)
                    .process(model, out);
        }
        return out.toString();
    }

    /**
     * @param filePath
     *            参数
     * @param model
     *            参数
     * @return 结果
     */
    public static String processFile(final String filePath,
            final Map<String, ?> model)
    {
        if (filePath == null)
        {
            return null;
        }
        Configuration configuration = null;
        ApplicationContext applicationContext = SpringUtils
                .getApplicationContext();
        if (applicationContext != null)
        {
            FreeMarkerConfigurer freeMarkerConfigurer = SpringUtils
                    .getBean("freemarkerConfig", FreeMarkerConfigurer.class);
            if (freeMarkerConfigurer != null)
            {
                configuration = freeMarkerConfigurer.getConfiguration();
            }
        }

        StringWriter out = new StringWriter();

        try
        {
            if (configuration != null)
            {
                Template template = configuration.getTemplate(filePath);

                template.process(model, out);
            }
        } catch (IOException | TemplateException e)
        {
            LOG.error("",e);
        }

        return out.toString();
    }

    /**
     * 获取参数
     * @param name
     *            名称
     * @param type
     *            类型
     * @param params
     *            参数
     * @param <T>
     *            泛型
     * @return 参数,若不存在则返回null
     * @throws TemplateModelException
     *             异常
     */
    public static <T> T getParameter(final String name, final Class<T> type,
            final Map<String, TemplateModel> params)
            throws TemplateModelException
    {
        Assert.hasText(name);
        Assert.notNull(type);
        Assert.notNull(params);
        TemplateModel templateModel = params.get(name);
        if (templateModel == null)
        {
            return null;
        }
        Object value = DeepUnwrap.unwrap(templateModel);
        return (T) CONVERT_UTILS.convert(value, type);
    }

    /**
     * 获取变量
     * @param name
     *            名称
     * @param env
     *            Environment
     * @return 变量
     * @throws TemplateModelException
     *             异常
     */
    public static TemplateModel getVariable(final String name,
            final Environment env) throws TemplateModelException
    {
        Assert.hasText(name);
        Assert.notNull(env);
        return env.getVariable(name);
    }

    /**
     * 设置变量
     * @param name
     *            名称
     * @param value
     *            变量值
     * @param env
     *            Environment
     * @throws TemplateException
     *             异常
     */
    @SuppressWarnings("deprecation")
    public static void setVariable(final String name, final Object value,
            final Environment env) throws TemplateException
    {
        Assert.hasText(name);
        Assert.notNull(env);
        if (value instanceof TemplateModel)
        {
            env.setVariable(name, (TemplateModel) value);
        } else
        {
            env.setVariable(name, ObjectWrapper.BEANS_WRAPPER.wrap(value));
        }
    }

    /**
     * 设置变量
     * @param variables
     *            变量
     * @param env
     *            Environment
     * @throws TemplateException
     *             异常
     */
    @SuppressWarnings("deprecation")
    public static void setVariables(final Map<String, Object> variables,
            final Environment env) throws TemplateException
    {
        Assert.notNull(variables);
        Assert.notNull(env);
        for (Entry<String, Object> entry : variables.entrySet())
        {
            String name = entry.getKey();
            Object value = entry.getValue();
            if (value instanceof TemplateModel)
            {
                env.setVariable(name, (TemplateModel) value);
            } else
            {
                env.setVariable(name, ObjectWrapper.BEANS_WRAPPER.wrap(value));
            }
        }
    }

}

package com.zzw.base.template;

import com.zzw.base.model.Message;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestAttributes;
import org.springframework.web.context.request.RequestContextHolder;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 * FlashMessageDirective
 */
@Component("flashMessageDirective")
public class FlashMessageDirective implements TemplateDirectiveModel
{

    /** "瞬时消息"属性名称 */
    public static final String FLASH_MESSAGE_ATTRIBUTE_NAME = FlashMessageDirective.class
            .getName() + ".FLASH_MESSAGE";

    /** 变量名称 */
    private static final String VARIABLE_NAME = "flashMessage";

    /**
     *
     * @param env 参数
     * @param params 参数
     * @param loopVars 参数
     * @param body 参数
     * @throws TemplateException 异常
     * @throws IOException 异常
     */
    @SuppressWarnings("rawtypes")
    public void execute(final Environment env, final Map params, final TemplateModel[] loopVars,
                        final TemplateDirectiveBody body) throws TemplateException, IOException
    {
        RequestAttributes requestAttributes = RequestContextHolder
                .currentRequestAttributes();
        if (requestAttributes != null)
        {
            Message message = (Message) requestAttributes.getAttribute(
                    FLASH_MESSAGE_ATTRIBUTE_NAME,
                    RequestAttributes.SCOPE_REQUEST);
            if (message != null)
            {
                Writer out = env.getOut();
                // out.write("$.messager.alert(\"" + message.getType() + "\",
                // \"" + message.getContent() + "\");");
                out.write("$.messager.alert(\"" + message.getType() + "\", \""
                        + message.getContent() + "\");");
            }
        }
    }

}

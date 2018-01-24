package com.zzw.base.template;

import com.zzw.base.service.CategoryService;
import com.zzw.base.service.UserService;
import com.zzw.base.utils.FreemarkerUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.Map;

/**
 *
 * Created by Administrator on 2016/11/14.
 */
@Component("panelDirective")
public class PanelDirective implements TemplateDirectiveModel
{
    /**
     * categoryService
     */
    @Autowired
    private CategoryService categoryService;
    /**
     * userService
     */
    @Autowired
    private UserService userService;

    /**
     * execute
     * @param environment 参数
     * @param map 参数
     * @param templateModels 参数
     * @param templateDirectiveBody 参数
     * @throws TemplateException 参数
     * @throws IOException 参数
     */
    @Override
    public void execute(final Environment environment, final Map map,
            final TemplateModel[] templateModels,
            final TemplateDirectiveBody templateDirectiveBody)
            throws TemplateException, IOException
    {

        map.put("categories", categoryService.getAllNestedCategorys(1L));
        map.put("permissionList", userService.getPermission());
        map.put("parent", categoryService
                .findCategory(Long.parseLong(map.get("parentId").toString())));
        map.put("current", categoryService
                .findCategory(Long.parseLong(map.get("currentId").toString())));

        String navigationHtml = FreemarkerUtils
                .processFile("/adminnew/include/panel.ftl", map);

        if (navigationHtml != null)
        {
            Writer out = environment.getOut();
            out.write(navigationHtml);
        }
    }
}

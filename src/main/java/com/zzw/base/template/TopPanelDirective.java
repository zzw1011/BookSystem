package com.zzw.base.template;

import com.zzw.base.entity.UserEntity;
import com.zzw.base.model.RoleModelEntity;
import com.zzw.base.service.CategoryService;
import com.zzw.base.service.RoleService;
import com.zzw.base.service.UserService;
import com.zzw.base.utils.FreemarkerUtils;
import com.zzw.base.utils.SysParamUtils;
import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.Writer;
import java.util.List;
import java.util.Map;

/**
 *
 * Created by Administrator on 2016/11/14.
 */
@Component("topPanelDirective")
public class TopPanelDirective implements TemplateDirectiveModel
{
    /**
     * userService
     */
    @Autowired
    private UserService userService;
    /**
     * roleService
     */
    @Autowired
    private RoleService roleService;
    /**
     * categoryService
     */
    @Autowired
    private CategoryService categoryService;

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
        map.put("parent", categoryService
                .findCategory(Long.parseLong(map.get("parentId").toString())));
        map.put("current", categoryService
                .findCategory(Long.parseLong(map.get("currentId").toString())));

        UserEntity user = userService
                .selectUserByUserName(userService.getCurrentUser());
        if (null != user)
        {
            List<RoleModelEntity> roleList = roleService
                    .selectRoleByUserId(user.getId());
            user.setRoleList(roleList);
        }

        map.put("user", user);
        map.put("returnUrl", SysParamUtils.getString("pcClearCacheReturnUrl"));
        map.put("pcPath", SysParamUtils.getString("pcClearCachePath"));
        String navigationHtml = FreemarkerUtils
                .processFile("/adminnew/include/toppanel.ftl", map);

        if (navigationHtml != null)
        {
            Writer out = environment.getOut();
            out.write(navigationHtml);
        }
    }
}

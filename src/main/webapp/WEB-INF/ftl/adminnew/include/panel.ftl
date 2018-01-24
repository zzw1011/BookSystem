<div class="con_left">
    <div class="logo">
        <a href="javascript:void(0);" style="background: none;color:#fff; line-height: 100px; text-align: center; font-size: 35px; letter-spacing: 5px;">图书管理</a>
    </div>
    <ul class="left_list">
    <li>
        <a href="../common/index.do"
           class="welcome list_btn clearfix cur">
            <i class="left_icon"></i>
            <span class="center_text">欢迎</span>

        </a>
    </li>
        <#if permissionList??>
    <#list categories as category>
        <#if category.permissionCode??&&permissionList?seq_contains(category.permissionCode)>
            <li>
                <a href="<#if category.categoryUrl?? && category.categoryUrl!="">${category.categoryUrl}<#else>javascript:void(0);</#if>"
                   class="<#if category.categoryDesc?? && category.categoryDesc != ''>${category.categoryDesc!''}<#else>welcome</#if> list_btn clearfix <#if parent??&& parent.id==category.id>cur</#if>">
                    <i class="left_icon"></i>
                    <span class="center_text">${category.categoryName}</span>
                    <#if category.categoryUrl?? && category.categoryUrl!=""><#else><i class="right_icon"></i></#if>
                </a>
                <ul class="left_sub_list" <#if parent??&& parent.id!=category.id>style="display: none" </#if>>
                    <#list category.children as child>
                        <#if child.permissionCode??&&permissionList?seq_contains(child.permissionCode)>
                            <li>
                                <#if child.categoryUrl?? && child.categoryName??>
                                    <a href="${child.categoryUrl}"
                                       <#if current??&&current.id==child.id>class="cur"</#if>>${child.categoryName}</a>
                                </#if>
                            </li>
                        </#if>
                    </#list>
                </ul>
            </li>
        </#if>
    </#list>
        </#if>
    </ul>
</div>

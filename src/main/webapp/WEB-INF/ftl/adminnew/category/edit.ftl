<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<#include "../include/head.ftl">

<body>
<div class="wrap">
<@panel currentId="10101" parentId="101">
</@panel>
    <div class="con_right">
    <@topPanel currentId="10101" parentId="101">
    </@topPanel>
        <div class="right_content">
            <div class="table_box">
                <div class="table_head">
                    <i></i>
                    <span>修改子栏目</span>
                </div>
                <div class="table_con ">
                    <form id="editForm" action="update.do" method="post">
                        <input type="hidden" name="classifyId" value="${category.classifyId?c}">
                        <input type="hidden" name="pid" value="${category.pid?c}">
                        <input type="hidden" name="id" value="${category.id?c}">
                        <table class="tab_edit">
                            <tr>
                                <td class="input_name">
                                    <label>名称</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" type="text" name="categoryName"
                                           data-options="required:true" value="${category.categoryName}"
                                           validType="length[0,50]" style="width:500px;"></input>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">
                                    <label>URL</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" type="text" name="categoryUrl"
                                           value="${category.categoryUrl!''}"
                                           validType="length[0,500]" style="width:500px;"></input>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">
                                    <label>描述</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" name="categoryDesc"
                                           value="${category.categoryDesc!''}"
                                           data-options="multiline:true" style="width:500px;height:100px"></input>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">
                                    <label>相关权限</label>
                                </td>
                                <td><#if category.classifyId==2>
                                    <input class="easyui-textbox" type="text" name="permissionCode"
                                           validType="length[0,500]"
                                           style="width:200px;"
                                           <#if category.permissionCode??>value="${category.permissionCode}"</#if>></input>
                                <#else >
                                    <input type="hidden" id="permisionCode" name="permissionCode">
                                    <select id="permissionSelect" style="width:200px;"/>
                                </#if>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div class="table_bottom">
                    <a href="javascript:void(0);" class="btn save" id="saveButton">保存</a>
                    <a href="javascript:void(0);" class="btn back" id="cancelButton">返回</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $().ready(function () {
        $permissionSelect = $("#permissionSelect");
        $cancelButton = $("#cancelButton");
        $saveButton = $("#saveButton");
        $editForm = $("#editForm");
        $permisionCode = $("#permisionCode");

        $permissionSelect.combotree({
            url: 'getPermissionTreeDate.do?classifyId=${permissionClassifyId?c}',
            method: 'GET',
            onLoadSuccess: function () {
                $permissionSelect.combotree('setValues', '${category.permissionCode!''}');
            }
        });

        $cancelButton.click(function () {
            window.location.href = "tree.do?classifyId=${category.classifyId?c}";
        });

        $saveButton.click(function () {
            var classifyId = ${category.classifyId?c};
            if (classifyId != 2) {
                var permission = $permissionSelect.combotree("getValue");
                $permisionCode.val(permission);
            }
            if ($editForm.form('validate')) {
                $editForm.submit();
            }
        });
    });
</script>
</html>

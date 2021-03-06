<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<#include "../include/head.ftl">

<body>
<div class="wrap">
<@panel currentId="10201" parentId="102">
</@panel>
    <div class="con_right">
    <@topPanel currentId="10201" parentId="102">
    </@topPanel>
        <div class="right_content">
            <div class="table_box">
                <div class="table_head">
                    <i></i>
                    <span>新增角色</span>
                </div>
                <div class="table_con ">
                    <form id="editForm" action="save.do" method="post">
                        <table class="tab_edit">
                            <tr>
                                <td class="input_name">
                                    <label>角色名称</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" type="text" name="roleName"
                                           data-options="required:true"
                                           validType="length[0,20]"></input>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">
                                    <label>备注</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" type="text" name="roleRemark"
                                           data-options="required:true"
                                           validType="length[0,50]"></input>
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

        var $saveButton = $("#saveButton");
        var $editForm = $("#editForm");
        var $cancelButton = $("#cancelButton");

        $saveButton.click(function () {
            if ($editForm.form('validate')) {
                $editForm.submit();
            }
        });

        $cancelButton.click(function () {
            window.location.href = "list.do";
        });

    });
</script>
</html>

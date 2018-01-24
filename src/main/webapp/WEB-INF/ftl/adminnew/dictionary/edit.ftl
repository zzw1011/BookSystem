<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<#include "../include/head.ftl">

<body>
<div class="wrap">
<@panel currentId="10105" parentId="101">
</@panel>
    <div class="con_right">
    <@topPanel currentId="10105" parentId="101">
    </@topPanel>
        <div class="right_content">
            <div class="table_box">
                <div class="table_head">
                    <i></i>
                    <span>修改数据字典</span>
                </div>
                <div class="table_con ">
                            <form id="editForm" action="update.do" method="post">
                                <input type="hidden" value="${dictionary.id?c}" name="id">
                                <table class="tab_edit">
                                    <tr>
                                        <td class="input_name">KEY</td>
                                        <td >
                                            <input class="easyui-textbox" type="text" name="dictionaryKey"
                                                   <#if dictionary.dictionaryKey??>value="${dictionary.dictionaryKey}"</#if>
                                                   data-options="required:true" style="width:500px;"
                                                   validType="length[0,50]"></input>
                                        </td>
                                    </tr>
                                    <tr>
                                        <td class="input_name">描述</td>
                                        <td >
                                            <input class="easyui-textbox" type="text" name="bewrite"
                                                   <#if dictionary.bewrite??>value="${dictionary.bewrite}"</#if>
                                                   data-options="multiline:true"
                                                   validType="length[0,500]" style="width:500px;height:100px"></input>
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

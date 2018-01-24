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
                    <span>修改字典</span>
                </div>
                <div class="table_con ">
                    <form id="editForm" action="update.do" method="post">
                        <input type="hidden" value="${dictionaryValue.id?c}" name="id">
                        <input type="hidden" value="${dictionaryValue.dictionaryId?c}" name="dictionaryId">
                        <table class="tab_edit">
                            <tr>
                                <td class="input_name">字典值</td>
                                <td >
                                    <input class="easyui-textbox" type="text" name="dictionaryValue"
                                           data-options="required:true"
                                       <#if dictionaryValue.dictionaryValue??>value="${dictionaryValue.dictionaryValue}"</#if>
                                           validType="length[0,50]" style="width:500px;"></input>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">备注</td>
                                <td >
                                    <input class="easyui-textbox" type="text"
                                           name="dicBewrite"
                                           style="width:500px;"<#if dictionaryValue.dicBewrite??>value="${dictionaryValue.dicBewrite}"</#if>></input>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">扩展字段</td>
                                <td >
                                    <input class="easyui-numberbox" type="text"
                                           name="dicTxt"
                                           <#if dictionaryValue.dicTxt??>value="${dictionaryValue.dicTxt}"</#if>  validType="number[5]" style="width:500px;"></input>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">排序</td>
                                <td >
                                    <input class="easyui-textbox" type="text" data-options="required:true"
                                           name="dicOrder"
                                           <#if dictionaryValue.dicOrder??>value="${dictionaryValue.dicOrder}"</#if>
                                           validType="number[3]" style="width:500px;"></input>
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
        $.extend($.fn.validatebox.defaults.rules, {
            number: {
                validator: function (value, param) {
                    var z = /^[0-9]*$/;

                    if (z.test(value) && value.length <= param[0]) {
                        return true;
                    }
                    else {
                        return false
                    }
                },
                message: '请输入长度不超过{0}的数字.'
            }
        });

        var $saveButton = $("#saveButton");
        var $editForm = $("#editForm");
        var $cancelButton = $("#cancelButton");

        $saveButton.click(function () {
            if ($editForm.form('validate')) {
                $editForm.submit();
            }
        });

        $cancelButton.click(function () {
            window.location.href = "list.do?dictionaryId=${dictionaryValue.dictionaryId?c}";
        });

    });
</script>
</html>

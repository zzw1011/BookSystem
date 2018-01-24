<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<#include "../include/head.ftl">

<body>
<div class="wrap">
<@panel currentId="10102" parentId="101">
</@panel>
    <div class="con_right">
    <@topPanel currentId="10102" parentId="101">
    </@topPanel>
        <div class="right_content">
            <div class="table_box">
                <div class="table_head">
                    <i></i>
                    <span>修改系统参数</span>
                </div>
                <div class="table_con ">
                    <form id="editForm" action="update.do" method="post">
                        <input type="hidden" id="id" value="${systemParameter.id?c}" name="id">
                        <table class="tab_edit">
                            <tr>
                                <td class="input_name">
                                    <label>参数主键</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" id="parameterKey" name="parameterKey"  style="width:500px;height:32px"
                                           value="${systemParameter.parameterKey}"
                                           data-options="required:true" validType="number[50]"></input>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">
                                    <label>参数值</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" name="parameterValue"  style="width:500px;height:32px"
                                           value="${systemParameter.parameterValue}"
                                           data-options="required:true" validType="length[0,500]"></input>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">
                                    <label>说明</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" style="width: 500px;"  name="instruction"
                                           value="${systemParameter.instruction}"
                                           data-options="required:true" validType="length[0,255]"></input>
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
                    var z = /[a-zA-Z0-9]*/;

                    if (z.test(value) && value.length <= param[0]) {
                        return true;
                    }
                    else {
                        return false
                    }
                },
                message: '请输入长度不超过{0}的英文和数字.'
            }
        });

        var $saveButton = $("#saveButton");
        var $editForm = $("#editForm");
        var $cancelButton = $("#cancelButton");

        $saveButton.click(function () {
            var parameterKey = $("#parameterKey").val();
            var id = $("#id").val();
            if ($editForm.form('validate')) {
                $.ajax({
                    type: "POST",
                    url: "check.do",
                    data: {parameterKey: parameterKey, id: id},
                    success: function (data) {
                        data = JSON.parse(data);
                        if (data.type == 'success') {
                            $editForm.submit();
                        }
                        else {
                            alert('参数主键已存在');
                        }

                    }
                });
            }
        });

        $cancelButton.click(function () {
            window.location.href = "list.do";
        });

    });
</script>
</html>

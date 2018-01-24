<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<#include "../include/head.ftl">

<body>
<div class="wrap">
<@panel currentId="10106" parentId="102">
</@panel>
    <div class="con_right">
    <@topPanel currentId="10106" parentId="102">
    </@topPanel>
        <div class="right_content">
            <div class="table_box">
                <div class="table_head">
                    <i></i>
                    <span>修改密码</span>
                </div>
                <div class="table_con ">
                    <form id="editForm" action="update.do" method="post">
                        <table class="tab_edit">
                            <tr>
                                <td class="input_name">
                                    <label>原密码</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" type="password" name="oldPassword" id="oldPassword"
                                           data-options="required:true" style="width:300px">
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">
                                    <label>新密码</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" type="password" name="password" id="password"
                                           data-options="required:true" validType="length[8,20]"
                                           style="width:300px;"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">
                                    <label>确认密码</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" type="password" name="confirmPassword"
                                           id="confirmPassword"
                                           data-options="required:true" validType="passwordEqual"
                                           style="width:300px;"></input>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div class="table_bottom">
                    <a href="javascript:void(0);" class="btn save" id="saveButton">保存</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $().ready(function () {
        var $password = $("#password");
        var $oldPassword = $("#oldPassword");
        var $confirmPassword = $("#confirmPassword");
        var $editForm = $("#editForm");
        var $saveButton = $("#saveButton");

        $.extend($.fn.validatebox.defaults.rules, {
            passwordEqual: {
                validator: function (value, param) {
                    var password = $password.val();

                    if (value == password) {
                        return true;
                    }
                    else {
                        return false;
                    }
                },
                message: '新密码两次输入不一致.'
            }
        });

        $saveButton.click(function () {
            if ($editForm.form('validate')) {
                $.ajax({
                    type: "POST",
                    url: "update.do",
                    data: {
                        password: $password.val(),
                        oldPassword: $oldPassword.val(),
                        confirmPassword: $confirmPassword.val()
                    },
                    success: function (data) {
                        data = JSON.parse(data);
                        if (data.type == 'success') {
                            $.messager.alert('Success', data.content);
                        }
                        else {
                            $.messager.alert('Error', data.content, 'error');
                        }
                    }
                });
            }
        });
    });
</script>
</html>

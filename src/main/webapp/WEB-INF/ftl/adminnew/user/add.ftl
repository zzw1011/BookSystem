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
                    <span>新增管理员</span>
                </div>
                <div class="table_con ">
                    <form id="editForm" action="save.do" method="post">
                        <input type="hidden" id="roleIds" name="roleIds" value="${roleIds!''}"/>
                        <table class="tab_edit">
                            <tr>
                                <td class="input_name">
                                    <label>用户名</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" type="text" id="userName" name="userName"
                                           data-options="required:true"
                                           validtype="account[6,20]" style="height:32px"></input>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">
                                    <label>密码</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" type="password" id="password"
                                           name="userPassword"
                                           data-options="required:true"
                                           validType="length[8,20]" style="height:32px"></input>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">
                                    <label>确认密码</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" type="password" name="confirmPassword"
                                           data-options="required:true"
                                           validType="passwordEqual" style="height:32px"></input>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">
                                    <label>姓名</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" type="text" name="realName"
                                           data-options="required:true"
                                           validType='length[0,50]' style="height:32px"></input>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">
                                    <label>性别</label>
                                </td>
                                <td>
                                    <input name="gender" type="radio" value="0" checked="checked"/>男
                                    <input name="gender" type="radio" value="1"/>女
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">
                                    <label>电话</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" type="text" name="phoneNumber"
                                           validType='mobile' style="height:32px"></input>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">
                                    <label>Email</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" type="text" name="email"
                                           validType='email' style="height:32px"></input>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">选择角色</td>
                                <td>
                                    <select id="roles" class="easyui-combobox" style="width:200px;">
                                    </select>
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
        var $password = $("#password");

        $("#roles").combotree({
            required: true,
            url: '${base}/admin/role/findRoleTree.do',
            valueField: 'id',
            textField: 'text',
            multiple: true,
            checkbox: true,
            method:'post'
        });

        $('#roles').combotree({
            onLoadSuccess:function () {
                $('#roles').combotree("setValues",$('#roleIds').val());
            }
        });

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
            },
            account: {//param的值为[]中值
                validator: function (value, param) {
                    if (value.length < param[0] || value.length > param[1]) {
                        $.fn.validatebox.defaults.rules.account.message = '用户名长度必须在' + param[0] + '至' + param[1] + '范围';
                        return false;
                    } else {
                        if (!/^[\w]+$/.test(value)) {
                            $.fn.validatebox.defaults.rules.account.message = '用户名只能数字、字母、下划线组成.';
                            return false;
                        } else {
                            return true;
                        }
                    }
                }, message: ''
            }


        });

        $saveButton.click(function () {
            var userName = $("#userName").val();
            if ($editForm.form('validate')) {
                var val = $('input:radio[name="gender"]:checked').val();
                if (val == null) {
                    return false;
                }
                else {
                    $.ajax({
                        type: "POST",
                        url: "check.do",
                        data: {userName: userName},
                        success: function (data) {
                            data = JSON.parse(data);
                            if (data.type == 'success') {
                                $('#roleIds').val($('#roles').combotree("getValues").join(','));
                                $editForm.submit();
                            } else {
                                alert('用户名已存在');
                            }

                        }
                    });
                }
            }
        });

        $cancelButton.click(function () {
            window.location.href = "list.do";
        });

    });
</script>
</html>

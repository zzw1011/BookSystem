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
                    <span>管理员账号</span>
                    <input class="right_btn" type="button" value="新增" id="addButton"/>
                </div>
                <div class="table_con clearfix">
                    <div class="search_box">
                        <div id="tb" style="padding:10px;">
                        <input class="easyui-textbox" type="text" id="realName" data-options="prompt:'请输入姓名'" ></input>
                        <input class="easyui-textbox" type="text" id="userName" data-options="prompt:'请输入用户名'"></input>

                        <input type="button" value="查询" id="searchButton"/>
                        </div>

                        <table class="easyui-datagrid" style="width:100%;height:auto"
                               id="listTable">
                            <thead>
                            <tr>
                                <th data-options="field:'userId',align:'center'" hidden="true">ID</th>
                                <th data-options="field:'userName',align:'center'" width="150">用户</th>
                                <th data-options="field:'roleName',align:'center',formatter:formatRoleName"  width="150">角色</th>
                                <th data-options="field:'realName',align:'center'" width="150">姓名</th>
                                <th data-options="field:'genderName',align:'center'" width="90">性别</th>
                                <th data-options="field:'phoneNumber',align:'center'" width="100">电话</th>
                                <th data-options="field:'createDate',align:'center'" width="120">创建时间</th>
                                <th data-options="field:'modifyDate',align:'center'" width="120">更新时间</th>
                                <th data-options="field:'_operate',align:'center',formatter:formatEditAndDel" width="50">操作</th>
                            </tr>
                            </thead>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script>
    function formatRoleName(val,row,index) {
        if(val){
           return val.substring(0,val.length-1)
        }
    }
    function editDatagrid(index)
    {
        $("#listTable").datagrid('selectRow',index);
        var rows = $("#listTable").datagrid('getSelections');

        if (rows.length == 0 || rows.length > 1) {
            $.messager.alert('Tips', '请选择一条记录进行修改！', 'info');
        }
        else {
            var id = rows[0].id;

            window.location.href = "edit.do?id=" + id;
        }
    }

    function deleteDategrid(index) {
        $("#listTable").datagrid('selectRow', index);
        var rows = $("#listTable").datagrid('getSelections');

        var ids = [];
        if (rows.length == 0) {
            $.messager.alert('Tips', '请选择一条记录进行操作！', 'info');
            return;
        }
        else {
            for (var i = 0; i < rows.length; i++) {
                ids.push(rows[i].id);
            }
        }

        $.messager.confirm('Confirm', '是否确认删除？', function (r) {
            if (r) {

                for (var i = 0; i < rows.length; i++) {
                    ids.push(rows[i].id);
                }

                $.ajax({
                    type: "POST",
                    url: "delete.do",
                    data: {ids: ids.join(",")},
                    success: function (data) {
                        ids = [];
                        data = JSON.parse(data);
                        if (data.type == 'success') {
                            $.messager.alert('Success', data.content);
                            $("#listTable").datagrid('reload');
                        }
                        else {
                            $.messager.alert('Error', '删除失败!', 'error');
                        }
                    }
                });
            }
        });
    }

    $().ready(function () {
        var $listTable = $("#listTable");
        var $addButton = $("#addButton");
        var $searchButton = $("#searchButton");

    <@flash_message/>

        $listTable.datagrid({
            nowrap: false,
            striped: true,
            border: true,
            collapsible: false,//是否可折叠的
            url: 'getPage.do',
            method: 'GET',
            remoteSort: false,
            idField: 'id',
            singleSelect: true,//是否单选
            pagination: true,//分页控件
            rownumbers: true,//行号
            scrollbarSize: 0,
            fitColumns: true,
            frozenColumns: [[
            ]]
        });

        $addButton.click(function () {
            window.location.href = "add.do";
        });

        $searchButton.click(function () {
            $listTable.datagrid({
                nowrap: false,
                striped: true,
                border: true,
                collapsible: false,//是否可折叠的
                url: 'search.do?realName=' + $("#realName").val() + '&userName=' + $("#userName").val(),
                method: 'GET',
                remoteSort: false,
                idField: 'id',
                singleSelect: true,//是否单选
                fitColumns: true,
                pagination: true,//分页控件
                rownumbers: true,//行号
                scrollbarSize: 0,
                frozenColumns: [[
                ]]
            });
        });
    });


</script>
</html>

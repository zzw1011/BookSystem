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
                    <span>角色列表</span>
                    <input id="addButton" class="right_btn" type="button" value="新增"/>
                </div>
                <div class="table_con clearfix">
                    <div class="search_box">
                        <div id="tb" style="padding:10px;">
                            <input class="easyui-textbox" type="text" id="roleName" style="height: 32px"
                                   data-options="prompt:'请输入角色名'">
                            <input id="searchButton" type="button" value="搜索"/>
                        </div>
                        <table class="easyui-datagrid" style="width:100%;height:auto"
                               id="listTable">
                            <thead>
                            <tr>
                                <th data-options="field:'roleId',align:'center'" hidden="true">ID</th>
                                <th data-options="field:'roleName',align:'center'" width="100">角色名称</th>
                                <th data-options="field:'roleRemark',align:'center'" width="200">备注</th>
                                <th data-options="field:'_operate',align:'center',formatter:formatAuditAndEditAndDel"
                                    width="100">操作
                                </th>
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
    function editDatagrid(index) {
        $("#listTable").datagrid('selectRow', index);

        var rows = $("#listTable").datagrid('getSelections');

        if (rows.length == 0 || rows.length > 1) {
            $.messager.alert('Tips', '请选择一条记录进行修改！', 'info');
        }
        else {
            var roleId = rows[0].roleId;

            window.location.href = "edit.do?roleId=" + roleId;
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
                ids.push(rows[i].roleId);
            }
        }

        $.messager.confirm('Confirm', '是否确认删除？', function (r) {
            if (r) {

                for (var i = 0; i < rows.length; i++) {
                    ids.push(rows[i].roleId);
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

    function auditDategrid(index) {
        $("#listTable").datagrid('selectRow', index);
        var rows = $("#listTable").datagrid('getSelections');

        if (rows.length == 0 || rows.length > 1) {
            $.messager.alert('Tips', '请选择一条记录进行修改！', 'info');
        }
        else {
            var roleId = rows[0].roleId;

            window.location.href = "../rolePermission/tree.do?roleId=" + roleId;
        }
    }

    $().ready(function () {
        var $listTable = $("#listTable");
        var $addButton = $("#addButton");
        var $deleteButton = $("#deleteButton");
        var $editButton = $("#editButton");
        var $permissionButton = $("#permissionButton");
        var $searchButton = $("#searchButton");
        var $addUserPermissionButton = $("#addUserPermissionButton");
        var $removeUserPermissionButton = $("#removeUserPermissionButton");
        var ids = [];

    <@flash_message/>

        $listTable.datagrid({
            nowrap: false,
            striped: true,
            border: true,
            collapsible: false,//是否可折叠的
            url: 'getPage.do',
            method: 'GET',
            remoteSort: false,
            idField: 'roleId',
            singleSelect: true,//是否单选
            pagination: true,//分页控件
            scrollbarSize: 0,
            fitColumns: true,
            rownumbers: true,//行号
            frozenColumns: [[]]
        });

        $addButton.click(function () {
            window.location.href = "add.do";
        });

        $deleteButton.click(function () {
            var rows = $listTable.datagrid('getSelections');

            if (rows.length == 0) {
                $.messager.alert('Tips', '请选择一条记录进行操作！', 'info');
                return;
            }

            $.messager.confirm('Confirm', '是否确认删除？', function (r) {
                if (r) {

                    for (var i = 0; i < rows.length; i++) {
                        ids.push(rows[i].roleId);
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
                                $listTable.datagrid('reload');
                            }
                            else {
                                $.messager.alert('Error', '删除失败!', 'error');
                            }
                        }
                    });
                }
            });

        });

        $editButton.click(function () {
            var rows = $listTable.datagrid('getSelections');

            if (rows.length == 0 || rows.length > 1) {
                $.messager.alert('Tips', '请选择一条记录进行修改！', 'info');
            }
            else {
                var roleId = rows[0].roleId;

                window.location.href = "edit.do?roleId=" + roleId;
            }
        });

        $permissionButton.click(function () {
            var rows = $listTable.datagrid('getSelections');

            if (rows.length == 0 || rows.length > 1) {
                $.messager.alert('Tips', '请选择一条记录进行修改！', 'info');
            }
            else {
                var roleId = rows[0].roleId;

                window.location.href = "../rolePermission/tree.do?roleId=" + roleId;
            }
        });

        $removeUserPermissionButton.click(function () {
            var rows = $listTable.datagrid('getSelections');

            if (rows.length == 0 || rows.length > 1) {
                $.messager.alert('Tips', '请选择一条记录进行修改！', 'info');
            }
            else {
                var roleId = rows[0].roleId;

                window.location.href = "../rolePermission/removeUserPermission.do?roleId=" + roleId;
            }
        });

        $addUserPermissionButton.click(function () {
            var rows = $listTable.datagrid('getSelections');

            if (rows.length == 0 || rows.length > 1) {
                $.messager.alert('Tips', '请选择一条记录进行修改！', 'info');
            }
            else {
                var roleId = rows[0].roleId;

                window.location.href = "../rolePermission/addUserPermission.do?roleId=" + roleId;
            }
        });

        $searchButton.click(function () {
            $listTable.datagrid({
                nowrap: false,
                striped: true,
                border: true,
                collapsible: false,//是否可折叠的
                url: 'search.do?roleName=' + $("#roleName").val(),
                method: 'GET',
                remoteSort: false,
                idField: 'id',
                singleSelect: true,//是否单选
                pagination: true,//分页控件
                scrollbarSize: 0,
                fitColumns: true,
                rownumbers: true,//行号
                frozenColumns: [[]]
            });
        });
    });
</script>
</html>

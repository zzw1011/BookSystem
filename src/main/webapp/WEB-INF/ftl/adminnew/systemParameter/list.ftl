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
                    <span>系统参数列表</span>
                    <input id="addButton" class="right_btn" type="button" value="新增"/>
                </div>
                <div class="table_con clearfix">
                    <div class="search_box">
                        <div id="tb" style="padding:10px;">
                            <input class="easyui-textbox" type="text" id="parameterKey"
                                   data-options="prompt:'请输入参数主键'"></input>
                            <input id="searchButton" type="button" value="搜索"/>
                        </div>
                    </div>
                    <table class="easyui-datagrid" style="width:100%;height:auto"
                           id="listTable">
                        <thead>
                        <tr>
                            <th data-options="field:'id',align:'center'" hidden="true">ID</th>
                            <th data-options="field:'parameterKey',align:'center'" width="100">参数主键</th>
                            <th data-options="field:'parameterValue',align:'center'" width="150">参数值</th>
                            <th data-options="field:'instruction',align:'center'" width="150">说明</th>
                            <th data-options="field:'createDate',align:'center'" width="60">添加时间
                            </th>
                            <th data-options="field:'_operate',align:'center',formatter:formatEditAndDel" width="40">
                                操作
                            </th>
                        </tr>
                        </thead>
                    </table>
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

    $().ready(function () {
        var $listTable = $("#listTable");
        var $addButton = $("#addButton");
        var $searchButton = $("#searchButton");
        var $clearCacheButton = $("#clearCacheButton");
        var $testButton = $("#testButton");

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
            scrollbarSize: 0,
            fitColumns: true,
            rownumbers: true,//行号
            frozenColumns: [[]]
        });

        $addButton.click(function () {
            window.location.href = "add.do";
        });

        $testButton.click(function () {
            window.location.href = "test.do";
        });

        $searchButton.click(function () {
            $listTable.datagrid({
                nowrap: false,
                striped: true,
                border: true,
                collapsible: false,//是否可折叠的
                url: 'search.do?parameterKey=' + $("#parameterKey").val(),
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

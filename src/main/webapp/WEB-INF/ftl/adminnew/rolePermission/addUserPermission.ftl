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
                    <span>用户授权</span>
                </div>
                <div class="table_con clearfix">
                    <div class="search_box">
                        <input id="saveButton" type="button" value="新增"/>
                        <input id="cancelButton" type="button" value="取消"/>

                        <input type="hidden" id="roleId" value="${roleId?c}" name="roleId">
                        <table class="easyui-datagrid" style="width:100%;height:auto"
                               id="listTable">
                            <thead>
                            <tr>
                                <th data-options="field:'id',align:'center'" hidden="true">ID</th>
                                <th data-options="field:'userName',align:'center'" width="100">用户</th>
                                <th data-options="field:'realName',align:'center'" width="100">姓名</th>
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

    $().ready(function () {
        var $listTable = $("#listTable");
        var $saveButton = $("#saveButton");
        var $cancelButton = $("#cancelButton");
        var roleId = $("#roleId").val();
        var ids = [];

    <@flash_message/>

        $listTable.datagrid({
            nowrap: false,
            striped: true,
            border: true,
            collapsible: false,//是否可折叠的
            url: 'getNoAttrPage.do?roleId=' + roleId,
            method: 'GET',
            remoteSort: false,
            idField: 'id',
            singleSelect: true,//是否单选
            pagination: true,//分页控件
            scrollbarSize: 0,
            fitColumns: true,
            frozenColumns: [[
                {field: 'ck', checkbox: true}
            ]]
        });

        $saveButton.click(function () {
            var rows = $listTable.datagrid('getSelections');
            if (rows.length == 0) {
                $.messager.alert('Tips', '请选择一条记录进行操作！', 'info');
                return;
            } else {
                var nodes = $listTable.datagrid('getChecked');
                for (var i = 0; i < nodes.length; i++) {
                    ids.push(nodes[i].id);
                }
                var url = "addUserRole.do";
                url += "?roleId=${roleId?c}&ids=" + ids;
                window.location.href = url;
            }
        });

        $cancelButton.click(function () {
            window.location.href = "${base}/admin/role/list.do";
        });
    });
</script>
</html>

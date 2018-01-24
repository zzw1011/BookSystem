<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<#include "../include/head.ftl">

<body>
<div class="wrap">
<@panel currentId="10104" parentId="101">
</@panel>
    <div class="con_right">
    <@topPanel currentId="10104" parentId="101">
    </@topPanel>
        <div class="right_content">
            <div class="table_box">
                <div class="table_head">
                    <i></i>
                    <span>操作日志</span>
                </div>
                <div class="table_con clearfix">
                    <div class="search_box">
                        <div id="tb" style="padding:10px;">
                            <input class="easyui-textbox" type="text" id="operator" style="height: 32px;" data-options="prompt:'请输入操作员'"></input>
                            <input class="easyui-datebox" style="width:200px;height: 32px" id="startDate" data-options="prompt:'开始时间'">
                             - <input class="easyui-datebox" style="width:200px;height: 32px" id="endDate" data-options="prompt:'结束时间'">
                            <input type="button" id="searchButton" value="搜索"/>

                        </div>

                        <table class="easyui-datagrid" style="width:100%;height:auto"
                               id="listTable">
                            <thead>
                            <tr>
                                <th data-options="field:'id',align:'center'" hidden="true">ID</th>
                                <th data-options="field:'operator',align:'center'" width="100">操作员</th>
                                <th data-options="field:'operation',align:'center'" width="80">操作</th>
                                <th data-options="field:'ip',align:'center'" width="80">IP</th>
                                <th data-options="field:'parameter',align:'center'" formatter="LongTitleFormatter" width="500">请求参数</th>
                                <th data-options="field:'createDate',align:'center'" width="80">创建时间</th>
                                <th data-options="field:'x',align:'center'" formatter="formatView" width="80">操作</th>
                            </tr>
                            </thead>
                        </table>
                        <div class="mg10">
                            <input type="button" id="deleteButton" value="批量删除"/>
                        </div>
                    </div>
                </div>

</body>

<script>
    function LongTitleFormatter(val,row,index) {
        if(val){
            if(val.length>100){
                return val.substring(0,100)+'...';
            }else{
                return val;
            }
        }
    }
    $().ready(function () {
        $listTable = $("#listTable");
        var $deleteButton = $("#deleteButton");
        var $searchButton = $("#searchButton");
        var ids = [];

    <@flash_message/>

        $listTable.datagrid({
            nowrap: false,
            striped: true,
            border: true,
            collapsible: false,//是否可折叠的
            url: 'getPage.do?operator=' + $("#operator").val() + "&startDate=" + $("#startDate").datebox('getValue') + "&endDate=" + $("#endDate").datebox('getValue'),
            method: 'GET',
            remoteSort: false,
            idField: 'id',
            singleSelect: false,//是否单选
            fitColumns: true,
            scrollbarSize: 0,
            pagination: true,//分页控件
            frozenColumns: [[
                {field: 'ck', checkbox: true}
            ]]
        });

        $deleteButton.click(function () {
            var rows = $listTable.datagrid('getSelections');

            if (rows.length == 0)
            {
                $.messager.alert('提示', '请选择一条记录进行操作！', 'info');
                return;
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
                                $listTable.datagrid('reload');
                                $listTable.datagrid('clearSelections');   // reload the user data
                                $listTable.datagrid('clearChecked');   // reload the user data
                            }
                            else {
                                $.messager.alert('Error', '删除失败!', 'error');
                            }
                        }
                    });
                }
            });

        });

        $searchButton.click(function () {
            $listTable.datagrid({
                nowrap: false,
                striped: true,
                border: true,
                collapsible: false,//是否可折叠的
                url: 'getPage.do?operator=' + $("#operator").val() + "&startDate=" + $("#startDate").datebox('getValue') + "&endDate=" + $("#endDate").datebox('getValue'),
                method: 'GET',
                remoteSort: false,
                idField: 'id',
                fitColumns: true,
                scrollbarSize: 0,
                singleSelect: true,//是否单选
                pagination: true,//分页控件
                frozenColumns: [[
                    {field: 'ck', checkbox: true}
                ]]
            });
        });
    });
    function viewDategrid(index)
    {
        $listTable.datagrid('selectRow',index);
        var rows = $listTable.datagrid('getSelections');
        // console.log(rows[0].id);
        if (rows.length == 0 || rows.length > 1) {
            $.messager.alert('Tips', '请选择一条记录！', 'info');
        }
        else {
            var id = rows[0].id;
            window.location.href = "detail.do?id=" + id;
        }
    }


</script>
</html>
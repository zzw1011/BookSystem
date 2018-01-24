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
                    <span>数据字典 子集列表</span>
                    <input class="right_btn" id="addButton" type="button" value="新增"/>
                </div>
                <div class="table_con clearfix">
                    <div class="search_box">
                        <table class="easyui-datagrid" id="listTable" style="width: 100%;height: auto;">
                            <thead>
                            <tr>
                                <th data-options="field:'id',align:'center'" width="50">ID</th>
                                <th data-options="field:'dictionaryValue',align:'center'" width="200">字典值</th>
                                <th data-options="field:'dicOrder',align:'center'" width="50">排序</th>
                                <th data-options="field:'dicBewrite',align:'center'" width="80">备注</th>
                                <th data-options="field:'dicTxt',align:'center'" width="80">扩展字段</th>
                                <th data-options="field:'createDate',align:'center'" formatter="Common.DateFormatter" width="50">创建时间</th>
                                <th data-options="field:'_operate',align:'center',formatter:formatEditAndDel" width="50">操作</th>
                            </tr>
                            </thead>
                        </table>
                        <p style="margin:5px;">
                            <input id="editDictionary" type="button" value="返回"/>
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>

<script>
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

    function deleteDategrid(index)
    {
        $("#listTable").datagrid('selectRow',index);
        var rows = $("#listTable").datagrid('getSelections');
        var ids=[];
        if (rows.length == 0) {
            $.messager.alert('Tips', '请选择一条记录进行操作！', 'info');
            return;
        }
        else
        {
            for (var i = 0; i < rows.length; i++)
            {
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
        var $editDictionary = $("#editDictionary");

    <@flash_message/>

        $listTable.datagrid({
            nowrap: false,
            striped: true,
            border: true,
            collapsible: false,//是否可折叠的
            url: 'getPage.do?dictionaryId=${dictionaryId?c}',
            method: 'GET',
            remoteSort: false,
            idField: 'id',
            singleSelect: true,//是否单选
            pagination: false,//分页控件
            fitColumns: true,
            scrollbarSize: 0,
            rownumbers: false,//行号
            frozenColumns: [[]]
        });

        $addButton.click(function () {
            window.location.href = "add.do?dictionaryId=${dictionaryId?c}";
        });

        $editDictionary.click(function () {
            window.location.href = "../dictionary/list.do";
        });
    });
</script>
</html>

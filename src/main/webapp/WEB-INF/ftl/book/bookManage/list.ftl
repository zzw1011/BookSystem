<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<#include "../../adminnew/include/head.ftl">

<body>
<div class="wrap">
<@panel currentId="${currentIds?c}" parentId="3026998">
</@panel>
    <div class="con_right">
    <@topPanel currentId="${currentIds?c}" parentId="3026998">
    </@topPanel>
        <div class="right_content">
            <div class="table_box">
                <div class="table_head">
                    <i></i>
                    <span>图书借阅</span>
                </div>
                <div class="table_con clearfix">
                    <div class="search_box">
                        <div id="tb" style="padding:10px;">
                            <input class="easyui-textbox" type="text" id="bookName" style="height: 32px"
                                   data-options="prompt:'请输入图书名'">
                            <input id="searchButton" type="button" value="搜索"/>
                        </div>
                        <table class="easyui-datagrid" style="width:100%;height:auto"
                               id="listTable">
                            <thead>
                            <tr>
                                <th data-options="field:'d',align:'center'" hidden="true">ID</th>
                                <th data-options="field:'bookId',align:'center'" width="100">编号</th>
                                <th data-options="field:'bookName',align:'center'" width="150">书名</th>
                                <th data-options="field:'bookAuthor',align:'center'" width="150">作者</th>
                                <th data-options="field:'bookType',align:'center'" width="100">类别</th>
                                <th data-options="field:'bookPublishing',align:'center'" width="100">出版社</th>
                                <th data-options="field:'_operate',align:'center',formatter:formatView" width="100">操作
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
<script>

    $(function () {
        var $searchButton = $("#searchButton");
        initBookLists();
        $searchButton.click(function () {
            initBookLists();
        });

    });

    function initBookLists() {
        var bookName = $("#bookName").val();
        $("#listTable").datagrid({
            loadMsg: '正在加载信息，请稍候。。。',
            nowrap: false,
            striped: true,
            border: true,
            collapsible: false,//是否可折叠的
            url: 'getPage.do?bookName='+bookName,
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
    }



    function viewDategrid(index)
    {
        $listTable.datagrid('selectRow',index);
        var rows = $listTable.datagrid('getSelections');
        if (rows.length == 0 || rows.length > 1) {
            $.messager.alert('Tips', '请选择一条记录！', 'info');
        }
        else {
            var id = rows[0].id;
            window.location.href = "detail.do?id=" + id;
        }
    }
</script>
</body>
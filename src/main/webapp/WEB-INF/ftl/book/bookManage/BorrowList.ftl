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
                            <input id="bookId" class="easyui-textbox" data-options="prompt:'请输入图书编号'" style="width: 200px;"/>
                            <input id="bookName" class="easyui-textbox" data-options="prompt:'请输入图书名'" style="width: 200px;"/>
                            <input id="bookAuthor" class="easyui-textbox" data-options="prompt:'请输入作者'" style="width: 200px;"/>
                            <select id="queryTypeSelect" class="easyui-combobox"
                                    style="width:150px;height:32px">
                                <option value="0">请选择类型</option>
                            <#list bookType.valueList as value>
                                <option value="${value.id?c}">${value.dictionaryValue}</option>
                            </#list>
                            </select>
                            <input id="searchButton" onclick="initBookLists();" type="button" value="搜索"/>
                        </div>
                        <table class="easyui-datagrid" style="width:100%;height:auto" id="Booksdg">
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<script>

    $(function () {
        initBookLists();
        $(window).resize(function () {
            $('#Booksdg').datagrid('resize');
            initBookLists();
        });
    });

    function initBookLists() {
        var bookId = $("#bookId").val();
        var bookName = $("#bookName").val();
        var bookAuthor = $("#bookAuthor").val();
        var bookType = $('#queryTypeSelect').combobox("getValue");
        $("#Booksdg").datagrid({
            loadMsg: '正在加载信息，请稍候。。。',
            nowrap: false,
            striped: true,
            border: true,
            collapsible: false,//是否可折叠的
            url: 'getPage.do?bookName='+bookName+'&bookId='+bookId+'&bookAuthor='+bookAuthor+'&bookType='+bookType,
            method: 'GET',
            remoteSort: false,
            idField: 'id',
            singleSelect: true,//是否单选
            pagination: true,//分页控件
            scrollbarSize: 0,
            fitColumns: true,
            rownumbers: true,//行号
            columns: [[
                {field:'bookId',title:'编号',align:'center', width: 80},
                {field:'bookName',title:'书名',align:'center', width: 100},
                {field:'bookAuthor',title:'作者', align:'center',width: 100},
                {field:'bookType',title:'类型', align:'center',width: 50,formatter: function (val, row, index){
                    return typeArray[val];
                }
                },
                {field:'bookPublishing',title:'出版社', align:'center',width: 100},
                {field:'_operate',title:'操作', align:'center',formatter:formatView ,width: 80}
            ]],
            toolbar: '#tb',
        });
    }

    typeArray = new Array();
    <#list bookType.valueList as value>
    typeArray[${value.id?c}] = "${value.dictionaryValue}";
    </#list>

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
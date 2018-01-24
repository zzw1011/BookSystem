<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<#include "../include/head.ftl">

<body>
<div class="Zeno_container">
    <div class="easyui-panel" style="width:100%;padding:10px;">
         <p style="margin:5px;">
             <a href="#" id="saveButton" class="easyui-linkbutton" data-options="iconCls:'icon-save'">添加</a>
             <a href="#" id="cancelButton" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
       	 </p>

		<input type="hidden" id="userId"  value="${userId?c}" name="userId">
        <table class="easyui-datagrid" style="width:100%;height:auto"
               id="listTable">
            <thead>
            <tr>
                <th data-options="field:'roleId',align:'center'" hidden="true">ID</th>
                <th data-options="field:'roleName',align:'center'" width="50%">角色名称</th>
                <th data-options="field:'roleRemark',align:'center'" width="50%">备注</th>
            </tr>
            </thead>
        </table>


    </div>
</div>

</body>

<script>

    $().ready(function () {
        var $listTable = $("#listTable");
        var $saveButton = $("#saveButton");
        var $cancelButton = $("#cancelButton");
        var userId = $("#userId").val();
        var ids = [];

    <@flash_message/>

        $listTable.datagrid({
            nowrap: false,
            striped: true,
            border: true,
            collapsible: false,//是否可折叠的
            url: 'getRolePage.do?userId=' + userId,
            method: 'GET',
            remoteSort: false,
            idField: 'roleId',
            singleSelect: true,//是否单选
            pagination: true,//分页控件
            rownumbers: true,//行号
            onLoadSuccess: function(data) {
                var rowData = data.rows;
                $.each(rowData,function(index,row){//遍历JSON
                    if(row.selFlg == 1){
                        $("#listTable").datagrid("selectRow", index);
//                        $("#listTable").datagrid("selectRecord", row.roleId);
                    }
                });
            }
        });

        $saveButton.click(function ()
        {

            var nodes = $listTable.datagrid('getChecked');
            for(var i=0; i<nodes.length; i++){
                ids.push(nodes[i].roleId);
            }
            var url = "reviseUserRole.do";
            url += "?userId=${userId}&ids=" + ids;
            window.location.href = url;
        });

        $cancelButton.click(function () {
            window.location.href = "${base}/admin/user/list.do";
        });
    });
</script>
</html>

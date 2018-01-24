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
                    <span>角色配权</span>
                </div>
                <div class="Zeno_container">
                    <div class="easyui-panel" style="width:100%;padding:10px;">
                        <div class="search_box">
                            <input id="saveButton" type="button" value="保存"/>
                            <input id="cancelButton" type="button" value="取消"/>
                        </div>
                        <div class="easyui-panel" style="padding:5px">
                            <ul id="tree">

                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<script>
    $().ready(function () {
        var $tree = $("#tree");
        var $saveButton = $("#saveButton");
        var $cancelButton = $("#cancelButton");
        var ids = [];

    <@flash_message/>

        $tree.tree({
            url: 'getTreeDate.do?classifyId=${classifyId?c}&roleId=${roleId?c}',
            method: 'GET',
            checkbox: true,
            animate: true,
            cascadeCheck: false,
            onCheck: function (node, checked) {
                //console.log($tree.tree('isLeaf', node.target))
                if (!$tree.tree('isLeaf', node.target)) {
                    //console.log(checked)
                    //console.log(node)
                    if (node.children.length > 0) {
                        var nodes = $tree.tree('getChildren', node.target);

                        for (var i = 0; i < nodes.length; i++) {
                            $tree.tree(checked ? 'check' : 'uncheck', nodes[i].target);
                        }
                    }
                }
            }

        });

        $saveButton.click(function () {
            var nodes = $tree.tree('getChecked');
            for (var i = 0; i < nodes.length; i++) {
                ids.push(nodes[i].id);
            }
            if (ids == undefined || ids=='' || ids==null){
                $.messager.alert('提示', '请选择权限!', 'info');
                return;
            }
            var url = "update.do";
            url += "?roleId=${roleId?c}&ids=" + ids;
            window.location.href = url;
        });

        $cancelButton.click(function () {
            window.location.href = "${base}/admin/role/list.do";
        });
    });
</script>
</html>

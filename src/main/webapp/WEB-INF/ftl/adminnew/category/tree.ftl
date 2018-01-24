<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<#include "../include/head.ftl">

<body>
<div class="wrap">
<@panel currentId="10101" parentId="101">
</@panel>
    <div class="con_right">
    <@topPanel currentId="10101" parentId="101">
    </@topPanel>
        <div class="right_content">
            <div class="table_box">
                <div class="table_head">
                    <i></i>
                    <span>栏目树</span>
                </div>
                <div class="table_con clearfix">
                    <div class="search_box">
                        <input id="saveButton" type="button" value="添加"/>
                        <input id="editButton" type="button" value="修改"/>
                        <input id="deleteButton" type="button" value="删除"/>
                        <input id="backButton" type="button" value="返回"/>
                    </div>

                    <div class="easyui-panel" style="padding:5px">
                        <ul id="tree">

                        </ul>
                        <ul id="tt">

                        </ul>
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
        var $editButton = $("#editButton");
        var $deleteButton = $("#deleteButton");
        var $backButton = $("#backButton");
        var ids = [];

    <@flash_message/>

        $tree.tree({
            url: 'getTreeDate.do?classifyId=${classifyId?c}',
            method: 'GET',
            checkbox: true,
            cascadeCheck: false
        });

        $backButton.click(function () {
            window.location.href = "${base}/admin/classify/list.do"
        });

        $saveButton.click(function () {
            var nodes = $tree.tree('getChecked');

            if (nodes.length == 0 || nodes.length > 1) {
                $.messager.alert('Tips', '请选择一条记录！', 'info');
            }
            else {
                var url = "add.do";

                url += "?classifyId=${classifyId?c}&id=" + nodes[0].id;

                window.location.href = url;
            }
        });

        $editButton.click(function () {
            var nodes = $tree.tree('getChecked');

            if (nodes.length == 0 || nodes.length > 1) {
                $.messager.alert('Tips', '请选择一条记录！', 'info');
            }
            else {
                if (nodes[0].id == "0") {
                    $.messager.alert('Tips', '根节点无法修改！', 'info');
                }
                else {
                    var url = "edit.do";

                    url += "?classifyId=${classifyId?c}&id=" + nodes[0].id;

                    window.location.href = url;
                }
            }
        });

        $deleteButton.click(function () {
            var nodes = $tree.tree('getChecked');

            if (nodes.length == 0) {
                $.messager.alert('Tips', '请选择一条记录！', 'info');
                return;
            }

            $.messager.confirm('Confirm', '是否确认删除？', function (r) {
                if (r) {
                    //确认删除
                    for (var i = 0; i < nodes.length; i++) {
                        var children = $tree.tree('getChildren', nodes[i].target);

                        if (children.length == 0 && nodes[i].id != "0") {
                            ids.push(nodes[i].id);
                        }
                        else {
                            if (nodes[i].id == "0") {
                                $.messager.alert('Error', '不能删除根节点', 'error');
                            }
                            else {
                                $.messager.alert('Error', '不能删除非叶子节点', 'error');
                            }
                            ids = [];
                            return;
                        }
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
                                $tree.tree('reload');
                            }
                            else {
                                $.messager.alert('Error', '删除失败!', 'error');
                            }
                        }
                    });
                }
            });
        });
    });
</script>
</html>

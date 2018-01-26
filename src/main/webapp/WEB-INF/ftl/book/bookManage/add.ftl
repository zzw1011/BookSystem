<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<#include "../../adminnew/include/head.ftl">

<body>
<div class="wrap">
<@panel currentId="3027002" parentId="3026998">
</@panel>
    <div class="con_right">
    <@topPanel currentId="3027002" parentId="3026998">
    </@topPanel>
        <div class="right_content">
            <div class="table_box">
                <div class="table_head">
                    <i></i>
                    <span>新增图书</span>
                </div>
                <div class="table_con ">
                    <form id="editForm" action="save.do" method="post" enctype="multipart/form-data" novalidate>
                        <table class="tab_edit">
                            <tr>
                                <td class="input_name" style="width: 10%">
                                    <label>图书编号</label>
                                </td>
                                <td style="width: 30%">
                                    <input class="easyui-textbox" type="text" name="bookId"
                                           data-options="required:true"
                                           validType="length[0,20]"></input>
                                </td>
                                <td class="input_name" style="width: 10%">
                                    <label>封面图片</label>
                                </td>
                                <td>
                                    <input name="filePath" data-options="buttonText:'选择文件',required:true"
                                           class="easyui-filebox" style="width: 200px;"/>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">
                                    <label>图书名称</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" type="text" name="bookName"
                                           data-options="required:true"
                                           validType="length[0,50]"></input>
                                </td>
                                <td class="input_name">
                                    <label>图书作者</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" type="text" name="bookAuthor"
                                           data-options="required:true"
                                           validType="length[0,50]"></input>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">
                                    <label>出版社</label>
                                </td>
                                <td>
                                    <input class="easyui-textbox" type="text" name="bookPublishing"
                                           data-options="required:true"
                                           validType="length[0,50]"></input>
                                </td>
                                <td class="input_name">
                                    <label>图书单价(元)</label>
                                </td>
                                <td>
                                    <input class="easyui-numberbox" type="text" name="bookMoney"
                                           data-options="required:true"  style="width: 200px;"
                                           validType="length[0,10]"></input>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name">
                                    <label>图书数量</label>
                                </td>
                                <td>
                                    <input class="easyui-numberbox" type="text" name="bookNum"
                                           data-options="required:true"  style="width: 200px;"
                                           validType="length[0,10]"></input>
                                </td>
                                <td class="input_name">
                                    <label>图书类型</label>
                                </td>
                                <td>
                                    <select name="bookType" class="easyui-combobox" data-options="required:true"
                                            style="width:200px;">
                                    <#list bookType.valueList as value>
                                        <option value="${value.id?c}">${value.dictionaryValue}</option>
                                    </#list>
                                    </select>
                                </td>
                            </tr>
                            <tr>
                                <td class="input_name"><label>内容简介</label></td>
                                <td colspan="3">
                                    <input id="content" name="bookDesc" type="hidden"/>
                                    <script id="container" type="text/plain"></script>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
                <div class="table_bottom">
                    <a href="javascript:void(0);" class="btn save" id="saveButton">保存</a>
                    <a href="javascript:void(0);" class="btn back" id="cancelButton">返回</a>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
<!-- 配置文件 -->
<script type="text/javascript" src="${base}/static/admin/ueditor/ueditor.config.js"></script>
<!-- 编辑器源码文件 -->
<script type="text/javascript" src="${base}/static/admin/ueditor/ueditor.all.js"></script>
<link type="text/css" src="${base}/static/admin/ueditor/themes/default/css/ueditor.min.css" rel="stylesheet"/>
<script>
    $().ready(function () {
        UE.getEditor('container', {
            initialFrameWidth: parseInt($('.tab_edit').css("width")) * 0.55,
            initialFrameHeight: 240
        });
        var $saveButton = $("#saveButton");
        var $editForm = $("#editForm");
        var $cancelButton = $("#cancelButton");

        $saveButton.click(function () {
            if ($editForm.form('validate')) {
                $('#content').val(UE.getEditor('container').getContent());
                $editForm.submit();
            }
        });

        $cancelButton.click(function () {
            window.location.href = "bookList.do";
        });

    });
</script>
</html>

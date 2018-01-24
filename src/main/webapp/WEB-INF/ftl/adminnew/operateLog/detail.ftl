<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
        "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">

<#include "../include/head.ftl">
<#include "../include/prettyPhoto.ftl">

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
                    <span>操作日志详情</span>
                </div>
                <div class="table_con ">

                    <table style="table-layout: fixed;word-wrap:break-word;" class="tab_edit" >
                        <tr>
                            <td style="width: 20%;" class="input_name">操作员&emsp;</td>
                            <td style="width: 75%;"><#if model.operator??>${model.operator}</#if></td>
                        </tr>
                        <tr>
                            <td class="input_name">操作&emsp;&emsp;</td>
                            <td><#if model.operation??>${model.operation}</#if></td>
                        </tr>
                        <tr>
                            <td class="input_name">IP&emsp;&emsp;&emsp;</td>
                            <td><#if model.ip??>${model.ip}</#if></td>
                        </tr>
                        <tr>
                            <td class="input_name">请求参数</td>
                            <td ><#if model.parameter??>${model.parameter}</#if></td>
                        </tr>
                        <tr>
                            <td class="input_name">创建时间</td>
                            <td><#if model.createDate??>${model.createDate?string("yyyy-MM-dd HH:mm:SS")}</#if></td>
                        </tr>
                    </table>

                </div>
                <div class="table_bottom">
                    <a href="javascript:void(0);" class="btn back" id="cancelButton">返回</a>
                </div>
                <script type="text/javascript">
                    $().ready(function () {
                        var $cancelButton = $("#cancelButton");
                        $cancelButton.click(function () {
                            window.location.href = "list.do";
                        });
                    });

                </script>
</body>


</html>
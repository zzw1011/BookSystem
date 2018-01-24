<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + path;
    String captchaId = UUID.randomUUID().toString();
%>
<!DOCTYPE HTML >
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8"/>
    <title></title>
    <meta content="width=device-width, initial-scale=1.0" name="viewport"/>
    <meta content="" name="description"/>
    <meta content="" name="author"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/admin/css/normalize.css"/>
    <link rel="stylesheet" type="text/css" href="<%=basePath%>/static/admin/css/style.css"/>
    <script src="<%=basePath%>/static/easyui/jquery.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>/static/easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="<%=basePath%>/static/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>

    <style>
        .login {
            overflow: hidden;
        }

        .login .logo {
            margin: 60px auto 0;
            padding: 15px;
            text-align: center;
            width: 300px;
        }

        .login .logo img {
            width: 100%;
        }

        .login .content {
            background-color: #ffffff;
            margin: 0 auto;
            padding: 20px 30px 30px;
            width: 300px;
        }

        .login .content {
            vertical-align: middle;
        }

        .login .content input {
            vertical-align: middle;
        }

        .login .copyright {
            color: #999999;
            font-size: 11px;
            margin: 0 auto;
            padding: 10px 10px 0;
            text-align: center;
            width: 250px;
        }

    </style>
</head>

<body class="bg">
<div class="system_title" style="position: relative;">
    <div style="height:100%;color:#fff;background:#368acd;">
        <p style="margin: 0px;font-size: 30px;line-height:26px;letter-spacing:2px;text-align: center">圖書管理系统</p>
        <p style="font-size: 14px;margin:5px 0 0 0px; text-align: center">Book Management System</p>
    </div>
</div>
<div class="login_box">
    <!-- BEGIN 登录 -->
    <form id="loginform" method="post"
          name="form1" action="main.do?captchaId=<%=captchaId%>">
        <div class="login_txt">
            <i>账号</i>
            <input id="userName" name="userName" onblur="haveUser();GetPwdAndChk()" />
        </div>
        <div class="login_txt">
            <i>密码</i>
            <input id="userPassword" name="userPassword" type="password" />
        </div>
        <div class="login_txt" style="position: relative;width:200px;margin-left: 80px;">
            <i>验证码</i>
            <input id="userCode" name="userCode" type="text" style="width:90px;" />
            <img style="position:absolute;right:-95px;top:5px;" onclick="reloadCaptcha();" title="看不清，换一张" src="getImage.do?captchaId=<%=captchaId%>" id="imgObj">
        </div>
        <div class="alert alert-error text-center" style="color: red;" id="message">${message}</div>
        <input type="button" name="" id="" value="登录" class="login_btn" onclick="login();"/>
    </form>
</div>
<script type="text/javascript">
    $().ready(function ()
    {
        if (window.top == window.self)
        {//不存在父页面
        }
        else {
            parent.location.href = "<%=basePath%>admin/login.jsp";
        }
    });

    function login() {
        $("#loginform").submit();
    }
    //更换验证码
    function reloadCaptcha() {
        var captchaURL = $('#imgObj').attr('src');
        captchaURL = captchaURL.replace(captchaURL.substring(captchaURL
                        .indexOf("=") + 1, captchaURL.length), Math.floor(Math
                        .random() * 9999999999));
        $('#imgObj').attr('src', captchaURL);
    }
    $("body").bind('keyup',function(event) {
        if(event.keyCode==13){
            $("#loginform").submit();
        }
    });
    //登录名及验证码的校验js
    var flag = true;
    function checks() {
        if (!haveUser()) {
            return false;
        }
        if (!checkVerification()) {
            return false;
        }
        saveUserInfo();
    }
    function haveUser() {
        var userName = $("#userName").val();
        $.ajax({
            type: "POST",
            url: "haveUser.do",
            data: "userName=" + userName,
            success: function (d) {
                if (d == "1") {
                    flag = false;
                    $("#message").html("登录用户不存在");
                    $("#message").show();
                } else if (d == "2") {
                    flag = false;
                    $("#message").html("登录用户已锁定");
                    $("#message").show();
                } else {
                    flag = true;
                    $("#message").html("");
                    $("#message").hide();
                }
            }
        });
        return flag;
    }



    function GetLastUser() {
        var id = "49BAC005-7D5B-4231-8CEA-16939BEACD67";//GUID标识符
        var usr = GetCookie(id);
        if (usr != null) {
            document.getElementById('userName').value = usr;
        } else {
            document.getElementById('userName').value = "001";
        }
        GetPwdAndChk();
    }
    //点击登录时触发客户端事件

    function SetPwdAndChk() {
        //取用户名
        var usr = document.getElementById('userName').value;
        //将最后一个用户信息写入到Cookie
        SetLastUser(usr);
        //如果记住密码选项被选中
        ResetCookie();
    }

    function SetLastUser(usr) {
        var id = "49BAC005-7D5B-4231-8CEA-16939BEACD67";
        var expdate = new Date();
        //当前时间加上两周的时间
        expdate.setTime(expdate.getTime() + 14 * (24 * 60 * 60 * 1000));
        SetCookie(id, usr, expdate);
    }
    //用户名失去焦点时调用该方法

    function GetPwdAndChk() {
        var usr = document.getElementById('userName').value;
        var pwd = GetCookie(usr);
        if (pwd != null) {
            document.getElementById('userPassword').value = pwd;
        } else {
            document.getElementById('userPassword').value = "";
        }
    }
    //取Cookie的值

    function GetCookie(name) {
        var arg = name + "=";
        var alen = arg.length;
        var clen = document.cookie.length;
        var i = 0;
        while (i < clen) {
            var j = i + alen;
            //alert(j);
            if (document.cookie.substring(i, j) == arg)
                return getCookieVal(j);
            i = document.cookie.indexOf(" ", i) + 1;
            if (i == 0)
                break;
        }
        return null;
    }
    var isPostBack = "";

    function getCookieVal(offset) {
        var endstr = document.cookie.indexOf(";", offset);
        if (endstr == -1)
            endstr = document.cookie.length;
        return unescape(document.cookie.substring(offset, endstr));
    }
    //写入到Cookie

    function SetCookie(name, value, expires) {
        var argv = SetCookie.arguments;
        //本例中length = 3
        var argc = SetCookie.arguments.length;
        var expires = (argc > 2) ? argv[2] : null;
        var path = (argc > 3) ? argv[3] : null;
        var domain = (argc > 4) ? argv[4] : null;
        var secure = (argc > 5) ? argv[5] : false;
        document.cookie = name
                + "="
                + escape(value)
                + ((expires == null) ? "" : ("; expires=" + expires
                        .toGMTString()))
                + ((path == null) ? "" : ("; path=" + path))
                + ((domain == null) ? "" : ("; domain=" + domain))
                + ((secure == true) ? "; secure" : "");
    }

    function ResetCookie() {
        var usr = document.getElementById('userName').value;
        var expdate = new Date();
        SetCookie(usr, null, expdate);
    }
    //点击空白处隐藏验证码错误提示信息
    $(function () {
        $(document).bind("click", function (e) {
            var target = $(e.target);
            if (target.closest("#code").length == 0) {
                $("#code").hide();
            }
        })
    })

    //点击空白处隐藏登录密码错误提示信息
    $(function () {
        $(document).bind("click", function (e) {
            var target = $(e.target);
            if (target.closest("#passwordError").length == 0) {
                $("#passwordError").hide();
            }
        })
    })
</script>
</body>
</html>

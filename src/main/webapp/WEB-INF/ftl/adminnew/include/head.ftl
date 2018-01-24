<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="initial-scale=1.0, maximum-scale=1.0, user-scalable=no" />
    <title>图书管理系统</title>
    <link href="${base}/static/easyui/themes/bootstrap/easyui.css" type="text/css" rel="stylesheet"/>
    <link href="${base}/static/easyui/themes/icon.css" type="text/css" rel="stylesheet"/>
    <link href="${base}/static/easyui/themes/color.css" type="text/css" rel="stylesheet"/>
    <link href="${base}/static/admin/css/normalize.css" type="text/css" rel="stylesheet"/>
    <link href="${base}/static/admin/css/style.css" type="text/css" rel="stylesheet"/>
    <script src="${base}/static/easyui/jquery.min.js" type="text/javascript"></script>
    <script src="${base}/static/easyui/jquery.easyui.min.js" type="text/javascript"></script>
    <script src="${base}/static/easyui/locale/easyui-lang-zh_CN.js" type="text/javascript"></script>
    <script src="${base}/static/easyui/jquery.tabs.menu.js" type="text/javascript"></script>
    <script src="${base}/static/easyui/datagrid-detailview.js" type="text/javascript"></script>
    <script type="text/javascript" src="${base}/static/admin/js/style.js" ></script>
    <script type="text/javascript" src="${base}/static/admin/js/commonJ.js" ></script>
    <script type="text/javascript" src="${base}/static/admin/js/validateform.js"></script>
    <script type="text/javascript">
        $(document).ready(function(e) {
            var counter = 0;
            if (window.history && window.history.pushState) {
                $(window).on('popstate', function () {
                    window.history.pushState('forward', null, '#');
                    window.history.forward(1);
                   // alert("不可回退");
                });
            }

            window.history.pushState('forward', null, '#'); //在IE中必须得有这两行
            window.history.forward(1);
        });
    </script>
</head>
$(function () {
    tabClose();
    tabCloseEven();


})

function addTab(title, url , obj) {
    var tt = $("#main-center");
    if (tt.tabs('exists', title)) {
        tt.tabs('select', title);
    }
    else {
        if (url) {
            var w = $(".tabs-panels.tabs-panels-noborder").height()-3;
            var content = '<iframe class="main-center-iframe" src="' + url + '" style="width:100%;height:' + w + 'px;" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe>';
        }
        else {
            var content = '未实现';
        }
        tt.tabs('add', {
            title: title,
            closable: true,
            content: content
        });
        tabClose();
    }
	//console.log(obj)
	$("ul.page-sidebar-menu > li > a.active").removeClass("active");
	$(obj).addClass("active");
}
$(window).resize(function(){
	var w=$(window).height()-130;
	$(".main-center-iframe").css("height",w+'px');
});
function tabClose() {
    var tt = $("#main-center");
    $(".tabs-inner").dblclick(function () {
        var subtitle = $(this).children("span").text();
        tt.tabs('close', subtitle);
    });
    $(".tabs-inner").bind('contextmenu', function (e) {
        e.preventDefault();
        $("#mm").menu('show', {
            left: e.pageX,
            top: e.pageY
        });
        var subtitle = $(this).children("span").text();
        $("#mm").data("currtab", subtitle);
    });
}

function tabCloseEven() {
    var tt = $("#main-center");
    //关闭当前
    $("#mm-tabclose").click(function () {
        var currtab_title = $("#mm").data("currtab");
        tt.tabs('close', currtab_title);
    });
    //刷新
    $("#mm-refresh").click(function () {
        var tab = tt.tabs('getSelected');
        tab.panel('refresh');
    });
    //全部关闭
    $("#mm-tabcloseall").click(function () {
        $('.tabs-inner span').each(function (i, n) {
            var t = $(n).text();
            tt.tabs('close', t);
            if (t != '首页') {
                tt.tabs('close', t);
            }
        });
    });
    //关闭除当前之外的TAB
    $('#mm-tabcloseother').click(function () {
        var currtab_title = $("#mm").data("currtab");
        $('.tabs-inner span').each(function (i, n) {
            var t = $(n).text();
            if (t != currtab_title && t != '首页') {
                tt.tabs('close', t);
            }
        });
    });
    //关闭当前右侧的TAB
    $('#mm-tabcloseright').click(function () {
        var nextall = $('.tabs-selected').nextAll();
        if (nextall.length > 0) {
            nextall.each(function (i, n) {
                var t = $('a:eq(0) span', $(n)).text();
                tt.tabs('close', t);
            });
            return false;
        }
    });
    //关闭当前左侧的TAB
    $('#mm-tabcloseleft').click(function () {
        var preall = $('.tabs-selected').prevAll();
        if (preall.length > 0) {
            preall.each(function (i, n) {
                var t = $('a:eq(0) span', $(n)).text();
                if (t != '首页') {
                    tt.tabs('close', t);
                }
            });
            return false;
        }
    });
    //退出
    $("#mm-exit").click(function () {
        $('#mm').menu('hide');
    });
}
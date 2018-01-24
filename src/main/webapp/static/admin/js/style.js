$().ready(function () {
    $(".right_nav_btn").click(function () {
        var $con = $(this).siblings();
        if ($con.css("display") == "none") {
            $con.show();
            $con.parent().addClass("box_sh");
        } else {
            $con.hide();
            $con.parent().removeClass("box_sh");
        }
        return false;
    });


    $(".search_btn span").click(function () {
        $(".search_btn").animate({width: "200px"}, 500);
        $(this).siblings("input").show();
        return false;
    });

    $(".cell span").click(function () {
        $(".cell span").removeClass("cur");
        $(this).addClass("cur");
    });

    $(".tab_btn li").click(function () {
        $(".tab_btn li").removeClass("cur");
        $(this).addClass("cur");
    });

    /* 后台左侧菜单处理  未完成*/
    $(".left_list").height($(window).height() - 110);
    $(window).resize(function () {
        $(".left_list").height($(window).height() - 110);
        init_left_menu();
    });

    $(".list_btn").click(function () {
        if ($(this).hasClass("cur")) {
            $(this).removeClass("cur");
            $(this).parent().find(".left_sub_list").slideUp();
        } else {
            $(".list_btn").removeClass("cur");
            $(".list_btn").parent().find(".left_sub_list").slideUp();
            $(this).addClass("cur");
            $(this).parent().find(".left_sub_list").slideDown();
        }
    });

    function IsPC() {
        var userAgentInfo = navigator.userAgent;
        var Agents = ["Android", "iPhone","SymbianOS", "Windows Phone","iPad", "iPod"];
        var flag = true;
        for (var v = 0; v < Agents.length; v++) {
            if (userAgentInfo.indexOf(Agents[v]) > 0) {
                flag = false;
                break;
            }
        }
        return flag;
    }

    function init_left_menu(){
        if(!IsPC()){
            $('.left_list span.center_text').hide();
            $('.left_list i.right_icon').hide();
            $('.left_sub_list').hide();
            //@todo
        }
    }
    init_left_menu();
})






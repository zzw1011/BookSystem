<div class="right_nav clearfix">
    <div class="right_nav_l">
        <div class="right_nav_btn">
            <i class="l_icon"></i>
            <span>${user.userName}</span>
            <i class="l_arrow"></i>
        </div>
        <div class="riht_nav_con" style="display: none;">
            <a href="${base}/admin/user/edit.do?id=${user.id?c}" class="cur">个人资料</a>
            <a href="${base}/admin/password/edit.do">修改密码</a>
        </div>
    </div>
    <div class="right_nav_r">
        <div class="nav_menu">
            <a href="${base}/admin/common/index.do" target="_blank" class="home">
                <i></i>
                <span>网站首页</span>
            </a>
            <a href="${base}/admin/common/clearCache.do"   class="cache" > <i></i><span>更新缓存</span></a>
            <a href="${base}/admin/common/logout.do" class="exit_login">
                <i></i>
                <span>退出</span>
            </a>
        </div>

        <div class="nav_btn search_btn">
            <input type="text" id="search"/>
            <span id="searchSpan"></span>
        </div>
    </div>
</div>
<div class="right_sub_nav">
    <a href="${base}/admin/common/index.do">
        <i></i>
        <span>home</span>
    </a>
    <#if current ??>
        <i>&gt;</i>
        <a href="javascript:void(0);">${parent.categoryName}</a>
        <i>&gt;</i>
        <a href="${current.categoryUrl}" class="f_w">${current.categoryName}</a>
    </#if>
</div>

<script type="text/javascript">

$().ready(function()
{
    var $search = $("#search");
    var $searchSpan = $("#searchSpan");

    $searchSpan.click(function()
    {
        if ($search.attr("style") != undefined && $search.attr("style") != null && $search.val()!= null && $search.val()!="")
        {
            $.ajax({
                type: "POST",
                url: "${base}/admin/common/search.do",
                data:
                {
                    categoryName:$search.val()
                },
                success: function (data)
                {
                    data = JSON.parse(data);
                    if (data.type == 'success')
                    {
                        window.location.href = data.content;
                    }else
                    {
                        $.messager.alert('Error', data.content, 'error');
                    }
                }
            });
        }
    });

    document.onkeydown = function(e)
    {
        var ev = document.all ? window.event : e;

        if(ev.keyCode==13)
        {
            if ($search.attr("style") != undefined && $search.attr("style") != null && $search.val()!= null && $search.val()!="")
            {
                $.ajax({
                    type: "POST",
                    url: "${base}/admin/common/search.do",
                    data:
                    {
                        categoryName:$search.val()
                    },
                    success: function (data)
                    {
                        data = JSON.parse(data);
                        if (data.type == 'success')
                        {
                            window.location.href = data.content;
                        }else
                        {
                            $.messager.alert('Error', data.content, 'error');
                        }
                    }
                });
            }
        }
    };

    $("#refrashAll").click(function()
    {
        $.messager.confirm('Confirm', '是否确认刷新缓存？', function (r) {
            if (r) {
                $.ajax({
                    type: "POST",
                    url: "${base}/admin/cache/refrashAll.do",
                    success: function (data)
                    {
                        data = JSON.parse(data);
                        if (data.type == 'success') {
                            $.messager.alert('Success', "刷新成功");
                        }else {
                            $.messager.alert('Error', '刷新失败!', 'error');
                        }
                    }
                });
            }
        });
    });
});

function clearPcCache() {
    window.location.href="${pcPath}?returnUrl=${returnUrl}"

}
</script>


/**
 * Created by jerrywang on 2017/1/12.
 */
jQuery.namespace("index");
$(function () {
    if("admin" == comm.js.username){
        $("li[name=settings_li]").show();
    }
    // index页面头脚信息初始化
    index.js.init();
    // 初始化body区
    var on = getParameter(location.hash,"on","");
    index.js.load(on);
    $(window).hashchange(function(){
        on = getParameter(location.hash,"on","");
        index.js.load(on);
    });
    $(window).scroll(function(){
        //scrollTop是浏览器滚动条的top位置，
        var scrollTop=$(document).scrollTop();
        if(scrollTop > 100){
            // $("#top_toolbar").slideDown();
            $("#top_toolbar").fadeIn();
        }else if(scrollTop < 100){
            // $("#top_toolbar").slideUp();
            $("#top_toolbar").fadeOut();
        }
    })
    // 主内容区大小赋值
    var h = $(window).height();
    $("#content-container").css({minHeight:h-182});
    $(window).resize(function () {
        var h = $(window).height();
        $("#content-container").css({minHeight:h-182});
    });

    // 关闭artTemplate缓存
    template.config('cache',false);
});

index.js = {};
index.js.load = function (on) {
    // 根据on的值,给相应菜单加样式
    $("#menu_bar").children().removeClass("current-menu-item");
    $("#top_toolbar").find("li").removeClass("current-menu-item");
    if (on == "") {
        // 初始化第一个页面
        var menu0 = $("#menu_bar").children().eq(0);
        // 初始化on值
        var onclickAttr = menu0.find("a").attr("onclick");
        on = onclickAttr.substring(20,onclickAttr.length-2);
        index.js.menuClick(on);

    } else {
        $("#menu_bar").children().each(function (i,e) {
            var oc = $(this).find("a").attr("onclick");
            if (oc.indexOf(on.split("/")[0]) > 0) {
                $(this).addClass("current-menu-item");
                $("#top_toolbar").find("li:eq("+i+")").addClass("current-menu-item");
                return false;
            }
        });
    }
    // $("body").remove($("script[type='text/heml']"));
    $("#content-container").load(path+"/"+on+".do");
};
// 菜单按钮点击事件
index.js.menuClick = function (menupath) {
    setHash('on='+menupath+'&obj='+$("a[name=a_object]").attr("value"));
};
// 初始化头脚信息
index.js.init = function(){
    // 获取用户基本信息
    $("a[name=a_name]").text(comm.js.nickname);
    // 获取项目信息
    $.post({
        type:'POST',
        async:false,
        url:path+"/index/getObjects.do",
        data:{username:comm.js.username},
        success:function (data) {
            $(".modal-body").html("");
            for (var i = 0; i < data.length; i++) {
                if (i == 0) {
                    $("a[name=a_object]").text(data[i].OBJECT_NAME).attr("value", data[i].OBJECT_CODE);
                }
                $(".modal-body").append("<div class='modal-item'><span value='" + data[i].OBJECT_CODE + "'>" + data[i].OBJECT_NAME + "</span></div>")

            }
        }
    });

    index.js.bell();
};
// 退出
index.js.logout = function(){
    $.post(path+"/index/logout.do",{},function (data) {
        if(data  = "true"){
            location.href = path+"/index/index.do";
        }
    });
};

// 铃铛
index.js.bell = function () {
    setInterval(function () {
        var obj = getParameter(location.hash, "obj", "");
        var dt = $("a[name=bell]").attr("dt");
        $.post(path + "/index/countNew.do",{obj:obj, dt:dt},function(data){
            if(data > 0){
                $("#warn-bell").addClass("icon-animated-bell");
            }else {
                $("#warn-bell").removeClass("icon-animated-bell");
            }
            $("#warn-bell").next().text(data);
        });
    },5*60*1000);
};

index.js.bellClick = function () {
    var param = {};
    param.obj = getParameter(location.hash, "obj", "");
    param.dt = $("a[name=bell]").attr("dt");
    var ran = Math.random()*100000000000000000;
    // $.ajax({
    //     type:'POST',
    //     async:false,
    //     url:path+'/index/getNew.do',
    //     contentType:'application/json',
    //     data:JSON.stringify(param),
    //     success:function (data) {
    //         // 给铃铛赋值新时间
    //         $("a[name=bell]").attr("dt", data.dt);
    //         //
    //         var on = getParameter(location.hash, "on", "");
    //         if("workbench/init" != on) {
                setHash("on=workbench/init&obj=" + param.obj +"&ran="+ran);
    //         }else{
    //             var html = template('grid_template',{'list':data.query,'source':'bell'});
    //             $("#grid").prepend(html);
    //             $(".grid-item").slideDown('normal');
    //             // 初始化页码按钮
    //             var page = {};
    //             page.total = 0;
    //             page.pageNum = 0;
    //             page.pageSize = 0;
    //             $("#page-bar").page({total});
    //         }
            $("#warn-bell").removeClass("icon-animated-bell").next().text("0");
    //     }
    //
    // });
};


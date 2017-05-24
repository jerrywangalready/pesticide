
/**
 * Created by jerrywang on 2017/1/12.
 */
jQuery.namespace("index");
$(function () {
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
            console.info(oc)
            console.info(on)
            if (oc.indexOf(on) > 0) {
                $(this).addClass("current-menu-item");
                $("#top_toolbar").find("li:eq("+i+")").addClass("current-menu-item");
                return false;
            }
        });
    }
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
};
// 退出
index.js.logout = function(){
    $.post(path+"/index/logout.do",{},function (data) {
        if(data  = "true"){
            location.href = path+"/index/index.do";
        }
    });
};


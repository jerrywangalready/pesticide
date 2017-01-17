/**
 * Created by jerrywang on 2017/1/12.
 */
jQuery.namespace("index");
$(function () {

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
    if (on == "") {
        // 初始化第一个页面
        var menu0 = $("#menu_bar").children().eq(0);
        menu0.addClass("current-menu-item");
        var onclickAttr = menu0.find("a").attr("onclick");
        onclickAttr.subs
        on = onclickAttr.substring(20,onclickAttr.length-2);

        console.info(on);
    } else {
        $("#menu_bar").children().each(function () {
            var oc = $(this).find("a").attr("onclick");
            if (oc.indexOf(on) > 0) {
                $(this).addClass("current-menu-item");
                return false;
            }
        });
    }
    $("#content-container").load(path+"/"+on+".do");
    // index页面头脚信息初始化
    index.js.init();
};
// 菜单按钮点击事件
index.js.menuClick = function (menupath) {
    setHash('on='+menupath);
};
// 初始化头脚信息
index.js.init = function(){
    // 获取用户基本信息
    $("a[name=a_name]").text(nickname);
};
// 退出
index.js.logout = function(){
    $.post(path+"/index/logout.do",{},function (data) {
        if(data  = "true"){
            location.href = path+"/index/index.do";
        }
    });
};

/**
 * Created by jerrywang on 2017/1/12.
 */
jQuery.namespace("index");
$(function () {
    var on = getParameter(location.hash,"on","");
    index.js.load(on);
    $(window).hashchange(function(){
        on = getParameter(location.hash,"on","");
        // if(on != ""){
            index.js.load(on);

        // }
    });
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
};

index.js.menuClick = function (menupath) {
    setHash('on='+menupath);
};
/**
 * Created by jerrywang on 2017/1/12.
 */
jQuery.namespace("index");
$(function () {
    var on = getParameter(location.hash,"on","/test/test1");
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
    $("#menu_bar").children().each(function () {
        var oc = $(this).find("a").attr("onclick");
        if(oc.indexOf(on)>0){
            $(this).addClass("current-menu-item");
            return false;
        }
    });
    $("#content-container").load(path+"/"+on+".do");
};

index.js.menuClick = function (menupath) {
    setHash('on='+menupath);
};
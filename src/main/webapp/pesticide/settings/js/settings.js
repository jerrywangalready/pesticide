/**
 * Created by Administrator on 2017/1/15.
 */
jQuery.namespace("settings");
$(function () {
    settings.js.init();
});
settings.js = {};
settings.js.init = function () {
    // 获取type值
    var type = getParameter(location.hash,"type","users");
    // 绑定tab点击事件
    $("#tablist").find("a").each(function () {
        var type_attr = $(this).attr("type");
        if(type_attr == type){
            $(this).parent().addClass("active");
        }
        $(this).click(function () {
            settings.js.clickTab(type_attr);
        })
    });
    if (type == "objects"){
        settings.js.fillObject();
    }else{
        settings.js.fillUsers();
    }
    $("#isEnable").dict({table:"t_code_list",type:"YN",where:"",order:""});
};

// 点击tab
settings.js.clickTab = function (type) {
    var on = getParameter(location.hash,"on","");
    setHash("on=" + on + "&type=" + type);
};

// 填充users
settings.js.fillUsers = function () {
    $.post(path+"/settings/queryUsers.do",
        {},
        function (data) {
            console.info(data);
            var html = template('users',{'list':data});
            $("#testDiv").html(html);
        });
}

// 填充object
settings.js.fillObject = function () {
    $.post(path+"/settings/queryObject.do",
        {},
        function (data) {
            console.info(data);
            var html = template('objects',{'list':data});
            $("#testDiv").html(html);
        });
}

//查询项
settings.js.query=function () {

}


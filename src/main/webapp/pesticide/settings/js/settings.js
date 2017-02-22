/**
 * Created by Administrator on 2017/1/15.
 */
jQuery.namespace("settings");
$(function () {
    settings.js.init();
});
settings.js = {};
settings.js.init = function () {
    $.dictInit([{name:"yn",table:"s_code_list",type:"YN"}]);
    $("#query_isEnable").dict({table:"s_code_list",type:"YN"});
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
    $("#query_isEnable").dict({table:"t_code_list",type:"YN",where:"",order:""});
    // 初始化回车事件
    $("#query_nickname,#query_username").enter(function () {
        $("#query_box").query();
    });
    $("#query_isEnable").change(function () {
        $("#query_box").query();
    });
    $("#username").val("");
    $("#password").val("");
};

// 点击tab
settings.js.clickTab = function (type) {
    var on = getParameter(location.hash,"on","");
    setHash("on=" + on + "&type=" + type);
};

// 填充users
settings.js.fillUsers = function () {
    var collector = $("#query_box").collector();
    $.ajax({
        type:'POST',
        url:path+"/settings/queryUsers.do",
        contentType:'application/json',
        data:JSON.stringify(collector),
        success:function (data) {
            var html = template('users',{'list':data.list});
            $("#testDiv").html(html);
            // 初始化页码按钮
            $("#page-bar").page(data);
        }
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


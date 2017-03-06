/**
 * Created by Administrator on 2017/1/15.
 */
jQuery.namespace("settings");
$(function () {
    settings.js.init();
});
settings.js = {};
settings.js.updateUUID = "";
settings.js.init = function () {
    var type = getParameter(location.hash,"type","users");
    if(type=="users"){

    }else {

    }
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
    $("#add").find("input").each(function () {
        $(this).val("");
    })

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
            var html = template('settings_users',{'list':data.list});
            $("#testDiv").html(html);
            // 初始化页码按钮
            $("#page-bar").page(data);
        }
    });


};

// 填充object
settings.js.fillObject = function () {
    $.post(path+"/settings/queryObject.do",
        {},
        function (data) {
            var html = template('objects',{'list':data});
            $("#testDiv").html(html);
        });
};

//查询项
settings.js.query=function () {

};

//新增人员信息
settings.js.save=function (todo) {
    if(todo=="update"){

    }
    var param = $("#add").validate();
    if(param){
        $.ajax({
            type:'POST',
            url:path+'/settings/save.do',
            contentType:'application/json',
            data:JSON.stringify(param),
            success:function (data) {
                if(data=='success'){
                    layer.msg('保存成功!', {
                        offset: '50px'
                    });
                    $('#addUsersModal').modal('hide');
                    settings.js.query();
                }else if(data=='fail'){
                    layer.msg('保存失败!', {
                        offset: '50px'
                    });
                }
            }

        });
    }
};
// 校验输入的两次密码是否一致
settings.js.checkPassword=function () {
    var password = $("#password").val();
    var repassword = $("#repassword").val();
    if ((password!=repassword)&&(repassword!='')){
        alert("两次输入的密码不一致！");
        $("#repassword").val("");
    }
};
// 打开新增窗口
settings.js.showModal=function () {
    $('#addUsersModal').modal('show');
    $("#add").find("input").each(function () {
        $(this).val("");
    })
};
// 打开修改窗口
settings.js.updateUser=function (uuid) {
    settings.js.updateUUID = uuid;
    layer.open({
        type: 2,
        title: '人员修改页面',
        shadeClose: true,
        shade: 0.8,
        area: ['380px', '75%'],
        content: [path+'/settings/addOrUpdateInit.do','no']
    });
};
// 删除一条人员记录
settings.js.deleteUser=function (uuid) {

};

function getUpdateUUID() {
    return settings.js.updateUUID;
};


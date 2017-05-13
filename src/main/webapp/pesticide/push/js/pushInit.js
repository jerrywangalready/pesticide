/**
 * Created by DCH on 2017/5/7.
 */

jQuery.namespace("push");
$(function () {
    push.js.init();
});

push.js = {};
// 初始化
push.js.init = function () {
    push.js.query();
};

push.js.query = function () {
    var param = $("#query_box").collector();
    param.principal = comm.js.username;
    param.object_code = getParameter(location.hash,"obj","");
    $.ajax({
        type:'POST',
        url:path+'/push/getPushList.do',
        contentType:'application/json',
        data:JSON.stringify(param),
        success:function (data) {
            var html = template('grid_template',{'list':data.list});
            $("#grid").html(html);
            // 初始化页码按钮
            $("#page-bar").page(data);
        }
    });
};

push.js.detail = function (obj, model_code) {
    var state = $(obj).attr("state");

    if(state=="0"){
        $.post(path+"/push/getPushDetail.do",{modelCode:model_code},function (data) {
            var html = template('grid_two',{'list':data});
            $(obj).after(html);
        });
        $(obj).attr("state","1");
    }else{
        $(obj).next().remove();
        $(obj).attr("state","0");
    }

};


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
    // param.principal = comm.js.username;
    param.object_code = getParameter(location.hash,"obj","");
    $.ajax({
        type:'POST',
        url:path+'/push/getPushList.do',
        contentType:'application/json',
        data:JSON.stringify(param),
        success:function (data) {
            if(data.length == 0){
                $("#publish_button").attr("disabled", "disabled");
            }
            var html = template('model_grid_template',{'list':data});
            $("#grid").html(html);
        }
    });

    // 判断是否有权限发布
    $.post(path + "/push/checkRole.do",{},function (data) {
        if(data != "0"){
            $("#publish_button").show();
        }
    });
};

push.js.detail = function (obj, model_code) {
    var state = $(obj).attr("state");

    if(state=="0"){
        var param = {};
        param.object_code = getParameter(location.hash,"obj","");
        param.modelCode = model_code;
        $.ajax({
            type:'POST',
            url:path+'/push/getPushDetail.do',
            contentType:'application/json',
            data:JSON.stringify(param),
            success:function (data) {
                var html = template('grid_two',{'list':data});
                $(obj).after(html);
            }

        });
        // $.post(path+"/push/getPushDetail.do",{modelCode:model_code},function (data) {
        //     var html = template('grid_two',{'list':data});
        //     $(obj).after(html);
        // });
        $(obj).attr("state","1");
    }else{
        $(obj).next().remove();
        $(obj).attr("state","0");
    }

};

push.js.publish = function () {
    // 获取选中的模块
    var modelCodes = "";
    $("input[name=model]:checked").each(function () {
        modelCodes += "," + $(this).val() ;
    });
    modelCodes = modelCodes.replace(",","");

    var param = {};
    param.object_code = getParameter(location.hash,"obj","");
    param.modelCodes = modelCodes;
    $.ajax({
        type:'POST',
        url:path+'/push/publish.do',
        contentType:'application/json',
        data:JSON.stringify(param),
        success:function (data) {
            if("true" == data){
                layer.alert("发布成功");
                push.js.query();
            }else {
                layer.alert("发布失败");
            }
        }

    });

};


/**
 * Created by jerrywang on 2017/5/1.
 */
jQuery.namespace("workbenchChoseModel");
$(function () {
    workbenchChoseModel.js.param = parent.workbenchDetail.js.getParameter();
    workbenchChoseModel.js.init();
});

workbenchChoseModel.js = {};
workbenchChoseModel.js.init = function () {

    $.post(path + "/workbench/getModel.do", {objectId: workbenchChoseModel.js.param.objectCode}, function (data) {
        for (var i = 0; i < data.length; i++) {
            $("#model_body").append("<div style='padding-left: 15px;'><label><input type='checkbox' name='push_model' value='" + data[i] + "'>" + data[i] + "</label></div>");
        }
        $("input[value=" + workbenchChoseModel.js.param.model_code + "]").attr("checked", "checked");
        comm.js.iframeAuto();
    });
};


workbenchChoseModel.js.push = function () {
    //搜集参数
    var param = {};
    param.businessId = workbenchChoseModel.js.param.uuid;
    param.issueType = workbenchChoseModel.js.param.issueType;
    param.state = $("#state").val();
    var model_codes = "";
    $("input[name=push_model]:checked").each(function () {
        model_codes += $(this).val() + ",";
    });
    model_codes = model_codes.substr(0,model_codes.length-1);
    param.modelCodes = model_codes;
    param.description = "";
    $.ajax({
        type:'POST',
        url:path+'/workbench/push.do',
        contentType:'application/json',
        data:JSON.stringify(param),
        success:function (data) {
            if(data == "true"){
                parent.layer.msg("推送成功!");
                comm.js.closeLayer();
                parent.workbenchDetail.js.return();
            }else {
                layer.alert("推送失败!");
            }
        }
    });
};




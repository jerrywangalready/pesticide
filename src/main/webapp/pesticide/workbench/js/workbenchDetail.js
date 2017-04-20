/**
 * Created by jerrywang on 2017/4/10.
 */
jQuery.namespace("workbenchDetail");
$(function () {
    workbenchDetail.js.init();

    workbenchDetail.js.modalInit();


});

workbenchDetail.js = {};

workbenchDetail.js.init = function () {
    var hash = location.hash;
    var uuid = getParameter(hash, "uuid", "");
    var type = getParameter(hash, "type", "");
    var object = getParameter(hash, "obj", "");

    $.dictInit([
        {name: "mc", table: "s_model", key: "model_code", value: "model_name", where: "object_code=" + object},
        {name: "priority", table: "t_code_list", type: "priority"}
    ]);

    $.post(path + "/workbench/getDetail.do", {uuid: uuid, type: type}, function (data) {
        data.type = type;
        var html = template('detail_template', data);
        $("#detail_body").html(html);

        $("#description").html(data.description);

    });
};

workbenchDetail.js.modalInit = function () {
    var object = getParameter(location.hash, "obj", "");
    $.post(path + "/workbench/getModel.do", {objectId: object}, function (data) {
        for (var i = 0; i < data.length; i++) {
            $(".modal-body").append("<div style='padding-left: 15px;'><label><input type='checkbox' name='push_model' value='" + data[i] + "'>" + data[i] + "</label></div>");
        }
        $("input[value=" + $("#model_code").val() + "]").attr("checked", "checked");
    });
};

workbenchDetail.js.push = function () {
    //搜集参数


    $.post(path + '/workbench/push.do', {}, function (data) {

    });
};


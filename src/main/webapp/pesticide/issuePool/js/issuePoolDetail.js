/**
 * Created by jerrywang on 2017/4/10.
 */
jQuery.namespace("issuePoolDetail");
$(function () {
    issuePoolDetail.js.init();
});

issuePoolDetail.js = {};

issuePoolDetail.js.init = function () {
    var hash = location.hash;
    var uuid = getParameter(hash, "uuid", "");
    var type = getParameter(hash, "type", "");
    var object = getParameter(hash, "obj", "");

    $.dictInit([
        {name: "mc", table: "s_model", key: "model_code", value: "model_name", where: "object_code=" + object},
        {name: "priority", table: "t_code_list", type: "priority"},
        {name: "state", table: "t_code_list", type: "state"}
    ]);
    // 初始化详细页面
    $.post(path + "/workbench/getDetail.do", {uuid: uuid, type: type}, function (data) {
        data.type = type;
        data.username = comm.js.username;
        var html = template('detail_template', data);
        $("#detail_body").html(html);

        $("#description").html(data.description);

    });
    // 初始化操作日志
    $.post(path + "/workbench/getRecord.do", {businessId:uuid}, function (data) {
        var html = template('operation_template', {list:data});
        $("#operation_details").html(html);
    });
};

issuePoolDetail.js.return = function () {
    setHash("on=issuePool/init&obj="+getParameter(location.hash,"obj",""));
};

issuePoolDetail.js.update = function () {
    var hash = location.hash;
    var obj = getParameter(hash,"obj","");
    var uuid = getParameter(hash, "uuid", "");
    var type = getParameter(hash, "type", "");
    setHash("on=issuePool/update&obj="+obj+"&uuid="+uuid+"&type="+type);
};
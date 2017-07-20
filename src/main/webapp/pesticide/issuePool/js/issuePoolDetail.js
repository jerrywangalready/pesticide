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
    // 初始化附件信息
    $.post(path + "/workbench/getAttachment.do", {businessId: uuid}, function (data) {
        var html = template('attachment_template', {list:data});
        $("#attachment_box").html(html);
    });

    issuePoolDetail.js.initOperation(uuid);
};

issuePoolDetail.js.initOperation = function (uuid) {
    // 初始化操作日志
    $.post(path + "/workbench/getRecord.do", {businessId:uuid}, function (data) {
        var html = template('operation_template', {list:data});
        $("#operation_details").html(html);

        $(".recent-posts li:eq(1)").slideDown("normal");
        //
        $("#remark").enter(function () {
            issuePoolDetail.js.submitRemark();
        });
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

// download attachment
issuePoolDetail.js.downloadAttachment = function (uuid) {
    var form=$("<form>");//定义一个form表单
    form.attr("style","display:none");
    form.attr("target","");
    form.attr("method","post");
    form.attr("action",path + "/workbench/downloadAttachment.do");
    var input=$("<input>");
    input.attr("type","hidden");
    input.attr("name","uuid");
    input.attr("value",uuid);
    $("body").append(form);//将表单放置在web中
    form.append(input);
    form.submit();//表单提交
};

issuePoolDetail.js.submitRemark = function () {
    var uuid = $("#uuid").val();
    var remark = $("#remark").val();
    $.post(path + "/workbench/submitRemark.do",{uuid: uuid, remark: remark}, function (data) {
        if(data == "true"){
            issuePoolDetail.js.initOperation(uuid);
        }else {
            alert("操作失败");
        }
    });
};
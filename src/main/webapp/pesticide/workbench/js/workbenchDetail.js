/**
 * Created by jerrywang on 2017/4/10.
 */
jQuery.namespace("workbenchDetail");
$(function () {
    workbenchDetail.js.init();

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
    // 初始化详细页面
    $.post(path + "/workbench/getDetail.do", {uuid: uuid, type: type}, function (data) {
        data.type = type;
        var html = template('detail_template', data);
        $("#detail_body").html(html);

        $("#description").html(data.description);

    });
    // 初始化操作日志
    $.post(path + "/workbench/getRecord.do", {businessId:uuid}, function (data) {
        console.info(data)
        var html = template('operation_template', {list:data});
        $("#operation_details").html(html);
    });
};

/**
 * 送测按钮点击事件
 */
workbenchDetail.js.pushButtonClick = function (state) {
    layer.open({
        type:2,
        title:"选择送测模块",
        area:['300px','auto'],
        content:[path + '/workbench/choseModelInit.do?state='+state, 'no']
    });
};
workbenchDetail.js.getParameter = function () {
    var param = {};
    param.objectCode = getParameter(location.hash,"obj","");
    param.model_code = $("#model_code").val();
    param.uuid = $("#uuid").val();
    param.issueType = $("#issue_type").val();
    return param;
};
workbenchDetail.js.return = function () {
    setHash("on=workbench/init&obj="+getParameter(location.hash,"obj",""));
};

workbenchDetail.js.changePrincipal = function () {
    layer.open({
        type:2,
        title:"任务指派",
        area:['300px','250px'],
        content:[path + '/workbench/changePrincipalInit.do', 'no']
    });
};

workbenchDetail.js.reject = function () {
    layer.open({
        type:2,
        title:"任务退回",
        area:['300px','200px'],
        content:[path + '/workbench/rejectInit.do', 'no']
    });
};



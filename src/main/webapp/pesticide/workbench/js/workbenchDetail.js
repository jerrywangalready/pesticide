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
        console.info(data)
        data.type = type;
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

/**
 * 送测按钮点击事件
 */
workbenchDetail.js.pushButtonClick = function (state) {
    layer.open({
        type:2,
        title:"选择送测模块",
        area:['300px','500px'],
        content:[path + '/workbench/choseModelInit.do?state='+state, 'no']
    });
};
workbenchDetail.js.getParameter = function () {
    var param = {};
    param.objectCode = getParameter(location.hash,"obj","");
    param.model_code = $("#model_code").val();
    param.uuid = $("#uuid").val();
    param.issueType = $("#issue_type").val();
    param.state = $("#state").val();
    return param;
};
workbenchDetail.js.return = function () {
    setHash("on=workbench/init&obj="+getParameter(location.hash,"obj",""));
};
// 指派
workbenchDetail.js.changePrincipal = function () {
    layer.open({
        type:2,
        title:"任务指派",
        area:['300px','250px'],
        content:[path + '/workbench/changePrincipalInit.do', 'no']
    });
};

workbenchDetail.js.update = function () {
    alert()
};

// 修改状态
workbenchDetail.js.changeState = function (state) {
    var businessId = $("#uuid").val();
    var issueType = $("#issue_type").val();
    $.post(path + '/workbench/changeState.do',{businessId:businessId,issueType:issueType,state:state},function (data) {
        if(data == "true"){
            parent.layer.msg("操作成功!");
            workbenchDetail.js.return();
        }else {
            layer.alert("操作失败!");
        }
    });
};

// 修改状态
workbenchDetail.js.changeStateWithReason = function (state) {
    var title = "";
    switch (state){
        case "1": title = "退回";break;
        case "7": title = "拒绝";break;
        case "9": title = "废弃";break;
    }
    $("#state").val(state);
    layer.open({
        type:2,
        title:title+"原因",
        area:['300px','200px'],
        content:[path + '/workbench/inputReason.do', 'no']
    });
};


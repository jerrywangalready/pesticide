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
        var html = template('attachment_template', {list: data});
        $("#attachment_box").html(html);
    });

    workbenchDetail.js.initOperation(uuid);

};

workbenchDetail.js.initOperation = function (uuid) {
    // 初始化操作日志
    $.post(path + "/workbench/getRecord.do", {businessId: uuid}, function (data) {
        var html = template('operation_template', {list: data});
        $("#operation_details").html(html);
        //
        $(".recent-posts li:eq(1)").slideDown("normal");
        //
        $("#remark").enter(function () {
            workbenchDetail.js.submitRemark();
        });
    });
};

/**
 * 送测按钮点击事件
 */
workbenchDetail.js.pushButtonClick = function (state) {
    layer.open({
        type: 2,
        title: "选择送测模块",
        area: ['300px', '500px'],
        content: [path + '/workbench/choseModelInit.do?state=' + state, 'no']
    });
};
workbenchDetail.js.getParameter = function () {
    var param = {};
    param.objectCode = getParameter(location.hash, "obj", "");
    param.model_code = $("#model_code").val();
    param.uuid = $("#uuid").val();
    param.issueType = $("#issue_type").val();
    param.state = $("#state").val();
    return param;
};
workbenchDetail.js.return = function () {
    setHash("on=workbench/init&obj=" + getParameter(location.hash, "obj", ""));
};
// 指派
workbenchDetail.js.changePrincipal = function () {
    layer.open({
        type: 2,
        title: "任务指派",
        area: ['300px', '250px'],
        content: [path + '/workbench/changePrincipalInit.do', 'no']
    });
};

workbenchDetail.js.update = function () {
    var hash = location.hash;
    var obj = getParameter(hash, "obj", "");
    var uuid = getParameter(hash, "uuid", "");
    var type = getParameter(hash, "type", "");
    setHash("on=workbench/update&obj=" + obj + "&uuid=" + uuid + "&type=" + type);
};

// 修改状态
workbenchDetail.js.changeState = function (state) {
    var businessId = $("#uuid").val();
    var issueType = $("#issue_type").val();
    $.post(path + '/workbench/changeState.do', {
        businessId: businessId,
        issueType: issueType,
        state: state
    }, function (data) {
        if (data == "true") {
            parent.layer.msg("操作成功!");
            workbenchDetail.js.return();
        } else {
            layer.alert("操作失败!");
        }
    });
};

// 修改状态
workbenchDetail.js.changeStateWithReason = function (state) {
    var title = "";
    switch (state) {
        case "1":
            title = "退回";
            break;
        case "7":
            title = "拒绝";
            break;
        case "9":
            title = "废弃";
            break;
    }
    $("#state").val(state);
    layer.open({
        type: 2,
        title: title + "原因",
        area: ['300px', '200px'],
        content: [path + '/workbench/inputReason.do', 'no']
    });
};

// download attachment
workbenchDetail.js.downloadAttachment = function (uuid) {
    var form = $("<form>");//定义一个form表单
    form.attr("style", "display:none");
    form.attr("target", "");
    form.attr("method", "post");
    form.attr("action", path + "/workbench/downloadAttachment.do");
    var input = $("<input>");
    input.attr("type", "hidden");
    input.attr("name", "uuid");
    input.attr("value", uuid);
    $("body").append(form);//将表单放置在web中
    form.append(input);
    form.submit();//表单提交
};

workbenchDetail.js.submitRemark = function () {
    var uuid = $("#uuid").val();
    var remark = $("#remark").val();
    $.post(path + "/workbench/submitRemark.do", {uuid: uuid, remark: remark}, function (data) {
        if (data == "true") {
            workbenchDetail.js.initOperation(uuid);
        } else {
            alert("操作失败");
        }
    });
};

workbenchDetail.js.uploadAttachment = function (uuid) {
    $("#attachment_update_box").show();
    $("#attachment_box").hide();
    // 上传附件组件初始化
    $.post(path + "/issuePool/getAttachment.do", {businessId: uuid}, function (data) {
        var initialPreview = [];
        var initialPreviewConfig = [];
        for (var i = 0; i < data.length; i++) {
            initialPreview[i] = "/pesticide/comm/image/icon/" + data[i].image;
            var showDelete = false;
            if (comm.js.username == data[i].create_user) {
                showDelete = true;
            }
            initialPreviewConfig[i] = {
                caption: data[i].file_name,
                size: data[i].file_size,
                width: "120px",
                url: "/pesticide/creatioStrSSddddddddn/deleteAttachment.do",
                extra: {uuid:data[i].uuid},
                key: i,
                showDrag: false,
                showZoom: false,
                showDelete: showDelete
            };
        }
        // 上传附件组件初始化
        $("#attachment").fileinput({
            language: 'zh',
            showUpload: false,
            initialPreview: initialPreview,
            uploadUrl: path + '/creation/uploadFile.do',
            uploadExtraData: {businessId: uuid},
            initialPreviewAsData: true,
            initialPreviewConfig: initialPreviewConfig,
            // deleteUrl: path + '/creation/deleteAttachment.do',
            // deleteExtraData: {uuid: 'jjjjjj'},// 删除时额外传入的参数
            hiddenThumbnailContent: true,
            showPreview: true,
            overwriteInitial: false,
            showUploadedThumbs: true,
            maxFileSize: 2147483648,//2GB
            showCaption: true,
            dropZoneTitleClass: 'hide',
            dropZoneEnabled: false,
            showRemove: false,
            showCancel: false,
            showClose: false,
            // allowedFileTypes: ['jpg','txt','jpeg','png','xls'],
            // mainClass: "glyphicon glyphicon-send",
            // previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
            // showUploadedThumbs:false,
            initialCaption: "添加附件",
            layoutTemplates: {}
        }).on("filebatchselected", function (event, files) {
            $(this).fileinput("upload");
        }).on("fileuploaded", function (event, data, previewId, index) {
            workbenchDetail.js.initOperation(uuid);
            // console.info(event)
            // console.info(data)
            // console.info(previewId)
            // console.info(index)
        });
    });
};



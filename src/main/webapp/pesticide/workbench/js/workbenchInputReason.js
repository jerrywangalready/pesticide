/**
 * Created by jerrywang on 2017/5/23.
 */
jQuery.namespace("workbenchInputReason");
$(function () {
    workbenchInputReason.js.param = parent.workbenchDetail.js.getParameter();
    workbenchInputReason.js.init();
});

workbenchInputReason.js = {};
workbenchInputReason.js.init = function () {

};

workbenchInputReason.js.changeStateWithReason = function () {
    var param = {};
    param.businessId = workbenchInputReason.js.param.uuid;
    param.issueType = workbenchInputReason.js.param.issueType;
    param.state = workbenchInputReason.js.param.state;
    param.remark = $("#remark").val();
    $.ajax({
        type:'POST',
        url:path+'/workbench/changeStateWithReason.do',
        contentType:'application/json',
        data:JSON.stringify(param),
        success:function (data) {
            if(data == "true"){
                parent.layer.msg("操作成功!");
                comm.js.closeLayer();
                parent.workbenchDetail.js.return();
            }else {
                layer.alert("操作失败!");
            }
        }

    });
};
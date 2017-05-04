/**
 * Created by jerrywang on 2017/5/4.
 */
jQuery.namespace("workbenchReject");
$(function () {
    workbenchReject.js.param = parent.workbenchDetail.js.getParameter();
});

workbenchReject.js = {};
workbenchReject.js.reject = function () {
    var param = {};
    param.businessId = workbenchReject.js.param.uuid;
    param.issueType = workbenchReject.js.param.issueType;
    param.state = "6";
    param.remark = $("#remark").val();
    $.ajax({
        type:'POST',
        url:path+'/workbench/reject.do',
        contentType:'application/json',
        data:JSON.stringify(param),
        success:function (data) {
            if(data == "true"){
                parent.layer.msg("退回成功!");
                comm.js.closeLayer();
                parent.workbenchDetail.js.return();
            }else {
                layer.alert("退回失败!");
            }
        }

    });
};

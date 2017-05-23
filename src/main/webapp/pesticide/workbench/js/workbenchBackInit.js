/**
 * Created by jerrywang on 2017/5/23.
 */
jQuery.namespace("workbenchBackInit");
$(function () {
    workbenchBackInit.js.param = parent.workbenchDetail.js.getParameter();
    workbenchBackInit.js.init();
});

workbenchBackInit.js = {};
workbenchBackInit.js.init = function () {

};

workbenchBackInit.js.back = function () {
    var param = {};
    param.businessId = workbenchBackInit.js.param.uuid;
    param.issueType = workbenchBackInit.js.param.issueType;
    param.remark = $("#remark").val();
    $.ajax({
        type:'POST',
        url:path+'/workbench/back.do',
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
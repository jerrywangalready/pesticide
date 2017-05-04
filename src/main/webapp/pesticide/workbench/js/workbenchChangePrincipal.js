/**
 * Created by jerrywang on 2017/5/1.
 */
jQuery.namespace("workbenchChangePrincipal");
$(function () {
    workbenchChangePrincipal.js.param = parent.workbenchDetail.js.getParameter();
    workbenchChangePrincipal.js.init();
});

workbenchChangePrincipal.js = {};
workbenchChangePrincipal.js.init = function () {
    // 负责人下拉框初始化
    $("#principal").dict({table:"V_OBJECT_USERS",key:"username",value:"nickname",where:"object_code='"+workbenchChangePrincipal.js.param.objectCode+"'"});


};

workbenchChangePrincipal.js.changePrincipal = function () {
    var param = {};
    param.businessId = workbenchChangePrincipal.js.param.uuid;
    param.issueType = workbenchChangePrincipal.js.param.issueType;
    param.principal = $("#principal").val();
    param.principalName = $("#principal").find("option:selected").text();
    param.remark = $("#remark").val();
    $.ajax({
        type:'POST',
        url:path+'/workbench/changePrincipal.do',
        contentType:'application/json',
        data:JSON.stringify(param),
        success:function (data) {
            if(data == "true"){
                parent.layer.msg("指派成功!");
                comm.js.closeLayer();
                parent.workbenchDetail.js.return();
            }else {
                layer.alert("指派失败!");
            }
        }
    
    });
};

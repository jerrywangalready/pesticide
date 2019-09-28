/**
 * 工作流插件
 * author 王景钰
 * date 2018.11.24
 */
$.workflow = function(properties) {
    var templateId = properties.templateId == undefined ? "" : properties.templateId;
    var businessId = properties.businessId == undefined ? "" : properties.businessId;
    // 根据templateId和businessId判断是否已经开启相关流程
    // 如果未开启, 创建流程
    var returnData = {};
    $.ajax({
        type : 'POST',
        async : false,
        url : path+'/workflow/task.do',
        data : {templateId : templateId, businessId : businessId},
        success:function (result) {
            returnData.states = "1"


            layer.open({
                type : 2,
                title : "选择审批人",
                area : ['300px','250px'],
                content : [path + '/workflow/initChooseApproverPage.do', 'no']
            });
        }

    });
    // 获取下一级
    return returnData;

};
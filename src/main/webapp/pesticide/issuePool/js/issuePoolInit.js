/**
 * Created by DCH on 2017/4/11.
 */

jQuery.namespace("issuePool");
$(function () {
    issuePool.js.init();
});

issuePool.js = {};
// 初始化
issuePool.js.init = function () {
    $("#query_issue_type").dict({table: "t_code_list", type: "issueType", key:"code_key", value:"code_value"});
    $("#query_state").dict({table: "t_code_list", type: "state", key:"code_key", value:"code_value"});
    issuePool.js.query();
    // 初始化回车事件
    $("#issue_code,#issue_name,#query_create_user,#query_principal").enter(function () {
        $("#query_box").query();
    });
    $("#query_issue_type,#query_state").change(function () {
        $("#query_box").query();
    });
};

issuePool.js.query = function () {
    var param = $("#query_box").collector();
    param.principal = comm.js.username;
    param.object_code = getParameter(location.hash,"obj","");
    $.ajax({
        type:'POST',
        url:path+'/issuePool/getIssueList.do',
        contentType:'application/json',
        data:JSON.stringify(param),
        success:function (data) {
            var html = template('grid_template',{'list':data.list});
            $("#grid").html(html);
            // 初始化页码按钮
            $("#page-bar").page(data);
        }
    });
}

// 详细信息
issuePool.js.detail = function (uuid, type) {
    $.ajax({
        type:'POST',
        url:path+'/issuePool/getIssueList.do',
        contentType:'application/json',
        data:JSON.stringify(param),
        success:function (data) {

        }
    });
};
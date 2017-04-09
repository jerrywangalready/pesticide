/**
 * Created by jerrywang on 2017/1/15.
 */
jQuery.namespace("workbench");
$(function () {
    workbench.js.init();
});

workbench.js = {};
// 初始化
workbench.js.init = function () {
    var param = $("#query_box").collector();
    param.principal = comm.js.username;
    param.object_code = getParameter(location.hash,"obj","");
    $.ajax({
        type:'POST',
        url:path+'/workbench/getIssueList.do',
        contentType:'application/json',
        data:JSON.stringify(param),
        success:function (data) {
            var html = template('grid_template',{'list':data.list});
            console.info(html)
            $("#grid").html(html);
            // 初始化页码按钮
            $("#page-bar").page(data);
        }
    });
};
// 详细信息
workbench.js.detail = function (uuid, type) {
    alert(uuid);
    alert(type);
    $.ajax({
        type:'POST',
        url:path+'/workbench/getIssueList.do',
        contentType:'application/json',
        data:JSON.stringify(param),
        success:function (data) {

        }
    });
};
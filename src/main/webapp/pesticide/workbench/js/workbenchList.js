/**
 * Created by jerrywang on 2017/1/15.
 */
jQuery.namespace("workbenchList");
$(function () {
    workbenchList.js.init();
});

workbenchList.js = {};
// 初始化
workbenchList.js.init = function () {
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
            $("#grid").html(html);
            // 初始化页码按钮
            $("#page-bar").page(data);
        }
    });
};
// 详细信息
workbenchList.js.detail = function (uuid, type) {
    var hash = location.hash;
    var obj = getParameter(hash, "obj", "");
    setHash("on=workbench/detail&obj="+obj+"&uuid="+uuid+"&type="+type);
};
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
    $.dictInit([{name:"state",table:"t_code_list",type:"state", key:"code_key", value:"code_value"}]);

    var param = $("#query_box").collector();
    param.principal = comm.js.username;
    param.object_code = getParameter(location.hash,"obj","");
    $.ajax({
        type:'POST',
        url:path+'/workbench/getIssueList.do',
        contentType:'application/json',
        data:JSON.stringify(param),
        success:function (data) {
            $("a[name=bell]").attr("dt",data.dt);
            var html = template('grid_template',{'list':data.query.list});
            $("#grid").html(html);
            // 初始化页码按钮
            $("#page-bar").page(data.query);
        }
    });
};
workbenchList.js.isClick = true;
// 详细信息
workbenchList.js.detail = function (uuid, type) {
    workbenchList.js.isClick = true;
    setTimeout(function () {
        if (workbenchList.js.isClick){
            var hash = location.hash;
            var obj = getParameter(hash, "obj", "");
            setHash("on=workbench/detail&obj="+obj+"&uuid="+uuid+"&type="+type);
        }
    },300);
};

workbenchList.js.detailForNew = function (uuid, type) {
    workbenchList.js.isClick = false;
    var hash = location.hash;
    var obj = getParameter(hash, "obj", "");
    window.open(path + "/index/index.do#on=workbench/detail&obj="+obj+"&uuid="+uuid+"&type="+type);
};
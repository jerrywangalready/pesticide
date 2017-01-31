/**
 * Created by jerrywang on 2017/1/21.
 */
jQuery.namespace("demo");
$(function () {
    demo.js.init();
});

demo.js = {};
demo.js.init = function () {
    // 初始化查询项(必须先初始化字典表)
    $.dictInit([{name:"yn",table:"s_code_list",type:"YN"}]);
    $("#queryType").dict({table:"s_code_list",type:"YN"});
    // 初始化列表数据
    demo.js.query();
    // 初始化回车事件
    $("#queryCode,#queryName").enter(function () {
        $("#query_box").query();
    });
    $("#queryType").change(function () {
        $("#query_box").query();
    });

};

demo.js.query = function () {

    var collector = $("#query_box").collector();

    $.ajax({
        type:'POST',
        url:path+"/demo/queryDemo.do",
        contentType:'application/json',
        data:JSON.stringify(collector),
        success:function (data) {
            var html = template('users',{'list':data.list});
            $("#table_div").html(html);
            // 初始化页码按钮
            $("#page-bar").page(data);
        }
    });

};

demo.js.collector = function () {
    var hash = location.hash;
    var pn = getParameter(hash,"pn","1");
    var queryCode = $("#queryCode").val();
    var queryName = $("#queryName").val();
    var queryType = $("#queryType").val();

    return {queryCode:queryCode,queryName:queryName,queryType:queryType,pageNum:pn};
};

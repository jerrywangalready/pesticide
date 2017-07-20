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
            console.info(data);
            var html = template('demo_users',{'list':data.list});
            console.info(html);
            $("#table_div").html(html);
            // 初始化页码按钮
            $("#page-bar").page(data);
        }
    });

};

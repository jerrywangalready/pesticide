/**
 * Created by jerrywang on 2017/1/21.
 */
jQuery.namespace("demo");
$(function () {
    demo.js.init();
});

demo.js = {};
demo.js.init = function () {
    // 初始化列表数据
    demo.js.query();
    // 初始化查询项
    $("#queryType").dict({table:"s_code_list",type:"YN"});
    // 初始化回车事件
    $("#queryCode,#queryName").enter(function () {
        demo.js.query();
    });
    $("#queryType").change(function () {
        demo.js.query();
    });

};

demo.js.query = function () {

    var collector = demo.js.collector();

    console.info(collector)

    $.ajax({
        type:'POST',
        url:path+"/demo/queryDemo.do",
        contentType:'application/json',
        data:JSON.stringify(collector),
        success:function (data) {
            var html = template('users',{'list':data});
            $("#table_div").html(html);
        }
    });

};

demo.js.collector = function () {
    var queryCode = $("#queryCode").val();
    var queryName = $("#queryName").val();
    var queryType = $("#queryType").val();

    return {queryCode:queryCode,queryName:queryName,queryType:queryType};
};

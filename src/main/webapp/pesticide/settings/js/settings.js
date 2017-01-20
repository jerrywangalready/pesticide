/**
 * Created by Administrator on 2017/1/15.
 */
jQuery.namespace("settings");
$(function () {
    settings.js.init();
});
settings.js = {};
settings.js.init = function () {

    // 获取type值
    var type = getParameter(location.hash,"type","users");
    // 绑定tab点击事件
    $("#tablist").find("a").each(function () {
        var type_attr = $(this).attr("type");
        if(type_attr == type){
            $(this).parent().addClass("active");
        }
        $(this).click(function () {
            settings.js.clickTab(type_attr);
        })
    });

  $.post(path+"/settings/queryUsers.do",
      {},
      function (data) {
      console.info(data)
          $.dict({table:"s_code_list",type:"YN"},1);
          var html = template('test',{'list':data});
      $("#testDiv").html(html);
  });
};

// 点击tab
settings.js.clickTab = function (type) {
    var on = getParameter(location.hash,"on","");
    setHash("on=" + on + "&type=" + type);
};

//查询用户列表
settings.js.queryUsers=function () {
    $.post(path+"/settings/queryUsers.do",
        {},
        function (data) {
            console.info(data);
            var html = template('test',{'list':data});
        $("#testDiv").html(html);
        });
};


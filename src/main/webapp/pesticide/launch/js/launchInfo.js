/**
 * Created by jerrywang on 2017/6/26.
 */
jQuery.namespace("launchInfo");
$(function () {
    launchInfo.js.param = parent.launchInit.js.getParameter();
    launchInfo.js.init();
});
launchInfo.js = {};
launchInfo.js.init = function () {
    $.post(path + "/launch/checkRole.do",{objectCode:launchInfo.js.param.objectCode},function (data) {
        if(data == "true"){
            $("#launch_button").show();
        }
    });
    $.post(path + "/launch/getLaunchDetail.do"
        ,{objectCode:launchInfo.js.param.objectCode,versionCode:launchInfo.js.param.versionCode}
        ,function (data) {
        $("#total_num").text(data.length);
        for(var i=0;i<data.length;i++){
            $("#model_list_ul").append("<li>"+data[i].model_code+"</li>");
        }

    });
};

launchInfo.js.forLaunch = function () {
    $.post(path + "/launch/forLaunch.do",{objectCode:launchInfo.js.param.objectCode,versionCode:launchInfo.js.param.versionCode},function (data) {
        if(data == "true"){
            parent.layer.msg("操作成功!");
            comm.js.closeLayer();
            parent.launchInit.js.init();
        }else {
            layer.alert("操作失败!");
        }
    });
};
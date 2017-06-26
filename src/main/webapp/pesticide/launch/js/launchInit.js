/**
 * Created by jerrywang on 2017/6/13.
 */
jQuery.namespace("launchInit");
$(function () {
    launchInit.js.init();
});

launchInit.js = {};
launchInit.js.init = function () {

    var param = {};
    param.object_code = getParameter(location.hash,"obj","");
    $.ajax({
        type:'POST',
        url:path+'/launch/getIssueList.do',
        contentType:'application/json',
        data:JSON.stringify(param),
        success:function (data) {
            var html = template('laneTemplate',{'list':data});
            $(".lane-box").html(html);

            var maxHeight = 0;
            $(".lane").each(function () {
                if($(this).height()>maxHeight){
                    maxHeight = $(this).height();
                }
            });

            $(".lane-box").width(238*data.length);

            $(".lane").height(maxHeight);

            $(".lane").sortable({
                connectWith: ".lane",
                items: "div:not(.notSortable)",
                start: function(event, ui) {
                    // console.info(ui)
                },
                stop: function(event, ui) {

                    param.uuid = $(ui.item).attr("uuid");
                    param.issueType = $(ui.item).attr("it");
                    param.version_code = $(ui.item).parent().attr("vc");
                    $.ajax({
                        type:'POST',
                        url:path+'/launch/changeVersionCode.do',
                        contentType:'application/json',
                        data:JSON.stringify(param),
                        success:function (data) {
                            console.info(data);
                        }

                    });
                    launchInit.js.checkedPublishButton();
                }
            }).disableSelection();
            launchInit.js.checkedPublishButton();
        }

    });
};

launchInit.js.checkedPublishButton = function () {
    var flag = true;
    // 所有问题都解决才能开放[上线]按钮
    $(".lane:eq(0) .task-item").each(function () {
        if("6"!=$(this).attr("state")){
            flag = false;
            return false;
        }
    });
    if(flag){
        $("#publish_button").removeAttr("disabled");
    }else{
        $("#publish_button").attr("disabled","disabled");
    }
};

launchInit.js.forLaunch = function () {

    layer.open({
        type:2,
        title:"Model List",
        area:['500px','450px'],
        scrollbar:false,
        content:[path + '/launch/launchInfo.do', 'no']
    });
};

launchInit.js.getParameter = function () {
    var objectCode = getParameter(location.hash, "obj", "");
    var versionCode = $(".lane:eq(0)").attr("vc");
    var param = {};
    param.objectCode = objectCode;
    param.versionCode = versionCode;
    return param;
};

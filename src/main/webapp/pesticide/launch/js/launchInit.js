/**
 * Created by jerrywang on 2017/6/13.
 */
jQuery.namespace("launchInit");
$(function () {
    launchInit.js.init();
});

launchInit.js = {};
launchInit.js.init = function () {

    launchInit.js.gridInit();

    // 完成时间
    $('.form_date').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        // todayBtn:  1,
        autoclose: 1,
        todayHighlight: 1,
        startView: 2,
        minView: 2,
        forceParse: 0
    });
    
    $("#beginDate,#endDate").change(function () {
        launchInit.js.gridInit();
    });

    $("#search-switch").click(function () {
        var fla = $(this).attr("fla");
        if(fla == "1"){
            $("#search-switch").attr("fla","0").addClass("search-switch-close");
            $("#search_box").css({transform: "rotate(0deg)"});
        }else{
            $("#search-switch").attr("fla","1").removeClass("search-switch-close");
            $("#search_box").css({transform: "rotate(-90deg)"});

        }
    });

};

launchInit.js.gridInit = function () {
    var obj = getParameter(location.hash,"obj","");
    var param = {};
    param.object_code = obj;
    param.beginDate = $("#beginDate").val();
    param.endDate = $("#endDate").val();
    $.ajax({
        type:'POST',
        url:path+'/launch/getIssueList.do',
        contentType:'application/json',
        data:JSON.stringify(param),
        success:function (data) {
            var html = template('laneTemplate',{'list':data});
            $(".lane-box").html(html);
            // 找出可上线版本,增加上线按钮
            $.post(path + "/launch/getLaunchVersion.do",{obj:obj},function (data) {
                console.info(data)
                $("span[name=version_code][vc='"+data+"']").after("<button id=\"publish_button\" type=\"button\" class=\"right btn btn-xs btn-success\" style=\"margin-top: -2px;\" onclick=\"launchInit.js.forLaunch()\">上线</button>");
            });

            launchInit.js.resizeMaxHeight();

            $(".lane-box,.lane-box-mirror").width(238*data.length+8);

            $(".sort").sortable({
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
                            // console.info(data);
                        }

                    });
                    launchInit.js.checkedPublishButton();

                    launchInit.js.resizeMaxHeight();
                }
            }).disableSelection();
            launchInit.js.checkedPublishButton();

            // 左右阴影
            $(".task-pool").scroll(function () {
                if($(this).scrollLeft() < 1){
                    $(".left-box-shadow").hide();
                }else {
                    $(".left-box-shadow").show();
                }
                if($(this).scrollLeft() > 238*data.length-944){
                    $(".right-box-shadow").hide();
                }else {
                    $(".right-box-shadow").show();

                }

            }).scroll();

            // 浮动滚动条
            $(".task-pool").scroll(function () {
                $(".task-pool-mirror").scrollLeft($(this).scrollLeft());
            });
            $(".task-pool-mirror").scroll(function () {
                $(".task-pool").scrollLeft($(this).scrollLeft());
            });
            var height = $(".task-pool").height();
            var top = $(".task-pool").offset().top;
            // 浏览器右侧滚动条滚动时触发
            $(window).scroll(function () {
                var distancefrombottom = height - ($(window).height() - top + $(window).scrollTop());
                if (1 >= distancefrombottom) {
                    $(".task-pool-mirror").hide();
                } else {
                    $(".task-pool-mirror").show();
                    $(".task-pool-mirror").scrollLeft($(".task-pool").scrollLeft());
                    $(".task-pool-mirror").css("top", 0 - distancefrombottom - 28);
                }
            }).scroll().resize(function () {
                $(window).scroll();
            });


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

launchInit.js.resizeMaxHeight = function () {
    var num = 0;
    $(".lane").each(function () {
        var thisSize = $(this).children().length;
        if(thisSize>num){
            num = thisSize;
        }
    });
    $(".lane,.left-box-shadow,.right-box-shadow").height(num*125-90);
};

launchInit.js.getJar = function () {
    layer.open({
        type:2,
        title:"Model List",
        area:['500px','450px'],
        scrollbar:false,
        content:[path + '/getJar/init.do', 'no']
    });
};
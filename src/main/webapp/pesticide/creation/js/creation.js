/**
 * Created by jerrywang on 2017/1/18.
 */
jQuery.namespace("creation");
$(function () {
    creation.js.init();
});

creation.js = {};
// 富文本域
creation.js.description;
// 任务类型
creation.js.taskType = '1';
// 初始化
creation.js.init = function () {

    var obj = getParameter(location.hash,"obj","");

    CKEDITOR.replace('description',{ height: '440px'});
    creation.js.description = CKEDITOR.instances.description;
    creation.js.description.on('focus',function () {
        $('#cke_description').addClass("edit_focus");
    });
    creation.js.description.on('blur',function () {
        $('#cke_description').removeClass("edit_focus");
    });
    $("input[name=taskType_code]").each(function () {
        var taskRadio = $(this);
        taskRadio.parent().click(function () {
            var tv = taskRadio.val();
            switch(tv){
                case '1':creation.js.chooseTask1();break;
                case '2':creation.js.chooseTask2();break;
                case '3':creation.js.chooseTask3();break;
            }
        });
    });

    // 模块下拉框
    $("#model_select").dict({table:"s_model",key:"model_code",value:"model_name",where:"isenable='1' and object_code='"+obj+"'"});
    // 任务类型下拉框
    $("#taskType_select").dict({table:"t_code_list",type:"taskType",where:"isenable='1'"});
    // 开发人员下拉框
    $("#dev_staff_select").dict({table:"V_OBJECT_USERS",key:"username",value:"nickname",where:"object_code='"+obj+"'"});
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
    // 测试人员下拉框
    $("#test_staff_select").dict({table:"V_OBJECT_USERS",key:"username",value:"nickname",where:"object_code='"+obj+"'"});
    // 版本号下拉框
    $("#version_code").dict({table:"S_VERSION",key:"VERSION_CODE",value:"VERSION_CODE",where:"object_code='"+obj+"' AND publish_date > SYSDATE()"});
    // 绑定灰黑样式切换
    $("#model_select,#taskType_select,#dev_staff_select,#test_staff_select,#version_code").change(function () {
        if($(this).val()==""){
            $(this).addClass("select_empty");
        }else{
            $(this).removeClass("select_empty");
        }

    });


    $("#link_info_close").click(function () {
        $("#link_info").slideUp();
    });


};
// 保存
creation.js.save = function(todo){
    var param = $("#main_form").validate();
    var description = creation.js.description.getData();
    param.description = description;
    var obj = getParameter(location.hash,"obj","");
    param.object_code = obj;
    param.taskType_code = creation.js.taskType;
    param.todo = todo;
    if(param){
        $.ajax({
            type:'POST',
            url:path+'/creation/save.do',
            contentType:'application/json',
            data:JSON.stringify(param),
            success:function (data) {
                if("save" == todo){
                    layer.msg('保存成功!', {
                        offset: '50px'
                    });
                }
                if (data != '') {
                    $("#uuid").val(data);
                }
            }

        });
    }





};
// 提交
creation.js.commit = function () {
    // 保存数据
    creation.js.save('commit');
    // 动画
    var scrollT = $(document).scrollTop();
    // var scrollL = $(document).scrollLeft();
    var x = $("#menu_task_pool").offset();
    var h = $(window).height();
    var w = $(window).width();
    $("body").append("<div class='greybackground'></div><div id='letter' style='display:none;top:"+(h/2-50)+"px;left:"+(w/2-50)+"px;position: fixed;z-index: 99999;'><span class='glyphicon glyphicon-envelope' style='font-size:100px;'></span></div>")
    $(".greybackground").show("fast");
    $(".greybackground").addClass("addw").addClass("add");
    setTimeout(function () {
        $(".greybackground").hide();
        $("#letter").fadeIn("fast");
        $("#letter").animate({top:h/2-110},"100");
        $("#letter").animate({top:"-180px"},"500");
        $("body").append("<div id='miniletter' style='top:-30px;left:"+(x.left+25)+"px;position: fixed;z-index: 99999;'><span class='glyphicon glyphicon-envelope' style='font-size:13px;'></span></div>")
        setTimeout(function () {
            if($("#top_toolbar").is(":visible")){
                $("#li_task_pool").children().addClass("shake-horizontal-add");
                setTimeout(function () {
                    $("#li_task_pool").children().removeClass("shake-horizontal-add");
                },200);
            }else {
                $("#miniletter").animate({top:x.top-scrollT});
                $("#miniletter").fadeOut();
                setTimeout(function () {
                    $("#menu_task_pool").addClass("shake-horizontal-add");
                    setTimeout(function () {
                        $("#menu_task_pool").removeClass("shake-horizontal-add");
                    }, 200);
                },500);
            }

        },1100);
    },400);


    // $("#lalla").slideUp(10);
    // 刷新页面
    // $(window).hashchange();
    $("#main_form").find("input,select").each(function () {
        $(this).val("");
    })
    creation.js.description.setData("");


};
// 选择开发任务
creation.js.chooseTask1 = function () {
    creation.js.taskType = '1';
    $("#monitor").removeClass().addClass("mb_15 btn-primary");
    $("#dev_staff_select").show();
    $("#test_staff_select").show();
    $("#link_button").hide();
    $("#link_info_close").click();
    $(".bug_level").hide();

};

// 选择测试任务
creation.js.chooseTask2 = function () {
    creation.js.taskType = '2';
    $("#monitor").removeClass().addClass("mb_15 btn-success");
    $("#dev_staff_select").hide();
    $("#test_staff_select").show();
    $("#link_button").show();
    $("#link_info_close").click();
    $(".bug_level").hide();
};

// 选择修改任务
creation.js.chooseTask3 = function () {
    creation.js.taskType = '3';
    $("#monitor").removeClass().addClass("mb_15 btn-warning");
    $("#dev_staff_select").show();
    $("#test_staff_select").hide();
    $("#link_button").show();
    $("#link_info_close").click();
    $(".bug_level").show();
};
// 获取关联信息
creation.js.getLinkInfo = function () {
    var object_code = getParameter(location.hash,'obj','');
    $.ajax({
        type:'POST',
        url:path+"/creation/getLinkInfo.do",
        contentType:'application/json',
        data:JSON.stringify({"taskType":creation.js.taskType,'object_code':object_code}),
        success:function (data) {
            $("#link_info_body").html("");
            for(var i=0;i<data.length;i++){
                var item = data[i];
                $("#link_info_body").append("<div class='modal-item'><span mc='"+item.model_code+"' sta='"+item.staff+"' vc='"+item.version_code+"'>"+item.code+" : "+item.title+"</span></div>");
            }
            // 给弹出层的关联信息绑定点击事件
            $("#link_info_body span").each(function () {
                var text = $(this).text();
                var model_code = $(this).attr("mc");
                var staff = $(this).attr("sta");
                var version_code = $(this).attr("vc");
                $(this).parent().on('click',function () {
                    $("#link_table").modal('hide');
                    $("#link_info_text").text(text);
                    switch (creation.js.taskType) {
                        case "2":$("#link_info").removeClass("btn-success").addClass("btn-primary");$("#test_staff_select").val(staff);break;
                        case "3":$("#link_info").removeClass("btn-primary").addClass("btn-success");$("#dev_staff_select").val(staff);break;
                    }
                    $("#link_info").slideDown('fast');
                    $("#model_select").val(model_code);


                    $("#version_code").val(version_code);

                });
            })

        }
    });
};

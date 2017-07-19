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
// 项目编号
creation.js.objectCode = "";
creation.js.flag = false;
// 初始化
creation.js.init = function () {
    console.info(path)
    // 获取uuid
    var uuid = comm.js.getUUID();
    $("#uuid").val(uuid);

    creation.js.objectCode = getParameter(location.hash,"obj","");
    var h = $(window).height()-377;
    var th = 440 > h ? 440 : h;
    CKEDITOR.replace('description',{ height: th + 'px'});
    creation.js.description = CKEDITOR.instances.description;
    creation.js.description.on('focus',function () {
        $('#cke_description').addClass("edit_focus");
    });
    creation.js.description.on('blur',function () {
        $('#cke_description').removeClass("edit_focus");
    });

    // 模块下拉框
    $("#model_select").dict({table:"s_model",key:"model_code",value:"model_name",where:"isenable='1' and object_code='"+creation.js.objectCode+"'"});
    // 负责人下拉框初始化
    $("#principal").dict({table:"V_OBJECT_USERS",key:"username",value:"nickname",where:"object_code='"+creation.js.objectCode+"'"});
    // 优先级下拉框初始化
    $("#priority").dict({table:"t_code_list",key:"code_key",value:"code_value",type:"priority"});
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
    // 版本号下拉框
    $("#version_code").dict({table:"S_VERSION",key:"VERSION_CODE",value:"VERSION_CODE",where:"object_code='"+creation.js.objectCode+"' AND publish_date >= curdate() ORDER BY publish_date desc"})
        .focusin(function () {
            creation.js.flag = false;
            $(".version-add-div").animate({marginLeft:'-23px'},"fast");
        }).focusout(function () {
            if(!creation.js.flag){
                $(".version-add-div").animate({marginLeft:'0px'},"fast");
            }
        });
    $(".version-add-div").mouseenter(function () {
        creation.js.flag = true;
        $("#version_code").blur();
    }).mouseleave(function () {
        if(creation.js.flag){
            $(".version-add-div").animate({marginLeft:'0px'},"fast");
        }
    });
    // 绑定灰黑样式切换
    $("#model_select,#principal,#version_code,#priority").change(function () {
        if($(this).val()==""){
            $(this).addClass("select_empty");
        }else{
            $(this).removeClass("select_empty");
        }

    });

    // bug等级按钮点击事件绑定
    $("input[name=bug_level]").parent().click(function () {
        $(this).find("input").attr("checked",true);
    });

    $("#link_info_close").click(function () {
        $("#link_info").slideUp();
    });

    // 预估工时组件初始化
    $("#working_day").spinner({
        step: 0.5
    });

    // 上传附件组件初始化
    $("#attachment").fileinput({
        language: 'zh',
        showUpload: false,
        initialPreview: [
        ],
        uploadUrl: path + '/creation/uploadFile.do',
        uploadExtraData: {businessId: uuid},
        initialPreviewAsData: true,
        initialPreviewConfig: [
        ],
        hiddenThumbnailContent: true,
        showCaption: false,
        showPreview: true,
        overwriteInitial: false,
        showUploadedThumbs: true,
        maxFileSize: 2147483648,//2GB
        showCaption: true,
        dropZoneTitleClass: 'hide',
        dropZoneEnabled: false,
        showRemove: false,
        showCancel: false,
        showClose: false,
        initialCaption: "添加附件",
        layoutTemplates:{
            actions: '<div class="file-upload-indicator" title="Uploaded" style="margin-left: 0px;"><i class="glyphicon glyphicon-ok-sign text-success"></i></div>\n' +
            '<div class="file-actions">\n' +
                '    <div class="file-footer-buttons">\n' +
            '        {upload} {delete}  {other}' +
            '    </div>\n' +
            '    <div class="clearfix"></div>\n' +
            '</div>'
        }
    }).on("filebatchselected", function(event, files) {
        $(this).fileinput("upload");
    }).on("fileuploaded", function (event, data, previewId, index) {
        // console.info(event)
        // console.info(data)
        // console.info(previewId)
        // console.info(index)
    });

};
// 保存
creation.js.save = function(todo){

    var param = $("#main_form").validate();
    if(param){
        var description = creation.js.description.getData();
        param.description = description;
        param.object_code = creation.js.objectCode;
        param.create_user = comm.js.username;
        param.todo = todo;
        if(param.issueType == 'B'){
            param.bug_level = $("input[name=bug_level]:checked").val();
        }
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
                    $("#mode").val("u");
                    return true;
                }else{
                    $("#mode").val("i");
                }
            }

        });
        return true;
    }else{
        return false;
    }

};
// 提交
creation.js.commit = function () {
    // 保存数据
    var ret = creation.js.save('commit');
    if(ret){

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
            $(".greybackground").remove();
            $("#letter").fadeIn("fast");
            $("#letter").animate({top:h/2-110},"100");
            $("#letter").animate({top:"-180px"},"500");
            $("body").append("<div id='miniletter' style='top:-30px;left:"+(x.left+25)+"px;position: fixed;z-index: 99999;'><span class='glyphicon glyphicon-envelope' style='font-size:13px;'></span></div>")
            setTimeout(function () {
                $("#letter").remove();
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
                            $("#miniletter").remove();

                        }, 200);
                    },500);
                }

            },1100);
        },400);


        // $("#lalla").slideUp(10);
        // 刷新页面
        // $(window).hashchange();
        $("#main_form").find("#title,#deadline,#priority,#working_day,input[type=hidden]").each(function () {
            $(this).val("");
        });
        $("#isUrgency").prop("checked",false);
        if($("input[name=issueType][checked=checked]").val() == "B"){
            var model = "<p>Bug复现步骤</p><hr /><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>实际结果</p>"
                + "<hr /><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>期望结果</p><hr /><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>";
            creation.js.description.setData(model);
        }else {
            creation.js.description.setData("");
        }
    }



};
// 选择Task
creation.js.chooseTask = function (obj) {
    $(obj).find("input").attr("checked",true);
    $("#monitor").removeClass().addClass("mb_15 btn-primary");
    $("#link_info_close").click();
    $("#bug_level_div").hide();
    $("#working_day_div").show();
    creation.js.description.setData("");
};

// 选择bug
creation.js.chooseBug = function (obj) {
    $(obj).find("input").attr("checked",true);
    $("#monitor").removeClass().addClass("mb_15 btn-warning");
    $("#link_info_close").click();
    $("#bug_level_div").show();
    $("#working_day_div").hide();
    var model = "<p>Bug复现步骤</p><hr /><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>实际结果</p>"
            + "<hr /><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p><p>期望结果</p><hr /><p>&nbsp;</p><p>&nbsp;</p><p>&nbsp;</p>";
    creation.js.description.setData(model);
};

// 获取关联信息
creation.js.getLinkInfo = function () {

    setTimeout(function(){
        $("#link_proTask").focus();

    },800);
    // $("#link_proTask").focus();
    // 搜索前序任务绑定输入事件
    $("#link_proTask").keyup(function () {

        var code = $(this).val();
        if(code.length > 0){
            $.post(path+'/creation/searchTask.do',{code:code,objectCode:creation.js.objectCode},function (data) {
                $("#link_info_body").html("");
                for(var i=0;i<data.length;i++){
                    var item = data[i];
                    $("#link_info_body").append("<div class='modal-item'><span tp='"+item.ISSUE_TYPE+"' title='"+item.TITLE+"' tc='"+item.CODE+"'>"+item.CODE+" : "+item.TITLE+"</span></div>");
                    // $("#link_info_body").append("<div class='modal-item'><span mc='"+item.model_code+"' sta='"+item.staff+"' vc='"+item.version_code+"' title='"+item.title+"' tc='"+item.code+"'>"+item.code+" : "+item.title+"</span></div>");
                }
                // 给弹出层的关联信息绑定点击事件
                $("#link_info_body span").each(function () {
                    var text = $(this).text();
                    var taskCode = $(this).attr("tc");
                    var title = $(this).attr("title");
                    var taskType = $(this).attr("tp");
                    // var model_code = $(this).attr("mc");
                    // var staff = $(this).attr("sta");
                    // var version_code = $(this).attr("vc");
                    // 绑定点击一条任务的事件
                    $(this).parent().on('click',function () {
                        $("#link_table").modal('hide');
                        $("#link_info_text").text(text);
                        $("#parent_code").val(taskCode);
                        $("#parent_type").val(taskType);
                        // $("#title").val(title);
                        switch (taskType) {
                            case "T":$("#link_info").removeClass("btn-warning").addClass("btn-primary");
                                     // $("#test_staff_select").val(staff);
                                     break;
                            case "B":$("#link_info").removeClass("btn-primary").addClass("btn-warning");
                                     //$("#dev_staff_select").val(staff);
                                     break;
                        }
                        $("#link_info").slideDown('fast');

                    });
                })
            });
        }
    });

};
creation.js.addVersion = function () {
    var versionCode = $("#addVersionCode").val();
    var publishDate = $("#publishDate").val();
    var objectCode = getParameter(location.hash, "obj", "");
    $.post(path + "/creation/addVersion.do",{versionCode:versionCode,publishDate:publishDate,objectCode:objectCode},function (data) {
        if(data == "true"){
            $("#addVersionModal").modal('hide');
            layer.msg("操作成功!");
            // 版本号下拉框
            $("#version_code").html("<option class=\"select_empty\" value=\"\">-- 选择版本号 --</option>").dict({table:"S_VERSION",key:"VERSION_CODE",value:"VERSION_CODE",where:"object_code='"+creation.js.objectCode+"' AND publish_date >= curdate() ORDER BY publish_date desc"})
                .val(versionCode);
        }else {
            layer.alert("操作失败!");
        }
    });

};

/**
 * Created by jerrywang on 2017/5/28.
 */
jQuery.namespace("issuePoolUpdate");
$(function () {
    issuePoolUpdate.js.init();
});

issuePoolUpdate.js = {};
issuePoolUpdate.js.description;
issuePoolUpdate.js.init = function () {
    issuePoolUpdate.js.objectCode = getParameter(location.hash,"obj","");

    CKEDITOR.replace('description',{ height: '440px'});
    issuePoolUpdate.js.description = CKEDITOR.instances.description;
    issuePoolUpdate.js.description.on('focus',function () {
        $('#cke_description').addClass("edit_focus");
    });
    issuePoolUpdate.js.description.on('blur',function () {
        $('#cke_description').removeClass("edit_focus");
    });

    // 模块下拉框
    $("#model_select").dict({table:"s_model",key:"model_code",value:"model_name",where:"isenable='1' and object_code='"+issuePoolUpdate.js.objectCode+"'"});
    // 负责人下拉框初始化
    $("#principal").dict({table:"V_OBJECT_USERS",key:"username",value:"nickname",where:"object_code='"+issuePoolUpdate.js.objectCode+"'"});
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
    $("#version_code").dict({table:"S_VERSION",key:"VERSION_CODE",value:"VERSION_CODE",where:"object_code='"+issuePoolUpdate.js.objectCode+"' AND publish_date > SYSDATE()"});
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

    var hash = location.hash;
    var uuid = getParameter(hash,"uuid","");
    var type = getParameter(hash, "type","");
    // 获取信息详情
    $.post(path +"/workbench/getDetail.do",{uuid: uuid, type: type},function (data) {
        $("#title").val(data.title);
        if("T" == data.issue_type){
            $("input[name=issueType][value=T]:eq(0)").parent().click();
        }else if("B" == data.issue_type){
            $("input[name=issueType][value=B]:eq(0)").parent().click();
        }


        $("#uuid").val(data.uuid);
        $("#model_select").val(data.model_code);
        $("#principal").val(data.principal);
        $("#deadline").val(data.deadline);
        $("#version_code").val(data.version_code);
        $("#priority").val(data.priority);
        // if()
        $("#working_day").val(data.working_day);
        $("input[name=bug_level][value="+data.bug_level+"]").parent().click();
        issuePoolUpdate.js.description.setData(data.description);
        if(data.parent_code != ""){

            $.post(path + "/issuePool/getParentIssue.do",{issueCode:data.parent_code},function (result) {
                // 初始化关联的前序任务
                $("#link_info_text").text(result.code + " : " + result.title);
                $("#parent_code").val(result.code);
                $("#parent_type").val(result.issue_type);
                // $("#title").val(title);
                switch (result.issue_type) {
                    case "T":$("#link_info").removeClass("btn-warning").addClass("btn-primary");
                        // $("#test_staff_select").val(staff);
                        break;
                    case "B":$("#link_info").removeClass("btn-primary").addClass("btn-warning");
                        //$("#dev_staff_select").val(staff);
                        break;
                }
                $("#link_info").slideDown('fast');

            });
        }

        $.post(path + "/issuePool/getAttachment.do",{businessId: data.uuid},function (data) {
            console.info(data);
            var initialPreview = [];
            var initialPreviewConfig = [];
            for(var i=0;i<data.length;i++){
                initialPreview[i] = "/pesticide/comm/image/icon/"+data[i].image;
                initialPreviewConfig[i] = {caption: data[i].file_name, size: data[i].file_size, width: "120px", key: i};
            }
            // 上传附件组件初始化
            $("#attachment").fileinput({
                language: 'zh',
                showUpload: false,
                initialPreview: initialPreview,
                uploadUrl: path + '/creation/uploadFile.do',
                uploadExtraData: {businessId: uuid},
                initialPreviewAsData: true,
                initialPreviewConfig: initialPreviewConfig,
                // deleteUrl: path + '/creation/deleteAttachment.do',
                // deleteExtraData: {uuid: 'jjjjjj'},// 删除时额外传入的参数
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
                // allowedFileTypes: ['jpg','txt','jpeg','png','xls'],
                // mainClass: "glyphicon glyphicon-send",
                // previewFileIcon: "<i class='glyphicon glyphicon-king'></i>",
                // showUploadedThumbs:false,
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
        });
    });


};
// 选择Task
issuePoolUpdate.js.chooseTask = function (obj) {
    $(obj).find("input").attr("checked",true);
    $("#monitor").removeClass().addClass("mb_15 btn-primary");
    $("#link_info_close").click();
    $("#bug_level_div").hide();
    $("#working_day_div").show();

};

// 选择bug
issuePoolUpdate.js.chooseBug = function (obj) {
    $(obj).find("input").attr("checked",true);
    $("#monitor").removeClass().addClass("mb_15 btn-warning");
    $("#link_info_close").click();
    $("#bug_level_div").show();
    $("#working_day_div").hide();
};
// 保存
issuePoolUpdate.js.save = function(todo){

    var param = $("#main_form").validate();

    if(param){
        var description = issuePoolUpdate.js.description.getData();
        param.description = description;
        param.object_code = getParameter(location.hash,"obj","");
        param.create_user = comm.js.username;
        param.todo = todo;
        if(param.issueType == 'B'){
            param.bug_level = $("input[name=bug_level]:checked").val();
        }
        $.ajax({
            type:'POST',
            url:path+'/issuePool/save.do',
            contentType:'application/json',
            data:JSON.stringify(param),
            success:function (data) {
                if("save" == todo){
                    layer.msg('保存成功!', {
                        offset: '50px'
                    });
                }
            }

        });
    }

};
// 提交
issuePoolUpdate.js.commit = function () {
    // 保存数据
    issuePoolUpdate.js.save('commit');

    var hash = location.hash;
    var obj = getParameter(hash, "obj", "");
    var uuid = getParameter(hash, "uuid", "");
    var type = getParameter(hash, "type", "");
    setHash("on=issuePool/detail&obj="+obj+"&uuid="+uuid+"&type="+type);


};


// 获取关联信息
issuePoolUpdate.js.getLinkInfo = function () {

    setTimeout(function(){
        $("#link_proTask").focus();

    },800);
    var obj = getParameter(location.hash,"obj","");
    // $("#link_proTask").focus();
    // 搜索前序任务绑定输入事件
    $("#link_proTask").keyup(function () {

        var code = $(this).val();
        if(code.length > 0){
            $.post(path+'/creation/searchTask.do',{code:code,objectCode:obj},function (data) {
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

/**
 * Created by DCH on 2017/3/5.
 */
jQuery.namespace("settingsUpdate");
$(function () {
    settingsUpdate.js.init();
});
settingsUpdate.js={};
settingsUpdate.js.init = function () {
    var uuid = parent.getUpdateUUID();
    $.post(path+"/settings/queryUserByUUID.do",
        {uuid : uuid},
        function (data) {
            $("#password").val("");
            $("#uuid").val(uuid);
            $("#username").val(data.username);
            $("#nickname").val(data.nickname);
        });
};
//新增人员信息
settingsUpdate.js.save = function (todo) {
    var param = $("#add").validate();//获取add所有键值对
    param.todo = todo;
    if (param) {
        $.ajax({
            type: 'POST',
            url: path + '/settings/save.do',
            contentType: 'application/json',
            data: JSON.stringify(param),
            success: function (data) {
                if (data == 'success') {
                    layer.msg('保存成功!', {
                        offset: '50px'
                    });
                    $('#addUsersModal').modal('hide');
                    var index=parent.layer.getFrameIndex(window.name);//获取当前layer序列
                    setTimeout(function () {//延迟500ms
                        parent.layer.close(index);//关闭layer
                    },500)
                    parent.settings.js.query();
                } else if (data == 'fail') {
                    layer.msg('保存失败!', {
                        offset: '50px'
                    });
                }
            }

        });
    }
};
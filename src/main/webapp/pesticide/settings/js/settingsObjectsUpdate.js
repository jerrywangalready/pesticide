/**
 * Created by DCH on 2017/3/5.
 */
jQuery.namespace("objectUpdate");
$(function () {
    objectUpdate.js.init();
});
objectUpdate.js={};
objectUpdate.js.init = function () {
    var uuid = parent.getUpdateUUID();
    $.post(path+"/settings/queryObjectByUUID.do",
        {uuid : uuid},
        function (data) {
            $("#uuid").val(uuid);
            $("#object_name").val(data.object_name);
            $("#object_code").val(data.object_code);
        });
};
//新增人员信息
objectUpdate.js.save = function (todo) {
    var param = $("#add").validate();//获取add所有键值对
    param.todo = todo;
    if (param) {
        $.ajax({
            type: 'POST',
            url: path + '/settings/saveObject.do',
            contentType: 'application/json',
            data: JSON.stringify(param),
            success: function (data) {
                if (data == 'success') {
                    layer.msg('保存成功!', {
                        offset: '50px'
                    });
                    var index=parent.layer.getFrameIndex(window.name);//获取当前layer序列
                    setTimeout(function () {//延迟500ms
                        parent.layer.close(index);//关闭layer
                    },500)
                    parent.objects.js.fillObject();
                } else if (data == 'fail') {
                    layer.msg('保存失败!', {
                        offset: '50px'
                    });
                }
            }
        });
    }
};
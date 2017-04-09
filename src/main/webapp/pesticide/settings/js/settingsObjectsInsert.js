/**
 * Created by DCH on 2017/3/9.
 */
jQuery.namespace("settingsObjectsInsert");
$(function () {
    settingsObjectsInsert.js.init();
});
settingsObjectsInsert.js = {};
settingsObjectsInsert.js.init = function () {
    $("#object_code").val("");
    $("#object_name").val("");
};
//新增人员信息
settingsObjectsInsert.js.save = function (todo) {
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
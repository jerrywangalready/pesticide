/**
 * Created by DCH on 2017/3/25.
 */
jQuery.namespace("objects");
$(function () {
    objects.js.init();
});
objects.js = {};
objects.js.updateUUID = "";
objects.js.layerObject = "";
objects.js.init = function () {
    var type = getParameter(location.hash, "type", "users");
    $.dictInit([{name: "yn", table: "s_code_list", type: "YN"}]);
    $("#query_isEnable").dict({table: "s_code_list", type: "YN"});
    // // 获取type值
    var type = getParameter(location.hash, "type", "users");
    $("a[type=users]").click(function () {
        setHash("on=settings/users");
    })
    objects.js.fillObject();
    $("#query_isEnable").dict({table: "t_code_list", type: "YN", where: "", order: ""});
    // 初始化回车事件
    $("#query_objectCode,#query_objectName").enter(function () {
        $("#query_box").query();
    });
    $("#query_isEnable").change(function () {
        $("#query_box").query();
    });
    $("#add").find("input").each(function () {
        $(this).val("");
    })

};

// 填充object
objects.js.fillObject = function () {
    var collector = $("#query_box").collector();
    $.ajax({
        type: 'POST',
        url: path + "/settings/queryObject.do",
        contentType: 'application/json',
        data: JSON.stringify(collector),
        success: function (data) {
            var html = template('objects', {'list': data.list});
            $("#listDiv").html(html);
            // 初始化页码按钮
            $("#page-bar").page(data);
        }
    });

};

//查询项
objects.js.query = function () {

};
// 校验输入的两次密码是否一致
objects.js.checkPassword = function () {
    var password = $("#password").val();
    var repassword = $("#repassword").val();
    if ((password != repassword) && (repassword != '')) {
        alert("两次输入的密码不一致！");
        $("#repassword").val("");
    }
};
// 打开新增窗口
objects.js.showModal = function () {
    $('#addUsersModal').modal('show');
    $("#add").find("input").each(function () {
        $(this).val("");
    })
};
// 打开修改窗口
objects.js.updateUser = function (uuid) {
    objects.js.updateUUID = uuid;
    objects.js.layerObject = layer.open({
        type: 2,
        title: '人员修改页面',
        shadeClose: true,
        shade: 0.8,
        area: ['380px', '75%'],
        anim:2,
        content: [path + '/settings/addOrUpdateInit.do', 'no']
    });
};
// 打开新增窗口
objects.js.addUsers = function () {
    objects.js.layerObject = layer.open({
        type: 2,
        title: '人员新增页面',
        shadeClose: true,
        shade: 0.8,
        area: ['380px', '75%'],
        anim:2,
        content: [path + '/settings/addInit.do', 'no']
    });
};
// 删除一条人员记录
objects.js.deleteObject = function (uuid) {
    layer.confirm('确定要删除吗？此操作不可恢复！',{icon:7,title:'删除'},function (index) {
        $.post(path + "/settings/deleteObject.do",{uuid:uuid},function (data) {
            console.info(data);
        })
        layer.msg('删除成功！', {icon: 1});
        layer.close(index);
        objects.js.fillObject();
    });
};

function getUpdateUUID() {
    return objects.js.updateUUID;
};


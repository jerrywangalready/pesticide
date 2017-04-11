/**
 * Created by jerrywang on 2017/4/10.
 */
jQuery.namespace("workbenchDetail");
$(function () {
    workbenchDetail.js.init();
});

workbenchDetail.js = {};

workbenchDetail.js.init = function () {
    var hash = location.hash;
    var uuid = getParameter(hash,"uuid","");
    var type = getParameter(hash,"type","");
    var object = getParameter(hash, "obj", "");

    $.dictInit([
        {name:"mc",table:"s_model",key:"model_code",value:"model_name",where:"object_code="+object},
        {name:"priority",table:"t_code_list",type:"priority"}
    ]);

    $.post(path+"/workbench/getDetail.do",{uuid:uuid,type:type},function (data) {
        data.type = type;
        var html = template('detail_template',data);
        $("#detail_body").html(html);

        $("#description").html(data.description);
    });
};

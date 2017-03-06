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
            $("#username").val(data.username);
            $("#nickname").val(data.nickname);

        });
};
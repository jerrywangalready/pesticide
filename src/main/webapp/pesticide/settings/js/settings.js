/**
 * Created by Administrator on 2017/1/15.
 */
jQuery.namespace("settings");
$(function () {
    settings.js.init();
});

settings.js = {};
settings.js.init = function () {
  $.post(path+"/settings/queryUsers.do",
      {},
      function (data) {
      console.info(data)

  });
};
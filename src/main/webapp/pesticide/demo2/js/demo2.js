jQuery.namespace("demo2");
$(function () {
    demo2.js.init();
});

demo2.js = {};

demo2.js.init = function () {

    $.post(path + "/demo2/getInfo.do",{obj:"01"},function (data) {
        console.info(data);
    });
};

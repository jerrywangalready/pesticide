jQuery.namespace("chart");
$(function () {
    chart.js.init();
});

chart.js = {};
chart.js.init = function () {
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

    $("#startDate,#finishDate").change(function () {
        chart.js.query();
    });

    chart.js.query();
};

chart.js.query = function () {
    var objCode = getParameter(location.hash, "obj", "");
    var startDate = $("#startDate").val();
    var finishDate = $("#finishDate").val();
    $.post(path + "/chart/getVersion.do",{objectCode: objCode, startDate: startDate, finishDate: finishDate},function (data) {
        var html = template("itemTemplate",{list:data});
        $("#item_box").html(html);
    });
};

chart.js.getDetail = function (o,version) {
    var _this = $(o);
    var flag = _this.attr("flag");
    if(flag == '0'){
        _this.attr("flag","1");
        var objCode = getParameter(location.hash, "obj", "");
        $.post(path + "/chart/getDetail.do",{objectCode: objCode, version: version},function (data) {
            var html = template("detailTemplate",{list:data});
            _this.after(html);
            _this.next().slideDown();
        });
    }else {
        _this.attr("flag","0");
        _this.next().slideUp(500);
        setTimeout(function () {
            _this.next().remove();
        },501)
    }
};
jQuery.namespace("getJar");
$(function () {
    var scope = "0";
    if ("checked" != $("#scopeSwitch").attr("checked")) {
        scope = "1";
    }
    $.post(path + "/getJar/getJarList.do",{scope:scope},function(data){
        $("table").html("");
        for (var i = 0; i < data.length; i++) {
            $("table").append("<tr>"
                + "<td><div class='checkbox'><label><input type='checkbox'>"+data[i].jarName+"</label></div></td>"
                + "<td>"+data[i].jarDate+"</td></tr>");
        }
    });

});

getJar.js = {};
getJar.js.forDownload = function () {


};
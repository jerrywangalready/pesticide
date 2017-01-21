/**
 * Created by jerrywang on 2017/1/11.
 */
// var path = '<%=path%>';
$(function () {
    $("#username").focus();
    $("#loginButton").click(function () {
        var username = $("#username").val();
        var password = $("#password").val();
        var flag = true;
        if(username == ""){
            $("#username").parent().addClass("has-error");
            $("#username+span").addClass("glyphicon-remove");
            flag = false;
        }else{
            $("#username").parent().removeClass("has-error");
            $("#username+span").removeClass("glyphicon-remove");
            flag = true;
        }
        if(password == ""){
            $("#password").parent().addClass("has-error");
            $("#password+span").addClass("glyphicon-remove");
            flag = false;
        }else{
            $("#password").parent().removeClass("has-error");
            $("#password+span").removeClass("glyphicon-remove");
            flag = true;
        }
        if(!flag){
            return;
        }
        $.post(path+"/login/doLogin.do",{username:username,password:password},function (data) {
            if(data == "false"){
                $("#error_alert").slideDown("fast");
            }else{
                location.href = path+"/index/index.do";
            }
        });
    });
    $("#username,#password").keyup(function(e){
        if(e.keyCode==13){
            $("#loginButton").click();
        }
    }).focus(function () {
        $("#error_alert").slideUp("fast");
    });
});

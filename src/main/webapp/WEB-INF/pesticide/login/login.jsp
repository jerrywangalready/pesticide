<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: jerrywang
  Date: 2017/1/10
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%
        String path = request.getContextPath();
    %>
    <title>登录</title>
    <script type="text/javascript" src="<%=path%>/comm/plugins/jQuery/jquery-3.1.1.min.js"></script>
</head>
<body>
    <input id="username" placeholder="账号">
    <input id="password" placeholder="密码">
    <button id="loginButton">登录</button>
</body>
    <script type="text/javascript">
        var path = '<%=path%>';
        $(function () {
            $("#loginButton").click(function () {
                var username = $("#username").val();
                var password = $("#password").val();
                $.post(path+"/login/doLogin.do",{username:username,password:password},function (data) {
                    if(data == "false"){
                        alert("账号或密码错误!");
                    }else{
                        location.href = path+"/index/index.do";
                    }
                });
            });
        })

    </script>
</html>

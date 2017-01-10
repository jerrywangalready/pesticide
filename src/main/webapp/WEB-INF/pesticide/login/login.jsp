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
    <input id="username" >
    <button id="loginButton">登录</button>
</body>
    <script type="text/javascript">
        var path = '<%=path%>';
        $(function () {
            $("#loginButton").click(function () {
                $.post(path+"/login/doLogin.do",{},function () {

                });
            });
        })

    </script>
</html>

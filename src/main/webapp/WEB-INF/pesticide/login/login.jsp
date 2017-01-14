<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<!DOCTYPE html>
<%--
  Created by IntelliJ IDEA.
  User: jerrywang
  Date: 2017/1/10
  Time: 13:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="zh-CN">
<head>
    <%
        String path = request.getContextPath();
    %>
    <title>Pesticide</title>
    <link href="<%=path%>/comm/image/logo_mini_32.ico" rel="shortcut icon" />
    <link href="<%=path%>/pesticide/login/css/login.css" rel="stylesheet" />
    <link href="<%=path%>/comm/plugins/bootstrap/css/bootstrap.min.css" rel="stylesheet"/>
    <script type="text/javascript" src="<%=path%>/comm/plugins/jQuery/jquery-3.1.1.min.js"></script>
    <script type="text/javascript" src="<%=path%>/comm/plugins/bootstrap/js/bootstrap.min.js"></script>
    <script type="text/javascript" src="<%=path%>/pesticide/login/js/login.js"></script>
</head>
<body>
    <img id="logo" src="<%=path%>/comm/image/pesticide_logo2.png">
    <div style="height: 2px;width: 170px;margin:0 auto">
        <div id="error_alert" class="alert alert-danger alert-dismissible fade in" role="alert" style="display: none;padding:7px;">
            <button type="button" class="close" data-dismiss="alert"><span class="sr-only">Close</span></button>
            <strong>账号或密码错误!</strong>
        </div>
    </div>
    <div class="form-group login_div">
        <div class="form-group has-feedback">
            <input id="username" type="text" class="form-control shadow" placeholder="账 号">
            <span class="glyphicon form-control-feedback"></span>
        </div>
        <div class="form-group has-feedback">
            <input id="password" type="password" class="form-control shadow" placeholder="密 码">
            <span class="glyphicon form-control-feedback"></span>
        </div>
        <button class="form-control btn-warning" id="loginButton">登&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;录</button>
        <%--<div class="checkbox">--%>
            <%--<label style="color:#fff;">--%>
                <%--<input type="checkbox"> 记住我--%>
            <%--</label>--%>
        <%--</div>--%>
    </div>
</body>
<script type="text/javascript">
    var path = '<%=path%>';
</script>
</html>

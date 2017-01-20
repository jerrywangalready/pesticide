<%@ page import="com.sgcc.pesticide.login.model.UserToken" %><%--
  Created by IntelliJ IDEA.
  User: jerrywang
  Date: 2017/1/15
  Time: 14:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
    UserToken userToken = (UserToken) request.getSession().getAttribute("userToken");
%>
<link href="<%=path%>/comm/image/logo_mini_32.ico" rel="shortcut icon" />
<link rel="stylesheet" type="text/css" href="<%=path%>/pesticide/index/css/index.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/pesticide/index/css/_style.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/pesticide/index/css/_mobile.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/pesticide/index/css/primary-blue.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/comm/plugins/bootstrap/css/bootstrap.css" />
<script type="text/javascript" src="<%=path%>/comm/plugins/jQuery/jquery-3.1.1.min.js"></script>
<!--[if lt IE 9]>
<link rel="stylesheet" href="<%=path%>/pesticide/index/css/IE.css"/>
<script src="http//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<!--[if lte IE 8]>
<script type="text/javascript" src="<%=path%>/pesticide/index/js/IE.js"></script>
<![endif]-->
<script type="text/javascript" src="<%=path%>/comm/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/comm/plugins/hashchange/jquery.ba-hashchange.min.js"></script>
<script type="text/javascript" src="<%=path%>/comm/plugins/artTemplate/template.js"></script>
<script type="text/javascript" src="<%=path%>/comm/plugins/enterListener/enterListener.js"></script>
<script type="text/javascript" src="<%=path%>/comm/plugins/dictionary/dictionary.js"></script>
<script type="text/javascript" src="<%=path%>/comm/js/comm.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<script type="text/javascript">
    var path = '<%=path%>';
    <%if(userToken!=null){%>
        var username = '<%=userToken.getUsername()%>';
        var nickname = '<%=userToken.getNickname()%>';
    <%}%>
</script>

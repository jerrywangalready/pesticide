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
<link rel="stylesheet" type="text/css" href="<%=path%>/comm/plugins/bootstrap-datetimepicker-master/css/bootstrap-datetimepicker.min.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/comm/plugins/jquery-css3-shake/css/shake.css" />
<link rel="stylesheet" type="text/css" href="<%=path%>/comm/plugins/jquery-ui-1.12.1.custom/jquery-ui.css" />
<script type="text/javascript" src="<%=path%>/comm/plugins/jQuery/jquery-3.1.1.min.js"></script>
<!--[if lt IE 9]>
<link rel="stylesheet" href="<%=path%>/pesticide/index/css/IE.css"/>
<script src="http//html5shiv.googlecode.com/svn/trunk/html5.js"></script>
<![endif]-->
<!--[if lte IE 8]>
<script type="text/javascript" src="<%=path%>/pesticide/index/js/IE.js"></script>
<![endif]-->
<!-- 外部插件 -->
<script type="text/javascript" src="<%=path%>/comm/plugins/bootstrap/js/bootstrap.min.js"></script>
<script type="text/javascript" src="<%=path%>/comm/plugins/jquery-ui-1.12.1.custom/jquery-ui.js"></script>
<script type="text/javascript" src="<%=path%>/comm/plugins/hashchange/jquery.ba-hashchange.min.js"></script>
<script type="text/javascript" src="<%=path%>/comm/plugins/artTemplate/template.js"></script>
<script type="text/javascript" src="<%=path%>/comm/plugins/ckeditor/ckeditor.js"></script>
<%--<script src="http://cdn.ckeditor.com/4.7.0/standard-all/ckeditor.js"></script>--%>
<script type="text/javascript" src="<%=path%>/comm/plugins/bootstrap-datetimepicker-master/js/bootstrap-datetimepicker.js"></script>
<script type="text/javascript" src="<%=path%>/comm/plugins/bootstrap-datetimepicker-master/js/locales/bootstrap-datetimepicker.zh-CN.js"></script>
<script type="text/javascript" src="<%=path%>/comm/plugins/layer/layer.min.js"></script>

<!-- 自封装插件 -->
<script type="text/javascript" src="<%=path%>/comm/plugins/enterListener/enterListener.js"></script>
<script type="text/javascript" src="<%=path%>/comm/plugins/dictionary/dictionary.js"></script>
<script type="text/javascript" src="<%=path%>/comm/js/comm.js"></script>
<script type="text/javascript" src="<%=path%>/comm/plugins/validate/validate.js"></script>
<script type="text/javascript" src="<%=path%>/comm/plugins/page/page.js"></script>
<script type="text/javascript" src="<%=path%>/comm/plugins/collector/collector.js"></script>
<script type="text/javascript" src="<%=path%>/comm/plugins/query/query.js"></script>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<script type="text/javascript">
    var path = '<%=path%>';
    <%if(userToken!=null){%>
    comm.js.username = '<%=userToken.getUsername()%>';
    comm.js.nickname = '<%=userToken.getNickname()%>';
    <%}%>
</script>

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

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" /></head>
<script type="text/javascript">
    var path = '<%=path%>';
    <%if(userToken!=null){%>
        comm.js.username = '<%=userToken.getUsername()%>';
        comm.js.nickname = '<%=userToken.getNickname()%>';
    <%}%>
</script>

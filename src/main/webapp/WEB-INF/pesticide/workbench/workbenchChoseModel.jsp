<%--
  Created by IntelliJ IDEA.
  User: jerrywang
  Date: 2017/1/15
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ include file="../../comm/head.jsp"%>--%>
<%
    String path = request.getContextPath();
%>
<link href="<%=path%>/pesticide/workbench/css/workbench.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/pesticide/workbench/js/workbenchChoseModel.js"></script>
<input id="state" type="hidden" value="${requestScope.state}">
<div id="model_body" class="no-padding-lr checkbox">

</div>
<div class="layer_button">
    <button type="button" class="btn btn-primary" onclick="workbenchChoseModel.js.push()">确认</button>
</div>
<br>



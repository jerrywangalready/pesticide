<%--
  Created by IntelliJ IDEA.
  User: jerrywang
  Date: 2017/1/15
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../comm/head.jsp"%>
<link href="<%=path%>/pesticide/workbench/css/workbench.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/pesticide/workbench/js/workbenchInputReason.js"></script>
<div style="padding: 15px">
    <div style="margin-bottom: 15px;">
        <textarea id="remark" class="form-control" rows="3" placeholder="填写原因" maxlength="60"></textarea>
    </div>
    <div class="layer_button" style="padding: 0;">
        <button type="button" class="btn btn-primary" onclick="workbenchInputReason.js.changeStateWithReason()">确认</button>
    </div>

</div>

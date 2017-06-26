<%--
  Created by IntelliJ IDEA.
  User: jerrywang
  Date: 2017/1/15
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../comm/head.jsp"%>
<link href="<%=path%>/pesticide/launch/css/launchInfo.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/pesticide/launch/js/launchInfo.js"></script>
<div class="model_box">
    <div class="left model_num">
        <h2>Total</h2>
        <h1 id="total_num"></h1>
        <button type="button" class="btn btn-primary" onclick="launchInfo.js.forLaunch()">确认</button>
    </div>
    <div class="right model_list_box">
        <div class="model_list">
            <ul id="model_list_ul">
            </ul>
        </div>
    </div>
</div>
<div class="layer_button">
</div>

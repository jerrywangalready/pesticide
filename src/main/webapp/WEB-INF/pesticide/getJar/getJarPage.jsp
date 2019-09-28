<%--
  Created by IntelliJ IDEA.
  User: jerrywang
  Date: 2017/1/15
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../comm/head.jsp"%>
<link href="<%=path%>/pesticide/getJar/css/getJar.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/pesticide/getJar/js/getJar.js"></script>
<div class="model_box">
    <div class="checkbox">
        <label>
            <input type="checkbox" id="scopeSwitch" checked="true">
            只显示业务包
        </label>

    </div>
    <div class="jar-list-box">
        <table>
        </table>
    </div>
    <div class="layer_button">
        <button id="launch_button" type="button" style="float: right;" class="btn btn-primary" onclick="getJar.js.forDownload()">下载</button>
    </div>
</div>

<%--
  Created by IntelliJ IDEA.
  User: jerrywang
  Date: 2017/1/15
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../comm/comm.jsp"%>
<link href="<%=path%>/comm/plugins/UEdite/themes/default/css/umeditor.css" type="text/css" rel="stylesheet">
<link href="<%=path%>/pesticide/creation/css/creation.css" type="text/css" rel="stylesheet">
<script type="text/javascript" charset="utf-8" src="<%=path%>/comm/plugins/UEdite/umeditor.config.js"></script>
<script type="text/javascript" charset="utf-8" src="<%=path%>/comm/plugins/UEdite/umeditor.min.js"></script>
<script type="text/javascript" src="<%=path%>/comm/plugins/UEdite/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript" src="<%=path%>/pesticide/creation/js/creation.js"></script>
<div>
    <br>
    <form role="form">
        <div class="form-group">
            <input type="text" class="form-control" id="title" placeholder="标题">
        </div>
        <div id="edit_box">
            <script type="text/plain" id="myEditor" style="width:960px;height:240px;">
                <p>这里我可以写一些输入提示</p>
            </script>
        </div>

        <div class="row">
            <div class="col-md-4">
                <select class="form-control" id="model_select">
                    <option value="">选择模块</option>
                </select>
            </div>
            <div class="col-md-4"><input id="test"></div>
            <div class="col-md-4">.col-md-4</div>
        </div>

        <button type="submit" class="btn btn-default">Submit</button>

    </form>

</div>
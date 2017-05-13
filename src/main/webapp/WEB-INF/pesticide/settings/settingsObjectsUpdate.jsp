<%--
  Created by IntelliJ IDEA.
  User: DCH
  Date: 2017/3/2
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../comm/head.jsp"%>
<script type="text/javascript" src="<%=path%>/pesticide/settings/js/settingsObjectsUpdate.js"></script>
<div class="modal-body">
    <form id="add">
        <input type="hidden" id="uuid" name="uuid">
        <div class="form-group">
            <label for="object_code" class="control-label">项目编号:</label>
            <input id="object_code" name="object_code" type="text" class="form-control shadow" validate="required">
        </div>
        <div class="form-group">
            <label for="object_name" class="control-label">项目名称:</label>
            <input id="object_name" name="object_name" type="text" class="form-control shadow" validate="required">
        </div>
    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    <button type="button" class="btn btn-primary" onclick="objectUpdate.js.save('update')">保存</button>
</div>


<%--
  Created by IntelliJ IDEA.
  User: DCH
  Date: 2017/3/2
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../comm/head.jsp"%>
<script type="text/javascript" src="<%=path%>/pesticide/settings/js/settingsUsersUpdate.js"></script>
<div class="modal-body">
    <form id="add">
        <input type="hidden" id="uuid" name="uuid">
        <div class="form-group">
            <label for="username" class="control-label">账号:</label>
            <input id="username" name="username" type="text" class="form-control shadow" readonly="true" >
        </div>
        <div class="form-group">
            <label for="password" class="control-label">密码:</label>
            <input id="password" name="password" type="password" class="form-control shadow" validate="password">
        </div>
        <div class="form-group">
            <label for="repassword" class="control-label">再次输入:</label>
            <input id="repassword" name="repassword" type="password" class="form-control shadow" validate="password" onblur="parent.settings.js.checkPassword()">
        </div>
        <div class="form-group">
            <label for="nickname" class="control-label">真实姓名:</label>
            <input id="nickname" name="nickname" type="text" class="form-control" validate="required">
        </div>
    </form>
</div>
<div class="modal-footer">
    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
    <button type="button" class="btn btn-primary" onclick="settingsUpdate.js.save('update')">保存</button>
</div>


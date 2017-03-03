<%--
  Created by IntelliJ IDEA.
  User: DCH
  Date: 2017/3/2
  Time: 23:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../comm/comm.jsp"%>
<div class="modal fade" id="addUsersModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" >
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">添加人员信息</h4>
            </div>
            <div class="modal-body">
                <form id="add">
                    <div class="form-group">
                        <label for="username" class="control-label">账号:</label>
                        <input id="username" name="username" type="text" class="form-control shadow" validate="username">
                    </div>
                    <div class="form-group">
                        <label for="password" class="control-label">密码:</label>
                        <input id="password" name="password" type="password" class="form-control shadow" validate="password">
                    </div>
                    <div class="form-group">
                        <label for="repassword" class="control-label">再次输入:</label>
                        <input id="repassword" name="repassword" type="password" class="form-control shadow" validate="password" onblur="settings.js.checkPassword()">
                    </div>
                    <div class="form-group">
                        <label for="nickname" class="control-label">真实姓名:</label>
                        <input id="nickname" name="nickname" type="text" class="form-control" validate="required">
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="settings.js.save('save')">保存</button>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $(function () {
        var uuid = parent.settings.js.getUpdateUUID();
        alert(uuid);
    })
</script>

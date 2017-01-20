<%--
  Created by IntelliJ IDEA.
  User: jerrywang
  Date: 2017/1/12
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../comm/comm.jsp"%>
<link href="<%=path%>/pesticide/settings/css/settings.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/pesticide/settings/js/settings.js"></script>

<div style="text-align: left;">
    <ul class="nav nav-tabs" role="tablist" id="tablist">
        <li role="presentation"><a href="javascript:void(0);" type = "users">用户</a></li>
        <li role="presentation"><a href="javascript:void(0);" type = "projects">项目</a></li>
    </ul>
    <div id="testDiv">
    </div>



</div>

<script id="test" type="text/html">
    <table class="table table-striped border_solid">
        <thead>
        <tr>
            <th>序号</th>
            <th>姓名</th>
            <th>账号</th>
            <th>密码</th>
            <th>是否在用</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        {{each list as value i}}
        <tr>
            <td>{{i+1}}</td>
            <td>{{value.nickname}}</td>
            <td>{{value.username}}</td>
            <td>{{value.password}}</td>
            <%--<td>{{$.dict({table:"s_code_list",type:"YN"},value.isEnable)}}</td>--%>
            <td>{{value.isEnable}}</td>
            <td>
                <span class="glyphicon glyphicon-pencil table_oper_button"></span>
                <span class="glyphicon glyphicon-trash table_oper_button"></span>
            </td>
        </tr>
        {{/each}}
        </tbody>
    </table>
</script>



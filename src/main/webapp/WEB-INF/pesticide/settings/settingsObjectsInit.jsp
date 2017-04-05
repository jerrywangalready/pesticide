<%--
  Created by IntelliJ IDEA.
  User: 杜成皓
  Date: 2017/1/12
  Time: 13:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../comm/comm.jsp"%>
<link href="<%=path%>/pesticide/settings/css/settings.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/pesticide/settings/js/settingsObjects.js"></script>

<div>
    <ul class="nav nav-tabs" role="tablist" id="tablist">
        <li role="presentation"><a href="javascript:void(0);" type = "users">用户</a></li>
        <li role="presentation" class="active"><a href="javascript:void(0);" type = "objects">项目</a></li>
    </ul>
    <div style="margin-top:7px;">
        <form class="form-inline row" role="form" id="query_box">
            <div class="form-group col-md-4">
                <input type="text" class="form-control" id="query_objectCode" name="query_objectCode" placeholder="项目编码" >
            </div>
            <div class="form-group col-md-4">
                <input type="text" class="form-control" id="query_objectName" name="query_objectName" placeholder="项目名称">
            </div>
            <div class="form-group col-md-4">
                <select class="form-control" id="query_isEnable" name="query_isEnable">
                    <option value="">-- 是否在用 --</option>
                </select>
            </div>

        </form>
    </div>
    <div style="float:left;padding: 8px;">
        <!-- Button trigger modal -->
        <button type="button" class="btn" data-toggle="modal" onclick="settings.js.addUsers()">
            <span class="glyphicon glyphicon-plus"></span>
        </button>
    </div>
    <div id="listDiv">
    </div>
</div>
<nav id="page-bar">
</nav>
<script id="objects" type="text/html">
    <table class="table table-striped border_solid table-hover">
        <thead>
        <tr>
            <th>序号</th>
            <th>项目编号</th>
            <th>项目名称</th>
            <th>是否在用</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        {{each list as value i}}
        <tr>
            <td>{{i+1}}</td>
            <td>{{value.object_code}}</td>
            <td>{{value.object_name}}</td>
            <td>{{value.isEnable | dict:'yn'}}</td>
            <td>
                <span class="glyphicon glyphicon-pencil table_oper_button"></span>
                <span class="glyphicon glyphicon-trash table_oper_button"></span>
            </td>
        </tr>
        {{/each}}
        </tbody>
    </table>
</script>
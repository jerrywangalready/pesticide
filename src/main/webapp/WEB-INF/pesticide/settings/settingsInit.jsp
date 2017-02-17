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

<div>
    <ul class="nav nav-tabs" role="tablist" id="tablist">
        <li role="presentation"><a href="javascript:void(0);" type = "users">用户</a></li>
        <li role="presentation"><a href="javascript:void(0);" type = "objects">项目</a></li>
    </ul>
    <div style="margin-top:7px;">
        <form class="form-inline row" role="form">
            <div class="form-group col-md-4">
                    <input type="text" class="form-control" id="exampleInputEmail1" placeholder="姓名" >
            </div>
            <div class="form-group col-md-4">
                <input type="text" class="form-control" id="exampleInputEmail2" placeholder="账号">
            </div>
            <div class="form-group col-md-4">
                是否在用：
                <select class="form-control" id="isEnable">
                    <option value=""></option>
                </select>
            </div>

        </form>
    </div>
    <div style="float:left;padding: 8px;">
        <!-- Button trigger modal -->
        <button type="button" class="btn" data-toggle="modal" data-target="#myModal">
            <span class="glyphicon glyphicon-plus"></span>
        </button>
    </div>
    <div id="testDiv">
    </div>


    <!-- Modal -->
    <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true" data-backdrop="static" >
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                    <h4 class="modal-title" id="myModalLabel">添加人员信息</h4>
                </div>
                <div class="modal-body">
                    ...
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                    <button type="button" class="btn btn-primary" >保存</button>
                </div>
            </div>
        </div>
    </div>





</div>
<nav id="page-bar">
</nav>

<script id="users" type="text/html">
    <table class="table table-striped border_solid table-hover">
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


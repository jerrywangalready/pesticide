<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2017/1/21
  Time: 21:37
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../comm/comm.jsp"%>
<script type="text/javascript" src="<%=path%>/pesticide/demo/js/demo.js"></script>
<div style="margin-top:7px;">
    <form id="query_box" class="form-inline row" role="form">
        <div class="form-group col-md-4">
            <input type="text" class="form-control" id="queryCode" name="queryCode" placeholder="编号" >
        </div>
        <div class="form-group col-md-4">
            <input type="text" class="form-control" id="queryName" name="queryName" placeholder="名称">
        </div>
        <div class="form-group col-md-4">
            <label>类型:</label>
            <select class="form-control" id="queryType" name="queryType">
                <option value=""></option>
            </select>
        </div>

    </form>
</div>
<div id="table_div">

</div>
<nav id="page-bar">
</nav>

<script id="users" type="text/html">
    <table class="table border_solid table-hover">
        <thead>
        <tr>
            <th>序号</th>
            <th>编号</th>
            <th>名称</th>
            <th>类型</th>
            <th>是否在用</th>
            <th>操作</th>
        </tr>
        </thead>
        <tbody>
        {{if list.length == 0}}
        <tr>
            <td>未查询到任何数据!</td>
        </tr>
        {{/if}}
        {{each list as value i}}
        <tr>
            <td>{{i+1}}</td>
            <td>{{value.code}}</td>
            <td>{{value.name}}</td>
            <td>{{value.type | dict:'yn'}}</td>
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
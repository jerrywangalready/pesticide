<%--
  Created by IntelliJ IDEA.
  User: jerrywang
  Date: 2017/1/15
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../comm/comm.jsp"%>
<link href="<%=path%>/pesticide/workbench/css/workbench.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/pesticide/workbench/js/workbenchDetail.js"></script>
<br>
<div id="detail_body">

</div>

<script id="detail_template" type="text/html">
    <div style="height:50px;">
        <h4 class="left" style="margin-top:12px;">{{issue_code}}</h4>
        <button type="button" class="btn btn-default navbar-btn right" style="font-size:11px;padding: 4px 10px;">返回</button>
    </div>
    <div class="alert alert-{{if type == 'T'}}info{{/if}}{{if type == 'B'}}warning{{/if}}" role="alert">
        <table class="issue_card">
            <tr>
                <td style="width:15%;">前序任务 :</td>
                <td style="width:18%;">{{parent_code}}</td>
                <td style="width:15%;">模块 :</td>
                <td style="width:18%;">{{model_code | dict:'mc'}}</td>
                <td style="width:15%;">发布者 :</td>
                <td style="width:19%;">{{create_user_name}}</td>
            </tr>
            <tr>
                <td>完成时间 :</td>
                <td>{{deadline}}</td>
                <td>版本 :</td>
                <td>{{version_code}}</td>
                <td>接收者 :</td>
                <td>{{principal_user_name}}</td>
            </tr>
            <tr>
                <td>估算工时 :</td>
                <td>{{working_day}}</td>
                <td>优先级 :</td>
                <td>{{priority | dict:'priority'}}</td>
                <td>问题级别 :</td>
                <td>{{bug_level}}</td>
            </tr>
        </table>
    </div>
    <hr>
    <div>
        <span>{{title}}</span>
    </div>
    <hr>
    <div id="description">

    </div>
    <hr>
    <div>
        <button type="button" class="btn btn-success full_button" onclick="creation.js.save('save')">送测</button>
    </div>
</script>


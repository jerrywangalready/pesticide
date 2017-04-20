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
<!-- Modal -->
<div class="modal fade" id="handle_Modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">选择发布模块</h4>
            </div>
            <div class="modal-body no-padding-lr checkbox">

            </div>
            <div class="modal-footer">
                <%--<button type="button" class="btn btn-default" data-dismiss="modal">Close</button>--%>
                <button type="button" class="btn btn-primary" onclick="workbenchDetail.js.push()">确认</button>
            </div>
        </div>
    </div>
</div>

<script id="detail_template" type="text/html">
    <div style="height:50px;">
        <h4 class="left" style="margin-top:12px;">{{issue_code}}</h4>
        <button type="button" class="btn btn-default btn-sm right" style="margin-top:10px;">返回</button>
        <div class="btn-group btn-group-sm right" style="margin-right:10px;margin-top:10px;">
            <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#handle_Modal">送测</button>
            <input id="uuid" type="hidden" value="{{uuid}}">
            <input id="model_code" type="hidden" value="{{model_code}}">
            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="caret"></span>
                <span class="sr-only">Toggle Dropdown</span>
            </button>
            <ul class="dropdown-menu">
                <li><a href="#">暂缓测试</a></li>
                <li><a href="#">指派</a></li>
                <li role="separator" class="divider"></li>
                <li><a href="#">退回</a></li>
            </ul>
        </div>
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
        <span style="font-size:16px;">{{title}}</span>
    </div>
    <hr>
    <div id="description">

    </div>
    <%--<hr>--%>
</script>


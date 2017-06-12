<%--
  Created by IntelliJ IDEA.
  User: jerrywang
  Date: 2017/1/15
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ include file="../../comm/comm.jsp"%>--%>
<%
    String path = request.getContextPath();
%>
<link href="<%=path%>/pesticide/workbench/css/workbench.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/pesticide/issuePool/js/issuePoolDetail.js"></script>
<br>
<div id="detail_body">

</div>
<div id="operation_details" >

</div>
<script id="detail_template" type="text/html">
    <div style="height:50px;">
        <h4 class="left" style="margin-top:12px;">{{issue_code}}</h4>
        <span id="issue_state" class="label label-primary" style="margin: 14px;float: left;">{{state | dict:'state'}}</span>
        <button type="button" class="btn btn-default btn-sm right" style="margin-top:10px;" onclick="issuePoolDetail.js.return()">返回</button>
        {{if create_user == username}}
        <button type="button" class="btn btn-primary btn-sm right" style="margin-top:10px;margin-right: 10px;" onclick="issuePoolDetail.js.update()">修改</button>
        {{/if}}
        <input id="uuid" type="hidden" value="{{uuid}}">
        <input id="model_code" type="hidden" value="{{model_code}}">
        <input id="issue_type" type="hidden" value="{{issue_type}}">
        <%--{{if state == '1'}}--%>
        <%--<div class="btn-group btn-group-sm right" style="margin-right:10px;margin-top:10px;">--%>
            <%--<button type="button" class="btn btn-primary" onclick="workbenchDetail.js.pushButtonClick('2')">送测</button>--%>
            <%--<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
                <%--<span class="caret"></span>--%>
                <%--<span class="sr-only">Toggle Dropdown</span>--%>
            <%--</button>--%>
            <%--<ul class="dropdown-menu">--%>
                <%--<li><a onclick="workbenchDetail.js.pushButtonClick('3')" href="javascript:void(0);">暂缓测试</a></li>--%>
                <%--<li><a onclick="workbenchDetail.js.changePrincipal()" href="javascript:void(0);">指派</a></li>--%>
                <%--<li role="separator" class="divider"></li>--%>
                <%--<li><a onclick="workbenchDetail.js.reject()" href="javascript:void(0);">退回</a></li>--%>
            <%--</ul>--%>
        <%--</div>--%>
        <%--{{/if}}--%>
        <%--{{if state == '4'}}--%>
        <%--<div class="btn-group btn-group-sm right" style="margin-right:10px;margin-top:10px;">--%>
            <%--<button type="button" class="btn btn-primary" onclick="workbenchDetail.js.finish()">完成</button>--%>
            <%--<button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">--%>
                <%--<span class="caret"></span>--%>
                <%--<span class="sr-only">Toggle Dropdown</span>--%>
            <%--</button>--%>
            <%--<ul class="dropdown-menu">--%>
                <%--<li><a onclick="workbenchDetail.js.back()" href="javascript:void(0);">不通过</a></li>--%>
                <%--<li role="separator" class="divider"></li>--%>
                <%--<li><a onclick="workbenchDetail.js.terminate()" href="javascript:void(0);">终止</a></li>--%>
            <%--</ul>--%>
        <%--</div>--%>
        <%--{{/if}}--%>
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

    <div>
        <span style="font-size:16px;">{{title}}</span>
    </div>
    <hr>
    <div id="description" style="min-height: 100px;">

    </div>
    <hr>

</script>

<script id="operation_template" type="text/html">
    <ul class="recent-posts">
        {{each list as value i}}
        <li>
            <%--<div class="user-thumb"> <img alt="User" src="img/demo/av1.jpg" height="40" width="40"> </div>--%>
            <div class="article-post" style="min-height: 40px;">
                <div class="left" style="width:95px;margin-right: 12px;border-right:2px solid #ccc;">
                    <div style="text-align: center;height: 20px;">
                        <span class="user-info">{{value.sdate}}</span>
                    </div>
                    <div style="text-align: center;height: 20px;">
                        <span class="user-info">{{value.stime}}</span>
                    </div>
                </div>
                <div class="left">
                    <a>{{value.nickname}}</a>&nbsp;&nbsp;<span class="user-info">{{value.operate_detail}}</span>
                    <p>{{value.remark}}</p>
                </div>
            </div>
        </li>
        {{/each}}
    </ul>
</script>
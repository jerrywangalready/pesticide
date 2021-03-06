<%--
  Created by IntelliJ IDEA.
  User: jerrywang
  Date: 2017/1/15
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ include file="../../comm/head.jsp"%>--%>
<%
    String path = request.getContextPath();
%>
<link href="<%=path%>/pesticide/workbench/css/workbench.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/pesticide/workbench/js/workbenchDetail.js"></script>
<br>
<div id="detail_body">

</div>
<div id="attachment_box">

</div>
<div id="attachment_update_box" style="display: none;">
    <label class="control-label">附件</label>
    <input id="attachment" name="attachment" type="file" multiple class="file-loading">
    <br>
</div>
<div id="operation_details" >

</div>
<script id="detail_template" type="text/html">
    <div style="height:50px;">
        <h4 class="left" style="margin-top:12px;">{{issue_code}}</h4>
        <div class="label-box">
            <span class="label label-{{if state == '9'}}default{{/if}}{{if state == '4'}}primary{{/if}}{{if state == '5'}}success{{/if}}{{if state == '3'}}info{{/if}}{{if state == '2'}}warning{{/if}}{{if state == '1'}}danger{{/if}}">{{state | dict:'state'}}</span>
        </div>
        <button type="button" class="btn btn-default btn-sm right" style="margin-top:10px;" onclick="workbenchDetail.js.return()">返回</button>
        <input id="uuid" type="hidden" value="{{uuid}}">
        <input id="model_code" type="hidden" value="{{model_code}}">
        <input id="issue_type" type="hidden" value="{{issue_type}}">
        <input id="state" type="hidden" value="">
        {{if state == '1'}}
        <div class="btn-group btn-group-sm right" style="margin-right:10px;margin-top:10px;">
            <button type="button" class="btn btn-primary" onclick="workbenchDetail.js.pushButtonClick('2')">送测</button>
            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="caret"></span>
                <span class="sr-only">Toggle Dropdown</span>
            </button>
            <ul class="dropdown-menu">
                <li><a onclick="workbenchDetail.js.uploadAttachment('{{uuid}}')" href="javascript:void(0);">上传附件</a></li>
                <li><a onclick="workbenchDetail.js.changePrincipal()" href="javascript:void(0);">指派</a></li>
                {{if create_user == username}}
                <li role="separator" class="divider"></li>
                <li><a onclick="workbenchDetail.js.update()" href="javascript:void(0);">修改</a></li>
                <li><a onclick="workbenchDetail.js.changeStateWithReason('9')" href="javascript:void(0);">废弃</a></li>
                {{/if}}
            </ul>
        </div>
        {{/if}}
        {{if state == '3'}}
        <div class="btn-group btn-group-sm right" style="margin-right:10px;margin-top:10px;">
            <button type="button" class="btn btn-primary" onclick="workbenchDetail.js.changeState('4')">通过</button>
            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                <span class="caret"></span>
                <span class="sr-only">Toggle Dropdown</span>
            </button>
            <ul class="dropdown-menu">
                <li><a onclick="workbenchDetail.js.uploadAttachment()" href="javascript:void(0);">上传附件</a></li>
                <li><a onclick="workbenchDetail.js.changeStateWithReason('1')" href="javascript:void(0);">退回</a></li>
                {{if create_user == username}}
                <li role="separator" class="divider"></li>
                <li><a onclick="workbenchDetail.js.update()" href="javascript:void(0);">修改</a></li>
                <li><a onclick="workbenchDetail.js.changeStateWithReason('9')" href="javascript:void(0);">废弃</a></li>
                {{/if}}
            </ul>
        </div>
        {{/if}}
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

<script id="attachment_template" type="text/html">
    {{each list as value i}}
    <div class="attachment_item">
        <span class="attachment {{value.typeClass}} left"></span>
        <a class="attachment_a left" href="javascript:void(0);" onclick="workbenchDetail.js.downloadAttachment('{{value.uuid}}')">{{value.file_name}}</a>
        <span class="attachment_size">{{value.create_time}}</span>
        <span class="attachment_size">{{value.nickname}}</span>
        <span class="attachment_time">{{value.file_size}}</span>
    </div>
    {{/each}}
    {{if list.length > 0}}
    <hr>
    {{/if}}
</script>

<script id="operation_template" type="text/html">
    <ul class="recent-posts">
        <li>
            <div class="article-post" style="min-height: 54px;">
                <div class="left" style="width:95px;margin-right: 12px;border-right:2px solid #ccc;height: 55px;">
                    <div style="text-align: center;height: 20px;">
                        <span class="user-info"></span>
                    </div>
                    <div style="text-align: center;height: 20px;">
                        <span class="user-info">备注</span>
                    </div>
                </div>
                <div class="left">
                    <textarea id="remark" class="left form-control" rows="2" style="height: 55px;width: 780px;"></textarea>
                    <button id="submit_remark" onclick="workbenchDetail.js.submitRemark();" class="left btn-sm btn-primary" style="margin:11px 0 0 13px;">确认</button>
                </div>
            </div>
        </li>
        {{each list as value i}}
        <li {{if i == 0}}style="display:none;"{{/if}}>
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


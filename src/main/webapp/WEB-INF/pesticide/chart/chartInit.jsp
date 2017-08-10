<%--
  Created by IntelliJ IDEA.
  User: jerrywang
  Date: 2017/1/15
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<link href="<%=path%>/pesticide/chart/css/chart.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/pesticide/chart/js/chart.js"></script>
<div>
    <br>
    <div id="search_box" class="row">
        <div class="mb_15 col-md-4">
            <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd"
                 data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
                <input class="form-control" size="16" type="text" id="startDate" name="startDate" readonly placeholder="开始时间">
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
            <input type="hidden" id="dtp_input1" value=""/>
        </div>
        <div class="mb_15 col-md-4">
            <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd"
                 data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                <input class="form-control" size="16" type="text" id="finishDate" name="finishDate" readonly placeholder="结束时间">
                <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
            </div>
            <input type="hidden" id="dtp_input2" value=""/>
        </div>
    </div>
    <hr>
    <div id="item_box">

    </div>
    <br>
</div>
<script id="itemTemplate" type="text/html">
    {{each list as value i}}
    <div class="version-item" flag="0" onclick="chart.js.getDetail(this,'{{value.version_code}}')">
        <div class="left version-item-info">
            <div>
                <h4>{{value.version_code}}</h4>
            </div>
            <div>
                <span class="left">{{value.publish_date}}</span>
                {{if value.is_complete == '1'}}
                <span id="issue_state" class="label label-success" style="margin: 3px 20px;float: left;">已上线</span>
                {{/if}}
            </div>
        </div>
        <div class="left version-item-progress">
            <div class="progress total">
                <div class="progress-bar progress-bar-{{if value.percent == '100'}}success{{else}}primary{{/if}} progress-bar-striped" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="min-width: 2em;width: {{value.percent}}%">
                    <%--<span class="sr-only">60% Complete (warning)</span>--%>
                    {{value.percent}}%
                </div>
            </div>

        </div>
    </div>
    {{/each}}
</script>
<script id="detailTemplate" type="text/html">
    <div class="version-item-detail" style="display: none">
        {{each list as value i}}
        <div class="left item-detail">
            <div class="left nickname">
                <span>{{value.nickname}}</span>
            </div>
            <div class="left detail-progress-box">
                <div class="left version-item-progress-detail">
                    <div class="progress">
                        <div class="progress-bar progress-bar-{{if value.percent == '100'}}success{{else}}info{{/if}} progress-bar-striped" role="progressbar" aria-valuenow="60" aria-valuemin="0" aria-valuemax="100" style="min-width: 2em;width: {{value.percent}}%">
                            <%--<span class="sr-only">60% Complete (warning)</span>--%>
                            {{value.percent}}%
                        </div>
                    </div>

                </div>
            </div>
            <div class="right num">
                <span>{{value.finished}}/{{value.total}}</span>
            </div>
        </div>
        {{/each}}
    </div>
</script>
<%--
  Created by IntelliJ IDEA.
  User: jerrywang
  Date: 2017/1/15
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<link href="<%=path%>/pesticide/launch/css/launch.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/pesticide/launch/js/launchInit.js"></script>
<br>
<div style="height: 40px;">
    <div style="position: absolute;margin-top: 6px;width:959px;">
        <div id="search_box" class="row search-box">
            <div class="mb_15 col-md-4" style="width: 195px;">
                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd"
                     data-link-field="dtp_input1" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="16" type="text" id="beginDate" style="width: 100px;" name="beginDate" readonly placeholder="开始时间">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
                <input type="hidden" id="dtp_input1" value=""/>
            </div>
            <div class="mb_15 col-md-4">
                <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd"
                     data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                    <input class="form-control" size="16" type="text" id="endDate" style="width: 100px;" name="endDate" readonly placeholder="结束时间">
                    <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                    <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                </div>
                <input type="hidden" id="dtp_input2" value=""/>
            </div>
        </div>
    </div>
</div>
<div style="height: 0px;position: relative;z-index: 1000;">
    <div id="search-switch" class="search-switch" fla="1">
        <span class="glyphicon glyphicon-share-alt search-switch-span" title="查询项开关"></span>
    </div>
</div>
<br>
<div style="position: relative;overflow: hidden">
    <div class="task-pool">
        <div class="left lane-box">
    
        </div>
    </div>
    <span class="left-box-shadow" style="display: none;"></span>
    <span class="right-box-shadow" style="display: none;"></span>
    <div class="task-pool-mirror">
        <div class="left lane-box-mirror"></div>
    </div>
</div>
<script id="laneTemplate" type="text/html">
    {{each list as value i}}
    <div class="left lane {{if value.IS_COMPLETE == '1'}} noSort launched{{else}}sort{{/if}}" vc="{{value.VERSION_CODE}}">
        <div class="lane-title notSortable">
            <span name="version_code" vc="{{value.VERSION_CODE}}">版本 : {{if value.VERSION_CODE == ''}}X.X{{else}}{{value.VERSION_CODE}}{{/if}}</span>
            {{if value.IS_COMPLETE == '1'}}
            <span class="right">已上线</span>
            {{/if}}
        </div>
        {{each value.issueList as issueMap j}}
        <div class="task-item" uuid="{{issueMap.UUID}}" it="{{issueMap.ISSUE_TYPE}}" state="{{issueMap.STATE}}">
            <div class="task-item-title {{if issueMap.ISSUE_TYPE == 'T'}}task{{else}}bug{{/if}} notSortable">
                <span>{{issueMap.CODE}}</span>
                <span class="right glyphicon {{if issueMap.STATE == '6'}}glyphicon-ok{{/if}}" style="margin-top: 2px;"></span>
            </div>
            <div class="task-item-body notSortable">
                <span>{{issueMap.TITLE}}</span>
            </div>
        </div>
        {{/each}}
    </div>
    {{/each}}
</script>
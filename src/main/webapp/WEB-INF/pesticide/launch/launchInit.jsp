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
    <div class="left lane" vc="{{value.VERSION_CODE}}">
        <div class="lane-title notSortable">
            <span>版本 : {{if value.VERSION_CODE == ''}}X.X{{else}}{{value.VERSION_CODE}}{{/if}}</span>
            {{if i == 0}}
            <button id="publish_button" type="button" class="right btn btn-xs btn-success" style="margin-top: -2px;" onclick="launchInit.js.forLaunch()">上线</button>
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
<%--
  Created by IntelliJ IDEA.
  User: DCH
  Date: 2017/5/4
  Time: 22:58
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%
    String path = request.getContextPath();
%>
<link href="<%=path%>/pesticide/push/css/push.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/pesticide/push/js/pushInit.js"></script>
<br>
<div >
    <div style="height:42px;">
        <h3 class="left" style="margin-top:5px;">送测列表</h3>
        <button type="button" id="publish_button" style="display: none;" class="btn btn-primary right" onclick="push.js.publish()">发布</button>
    </div>
    <div id="grid">
    </div>
</div>
<script id="model_grid_template" type="text/html">
    {{if list.length == 0}}
    <div class="nothing"><span></span></div>
    {{/if}}
    {{each list as value i}}
    <div class="grid-item" state="0" onclick="push.js.detail(this,'{{value.MODEL_CODE}}')">
        <input name="model" type="checkbox" checked="checked" class="left" value="{{value.MODEL_CODE}}" disabled>
        <div class="left">
            <div class="left">
                <span class="black">{{value.MODEL_CODE}}</span>
                <span class="gray">{{value.MODEL_NAME}}</span>
            </div>
        </div>
    </div>
    {{/each}}
</script>
<script id="grid_two" type="text/html">
    <div style="padding-left:30px; ">
        {{each list as value }}
        <div class="grid-item bg-grey {{if value.ISSUE_TYPE == 'T'}}task{{/if}}{{if value.ISSUE_TYPE == 'B'}}bug{{/if}}">
            <%--<input type="checkbox" checked="checked" class="left">--%>
            <div class="left">
                <div class="left">
                    <span class="black" style="margin-right: 10px;">{{value.CODE}}</span>
                    <span class="black">{{value.TITLE}}</span>
                </div>
            </div>
        </div>
        {{/each}}
    </div>
</script>

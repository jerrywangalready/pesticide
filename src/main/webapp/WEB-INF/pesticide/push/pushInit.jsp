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
<div >
    <form class="form-inline row query-form" role="form" id="query_box">
    </form>
    <div id="grid">
    </div>
    <nav id="page-bar">
    </nav>
</div>
<script id="grid_template" type="text/html">
    {{if list.length == 0}}
    <div><span>未查询到任何数据！</span></div>
    {{/if}}
    {{each list as value i}}
    <div class="grid-item task" state="0" onclick="push.js.detail(this,'{{value.MODEL_CODE}}')">
        <input type="checkbox" checked="checked" class="left">
        <div class="left">
            <div class="left">
                <span class="black">{{value.MODEL_CODE}}</span>
                <span class="black">{{value.MODEL_NAME}}</span>
            </div>
        </div>
    </div>
    {{/each}}
</script>
<script id="grid_two" type="text/html">
    <div>
        {{each list as value }}
        <div class="grid-item task">
            <input type="checkbox" checked="checked" class="left">
            <div class="left">
                <div class="left">
                    <span class="black">{{value.CODE}}</span>
                    <span class="black">{{value.TITLE}}</span>
                </div>
            </div>
        </div>
        {{/each}}
    </div>
</script>

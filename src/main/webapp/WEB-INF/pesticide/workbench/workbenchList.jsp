<%--
  Created by IntelliJ IDEA.
  User: jerrywang
  Date: 2017/1/15
  Time: 10:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../comm/head.jsp"%>
<link href="<%=path%>/pesticide/workbench/css/workbench.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/pesticide/workbench/js/workbenchList.js"></script>
<br>
<div >
    <div id="query_box"></div>
    <div id="grid">
    </div>
    <nav id="page-bar">
    </nav>
</div>

<script id="grid_template" type="text/html">
        {{each list as value i}}
        <div class="grid-item {{if value.ISSUE_TYPE == 'T'}}task{{/if}}{{if value.ISSUE_TYPE == 'B'}}bug{{/if}}"
             onclick="workbenchList.js.detail('{{value.UUID}}','{{value.ISSUE_TYPE}}')">
            <div class="left">
                <div class="left code_title">
                    <div class="left code">
                        <span>{{value.CODE}}</span>
                    </div>
                    <div class="left title">
                        <span>{{value.TITLE}}</span>
                    </div>
                </div>
                <div class="left">
                    <span class="gray">{{value.VERSION_CODE}}</span>
                </div>
            </div>
            <div class="right">
                <div class="priority">
                    <span class="glyphicon glyphicon-flag l{{value.PRIORITY}}"></span>
                </div>
            </div>
        </div>
        {{/each}}
</script>
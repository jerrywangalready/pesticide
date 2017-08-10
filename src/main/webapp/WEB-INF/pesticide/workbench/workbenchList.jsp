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
    {{if list.length == 0 && source != 'bell'}}
    <div class="nothing"><span></span></div>
    {{/if}}
    {{each list as value i}}
    <div class="grid-item {{if value.ISSUE_TYPE == 'T'}}task{{/if}}{{if value.ISSUE_TYPE == 'B'}}bug{{/if}}" {{if source == 'bell'}}style="display: none"{{/if}}
         onclick="workbenchList.js.detail('{{value.UUID}}','{{value.ISSUE_TYPE}}')"
        ondblclick="workbenchList.js.detailForNew('{{value.UUID}}','{{value.ISSUE_TYPE}}')">
        <%--<div class="bookmark">--%>
            <%--<span class="left glyphicon glyphicon-bookmark {{if value.STATE != '4'}}hide{{/if}}"></span>--%>
        <%--</div>--%>
        <div class="left">
            <div class="priority">
                <span class="glyphicon glyphicon-flag l{{value.PRIORITY}}" title="优先级"></span>
            </div>
        </div>
        <div class="left item-left">
            <div class="left code_title">
                <div class="left code">
                    <span>{{value.CODE}}</span>
                </div>
                <div class="left title">
                    <span>{{value.TITLE}}</span>
                </div>
            </div>
            <div class="left brief_info">
                <span class="gray">{{if value.STATE == '1'}}{{value.CREATE_USER}}{{else}}{{value.PRINCIPAL}}{{/if}}</span>
                <span class="gray">{{value.UPDATE_TIME}}</span>
                <span class="gray">{{value.VERSION_CODE}}</span>
            </div>
        </div>
        <%--<div class="right item-label">--%>
            <%--<span class="label label-{{if value.STATE == '9'}}default{{/if}}{{if value.STATE == '4'}}primary{{/if}}{{if value.STATE == '5'}}success{{/if}}{{if value.STATE == '3'}}info{{/if}}{{if value.STATE == '2'}}warning{{/if}}{{if value.STATE == '1'}}danger{{/if}}">{{value.STATE | dict:'state'}}</span>--%>
        <%--</div>--%>
    </div>
    {{/each}}
</script>
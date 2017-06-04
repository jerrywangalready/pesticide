<%--
  Created by IntelliJ IDEA.
  User: jerrywang
  Date: 2017/1/15
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%--<%@ include file="../../comm/comm.jsp"%>--%>
<%
    String path = request.getContextPath();
%>
<link href="<%=path%>/pesticide/creation/css/creation.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/pesticide/issuePool/js/issuePoolUpdate.js"></script>
<div>
    <br>
    <form role="form" id="main_form">
        <input type="hidden" id="uuid" name="uuid">
        <div class="row">
            <div class="form-group has-defaut has-feedback btn-primary" style="display: none;" id="link_info">
                <span class="glyphicon glyphicon-link" style="margin-left:15px;"></span>
                <span style="margin-left: 7px;" id="link_info_text"></span>
                <span class="glyphicon glyphicon-remove" id="link_info_close" aria-hidden="true"></span>
                <input type="hidden" id="parent_code" name="parent_code">
                <input type="hidden" id="parent_type" name="parent_type">
            </div>
            <div class="col-md-9">
                <div class="form-group">
                    <input type="text" class="form-control" name="title" id="title" validate="required" placeholder="标题">
                </div>

                <textarea name="description" id="description" style="display: none;" rows="10" cols="80"></textarea>


            </div>
            <div class="col-md-3">
                <div style="height: 456px;">
                    <div class="mb_15 btn-group row" data-toggle="buttons" style="margin-left: 0px;width: 100%;">
                        <label class="btn btn-primary col-md-6 active" onclick="issuePoolUpdate.js.chooseTask(this)">
                            <input type="radio" name="issueType" autocomplete="off" value="T" checked>Task
                        </label>
                        <label class="btn btn-warning col-md-6" onclick="issuePoolUpdate.js.chooseBug(this)">
                            <input type="radio" name="issueType" autocomplete="off" value="B">Bug
                        </label>
                    </div>
                    <div class="mb_15 btn-primary" id="monitor" style="width: 100%;height: 2px;"></div>
                    <div class="mb_15">
                        <button type="button" class="btn btn-default full_button" id="link_button"
                                data-toggle="modal" data-target="#link_table" onclick="issuePoolUpdate.js.getLinkInfo()">关联前序任务</button>
                    </div>
                    <div class="mb_15">
                        <select class="form-control select_empty" id="model_select" name="model_code" validate="required">
                            <option class="select_empty" value="">-- 选择模块 --</option>
                        </select>
                    </div>
                    <div class="mb_15">
                        <select class="form-control select_empty" id="principal" name="principal" validate="required">
                            <option class="select_empty" value="">-- 选择负责人 --</option>
                        </select>
                    </div>
                    <div class="mb_15">
                        <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd"
                             data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                            <input class="form-control" size="16" type="text" id="deadline" name="deadline" readonly placeholder="完成时间">
                            <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                            <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                        </div>
                        <input type="hidden" id="dtp_input2" value=""/>
                    </div>
                    <div class="mb_15">
                        <select class="form-control select_empty" id="version_code" name="version_code">
                            <option class="select_empty" value="">-- 选择版本号 --</option>
                        </select>
                    </div>
                    <div class="mb_15">
                        <select class="form-control select_empty" id="priority" name="priority" validate="require">
                            <option class="select_empty" value="">-- 选择优先级 --</option>
                        </select>
                    </div>
                    <div id="bug_level_div" class="mb_15 btn-group" data-toggle="buttons" style="margin-left: 0px;width: 100%;display: none;">
                        <label class="btn btn-danger border_bottom_3px col-md-3" title="bug级别:非常严重">
                            <input type="radio" name="bug_level" autocomplete="off" value="A">A
                        </label>
                        <label class="btn btn-warning border_bottom_3px col-md-3 active" title="bug级别:严重">
                            <input type="radio" name="bug_level" autocomplete="off" value="B" checked>B
                        </label>
                        <label class="btn btn-success border_bottom_3px col-md-3" title="bug级别:一般">
                            <input type="radio" name="bug_level" autocomplete="off" value="C">C
                        </label>
                        <label class="btn btn-info border_bottom_3px col-md-3" title="bug级别:不严重">
                            <input type="radio" name="bug_level" autocomplete="off" value="D">D
                        </label>
                    </div>
                    <div class="mb_15" id="working_day_div">
                        <input id="working_day" name="working_day" class="ui-spinner-input" style="width:181px;text-indent:10px;" placeholder="估算工时">
                    </div>
                </div>
                <br>
                <div>
                    <div class="mb_15">
                        <button type="button" class="btn btn-success full_button" onclick="issuePoolUpdate.js.save('save')" >保存</button>
                    </div>
                    <div class="mb_15">
                        <button type="button" class="btn btn-primary full_button" onclick="issuePoolUpdate.js.commit()" >提交</button>
                    </div>
                </div>
            </div>
        </div>

        <%--<img style="width: 15%;height: auto;" src='/upload/1eba5114f3204fabad340569bc98ea3e.png' />--%>
    </form>
</div>
<div class="modal fade" id="link_table" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">关联前序任务</h4>
            </div>
            <div class="link-proTask-search-input form-group has-feedback">
                <input type="text" class="form-control" id="link_proTask" aria-describedby="inputSuccess2Status" placeholder="输入任务号">
                <span class="glyphicon glyphicon-search form-control-feedback" aria-hidden="true"></span>
            </div>
            <div class="modal-body no-padding-lr" id="link_info_body">
            </div>
        </div>
    </div>
</div>
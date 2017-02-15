<%--
  Created by IntelliJ IDEA.
  User: jerrywang
  Date: 2017/1/15
  Time: 12:10
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../comm/comm.jsp"%>
<link href="<%=path%>/pesticide/creation/css/creation.css" type="text/css" rel="stylesheet">
<script type="text/javascript" src="<%=path%>/pesticide/creation/js/creation.js"></script>
<div>
    <br>
    <form role="form" id="main_form">
        <input type="hidden" id="uuid" name="uuid">
        <div class="row">
            <div class="form-group has-defaut has-feedback btn-primary" style="display: none;" id="link_info">
                <span style="margin-left: 15px;" id="link_info_text"></span>
                <span class="glyphicon glyphicon-remove" id="link_info_close" aria-hidden="true"></span>
            </div>
            <div class="col-md-9">
                <div class="form-group">
                    <input type="text" class="form-control" name="title" id="title" validate="required" placeholder="标题">
                </div>

                <textarea name="description" id="description" style="display: none;" rows="10" cols="80"></textarea>


            </div>
            <div class="col-md-3">
                <div class="mb_15 btn-group row" id="ssss" data-toggle="buttons" style="margin-left: 0px;width: 100%;">
                    <label class="btn btn-primary col-md-4 active">
                        <input type="radio" name="taskType_code" autocomplete="off" value="1" checked>开发
                    </label>
                    <label class="btn btn-success col-md-4">
                        <input type="radio" name="taskType_code" autocomplete="off" value="2">测试
                    </label>
                    <label class="btn btn-warning col-md-4">
                        <input type="radio" name="taskType_code" autocomplete="off" value="3">修改
                    </label>
                </div>
                <div class="mb_15 btn-primary" id="monitor" style="width: 100%;height: 2px;"></div>
                <div class="mb_15">
                    <button type="button" class="btn btn-default full_button" id="link_button" data-toggle="modal" data-target="#link_table" style="display: none;" onclick="creation.js.getLinkInfo()">关联前序任务</button>
                </div>
                <div class="mb_15">
                    <select class="form-control select_empty" id="model_select" name="model_code" validate="required">
                        <option class="select_empty" value="">-- 选择模块 --</option>
                    </select>
                </div>
                <div class="mb_15">
                    <select class="form-control select_empty" id="dev_staff_select" name="develop_user" validate="required">
                        <option class="select_empty" value="">-- 选择开发人员 --</option>
                    </select>
                </div>
                <div class="mb_15">
                    <select class="form-control select_empty" id="test_staff_select" name="test_user">
                        <option class="select_empty" value="">-- 选择测试人员 --</option>
                    </select>
                </div>
                <div class="mb_15">
                    <div class="input-group date form_date" data-date="" data-date-format="yyyy-mm-dd" data-link-field="dtp_input2" data-link-format="yyyy-mm-dd">
                        <input class="form-control" size="16" type="text" id="finish_date" readonly placeholder="完成时间">
                        <span class="input-group-addon"><span class="glyphicon glyphicon-remove"></span></span>
                        <span class="input-group-addon"><span class="glyphicon glyphicon-calendar"></span></span>
                    </div>
                    <input type="hidden" id="dtp_input2" value=""/>
                </div>
                <div class="mb_15">
                    <select class="form-control select_empty" id="version_code" name="version_code" validate="required">
                        <option class="select_empty" value="">-- 选择版本号 --</option>
                    </select>
                </div>
                <div class="mb_15 bug_level" style="display: none;">
                    <div class="radio col-md-3">
                        <label style="color: #3071a9;">
                            <input type="radio" name="optionsRadios" id="optionsRadios1" value="option1" checked>
                            D
                        </label>
                    </div>
                    <div class="radio col-md-3">
                        <label style="color:#449d44;">
                            <input type="radio" name="optionsRadios" id="optionsRadios2" value="option2">
                            C
                        </label>
                    </div>
                    <div class="radio col-md-3">
                        <label style="color: #f0ad4e;">
                            <input type="radio" name="optionsRadios" id="optionsRadios3" value="option3">
                            B
                        </label>
                    </div>
                    <div class="radio col-md-3 ">
                        <label style="color: #a94442;">
                            <input type="radio" name="optionsRadios" id="optionsRadios4" value="option3">
                            A
                        </label>
                    </div>
                </div>
                <div class="mb_15 checkbox">
                    <label style="color: #a94442;">
                        <input type="checkbox"> 紧急
                    </label>
                </div>
                <%--<div class="mb_15">--%>
                    <%--<input type="text" name="test" class="form-control" validate="required,length[-5]">--%>
                <%--</div>--%>
                <br>
                <div class="mb_15">
                    <button type="button" class="btn btn-success full_button" onclick="creation.js.save('save')">保存</button>
                </div>
                <div class="mb_15">
                    <button type="button" class="btn btn-primary full_button" onclick="creation.js.commit()">提交</button>
                </div>
            </div>
        </div>

        <img style="width: 15%;height: auto;" src='/upload/1eba5114f3204fabad340569bc98ea3e.png' />
    </form>
</div>
<div class="modal fade" id="link_table" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel">关联前序任务</h4>
            </div>
            <div class="modal-body no-padding-lr" id="link_info_body">
            </div>
        </div>
    </div>
</div>
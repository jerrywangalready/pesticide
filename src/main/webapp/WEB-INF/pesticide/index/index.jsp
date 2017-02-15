<!DOCTYPE HTML>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="../../comm/head.jsp"%>
<html lang="zh-CN">
<head>
    <meta charset="utf-8" />

    <meta name="viewport" content="width=device-width, minimum-scale=1.0, maximum-scale=1.0" />

    <title>Pesticide</title>
    <script type="text/javascript" src="<%=path%>/pesticide/index/js/index.js"></script>
<body>
<!-- START Top-Toolbar -->
<aside id="top_toolbar" class="top-aside clearfix head_float" style="display: none;">
    <div class="center-wrap">
        <div class="one_half">
            <div class="sidebar-widget">
                <ul class="custom-menu">
                    <li class="current-menu-item"><a href="javascript:void(0);" onclick="index.js.menuClick('workbench/init')">工作台</a></li>
                    <li><a href="javascript:void(0);" onclick="index.js.menuClick('creation/init')">创建</a></li>
                    <li id="li_task_pool"><a class="shake shake-horizontal" href="javascript:void(0);" onclick="index.js.menuClick('workbench/workbench')">任务池</a></li>
                    <li><a href="javascript:void(0);" onclick="index.js.menuClick('workbench/workbench')">记录</a></li>
                    <li><a href="javascript:void(0);" onclick="index.js.menuClick('workbench/workbench')">图表</a></li>
                    <li><a href="javascript:void(0);" onclick="index.js.menuClick('workbench/workbench')">设置</a></li>
                </ul>
            </div>
        </div>
        <!-- END top-toolbar-left -->
        <div class="one_half">
            <div class="sidebar-widget">
                <ul class="social_icons">
                    <li style="width:45px;"><span style="float:left;margin:2px 2px;" class="glyphicon glyphicon-bell"></span> <a name="bell" href="javascript:void(0);"></a></li>
                    <li><span class="glyphicon glyphicon-book"></span>
                        <a name="a_object" href="javascript:void(0);" class="" data-toggle="modal" data-target="#myModal"></a>
                    </li>
                    <li><span class="glyphicon glyphicon-user"></span> <a name="a_name" href="javascript:void(0);" class=""></a></li>
                    <li><span class="glyphicon glyphicon-log-out"></span> <a href="javascript:void(0);" class="" onclick="index.js.logout()">退出</a></li>
                </ul>
            </div>
        </div>
        <!-- END top-toolbar-right -->
    </div>
    <!-- END center-wrap -->
    <div class="top-aside-shadow"></div>
</aside>
<aside class="top-aside clearfix">
    <div class="center-wrap">
        <div class="one_half">
            <div class="sidebar-widget">
            </div>
        </div>
        <!-- END top-toolbar-left -->
        <div class="one_half">
            <div class="sidebar-widget">
                <ul class="social_icons">
                    <li style="width:45px;"><span style="float:left;margin:2px 2px;" class="glyphicon glyphicon-bell"></span> <a name="bell" href="javascript:void(0);"></a></li>
                    <li><span class="glyphicon glyphicon-book"></span>
                        <a name="a_object" href="javascript:void(0);" class="" data-toggle="modal" data-target="#myModal"></a>
                    </li>
                    <li><span class="glyphicon glyphicon-user"></span> <a name="a_name" href="javascript:void(0);" class=""></a></li>
                    <li><span class="glyphicon glyphicon-log-out"></span> <a href="javascript:void(0);" class="" onclick="index.js.logout()">退出</a></li>
                </ul>
            </div>
        </div>
        <!-- END top-toolbar-right -->
    </div>
    <!-- END center-wrap -->
    <div class="top-aside-shadow"></div>
</aside>
<!-- END Top-Toolbar -->






<!-- START Header -->
<header>
    <div class="center-wrap">
        <div class="companyIdentity">
            <a href="page-template-homepage-jquery.html"><img class="logo" src="<%=path%>/comm/image/logo.png" alt="Pesticide" /></a>
        </div>
        <!-- END companyIdentity -->

        <!-- START Main Navigation -->
        <nav>
            <ul id="menu_bar">
                <li class="current-menu-item"><a href="javascript:void(0);" onclick="index.js.menuClick('workbench/init')">工作台</a></li>
                <li><a href="javascript:void(0);" onclick="index.js.menuClick('creation/init')">创建</a></li>
                <li><a id="menu_task_pool" class="shake shake-horizontal" href="javascript:void(0);" onclick="index.js.menuClick('test/test3')">任务池</a></li>
                <li><a href="javascript:void(0);" onclick="index.js.menuClick('demo/init')">Demo</a></li>
                <li><a href="javascript:void(0);" onclick="index.js.menuClick('test/test4')">记录</a></li>
                <li><a href="javascript:void(0);" onclick="index.js.menuClick('test/test5')">图表</a></li>
                <li><a href="javascript:void(0);" onclick="index.js.menuClick('settings/init')">设置</a></li>
            </ul>
        </nav>
        <!-- END Main Navigation -->
    </div>
    <!-- END center-wrap -->
</header>
<!-- END Header -->


<!-- START Banner Area -->
<section class="banner-slider" style="height: 20px;">
    <!-- END center-wrap -->
    <div class="shadow top"></div>
    <div class="shadow bottom"></div>
    <div class="tt-overlay"></div>
</section>
<!-- END Banner Area -->

<!-- START Content Container -->
<section id="content-container" class="clearfix">

</section>
<!-- END Content Container -->

<!-- START Footer Callout -->
<%--<div class="footer-callout clearfix">--%>
    <%--<div class="center-wrap tt-relative">--%>
        <%--<div class="footer-callout-content">--%>
            <%--<p class="callout-heading">Site-wide callout section</p>--%>
            <%--<p class="callout-text">Engage your customers and promote a rewarding call-to-action. Easily toggle this on or off.</p>--%>
        <%--</div>--%>
        <%--<!-- END footer-callout-content -->--%>
        <%--<div class="footer-callout-button">--%>
            <%--<a href="../../../../../../../../../Documents/hh/H159/content-responsive-design.html" class="large green button">View Theme Features &rarr;</a>--%>
        <%--</div>--%>
        <%--<!-- END footer-callout-button -->--%>
    <%--</div>--%>
    <%--<!-- END center-wrap -->--%>
<%--</div>--%>
<!-- END Footer Callout -->

<!-- START Footer -->
<footer>
    <!-- END center-wrap -->
    <div class="footer-copyright clearfix">
        <div class="center-wrap clearfix">
            <div class="foot-copy">
                <p>Copyright &copy; 2017 State Grid Corporation of China. All rights reserved.</p>
            </div>
            <!-- END foot-copy -->
            <a href="#" id="scroll_to_top" class="link-top">Scroll to Top</a>
            <ul class="footer-nav">
                <li><a href="../../../../../../../../../Documents/hh/H159/page-template-homepage-lightbox-hero.html">Pages</a></li>
                <li><a href="../../../../../../../../../Documents/hh/H159/content-responsive-design.html">Features</a></li>
                <li><a href="../../../../../../../../../Documents/hh/H159/content-color-autumn.html">Colors</a></li>
                <li><a href="../../../../../../../../../Documents/hh/H159/page-template-shortcodelist.html">Shortcodes</a></li>
                <li><a href="../../../../../../../../../Documents/hh/H159/page-template-blog.html">Blog</a></li>
            </ul>
        </div>
        <!-- END center-wrap -->
    </div>
    <!-- END footer-copyright -->
    <div class="shadow top"></div>
    <div class="tt-overlay"></div>
</footer>

<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog-index" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">
                    <span aria-hidden="true">&times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">切换项目</h4>
            </div>
            <div class="modal-body no-padding-lr">

            </div>
        </div>
    </div>
</div>


<%--<script type="text/javascript" src="../../../../../../../../../Documents/hh/H159/js/custom-main.js"></script>--%>
<%--<script type="text/javascript" src="../../../../../../../../../Documents/hh/H159/js/jquery.prettyPhoto.js"></script>--%>
<%--<script type="text/javascript" src="../../../../../../../../../Documents/hh/H159/js/slides.min.jquery.js"></script>--%>
<%--<script type="text/javascript" src="../../../../../../../../../Documents/hh/H159/js/jquery.cycle.all.min.js"></script>--%>
<%--<script type="text/javascript" src="../../../../../../../../../Documents/hh/H159/js/jquery.easing.1.3.js"></script>--%>
</body>
</html>
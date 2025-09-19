<#-- page import (style + script) -->
<#macro commonStyle>

    <#-- favicon、logo -->
    <link rel="icon" href="${request.contextPath}/static/favicon.ico" />

    <#-- meta -->
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <!-- Tell the browser to be responsive to screen width -->
    <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport">

    <#-- link style -->
    <!-- Bootstrap -->
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/bower_components/bootstrap/css/bootstrap.min.css">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/bower_components/font-awesome/css/font-awesome.min.css">
    <!-- Ionicons -->
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/bower_components/Ionicons/css/ionicons.min.css">
    <!-- Theme style -->
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/dist/css/AdminLTE.min.css">
    <!-- AdminLTE Skins. Choose a skin from the css/skins folder instead of downloading all of them to reduce the load. -->
    <link rel="stylesheet" href="${request.contextPath}/static/adminlte/dist/css/skins/_all-skins.min.css">

    <!-- HTML5 Shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

</#macro>

<#macro commonScript>

    <!-- jQuery -->
    <script src="${request.contextPath}/static/adminlte/bower_components/jquery/jquery.min.js"></script>
    <!-- Bootstrap -->
    <script src="${request.contextPath}/static/adminlte/bower_components/bootstrap/js/bootstrap.min.js"></script>
    <!-- SlimScroll -->
    <script src="${request.contextPath}/static/adminlte/bower_components/jquery-slimscroll/jquery.slimscroll.min.js"></script>
    <!-- FastClick -->
    <script src="${request.contextPath}/static/adminlte/bower_components/fastclick/fastclick.js"></script>

    <#-- jquery.cookie -->
    <script src="${request.contextPath}/static/plugins/jquery/jquery.cookie.js"></script>
    <#-- jquery.validate -->
    <script src="${request.contextPath}/static/plugins/jquery/jquery.validate.min.js"></script>
    <#-- layer -->
    <script src="${request.contextPath}/static/plugins/layer/layer.js"></script>

    <script>
        // init page param
        var base_url = '${request.contextPath}';

        // init menu status
        if ( 'close' == $.cookie('sidebar_status') ) {
            $('body').addClass('sidebar-collapse');
        } else {
            $('body').removeClass('sidebar-collapse');
        }
        // init body fixed
        $('body').addClass('fixed');
        // init menu speed
        $('.sidebar-menu').attr('data-animation-speed', 1);
    </script>

    <!-- AdminLTE App -->
    <script src="${request.contextPath}/static/adminlte/dist/js/adminlte.min.js"></script>

    <script src="${request.contextPath}/static/js/common.1.js"></script>

</#macro>

<#-- page module: Header-->
<#macro commonHeader>
    <header class="main-header">
        <a href="${request.contextPath}/" class="logo">
            <span class="logo-mini"><b>XXL</b></span>
            <span class="logo-lg"><b>XXL SSO</b></span>
        </a>
        <nav class="navbar navbar-static-top" role="navigation">
            <!--header left -->
            <a href="#" class="sidebar-toggle" data-toggle="push-menu" role="button">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </a>
            <!--header right -->
            <div class="navbar-custom-menu">
                <ul class="nav navbar-nav">
                    <li class="dropdown user user-menu">
                        <a href="javascript:void(0);" id="logoutBtn"  >
                            <span class="hidden-xs">注销【${loginInfo.userName}】</span>
                        </a>
                    </li>
                </ul>
            </div>
        </nav>
    </header>
</#macro>

<#-- page module: Left-->
<#macro commonLeft pageName >
    <!-- Left side column. contains the logo and sidebar -->
    <aside class="main-sidebar">
        <!-- sidebar: style can be found in sidebar.less -->
        <section class="sidebar">
            <!-- sidebar menu: : style can be found in sidebar.less -->
            <ul class="sidebar-menu">
                <li class="header">导航</li>
                <li class="nav-click <#if pageName == "help">active</#if>" ><a href="${request.contextPath}/"><i class="fa fa-circle-o text-gray"></i><span>使用教程</span></a></li>
            </ul>
        </section>
        <!-- /.sidebar -->
    </aside>
</#macro>

<#-- page module: Footer-->
<#macro commonFooter >
    <footer class="main-footer">
        Powered by <b>XXL-SSO</b> 2.1.0
        <div class="pull-right hidden-xs">
            <strong>Copyright &copy; 2018-${.now?string('yyyy')} &nbsp;
                <a href="https://www.xuxueli.com/" target="_blank" >xuxueli</a>
                &nbsp;
                <a href="https://github.com/xuxueli/xxl-sso" target="_blank" >github</a>
            </strong><!-- All rights reserved. -->
        </div>
    </footer>
</#macro>
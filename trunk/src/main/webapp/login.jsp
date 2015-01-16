<%@ page pageEncoding="utf-8" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 指定页面显示的图标 -->
    <link rel="icon" href="icon/show.ico">
    <title>登录页面</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- BootStrap Theme css -->
    <link href="css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- 此案例的自定义样式 -->
    <link href="css-prj/login.css" rel="stylesheet">
    <!-- 导入外部的js文件 -->
    <script type="application/javascript" src="js-prj/login.js"></script>
</head>
<body>
<!-- 导航栏区 -->
<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#mynav">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="javascript:void(0);">TPCS</a>
        </div>

        <div class="navbar-collapse collapse" id="mynav">
            <ul class="nav navbar-nav">
                <li class="active"><a href="#">主页</a></li>
                <li><a href="html/help.html">帮助</a></li>
            </ul>
            <!-- 右边 -->
            <ul class="nav navbar-nav navbar-right">
                <li><a href="html/contact.html">联系我们</a></li>
                <li><a href="javascript:void(0);" onclick="SetHome(this,'http://192.168.3.169:8080/tpcs');">设为首页</a>
                </li>
                <li><a href="javascript:void(0);" onclick="AddFavorite('我的网站',location.href)">加入收藏</a></li>
            </ul>

        </div>
        <!-- nav bar -->
    </div>
</nav>
<!-- 登录区 -->
<div class="container">
    <div class="row">
        <div class="col-md-offset-2 col-md-8">

            <div class="login_layout">
                <fieldset>
                    <legend>用户登录入口</legend>
                    <form class="form-horizontal" role="form" method="post" action="${path}/employees/login">
                        <div class="form-group">
                            <label for="inputUserName2" class="col-sm-2 control-label">用户名</label>

                            <div class="col-sm-5">
                                <input type="text" name="str" class="form-control" id="inputUserName2"
                                       placeholder="请输入用户名"/>
                            </div>
                            <div class="col-sm-5">
                                <label class="control-label alert-danger">login message</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <label for="inputPassword2" class="col-sm-2 control-label">密&nbsp;码</label>

                            <div class="col-sm-5">
                                <input type="password" name="password" class="form-control" id="inputPassword2"
                                       placeholder="请输入密码"/>
                            </div>
                            <div class="col-sm-4">
                                <label class="control-label alert-danger">${errorMsg}</label>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-5">
                                <div class="checkbox-inline">
                                    <label>
                                        <input type="checkbox" name="saveme" value="true"/>&nbsp;记住我
                                    </label>
                                </div>
                                <div class="checkbox-inline">
                                    <label>
                                        <input type="checkbox" name="isStudent" value="true"/>&nbsp;我是学员
                                    </label>
                                </div>
                            </div>
                        </div>

                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-5">
                                <button type="submit" class="btn btn-primary">登录</button>
                            </div>
                        </div>
                    </form>
                </fieldset>
            </div>

        </div>
    </div>

    <!-- 最后的部份 -->
    <div class="row">
        <div class="col-md-offset-2 col-md-8">
            <div class="row">
                <div class="col-md-offset-4 col-md-2">
                    <img src="images/logo-s.png">
                </div>
                <div class="col-md-6">
                    <div class="row">
                        <div class="col-md-offset-2 col-md-10">
                            <a href="http://www.sztzedu.com">威思登软件科技有限公司&copy;所有</a>
                        </div>
                    </div>
                    <div class="gap"></div>
                    <div class="row">
                        <div class="col-md-offset-2 col-md-10">
                            <span class="text-info">天智教育&reg; |&nbsp;</span>
                            <span class="text-info">实训管理平台 |&nbsp;</span>
                            <span class="text-info">2012－2020 </span>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>

<!-- 导入相关的js -->
<script src="js/jquery-1.9.1.min.js"></script>
<script src="js/bootstrap.min.js"></script>
</body>
</html>
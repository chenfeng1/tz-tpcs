<%@ page pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
  <!-- 导航栏区 -->
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="container-fluid">
        <!-- 导航栏的头部,并且可以适合其它尺码的设备 -->
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#mynav">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="javascript:void(0);">TPCS[要换成一个图标]</a>
        </div>

        <div class="navbar-collapse collapse" id="mynav">
            <ul class="nav navbar-nav">
                <li><a href="#">主页</a></li>

                <%--迭代第1级菜单--%>
                <c:forEach items="${sessionScope.loginUserResources}" var="res" varStatus="stat">
                    <!-- 下接菜单: 班级 -->
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle" data-toggle="dropdown">${res.name}<strong class="caret"></strong></a>
                        <ul class="dropdown-menu" role="menu">
                            <%--迭代第2级菜单--%>
                            <c:forEach items="${res.children}" var="subRes">
                                <c:if test="${subRes.show == true}">
                                    <li><a href="${path}/${subRes.value}">${subRes.name}</a></li>
                                </c:if>
                            </c:forEach>
                        </ul>
                    </li>
                </c:forEach>

                <!-- 固定菜单: 我的 -->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">我的<strong class="caret"></strong></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">
                            <span class="glyphicon glyphicon-envelope"> </span>
                            &nbsp;提醒消息&nbsp;
                            <span id="unread_msg" class="badge" style="background-color: darkorange">10</span>
                        </a></li>
                        <li><a href="#">
                            <span class="glyphicon glyphicon-transfer"> </span>
                            &nbsp;站内私信
                        </a></li>
                        <li class="divider"></li>
                        <li><a href="#">
                            <span class="glyphicon glyphicon-user"> </span>
                            &nbsp;个人资料
                        </a></li>
                        <li><a href="#">
                            <span class="glyphicon glyphicon-picture"> </span>
                            &nbsp;修改头像
                        </a></li>
                        <li><a href="#">
                            <span class="glyphicon glyphicon-edit"> </span>
                            &nbsp;修改密码
                        </a></li>
                        <li class="divider"></li>
                        <li><a href="${path}/logout">
                            <span class="glyphicon glyphicon-off"> </span>
                            &nbsp;安全退出
                        </a></li>
                    </ul>
                </li>

            </ul>
        </div>
    </div>
</nav>

<div class="container">
    <!-- 由于导航用了navbar-fixed-top,所以，先与上面定义一个间隔  -->
    <div class="gap"></div>

    <!-- 菜单提示栏 -->
    <div class="panel panel-default">
        <!-- 里面的值需要通过JS来操作，它的值是根据当前的菜单来定的 -->
        <div class="panel-heading">
            <%--toReplace 测试区域，使用后注释--%>
            <%--<div style="background-color: #ffff00">测试区域 a:${sessionScope.loginUser.number} , b:${sessionScope.loginUserResources}</div>--%>
            首页&nbsp;
            <span class="glyphicon glyphicon-arrow-right"></span>&nbsp;
            <span id="module">班级</span>&nbsp;
            <span class="glyphicon glyphicon-arrow-right"></span>&nbsp;
            <span id="function">班级列表</span>&nbsp;
        </div>
    </div>
</div>

<%@ page pageEncoding="utf-8"%>
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
                <!-- 下接菜单: 班级 -->
                <li class="dropdown active">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">班级<strong class="caret"></strong></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="${path}/clazz/list">班级列表</a></li>
                        <li><a href="#">导入班级</a></li>
                        <li class="divider"></li>
                        <li><a href="#">班级活动</a></li>
                    </ul>
                </li>
                <!-- 下接菜单: 学员 -->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">学员<strong class="caret"></strong></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">学员列表</a></li>
                        <li><a href="#">导入学员</a></li>
                    </ul>
                </li>
                <!-- 下接菜单: 知识库 -->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">知识库<strong class="caret"></strong></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">课程列表</a></li>
                        <li><a href="#">查看课程</a></li>
                        <li class="divider"></li>
                        <li><a href="#">知识中心</a></li>
                    </ul>
                </li>
                <!-- 下接菜单: 项目案例 -->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">项目案例<strong class="caret"></strong></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">项目列表</a></li>
                        <li><a href="#">分配项目</a></li>
                        <li class="divider"></li>
                        <li><a href="#">进行中的项目</a></li>
                        <li><a href="#">项目追踪</a></li>
                    </ul>
                </li>
                <!-- 下接菜单: 实训过程 -->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">实训过程<strong class="caret"></strong></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">学员请假</a></li>
                        <li><a href="#">学员违纪</a></li>
                        <li><a href="#">学员转班</a></li>
                        <li class="divider"></li>
                        <li><a href="#">学员访谈</a></li>
                        <li><a href="#">学员评价</a></li>
                        <li class="divider"></li>
                        <li><a href="#">模拟面试</a></li>
                    </ul>
                </li>
                <!-- 下接菜单: 考试 -->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">考试<strong class="caret"></strong></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">考试安排</a></li>
                        <li><a href="#">考试记录</a></li>
                        <li class="divider"></li>
                        <li><a href="#">试卷列表</a></li>
                        <li><a href="#">导入题库</a></li>
                    </ul>
                </li>
                <!-- 下接菜单: 就业 -->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">就业<strong class="caret"></strong></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">合作企业</a></li>
                        <li><a href="#">订单培训</a></li>
                        <li class="divider"></li>
                        <li><a href="#">就业数据</a></li>
                    </ul>
                </li>
                <!-- 下接菜单: 员工 -->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">员工<strong class="caret"></strong></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">员工列表</a></li>
                        <li><a href="#">新增员工</a></li>
                        <li class="divider"></li>
                        <li><a href="#">通信录</a></li>
                    </ul>
                </li>
                <!-- 下接菜单: 报表 -->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">报表<strong class="caret"></strong></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">学员统计</a></li>
                        <li><a href="#">实训过程分析</a></li>
                        <li class="divider"></li>
                        <li><a href="#">实训报告</a></li>
                        <li><a href="#">数据分析</a></li>
                        <li class="divider"></li>
                        <li><a class="#">学员渠道分析</a></li>
                        <li><a class="#">学历分析</a></li>
                        <li><a class="#">就业分析</a></li>
                    </ul>
                </li>
                <!-- 下接菜单: 系统 -->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">系统<strong class="caret"></strong></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">
                            <span class="glyphicon glyphicon-cog"></span>
                            &nbsp;全局设置
                        </a></li>
                        <li class="divider"></li>
                        <li><a href="#">角色维护</a></li>
                        <li><a href="#">角色权限设置</a></li>
                    </ul>
                </li>
                <!-- 下接菜单: 日志 -->
                <li class="dropdown">
                    <a href="#" class="dropdown-toggle" data-toggle="dropdown">日志<strong class="caret"></strong></a>
                    <ul class="dropdown-menu" role="menu">
                        <li><a href="#">
                            <span class="glyphicon glyphicon-search"></span>
                            &nbsp;日志中心
                        </a></li>
                    </ul>
                </li>
                <!-- 下接菜单: 我的 -->
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
            首页&nbsp;
            <span class="glyphicon glyphicon-arrow-right"></span>&nbsp;
            <span id="module">班级</span>&nbsp;
            <span class="glyphicon glyphicon-arrow-right"></span>&nbsp;
            <span id="function">班级列表</span>&nbsp;
        </div>
    </div>
</div>

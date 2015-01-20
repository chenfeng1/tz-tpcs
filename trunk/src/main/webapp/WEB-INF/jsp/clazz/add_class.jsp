<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- 指定页面显示的图标 -->
    <link rel="icon" href="${pageContext.request.contextPath }/icon/show.ico">
    <title>新增班级</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
    <!-- BootStrap Theme css -->
    <link href="${pageContext.request.contextPath }/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- 此案例的自定义样式 -->
    <link href="${pageContext.request.contextPath }/css-prj/main.css" rel="stylesheet">
    <!-- 导入外部的js文件 -->
</head>
<body>
<!-- 内容区 -->
<div class="container">
    <form class="form-horizontal" role="form" action="${pageContext.request.contextPath}/clazz/save" method="post" >
        <div class="form-group">
            <label for="classname" class="col-md-offset-1 col-md-1 control-label">班级名</label>
            <div class="col-md-3">
                <input type="text" id="classname" class="form-control" placeholder="班级的名称" name="clazz.name"/>
            </div>
            <div class="col-md-7" id="classname_id">提示信息</div>
        </div>
        <div class="form-group">
            <label for="classroom" class="col-md-offset-1 col-md-1 control-label">所在教室</label>
            <div class="col-md-3">
                <input type="text" id="classroom" class="form-control" placeholder="教室的名称"/>
            </div>
            <div class="col-md-7" id="classroom_id"></div>
        </div>
        <div class="form-group">
            <label for="classname" class="col-md-offset-1 col-md-1 control-label">开班日期</label>
            <div class="col-md-2">
                <input type="date" id="open_date" class="form-control" placeholder="2015-01-27" name="clazz.open"/>
            </div>
            <div class="col-md-8" id="open_date_id"></div>
        </div>
        <div class="form-group">
            <label for="classname" class="col-md-offset-1 col-md-1 control-label">开班人数</label>
            <div class="col-md-2">
                <input type="number" id="class_students" class="form-control" placeholder="30"/>
            </div>
            <div class="col-md-8" id="class_students_id"></div>
        </div>
        <div class="form-group">
            <label for="class_adviser" class="col-md-offset-1 col-md-1 control-label">班主任</label>
            <div class="col-md-3">
                <input type="text" id="class_adviser" class="form-control" placeholder="班主任名" name="clazz.advisor"/>
            </div>
            <div class="col-md-7" id="class_adviser_id"></div>
        </div>

        <div class="form-group">
            <label for="trainer_date" class="col-md-offset-1 col-md-1 control-label">训练营日</label>
            <div class="col-md-2">
                <input type="date" id="trainer_date" class="form-control" placeholder="2015-01-18"/>
            </div>
            <div class="col-md-8" id="trainer_date_id"></div>
        </div>

        <div class="form-group">
            <label for="teachername" class="col-md-offset-1 col-md-1 control-label">讲师名</label>
            <div class="col-md-3">
                <input type="text" id="teachername" class="form-control" placeholder="讲师名"/>
            </div>
            <div class="col-md-7" id="teachername_id"></div>
        </div>

        <div class="col-md-offset-2">
            <button type="submit" class="btn btn-primary">保存</button>&nbsp;
            <button type="reset" class="btn btn-primary">重填</button>
        </div>
    </form>
</div>
</body>
</html>
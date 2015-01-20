<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="zh-cn">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="icon" href="${path}/icon/show.ico">
   <!-- 指定页面显示的图标 -->
    <%--<title><tiles:insertAttribute name="title" ignore="true"/></title>--%>
    <title>班级列表</title>
    <!-- Bootstrap -->
    <link href="${path}/css/bootstrap.min.css" rel="stylesheet">
    <!-- BootStrap Theme css -->
    <link href="${path}/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- 此案例的自定义样式 -->
    <link href="${path}/css-prj/main.css" rel="stylesheet">
    <%--加载相关的 js 库文件--%>
    <script src="${path}/js/jquery-1.9.1.min.js"></script>
    <script src="${path}/js/bootstrap.min.js"></script>
    <!-- 导入外部自定义的js文件 -->

</head>
<body>
        <%--头部--%>
		<tiles:insertAttribute name="header" />
        <%--中部--%>
		<tiles:insertAttribute name="body" />
        <%--底部--%>
		<tiles:insertAttribute name="footer" />
</body>
</html>
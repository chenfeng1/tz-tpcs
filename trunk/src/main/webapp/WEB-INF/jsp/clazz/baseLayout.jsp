<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache"/>
   <!--  <link href="/style/main.css" rel="stylesheet" type="text/css"> -->
    <title><tiles:insertAttribute name="title" ignore="true"/></title>
   <!-- 指定页面显示的图标 -->
    <link rel="icon" href="${pageContext.request.contextPath }/icon/show.ico">
    <title>班级列表</title>

    <!-- Bootstrap -->
    <link href="${pageContext.request.contextPath }/css/bootstrap.min.css" rel="stylesheet">
    <!-- BootStrap Theme css -->
    <link href="${pageContext.request.contextPath }/css/bootstrap-theme.min.css" rel="stylesheet">
    <!-- 此案例的自定义样式 -->
    <link href="${pageContext.request.contextPath }/css-prj/main.css" rel="stylesheet">
    <!-- 导入外部的js文件 -->
	<style>
		#btn{
			margin-left: 105px;
		}
		
	</style>
</head>
<body id="project">
	
		<tiles:insertAttribute name="header" />
	
	
		<tiles:insertAttribute name="body" />
	
	
		<tiles:insertAttribute name="footer" />
	
	<!-- 导入相关的js -->
	<script src="${pageContext.request.contextPath }/js/jquery-1.9.1.min.js"></script>
	<script src="${pageContext.request.contextPath }/js/bootstrap.min.js"></script>
</body>
</html>
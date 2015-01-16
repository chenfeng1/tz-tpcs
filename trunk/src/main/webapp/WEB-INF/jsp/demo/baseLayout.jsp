<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<html>
<head>
    <meta http-equiv="pragma" content="no-cache"/>
    <link href="/style/main.css" rel="stylesheet" type="text/css">
    <s:set name="title" ><tiles:getAsString name="title" /></s:set>
    <title><s:text name="%{#title}"/></title>
</head>
<body id="project">
<table border="1" class="mainTable">
    <tr>
        <td colspan="2" style="height: 100px;background-color: yellow">
            <tiles:insertAttribute name="header" />
        </td>
    </tr>
    <tr>
        <td style="width: 20%;background-color: silver">
            <tiles:insertAttribute name="menu" />
        </td>
        <td style="width: 80%;background-color: aqua">
            <tiles:insertAttribute name="body" />
        </td>
    </tr>
    <tr>
        <td style="height: 100px;background-color: blue" colspan="2">
            <tiles:insertAttribute name="footer" />
        </td>
    </tr>
</table>
</body>
</html>
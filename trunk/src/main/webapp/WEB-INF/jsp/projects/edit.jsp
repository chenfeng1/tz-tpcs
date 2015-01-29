<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<div class="container">
    <%--错误信息部分--%>
    <c:if test="${errors != null}">
        <div role="alert" class="alert alert-danger">
            <c:forEach items="${errors}" var="error">
                <strong>${error.defaultMessage}</strong>
                <br/>
            </c:forEach>
        </div>
    </c:if>

    <%--表单部分--%>
    <form class="form-horizontal" role="form" action="${path}/projects/${actionType}" method="post" enctype="multipart/form-data">
        <input type="hidden" name="id" value="${projectCase.id}">
        <input type="hidden" name="version" value="${projectCase.version}">
        <div class="form-group">
            <label for="name" class="col-md-1 control-label">项目名称<span style="color: red">*</span></label>
            <div class="col-md-4">
                <input type="text" id="name" name="name" value="${projectCase.name}" class="form-control" placeholder="项目名称"/>
            </div>
            <label for="code" class="col-md-1 control-label">项目代码<span style="color: red">*</span></label>
            <div class="col-md-2">
                <input type="text" id="code" name="code" value="${projectCase.code}" class="form-control" placeholder="项目代码"/>
            </div>
        </div>
        <div class="form-group">
            <label for="desc" class="col-md-1 control-label">项目描述<span style="color: red">*</span></label>
            <div class="col-md-7">
                <textarea rows="12" id="desc" name="desc" class="form-control" placeholder="项目描述">${projectCase.desc}</textarea>
            </div>
        </div>

        <div class="form-group" style="padding-left: 10px;">
            <label for="inputSpecification" class="control-label">上传项目规格说明书&nbsp;</label>
            <input type="file" id="inputSpecification" name="functionSpec" accept="application/pdf">
            <p class="help-block">项目需求规格说明书，支持pdf格式.</p>
        </div>

        <div class="form-group" style="padding-left: 10px;">
            <label for="snapshot" class="control-label">上传项目截图&nbsp;</label>
            <input type="file" id="snapshot" name="snapshot" accept="image/jpeg,image/gif,image/png">
            <p class="help-block">项目截图，支持png,jpg,gif格式.</p>
        </div>

        <div class="col-md-offset-5 col-md-2">
            <button type="submit" class="btn btn-primary">保存</button>&nbsp;
            <button type="reset" class="btn btn-primary">重填</button>
        </div>
    </form>
</div>

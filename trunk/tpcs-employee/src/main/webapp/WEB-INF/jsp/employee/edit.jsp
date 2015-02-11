<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>

<script type="text/javascript" src="${path}/js/jquery.form.js"></script>

<script type="text/javascript">
    //点击返回 或 取消按钮
    //点击返回 或 取消按钮
    $("#backBtn,#updateEmployeeCancelBtn").click(function(){
        var currentState = history.state;
        //console.log(currentState);
        $.post("${path}/employees/search",currentState,function(result){
            $("#empListDiv").html(result);
        });
    });

    //监听添加员工按钮点击事件
    $("#updateEmployeeBtn").click(function(){
        $("#updateEmployeeForm").ajaxForm({
            success: function(result) {
                $("#empListDiv").html(result);
            }
        }).submit();
    });

    /**
     * 添加员工时切换 “更多信息”
     */
    function showMoreEmpInfo(){
        $("#moreEmpInfo").slideToggle(500);
    }
    function showPasswordInput(){
        $("#passwordInput").slideToggle(500);
    }
</script>

<div class="row">
    <div class="col-md-3" style="padding:25px 0 15px 15px;">
        <button id="backBtn" class="btn-sm btn-default">
            <span class="glyphicon glyphicon-arrow-left"></span>
            返回
        </button>
    </div>
</div>

<!-- 提示栏  -->
<div class="row" style="background-color: #efefef;border-top: 1px solid #cccccc;border-bottom: 1px solid #cccccc;margin: 0 2px;">
    <div class="col-md-11">
        <label style="font-size: large;">编辑员工</label>
    </div>
</div>

<%--错误信息部分--%>
<c:if test="${errors != null}">
    <div role="alert" class="alert alert-danger">
        <c:forEach items="${errors}" var="error">
            <strong>${error.defaultMessage}</strong>
            <br/>
        </c:forEach>
    </div>
</c:if>

<!-- 员工 表单区 -->
<div class="row" style="margin: 10px -7px;">
    <div class="col-md-12" style="margin-top: 10px;">
        <strong>员工基本信息</strong>
        <hr style="margin-top: 2px;background-color:#269abc;height: 3px;"/>
        <form:form id="updateEmployeeForm" class="form-horizontal" role="form" action="${path}/employees/update" modelAttribute="form">
            <%--真实姓名--%>
            <div class="form-group">
                <label for="realname" class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">姓名</span><span style="color: red">*</span></label>
                <div class="col-md-4">
                    <form:input class="form-control" path="realname"/>
                </div>
            </div>
            <%--性别--%>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    <span class="text-muted" style="font-weight: normal">性别</span>
                    <span style="color: red">*</span>
                </label>
                <div class="col-md-4">
                        <%--迭代性别数组--%>
                    <c:forEach items="${genderArray}" var="gender">
                        <label class="radio-inline">
                                <%--显示radio input--%>
                            <form:radiobutton path="gender" value="${gender}"/>
                                <%--国际化--%>
                            <spring:message code="enum.gender.${gender}"/>
                        </label>
                    </c:forEach>
                </div>
            </div>
            <%--岗位--%>
            <div class="form-group">
                <label for="job" class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">岗位</span><span style="color: red">*</span></label>
                <div class="col-md-4">
                    <form:input class="form-control" path="job"/>
                </div>
            </div>
            <%--移动电话--%>
            <div class="form-group">
                <label for="mobilePhone" class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">移动电话</span><span style="color: red">*</span></label>
                <div class="col-md-4">
                    <form:input class="form-control" path="mobilePhone"/>
                </div>
            </div>
            <%--更多信息--%>
            <div class="form-group">
                <label class="col-md-2 control-label">
                    <a href="javascript:void(0);" onclick="showMoreEmpInfo();"><small>更多信息&gt;&gt;</small></a>
                </label>
            </div>
            <%--显示更多的信息--%>
            <div id="moreEmpInfo" style="display: none;">
                    <%--生日--%>
                <div class="form-group">
                    <label for="birthDate" class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">生日</span></label>
                    <div class="col-md-4">
                        <c:if test="${birthDate != null}">
                            <fmt:formatDate value="${birthDate}" pattern="yyyy-MM-dd" var="birthDate"/>
                        </c:if>
                        <input type="date" id="birthDate" class="form-control" value="${birthDate}"/>
                    </div>
                </div>
                    <%--邮箱地址--%>
                <div class="form-group">
                    <label for="email" class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">邮箱地址</span></label>
                    <div class="col-md-4">
                        <form:input path="email" class="form-control"/>
                    </div>
                </div>
                    <%--备注--%>
                <div class="form-group">
                    <label for="remark" class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">备注</span></label>
                    <div class="col-md-10">
                        <form:input path="remark" class="form-control"/>
                    </div>
                </div>
            </div>

            <%--帐号信息--%>
            <strong>帐号信息</strong>
            <hr style="margin-top: 2px;background-color:#269abc;height: 3px;"/>
            <%--员工号--%>
            <div class="form-group">
                <label class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">员工号</span><span style="color: red">*</span></label>
                <div class="col-md-1 control-label">
                    <label class="label label-default">${form.number}</label>
                </div>
            </div>
            <%--修改密码--%>
            <div class="form-group">
                <label class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">修改密码</span></label>
                <div class="col-md-1 control-label">
                    <a href="javascript:void(0);" onclick="showPasswordInput();"><label class="label label-danger" style="cursor: pointer">修改</label> </a>
                </div>
            </div>
            <div id="passwordInput" style="display: none;">
                <div class="form-group">
                    <label for="password" class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">密码</span><span style="color: red">*</span></label>
                    <div class="col-md-4">
                        <form:password path="password" showPassword="true" class="form-control"/>
                    </div>
                </div>
                <div class="form-group">
                    <label for="passwordConfirm" class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">确认密码</span><span style="color: red">*</span></label>
                    <div class="col-md-4">
                        <form:password path="passwordConfirm" showPassword="true" class="form-control"/>
                    </div>
                </div>
                <div class="col-md-offset-2">
                    <div class="checkbox-inline">
                        <label class="text-muted">
                            <form:checkbox path="changePassword"/>用户重新登录时修改密码
                        </label>
                    </div>
                </div>
            </div>


            <%--所属部门--%>
            <div class="form-group" style="margin-top: 15px;">
                <label for="deptName" class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">所属部门</span><span style="color: red">*</span></label>
                <div class="col-md-4">
                        <%--迭代部门下拉菜单--%>
                    <form:select id="deptName" itemLabel="name" itemValue="id" class="form-control" path="departmentId" items="${deptList}"/>
                </div>
            </div>

            <hr/>

            <div class="col-md-offset-2">
                <button id="updateEmployeeBtn" type="button" class="btn btn-info">确定</button>&nbsp;
                <button id="updateEmployeeCancelBtn" type="reset" class="btn btn-default">取消</button>
            </div>
            <%-- 隐藏域部分 --%>
            <form:hidden path="id"/>
            <form:hidden path="version"/>
            <form:hidden path="number"/>
        </form:form>
    </div>
</div>

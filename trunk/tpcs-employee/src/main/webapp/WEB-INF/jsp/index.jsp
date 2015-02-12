<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<style type="text/css">
    .testCss{
        background-color: #ffff00;
        text-align: center;
        height: 300px;
        vertical-align: middle;
        padding: 50px;
    }
</style>

<%--欢迎页面--%>
<%--内容区--%>
<div class="container testCss">
    <%--欢迎页面--%>
    <div>
        <h1>欢迎页面内容</h1>
    </div>
</div>

<%--判断，弹出提示框让他修改默认密码--%>
<script type="text/javascript" src="${path}/js/jquery.form.js"></script>
<script type="text/javascript" src="${path}/js/jquery-shake.js"></script>

<c:if test="${sessionScope.loginUser.changePassword}">
    <div class="modal fade" data-keyboard="false" data-backdrop="static"
         id="modifySelfPwdModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header" style="background-color: #0a6aa1">
                    <h5 class="modal-title" id="myModalLabel">修改默认密码</h5>
                </div>
                <div class="modal-body">
                    <p class="text-muted text-info">由于系统设定的安全需要，请立即修改默认密码</p>
                    <form class="form-horizontal" role="form" action="${path}/employees/changePassword" method="post">
                        <div class="form-group">
                            <label for="newPWD" class="col-md-3 control-label">输入新密码</label>
                            <div class="col-md-5">
                                <input type="password" name="password" id="newPWD" class="form-control"/>
                            </div>
                            <div class="col-md-4" id="newPWD_id">&nbsp;</div>
                        </div>
                        <div class="form-group">
                            <label for="newPWD2" class="col-md-3 control-label">再次输入新密码</label>
                            <div class="col-md-5">
                                <input type="password" name="passwordConfirm" id="newPWD2" class="form-control"/>
                            </div>
                            <div class="col-md-4" id="newPWD2_id">&nbsp;</div>
                        </div>
                    </form>
                </div>
                <div class="modal-footer">
                    <button id="changePasswordBtn" type="button" class="btn btn-primary">修改</button>
                </div>
            </div>
        </div>
    </div>
    <!-- end of modal -->

    <script type="text/javascript">
        $(function(){
            $("#modifySelfPwdModal").modal('show');
            $("#modifySelfPwdModal #changePasswordBtn").click(function(){
                $("#modifySelfPwdModal form").ajaxForm({
                    success: function(result) {
                        if(result.success){
                            alert("密码修改成功,请重新登录!");
                            window.location.href = "${path}/logout";
                        }else{
                            //如果失败，显示错误信息
                            var errMsg = "";
                            $(result.obj).each(function(){
                                errMsg += this.defaultMessage + "<br/>";
                            });
                            $("#modifySelfPwdModal input").val("");
                            $("#modifySelfPwdModal").find(".alert-danger").remove();
                            $("#modifySelfPwdModal").find(".modal-body").prepend('<div role="alert" class="alert alert-danger">'+errMsg+'</div>');
                            $(".modal-body").effect( "shake", {}, "fast" );
                        }
                    }
                }).submit();
            });
        });
    </script>
</c:if>


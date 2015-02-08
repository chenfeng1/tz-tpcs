<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
  $(function(){
    $("#backBtn").click(function(){
//      alert("return");
      var currentState = history.state;
      //console.log(currentState);
      $.post("${path}/employees/search",currentState,function(result){
        $("#empListDiv").html(result);
//        alert("aaaaa");
      });
    });
  });
</script>

<div class="row">
  <div class="col-md-3" style="padding:25px 0 15px 15px;">
    <%--<button id="backBtn" class="btn-sm btn-default" onclick="window.history.go(-1);">--%>
    <button id="backBtn" class="btn-sm btn-default">
      <span class="glyphicon glyphicon-arrow-left"></span>
      返回
    </button>
  </div>
</div>

<!-- 提示栏  -->
<div class="row" style="background-color: #efefef;border-top: 1px solid #cccccc;border-bottom: 1px solid #cccccc;margin: 0 2px;">
  <div class="col-md-11">
    <label style="font-size: large;">${label}</label>
  </div>
</div>

<!-- 添加员工的表单区 -->
<div class="row" style="margin: 10px -7px;">
  <div class="col-md-12" style="margin-top: 10px;">
    <strong>员工基本信息</strong>
    <hr style="margin-top: 2px;background-color:#269abc;height: 3px;"/>
    <form class="form-horizontal" role="form" action="#">
      <div class="form-group">
        <label for="realname" class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">姓名</span><span style="color: red">*</span></label>
        <div class="col-md-4">
          <input type="text" id="realname" class="form-control" value="${emp.realname}"/>
        </div>
      </div>
      <div class="form-group">
        <label class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">性别</span><span style="color: red">*</span></label>
        <div class="col-md-4">
          <label class="radio-inline">
            <input type="radio" name="gender" value="男" <c:if test='${emp.gender eq "MALE"}' var="isMale">checked</c:if> />男
          </label>
          <label class="radio-inline">
            <input type="radio" name="gender" value="女" <c:if test='${!isMale}'>checked</c:if>/>女
          </label>
        </div>
      </div>
      <div class="form-group">
        <label for="job" class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">岗位</span><span style="color: red">*</span></label>
        <div class="col-md-4">
          <input type="text" id="job" class="form-control" value="${emp.job}"/>
        </div>
      </div>
      <div class="form-group">
        <label for="mobilePhone" class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">移动电话</span><span style="color: red">*</span></label>
        <div class="col-md-4">
          <input type="text" id="mobilePhone" class="form-control" value="${emp.mobilePhone}"/>
        </div>
      </div>
      <div class="form-group">
        <label class="col-md-2 control-label">
          <a href="javascript:void(0);" onclick="showMoreEmpInfo();"><small>更多信息&gt;&gt;</small></a>
        </label>
      </div>
      <!-- 显示更多的信息 -->
      <div id="moreEmpInfo" style="display: none;">
        <div class="form-group">
          <label for="birthDate" class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">生日</span></label>
          <div class="col-md-4">
            <input type="date" id="birthDate" class="form-control" value="1982-12-24"/>
          </div>
        </div>
        <div class="form-group">
          <label for="email" class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">邮箱地址</span></label>
          <div class="col-md-4">
            <input type="text" id="email" class="form-control" value="hujl@sz-tz.org"/>
          </div>
        </div>
        <div class="form-group">
          <label for="remark" class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">备注</span></label>
          <div class="col-md-10">
            <input type="text" id="remark" class="form-control" value="2012年入职的"/>
          </div>
        </div>
      </div>

      <!-- 登录的帐号信息 -->
      <strong>帐号信息</strong>
      <hr style="margin-top: 2px;background-color:#269abc;height: 3px;"/>
      <div class="form-group">
        <label class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">员工号</span><span style="color: red">*</span></label>
        <div class="col-md-1 control-label">
          <label class="label label-default">1091</label>
        </div>
      </div>
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
            <input type="password" id="password" class="form-control"/>
          </div>
        </div>
        <div class="form-group">
          <label for="password2" class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">确认密码</span><span style="color: red">*</span></label>
          <div class="col-md-4">
            <input type="password" id="password2" class="form-control"/>
          </div>
        </div>
        <div class="col-md-offset-2">
          <div class="checkbox-inline">
            <label class="text-muted"><input type="checkbox">用户重新登录时修改密码</label>
          </div>
        </div>
      </div>

      <div class="form-group" style="margin-top: 15px;">
        <label for="deptName" class="col-md-2 control-label"><span class="text-muted" style="font-weight: normal">所属部门</span><span style="color: red">*</span></label>
        <div class="col-md-4">
          <!-- 此下拉框当前选中的应该是你添加员工时所选的部门，其它的项是所有其它的部门 -->
          <select id="deptName" class="form-control">
            <option value="1">天智教育</option>
            <option value="2" selected>教学部</option>
            <option value="3">市场部</option>
            <option value="4">财务部</option>
            <option value="5">渠道部</option>
            <option value="6">人力资源部</option>
          </select>
        </div>
      </div>

      <hr/>

      <div class="col-md-offset-2">
        <button type="submit" class="btn btn-info">确定</button>&nbsp;
        <button type="reset" class="btn btn-default">取消</button>
      </div>

    </form>
  </div>
</div><!-- end 添加员工表单区-->

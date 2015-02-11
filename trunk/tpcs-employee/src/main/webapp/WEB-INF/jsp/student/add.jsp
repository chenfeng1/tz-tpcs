<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script>
	function submitForm(){
		if($("#workingYears").val().trim()==""){
			$("#workingYears").val(0);
		}
		if($("#paid").val().trim()==""){
			$("#paid").val(0);
		}
		$("#studentForm").submit();
	}
	
	function getProvince(obj){
		var divisionCode=obj.value;
		//异步方式发送一个请求
    				$.ajax({
						//请求类型
						type: "POST",
						//请求路径
						url: "${path}/areas/getCity",
						//数据格式
						dataType:"json",
						//提交的数据
						data: {"code":divisionCode},
						//处理错误…
						error: function(){		
							alert("处理失败");
						},
						//处理成功…	
						//已经自动转为json对象
						success: function(areas){	
							
							var p = null;
							$("#city").empty();
							$.each(areas,function(){
								p+="<option value='"+this.divisionCode+"'>"+this.name+"</option>";
							});
							$("#city").append(p);
						}
					});
	}
	
	function checkClazzName(){
	var clazz_name = $("#classname").val();
				$.ajax({
						//请求类型
						type: "POST",
						//请求路径
						url: "${path}/clazz/checkClazz",
						//数据格式
						dataType:"json",
						//提交的数据
						data: {"clazz_name":clazz_name},
						//处理错误…
						error: function(){		
							alert("处理失败");
						},
						//处理成功…	
						//已经自动转为json对象
						success: function(result){	
							if(result=='0'){
								alert("班級不存在");
								return;
							}
						}
					});
	}
</script>
<!-- 内容区 -->
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
	
	<strong> 学员核心信息
		<button class="btn btn-default pull-right"
			onclick="javascript:history.go(-1)">返回</button>
	</strong>
	<hr style="margin-top: 2px;background-color: #204d74;height: 3px;" />
	<form id="studentForm" class="form-horizontal" role="form" action="${path }/students/save" method="get" >
		<input type="hidden" name="id" value="${student.id }" />
		<div class="form-group">
			<label for="classname" class="col-md-offset-1 col-md-1 control-label">班级名<span
				style="color: red">*</span></label>
			<div class="col-md-2">
				<input type="text" id="classname" class="form-control" name="clazzName" onblur="checkClazzName()"
					placeholder="班级的名称" value="${clazzName }"/>
			</div>
			<label for="stuName" class="col-md-1 control-label">学员名<span
				style="color: red">*</span></label>
			<div class="col-md-2">
				<input type="text" id="stuName" class="form-control"
					placeholder="学员的名称" name="realname" value="${student.realname }"/>
			</div>
			<label for="birth" class="col-md-1 control-label">生日</label>
			<div class="col-md-2">
				<input type="date" id="birth" class="form-control"
					placeholder="1992-01-01" name="birthDate" value="${student.birthDate }" />
			</div>
		</div>
		<div class="form-group">
			<label for="phone" class="col-md-offset-1 col-md-1 control-label">手机号<span
				style="color: red">*</span></label>
			<div class="col-md-2">
				<input type="text" id="phone" class="form-control" placeholder="手机号" name="phone" value="${student.phone }" />
			</div>
			<label for="bakPhone" class="col-md-1 control-label">备用号<span
				style="color: red">*</span></label>
			<div class="col-md-2">
				<input type="text" id="bakPhone" class="form-control"
					placeholder="备用号码" name="bakPhone" value="${student.bakPhone }" />
			</div>
			<label for="email" class="col-md-1 control-label">邮箱</label>
			<div class="col-md-2">
				<input type="email" id="email" class="form-control"
					placeholder="xxx@xxx.xx" name="email" value="${student.email }" />
			</div>
		</div>
		<div class="form-group">
			<label for="school" class="col-md-offset-1 col-md-1 control-label">院校<span
				style="color: red">*</span></label>
			<div class="col-md-2">
				<input type="text" id="school" class="form-control"
					placeholder="大学名" name="school" value="${student.school }" />
			</div>
			<label for="major" class="col-md-1 control-label">专业<span
				style="color: red">*</span></label>
			<div class="col-md-2">
				<input type="text" id="major" class="form-control"
					placeholder="所学专业" name="major" value="${student.major }" />
			</div>
			<label for="degree" class="col-md-1 control-label">学历<span
				style="color: red">*</span></label>
			<div class="col-md-2">
				<!-- <input type="text" id="degree" class="form-control" placeholder="学历" name="degree" /> -->
				<select class="form-control" id="degree" name="degree">
					<c:forEach items="${degree }" var="d">
						<option value="${d }"
							<c:if test="${student.degree==d}" >selected</c:if>
						><fmt:message key='enum.degree.${d }'></fmt:message></option>
					</c:forEach>
				</select>
			</div>
		</div>
		<div class="form-group">
			<label for="loadStatus"
				class="col-md-offset-1 col-md-1 control-label">贷款状态<span
				style="color: red">*</span></label>
			<div class="col-md-2">
				<select class="form-control" id="loadStatus" name="loanStatus">
					<c:forEach items="${loanStatus }" var="loan">
						<option value="${loan }"
							<c:if test="${loan==student.loanStatus }">selected</c:if>
						><fmt:message key='enum.loanStatus.${loan }'></fmt:message></option>
					</c:forEach>
				</select>
			</div>
			<label for="source" class="col-md-1 control-label">渠道来源<span
				style="color: red">*</span></label>
			<div class="col-md-2">
				<select class="form-control" id="source" name="source">
					<c:forEach items="${source }" var="s">
						<option value="${s }" 
						<c:if test="${student.source==s }" >selected</c:if>
						><fmt:message key="enum.source.${s }" /></option>
					</c:forEach>
				</select>
			</div>
			<label for="qq" class="col-md-1 control-label">QQ号</label>
			<div class="col-md-2">
				<input type="text" id="qq" class="form-control" placeholder="qq号码" name="qq" value="${student.qq }" />
			</div>
		</div>
		<!-- 由于学员的信息较多，采用折叠式输入框，下面的输入框默认是不显示的，可以点击更多来显示 -->
		<p style="font-weight: bolder">
			<a href="javascript:void(0);" onclick="showMore();">更多信息&gt;&gt;</a>
		</p>
		<div id="more" style="display: none;">
			<hr style="margin-top: 2px;background-color: #204d74;height: 3px;" />
			<div class="form-group">
				<label for="identityCard"
					class="col-md-offset-1 col-md-1 control-label">身份证</label>
				<div class="col-md-3">
					<input type="text" id="identityCard" class="form-control"
						placeholder="身份证号码" name="identityCard" value="${student.identityCard }"/>
				</div>
				<label for="province" class="col-md-1 control-label">省份</label>
				<div class="col-md-2">
					<!-- 通过AJAX动态加载省市级联菜单 -->
					<select id="province" class="form-control" name="province" onchange="getProvince(this)">
						<option value="">-请选择-</option>
						<c:forEach items="${map }" var="m">
							<option value="${m.key }"
								<c:if test="${student.province==m.key }">selected</c:if>
							>${m.value }</option>
						</c:forEach>
					</select>
				</div>
				<label for="city" class="col-md-1 control-label">城市</label>
				<div class="col-md-2">
					<select id="city" class="form-control" name="city" id="city">
						<option value="${student.city}">${city }</option>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="currentLoc"
					class="col-md-offset-1 col-md-1 control-label">所在地</label>
				<div class="col-md-2">
					<input type="text" id="currentLoc" class="form-control"
						placeholder="当前所在地" name="currentLoc" value="${student.currentLoc }" />
				</div>
				<label for="workLoc" class="col-md-1 control-label">工作地</label>
				<div class="col-md-2">
					<input type="text" id="workLoc" class="form-control"
						placeholder="期望工作地" name="workLoc" value="${student.workLoc }" />
				</div>
				<label for="workingYears" class="col-md-1 control-label">工作年限</label>
				<div class="col-md-2">
					<input type="number" id="workingYears" class="form-control"
						placeholder="0" name="workingYears" value="${student.workingYears }" />
				</div>
			</div>
			<div class="form-group">
				<label for="graduationDate"
					class="col-md-offset-1 col-md-1 control-label">毕业时间</label>
				<div class="col-md-2">
					<input type="date" id="graduationDate" class="form-control" name="graduationDate" value="${student.graduationDate}" />
				</div>
				<label class="col-md-1 control-label">是否需要毕设</label>
				<div class="col-md-2">
					<label class="radio-inline"> <input type="radio"
						name="needDesign" value="true" <c:if test="${student.needDesign==true }">checked</c:if>/>是
					</label> <label class="radio-inline"> <input type="radio"
						name="needDesign" value="false" <c:if test="${student.needDesign==false }">checked</c:if>/>否
					</label>
				</div>
				<label for="designDate" class="col-md-1 control-label">毕设日期</label>
				<div class="col-md-2">
					<input type="date" id="designDate" class="form-control" name="designDate" value="${student.designDate }"/>
				</div>
			</div>
			<div class="form-group">
				<label for="emergencyContact" class="col-md-2 control-label">紧急联系人</label>
				<div class="col-md-4">
					<input type="text" id="emergencyContact" class="form-control"
						placeholder="紧急联系人" name="emergencyContact" value="${student.emergencyContact }" />
				</div>
				<label for="emergencyPhone" class="col-md-2 control-label">紧急联系电话</label>
				<div class="col-md-4">
					<input type="text" id="emergencyPhone" class="form-control"
						placeholder="紧急联系电话" name="emergencyPhone" value="${student.emergencyPhone }" />
				</div>
			</div>
			<div class="form-group">
				<label for="remark" class="col-md-offset-1 col-md-1 control-label">备注</label>
				<div class="col-md-5">
					<input type="text" id="remark" class="form-control"
						placeholder="学员的备注信息" name="remark" value="${student.remark }" />
				</div>
				<label class="col-md-1 control-label">性别</label>
				<div class="col-md-2">
					<label class="radio-inline"> <input type="radio"
						name="gender" value="MALE" <c:if test="${student.gender=='MALE' }">checked</c:if> />男
					</label> <label class="radio-inline"> <input type="radio"
						name="gender" value="FEMALE" <c:if test="${student.gender=='FEMALE' }">checked</c:if>/>女
					</label>
				</div>
			</div>
		</div>
		<!-- 由于学员CRM信息，下面的输入框默认是不显示的，可以点击更多来显示 -->
		<p style="font-weight: bolder;text-align: right">
			<a href="javascript:void(0);" onclick="showCRMInfo();">&lt;&lt;CRM信息</a>
		</p>
		<div id="crmInfo" style="display: none;">
			<hr style="margin-top: 2px;background-color: orangered;height: 3px;" />
			<div class="form-group">
				<label for="paid" class="col-md-offset-1 col-md-1 control-label">已交费用</label>
				<div class="col-md-3">
					<input type="number" id="paid" class="form-control"
						placeholder="1500" name="paid" value="${student.paid }"/>
				</div>
				<label for="owner" class="col-md-1 control-label">服务专员</label>
				<div class="col-md-2">
					<input type="text" id="owner" class="form-control"
						placeholder="服务专员[咨讯师]" name="employeeName"/>
				</div>
				<label for="status" class="col-md-1 control-label">状态</label>
				<div class="col-md-2">
					<select id="status" class="form-control" name="status">
						<c:forEach items="${status }" var="s" varStatus="c" >
							<option value="${s }"
								<c:if test="${student.status==s }" >selected</c:if>
							><fmt:message key="enum.status.${s }" /></option>
						</c:forEach>
					</select>
				</div>
			</div>
			<div class="form-group">
				<label for="address" class="col-md-offset-1 col-md-1 control-label">联系地址1</label>
				<div class="col-md-2">
					<input type="text" id="address" class="form-control"
						placeholder="客户的联系地址1" name="address" value="${student.address }" />
				</div>
				<label for="address2" class="col-md-1 control-label">联系地址2</label>
				<div class="col-md-2">
					<input type="text" id="address2" class="form-control"
						placeholder="客户的联系地址2" name="address2" value="${student.address2 }"/>
				</div>
				<label for="level" class="col-md-1 control-label">成熟度</label>
				<div class="col-md-2">
					<select id="level" class="form-control" name="level">
						<c:forEach items="${level }" var="s" varStatus="v">
							<option value="${s }"
								<c:if test="${s==student.level}">selected</c:if>
							><fmt:message key='enum.level.${s }' /></option>
						</c:forEach>
					</select>
				</div>
			</div>
		</div>
		<div class="col-md-offset-2">
			<button type="button" class="btn btn-primary" onclick="submitForm()">保存</button>
			&nbsp;
			<button type="reset" class="btn btn-primary">重填</button>
		</div>
	</form>
</div>
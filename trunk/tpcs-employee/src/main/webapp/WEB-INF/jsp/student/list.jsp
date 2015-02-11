<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
	//提交前将pageNumber置为1
	function buttonSubmit(){
		$("#formPageNumber").val(1);
		$("#studentFormId").submit();
	}
	//提交表单查询
	function submitForm(num){
		var pageSize=$("#pageSize").val();
		$("#formPageSize").val(pageSize);
		$("#formPageNumber").val(num);
		$("#studentFormId").submit();
	}
	
	function next() {
		var cname = $("#cname").val();
		var sname = $("#sname").val();
		var degree = $("#degree").val();
		var v = $("#pageSize").val();
		var d = document.getElementById("loanStatus");
		var loanStatus = "";
		if (d.checked) {
			loanStatus = d.value;
		} else {
			loanStatus = "";
		}
		$("#next").attr(
				"href",
				"${path }/students/initList?pageNumber=${pager.pageNumber+1}&clazzname2="
						+ cname + "&realname2=" + sname + "&degree2=" + degree+"&pageSize="+v
						+ "&loanStatus2=" + loanStatus);
	}
	function next2() {
		var cname = $("#cname").val();
		var sname = $("#sname").val();
		var degree = $("#degree").val();
		var v = $("#pageSize").val();
		var d = document.getElementById("loanStatus");
		var loanStatus = "";
		if (d.checked) {
			loanStatus = d.value;
		} else {
			loanStatus = "";
		}

		$("#next2").attr(
				"href",
				"${path }/students/initList?pageNumber=${pager.pageCount}&clazzname2="
						+ cname + "&realname2=" + sname + "&degree2=" + degree+"&pageSize="+v
						+ "&loanStatus2=" + loanStatus);
	}
	function back() {
		var cname = $("#cname").val();
		var sname = $("#sname").val();
		var degree = $("#degree").val();
		var v = $("#pageSize").val();
		var d = document.getElementById("loanStatus");
		var loanStatus = "";
		if (d.checked) {
			loanStatus = d.value;
		} else {
			loanStatus = "";
		}

		$("#back").attr(
				"href",
				"${path }/students/initList?pageNumber=1&clazzname2=" + cname+"&pageSize="+v
						+ "&realname2=" + sname + "&degree2=" + degree
						+ "&loanStatus2=" + loanStatus);
	}
	function back2() {
		var cname = $("#cname").val();
		var sname = $("#sname").val();
		var degree = $("#degree").val();
		var v = $("#pageSize").val();
		var d = document.getElementById("loanStatus");
		var loanStatus = "";
		if (d.checked) {
			loanStatus = d.value;
		} else {
			loanStatus = "";
		}

		$("#back2").attr(
				"href",
				"${path }/students/initList?pageNumber=${pager.pageNumber-1}&clazzname2="
						+ cname + "&realname2=" + sname + "&degree2=" + degree+"&pageSize="+v
						+ "&loanStatus2=" + loanStatus);
	}

	function goNext(obj) {
		var cname = $("#cname").val();
		var sname = $("#sname").val();
		var degree = $("#degree").val();
		var pageNumber = obj.id;
		var v = $("#pageSize").val();
		var d = document.getElementById("loanStatus");
		var loanStatus = "";
		if (d.checked) {
			loanStatus = d.value;
		} else {
			loanStatus = "";
		}

		window.location = "${path }/students/initList?pageNumber=" + pageNumber+"&pageSize="+v
				+ "&clazzname2=" + cname + "&realname2=" + sname + "&degree2="
				+ degree + "&loanStatus2=" + loanStatus;
	}

	function go() {

		var cname = $("#cname").val();
		var sname = $("#sname").val();
		var degree = $("#degree").val();
		var v = $("#pageSize").val();

		var d = document.getElementById("loanStatus");
		var loanStatus = "";
		if (d.checked) {
			loanStatus = d.value;
		} else {
			loanStatus = "";
		}

		window.location = "${path}/students/initList?pageSize=" + v
				+ "&clazzname2=" + cname + "&realname2=" + sname + "&degree2="
				+ degree + "&loanStatus2=" + loanStatus;
	}

	function del(obj) {
		if (confirm("确定要删除吗?")) {
			var v = obj.id;
			$("#" + v).remove();
			window.location = "${path }/students/del?sid=" + obj.id;
		} else {
			return false;
		}
	}
	
	function gotoUpdate(obj){
		window.location="${path}/students/initUpdate?sid="+obj.id;
	}
</script>
<!-- 其它内容 -->
<div class="container">
	<!-- 查询区 -->
	<form class="form-horizontal" role="form" id="studentFormId"
		action="${path }/students/initList" style="background-color: #e8e8e8;" method="post">
		<div class="form-group">
			<label for="cname" class="col-md-1 control-label">班级名</label>
			<div class="col-md-2">
				<input type="text" id="cname" class="form-control"
					placeholder="输入班级名" name="clazzName" value="${clazzName}" />
			</div>

			<label for="sname" class="col-md-1 control-label">学员名</label>
			<div class="col-md-2">
				<input type="text" id="sname" class="form-control"
					placeholder="输入学员名" name="realName" value="${realName}" />
			</div>

			<label for="degree" class="col-md-1 control-label">学历</label>
			<div class="col-md-2">
				 <select class="form-control" id="degree" name="degree">
					<option value="-1">-请选择-</option>
					<c:forEach items="${degrees }" var="d">
						<option value="${d }" <c:if test="${d==degree }" >selected</c:if> ><fmt:message key='enum.degree.${d }'/></optioin>
					</c:forEach>
				</select>
			</div>

			<label for="loanStatus" class="col-md-1 control-label">未放款</label>
			<div class="col-md-1 checkbox">
				<label> <input id="loanStatus" type="checkbox" value="UNLOAN"
					class="checkbox" style="width: 22px;height: 22px;"
					name="loanStatus"
					<c:if test="${loanStatus=='UNLOAN' }" >checked</c:if> />
				</label>
			</div>
			<div class="col-md-1">
				<button type="button" class="btn btn-primary" id="search" onclick="buttonSubmit()">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</div>
		</div>
		<input id="formPageNumber" type="hidden" name="pageNumber" value="${pager.pageNumber}">
        <input id="formPageSize" type="hidden" name="pageSize" value="${pager.pageSize}">
	</form>
	<hr />

	<!-- 内容区 -->
	<div class="row">
		<div class="col-md-1">
			<button type="button" class="btn btn-primary"
				onclick="javaScript:window.location.href='${path}/students/initAdd'">
				<span class="glyphicon glyphicon-plus">&nbsp;添加学员</span>
			</button>
		</div>
	</div>
	<div class="row">
		<div class="col-xs-12">
			<table class="table table-hover">
				<thead>
					<tr>
						<th>#</th>
						<th>班级名</th>
						<th>学员名</th>
						<th>电话</th>
						<th>备用电话</th>
						<th>生日</th>
						<th>邮箱</th>
						<th>学历</th>
						<th>专业</th>
						<th>贷款状态</th>
						<th>操作</th>
					</tr>
				</thead>
				<tbody>
					<c:forEach items="${pager.list }" var="s" varStatus="v">
						<tr>
							<td>${v.count }</td>
							<td>${s.clazz.name }</td>
							<td>${s.realname }</td>
							<td>${s.phone }</td>
							<td>${s.bakPhone }</td>
							<td><fmt:formatDate value="${s.birthDate}"
									pattern="yyyy-MM-dd"></fmt:formatDate></td>
							<td>${s.email }</td>
							<td><fmt:message key="enum.degree.${s.degree }"/></td>
							<td>${s.major }</td>
							<td><fmt:message key='enum.loanStatus.${s.loanStatus }'/></td>
							<td><a class="label label-default" onclick="gotoUpdate(this)" id="${s.id }"><span
									class="glyphicon glyphicon-refresh"></span></a>&nbsp; <a
								class="label label-danger" onclick="del(this)" id="${s.id }"><span
									class="glyphicon glyphicon-trash"></span></a>&nbsp;</td>
						</tr>
					</c:forEach>
				</tbody>
			</table>
		</div>
	</div>

	<!-- 分页模块 -->
	<div class="pull-right">
		<ul class="pagination">

			<c:if test="${pager.pageNumber==1 }">
				<li><a href="#" onclick="submitForm(1)" id="back">&laquo;</a></li>
			</c:if>
			<c:if test="${pager.pageNumber!=1 }">
				<li><a href="#" onclick="submitForm(${pager.pageNumber-1})" id="back2">&laquo;</a></li>
			</c:if>

			<c:if test="${pager.pageNumber+3<=pager.pageCount }">
				<c:forEach var="i" begin="${pager.pageNumber }"
					end="${pager.pageNumber+3 }" step="1">
					<li><a id="${i }" onclick="submitForm(${i })">${i}</a></li>
				</c:forEach>
			</c:if>
			<c:if test="${pager.pageNumber+3>pager.pageCount }">
				<c:forEach var="i" begin="${pager.pageNumber }"
					end="${pager.pageCount}" step="1">
					<li><a id="${i }" onclick="submitForm(${i })">${i}</a></li>
				</c:forEach>
			</c:if>
			<c:if test="${pager.pageNumber!=pager.pageCount }">
				<li><a href="#" id="next" onclick="submitForm(${pager.pageNumber+1})">&raquo;</a></li>
			</c:if>
			<c:if test="${pager.pageNumber==pager.pageCount }">
				<li><a href="#" id="next2" onclick="submitForm(${pager.pageCount})">&raquo;</a></li>
			</c:if>

			<li>每页显示 <select style="height: 34px;width: 36px" id="pageSize"
				onchange="submitForm(1)">
					<option value="5"
						<c:if test="${pager.pageSize == 5 }">
        					selected
        				</c:if>>5</option>
					<option value="10"
						<c:if test="${pager.pageSize == 10 }">
        					selected
        				</c:if>>10</option>
					<option value="15"
						<c:if test="${pager.pageSize == 15 }">
        					selected
        				</c:if>>15</option>
					<option value="20"
						<c:if test="${pager.pageSize == 20 }">
        					selected
        				</c:if>>20</option>
			</select> 条
			</li>
		</ul>
	</div>
</div>

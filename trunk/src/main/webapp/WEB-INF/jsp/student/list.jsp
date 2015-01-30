<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<script type="text/javascript">
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
				"${path }/students/initList?pageNow=${paging.pageNow+1}&clazzname2="
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
				"${path }/students/initList?pageNow=${paging.pageCount}&clazzname2="
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
				"${path }/students/initList?pageNow=1&clazzname2=" + cname+"&pageSize="+v
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
				"${path }/students/initList?pageNow=${paging.pageNow-1}&clazzname2="
						+ cname + "&realname2=" + sname + "&degree2=" + degree+"&pageSize="+v
						+ "&loanStatus2=" + loanStatus);
	}

	function goNext(obj) {
		var cname = $("#cname").val();
		var sname = $("#sname").val();
		var degree = $("#degree").val();
		var pageNow = obj.id;
		var v = $("#pageSize").val();
		var d = document.getElementById("loanStatus");
		var loanStatus = "";
		if (d.checked) {
			loanStatus = d.value;
		} else {
			loanStatus = "";
		}

		window.location = "${path }/students/initList?pageNow=" + pageNow+"&pageSize="+v
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
	<form class="form-horizontal" role="form"
		action="${path }/students/initList" style="background-color: #e8e8e8;">
		<div class="form-group">
			<label for="cname" class="col-md-1 control-label">班级名</label>
			<div class="col-md-2">
				<input type="text" id="cname" class="form-control"
					placeholder="输入班级名" name="clazzname2" value="${clazzname2 }" />
			</div>

			<label for="sname" class="col-md-1 control-label">学员名</label>
			<div class="col-md-2">
				<input type="text" id="sname" class="form-control"
					placeholder="输入学员名" name="realname2" value="${realname2 }" />
			</div>

			<label for="degree" class="col-md-1 control-label">学历</label>
			<div class="col-md-2">
				 <select class="form-control" id="degree" name="degree2">
					<option value="-1">-请选择-</option>
					<c:forEach items="${degree }" var="d">
						<option value="${d }" <c:if test="${d==degree2 }" >selected</c:if> ><fmt:message key='enum.degree.${d }'/></optioin>
					</c:forEach>
				</select>
			</div>

			<label for="loanStatus" class="col-md-1 control-label">未放款</label>
			<div class="col-md-1 checkbox">
				<label> <input id="loanStatus" type="checkbox" value="UNLOAN"
					class="checkbox" style="width: 22px;height: 22px;"
					name="loanStatus2"
					<c:if test="${loanStatus2=='UNLOAN' }" >checked</c:if> />
				</label>
			</div>
			<div class="col-md-1">
				<button type="submit" class="btn btn-primary" id="search">
					<span class="glyphicon glyphicon-search"></span>
				</button>
			</div>
		</div>
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
					<c:forEach items="${paging.students }" var="s" varStatus="v">
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

			<c:if test="${paging.pageNow==1 }">
				<li><a href="" onclick="back()" id="back">&laquo;</a></li>
			</c:if>
			<c:if test="${paging.pageNow!=1 }">
				<li><a href="" onclick="back2()" id="back2">&laquo;</a></li>
			</c:if>

			<c:if test="${paging.pageNow+3<=paging.pageCount }">
				<c:forEach var="i" begin="${paging.pageNow }"
					end="${paging.pageNow+3 }" step="1">
					<li><a id="${i }" onclick="goNext(this)">${i}</a></li>
				</c:forEach>
			</c:if>
			<c:if test="${paging.pageNow+3>paging.pageCount }">
				<c:forEach var="i" begin="${paging.pageNow }"
					end="${paging.pageCount}" step="1">
					<li><a id="${i }" onclick="goNext(this)">${i}</a></li>
				</c:forEach>
			</c:if>
			<c:if test="${paging.pageNow!=paging.pageCount }">
				<li><a href="" id="next" onclick="next()">&raquo;</a></li>
			</c:if>
			<c:if test="${paging.pageNow==paging.pageCount }">
				<li><a href="" id="next2" onclick="next2()">&raquo;</a></li>
			</c:if>

			<li>每页显示 <select style="height: 34px;width: 36px" id="pageSize"
				onchange="go()">
					<option value="5"
						<c:if test="${paging.pageSize == 5 }">
        					selected
        				</c:if>>5</option>
					<option value="10"
						<c:if test="${paging.pageSize == 10 }">
        					selected
        				</c:if>>10</option>
					<option value="15"
						<c:if test="${paging.pageSize == 15 }">
        					selected
        				</c:if>>15</option>
					<option value="20"
						<c:if test="${paging.pageSize == 20 }">
        					selected
        				</c:if>>20</option>
			</select> 条
			</li>
		</ul>
	</div>
</div>

<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
	//提交前讲pageNumber置为1
	function buttonSubmit(){
		$("#formPageNumber").val(1);
		$("#clazzFormId").submit();
	}
	//提交表单查询
	function submitForm(num){
		var pageSize=$("#pageSize").val();
		$("#formPageSize").val(pageSize);
		$("#formPageNumber").val(num);
		$("#clazzFormId").submit();
	}
	
	function transfer(cid){
		$("#labelName").text("");
		$("#labelRoom").text("");
		$("#labelOpen").text("");
		$("#labelCount").text("");
		$("#labelAdvisor").text("");
		$("#labelTraining").text("");
		$("#labelLecturer").text("");
		$.ajax({
			//请求类型
			type: "POST",
			//请求路径
			url: "${path}/clazz/initUpdate",
			//数据格式
			dataType:"json",
			//提交的数据
			data: {"id":cid},
			//处理错误…
			error: function(){		
				alert("处理失败");
			},
			//处理成功…	
			//已经自动转为json对象
			success: function(clazz){	
			$("#hid").val(clazz.id);
			$("#classname").val(clazz.name);
			$("#usedname").val(clazz.name);
			$("#classroom").val(clazz.room);
			$("#open_date").val(clazz.open);
			$("#class_students").val(clazz.count);
			$("#class_adviser").val(clazz.advisor);
			$("#trainer_date").val(clazz.trainingDate);
			$("#teachername").val(clazz.lecturer);
			}
		});
	}
	function update(obj){
		$.ajax({
			//请求类型
			type: "POST",
			//请求路径
			url: "${path}/clazz/validUpdate",
			//数据格式
			dataType:"json",
			//提交的数据
			data: {"id":obj.id.value,"usedname":obj.usedname.value,"name":obj.name.value,"room":obj.room.value,"open":obj.open.value,"count":obj.count.value,"advisor":obj.advisor.value,"trainingDate":obj.trainingDate.value,"lecturer":obj.lecturer.value},
			//处理错误…
			error: function(){		
				document.getElementById("myClassForm").action = "${path}/clazz/update";
				document.getElementById("myClassForm").submit(); 
			},
			//处理成功…	
			//已经自动转为json对象
			success: function(errors){	
				$("#labelName").text("");
				$("#labelRoom").text("");
				$("#labelOpen").text("");
				$("#labelCount").text("");
				$("#labelAdvisor").text("");
				$("#labelTraining").text("");
				$("#labelLecturer").text("");
				$.each(errors,function(){
					if(this.field == "name"){
						$("#labelName").text(this.defaultMessage);
					}
					if(this.field == "room"){
						$("#labelRoom").text(this.defaultMessage);
					}
					if(this.field == "open"){
						$("#labelOpen").text(this.defaultMessage);
					}
					if(this.field == "count"){
						$("#labelCount").text(this.defaultMessage);
					}
					if(this.field == "advisor"){
						$("#labelAdvisor").text(this.defaultMessage);
					}
					if(this.field == "trainingDate"){
						$("#labelTraining").text(this.defaultMessage);
					}
					if(this.field == "lecturer"){
						$("#labelLecturer").text(this.defaultMessage);
					}
				});
			}
		});
		
	}
	
	function del(obj){
		if(confirm("是否确定删除?")){
			var v = obj.id;
			$("#"+v).remove();
			window.location="${path }/clazz/del?cid="+obj.id;
			
		}else{
			return false;
		}
	}
</script>

<div class="container">
	<%--查询区--%>
    <form id="clazzFormId" class="form-horizontal" role="form" action="${path }/clazz/list" method="post" >
        <div class="form-group">
            <label for="cname" class="col-md-1 control-label">班级名：</label>

            <div class="col-md-2">
                <input type="text" id="cname" class="form-control" placeholder="查询班级名" name="name" value="${name }"/>
            </div>
            <label for="min_nums" class="col-md-2 control-label">开班人数：</label>

            <div class="col-md-2">
                <input type="text" id="min_nums" class="form-control" placeholder="最少人数" name="min" value="${min }"/>
            </div>
            <label for="max_nums" class="col-md-1 control-label">至：</label>

            <div class="col-md-2">
                <input type="text" id="max_nums" class="form-control" placeholder="最多人数" name="max" value="${max }"/>
            </div>
            <div class="col-md-2">
                <button type="button" class="btn btn-primary" onclick="buttonSubmit()">
                    <span class="glyphicon glyphicon-search"></span>
                </button>
            </div>
        </div>
        <input id="formPageNumber" type="hidden" name="pageNumber" value="${pager.pageNumber}">
        <input id="formPageSize" type="hidden" name="pageSize" value="${pager.pageSize}">
    </form>
    <hr/>
<%--模态框 中的内容--%>
<%--Modal--%>
<div class="modal fade" id="myClassModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h4 class="modal-title" id="myModalLabel">班级更新</h4>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" action="" id="myClassForm">
                    <div class="form-group">
                    	<input type="hidden" name="id" id="hid" />
                    	<input type="hidden" name="usedname" id="usedname" />
                        <label for="classname" class="col-md-2 control-label">班级名</label>
                        <div class="col-md-5">
                            <input type="text" name="name" id="classname" class="form-control" placeholder="班级的名称"/>
                        </div>
                        <div class="col-md-5" id="classname_id">
			            <label class="control-label alert-danger" id="labelName">
			            </label>
			            </div>
			             </div>
                    <div class="form-group">
                        <label for="classroom" class="col-md-2 control-label">所在教室</label>
                        <div class="col-md-5">
                            <input type="text" id="classroom" name="room" class="form-control" placeholder="教室的名称"/>
                        </div>
                        <div class="col-md-5" id="classroom_id">
			            <label class="control-label alert-danger" id="labelRoom">
			            </label>
			            </div>
                    </div>
                    <div class="form-group">
                        <label for="classname" class="col-md-2 control-label">开班日期</label>
                        <div class="col-md-5">
                            <input type="date" id="open_date" name="open" class="form-control" value=""/>
                        </div>
                        <div class="col-md-5" id="classopen_id">
			            <label class="control-label alert-danger" id="labelOpen">
			            </label>
			            </div>
                    </div>
                    <div class="form-group">
                        <label for="classname" class="col-md-2 control-label">开班人数</label>
                        <div class="col-md-5">
                            <input type="number" id="class_students" name="count" class="form-control" placeholder="30"/>
                        </div>
                        <div class="col-md-5" id="classcount_id">
			            <label class="control-label alert-danger" id="labelCount">
			            </label>
			            </div>
                    </div>
                    <div class="form-group">
                        <label for="class_adviser" class="col-md-2 control-label">班主任</label>
                        <div class="col-md-5">
                            <input type="text" id="class_adviser" name="advisor" class="form-control" placeholder="班主任名"/>
                        </div>
                        <div class="col-md-5" id="classadviser_id">
			            <label class="control-label alert-danger" id="labelAdvisor">
			            </label>
			            </div>
                    </div>

                    <div class="form-group">
                        <label for="trainer_date" class="col-md-2 control-label">训练营日</label>
                        <div class="col-md-5">
                            <input type="date" id="trainer_date" name="trainingDate" class="form-control" value=""/>
                        </div>
                        <div class="col-md-5" id="classtrainning_id">
			            <label class="control-label alert-danger" id="labelTraining">
			            </label>
			            </div>
                    </div>

                    <div class="form-group">
                        <label for="teachername" class="col-md-2 control-label">讲师名</label>
                        <div class="col-md-5">
                            <input type="text" id="teachername" name="lecturer" class="form-control" placeholder="讲师名"/>
                        </div>
                        <div class="col-md-5" id="classlecturer_id">
			            <label class="control-label alert-danger" id="labelLecturer">
			            </label>
			            </div>
                    </div>

            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="update(this.form)">更新</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
            </div>
                </form>
            </div>
        </div>
    </div>
</div>

	<%--内容区--%>
    <div class="row">
        <div class="col-md-1">
            <button type="button" class="btn btn-primary" onclick="javaScript:window.location.href='${path}/clazz/initAdd'">
                <span class="glyphicon glyphicon-plus">&nbsp;添加</span>
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
                    <th>所在教室</th>
                    <th>开班时间</th>
                    <th>开班人数</th>
                    <th>班主任</th>
                    <th>训练营日期</th>
                    <th>训练营讲师</th>
                    <th>操作</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="list" items="${pager.list }" varStatus="v">
                	<tr id="${list.id }">
                		<td>${v.count }</td>
                        <td>${list.name }</td>
                		<td>${list.room }</td>
                		<td><fmt:formatDate value="${list.open }" type="date" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                		<td>${list.count}</td>
                		<td>${list.advisor}</td>
                		<td><fmt:formatDate value="${list.trainingDate }" type="date" pattern="yyyy-MM-dd"></fmt:formatDate></td>
                		<td>${list.lecturer}</td>
                		<td>
                        	<a href="" class="label label-default" data-toggle="modal" data-target="#myClassModal" onclick="transfer('${list.id }')"><span class="glyphicon glyphicon-refresh"></span></a>&nbsp;
                       	 	<a id=${list.id} onclick="del(this)" class="label label-danger"><span class="glyphicon glyphicon-trash"></span></a>&nbsp;
                    	</td>
                	</tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

	<%--分页模块--%>
    <div class="pull-right">
    	<span style="color: red">当前页</span>${pager.pageNumber }/${pager.pageCount}<span style="color: red">总页</span>
        <ul class="pagination">
        	<li></li>
        	<c:if test="${pager.pageNumber!=1 }">
        		  <li><a href="#" id="up" onclick="submitForm(${pager.pageNumber-1})">&laquo;</a></li>
        	</c:if>
          <c:if test="${pager.pageNumber==1 }">
        		  <li><a href="#" id="up2" onclick="submitForm(1)">&laquo;</a></li>
        	</c:if>
            <c:if test="${pager.pageNumber+3>pager.pageCount }">
            	<c:forEach var="i" begin="${pager.pageNumber }" end="${pager.pageCount }" step="1" >
            		<li><a id="${i }" onclick="submitForm(${i })">${i}</a></li>
            	</c:forEach>
            </c:if>
            <c:if test="${pager.pageNumber+3<=pager.pageCount }">
           	 	<c:forEach var="i" begin="${pager.pageNumber }" end="${pager.pageNumber+3 }" step="1" >
            		 <li><a onclick="submitForm(${i })" id="${i }">${i}</a></li>
            	</c:forEach>
            </c:if>
            <c:if test="${pager.pageNumber==pager.pageCount }">
            	<li><a href="#" onclick="submitForm(${pager.pageCount})">&raquo;</a></li>
        	</c:if>
        	<c:if test="${pager.pageNumber!=pager.pageCount }">
            	<li><a href="#" onclick="submitForm(${pager.pageNumber+1})">&raquo;</a></li>
        	</c:if>
        	<li>每页显示
        		<select style="height: 34px;width: 36px" id="pageSize" onchange="submitForm(1)">
        			<option value="5" 
        				<c:if test="${pager.pageSize == 5 }">
        					selected
        				</c:if>>5</option>
        			<option value="10" 
        				<c:if test="${pager.pageSize == 10 }">
        					selected
        				</c:if>
        			>10</option>
        			<option value="15" 
        				<c:if test="${pager.pageSize == 15 }">
        					selected
        				</c:if>
        			>15</option>
        		</select>
        		条
        	</li>
        </ul>
    </div>
   
  </div>

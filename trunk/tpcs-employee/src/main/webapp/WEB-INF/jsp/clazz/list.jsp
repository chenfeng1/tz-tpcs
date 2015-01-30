<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<script type="text/javascript">
	function transfer(cid,name,room,open,count,advider,trainner_date,lecturer){
		$("#hid").val(cid);
		$("#classname").val(name);
		$("#classroom").val(room);
		$("#open_date").val(open);
		$("#class_students").val(count);
		$("#class_adviser").val(advider);
		$("#trainer_date").val(trainner_date);
		$("#teachername").val(lecturer);
	}
	function update(){
		document.getElementById("myClassForm").action = "${path}/clazz/update";
		document.getElementById("myClassForm").submit();
	}
	function go(){
		var name = $("#cname").val();
		var min = $("#min_nums").val();
		var max = $("#max_nums").val();
		var v = $("#pageSize").val();
		window.location="${path}/clazz/list?pageSize="+v+"&name="+name+"&min="+min+"&max="+max;
	}
	function next(){
		var name = $("#cname").val();
		var min = $("#min_nums").val();
		var max = $("#max_nums").val();
		var size = $("#pageSize").val();
		$("#next").attr("href","${path }/clazz/list?pageNow=${paging.pageNow+1}&name="+name+"&min="+min+"&max="+max+"&pageSize="+size);
	}
	function next2(){
		var name = $("#cname").val();
		var min = $("#min_nums").val();
		var max = $("#max_nums").val();
		var size = $("#pageSize").val();
		$("#next2").attr("href","${path }/clazz/list?pageNow=${paging.pageCount}&name="+name+"&min="+min+"&max="+max+"&pageSize="+size);
	}
	function back(){
		var name = $("#cname").val();
		var min = $("#min_nums").val();
		var max = $("#max_nums").val();
		var size = $("#pageSize").val();
		$("#up").attr("href","${path }/clazz/list?pageNow=${paging.pageNow-1}&name="+name+"&min="+min+"&max="+max+"&pageSize="+size);
	}
	function back2(){
		var name = $("#cname").val();
		var min = $("#min_nums").val();
		var max = $("#max_nums").val();
		var size = $("#pageSize").val();
		$("#up2").attr("href","${path }/clazz/list?pageNow=1&name="+name+"&min="+min+"&max="+max+"&pageSize="+size);
	}
	function goNext(obj){
		var name = $("#cname").val();
		var min = $("#min_nums").val();
		var max = $("#max_nums").val();
		var size = $("#pageSize").val();
		var pageNow = obj.id;
		
		window.location="${path }/clazz/list?pageNow="+pageNow+"&name="+name+"&min="+min+"&max="+max+"&pageSize="+size;
	}
	
	function goNext2(obj){
		var name = $("#cname").val();
		var min = $("#min_nums").val();
		var max = $("#max_nums").val();
		var size = $("#pageSize").val();
		var pageNow = obj.id;
		window.location="${path }/clazz/list?pageNow="+pageNow+"&name="+name+"&min="+min+"&max="+max+"&pageSize="+size;
	}
	
	function del(obj){
	//	alert(obj.id);
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
    <form class="form-horizontal" role="form" action="${path }/clazz/list" method="get">
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
                <button type="submit" class="btn btn-primary">
                    <span class="glyphicon glyphicon-search"></span>
                </button>
            </div>
        </div>
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
                    	<input type="hidden" name="hid" id="hid" />
                        <label for="classname" class="col-md-2 control-label">班级名</label>
                        <div class="col-md-5">
                            <input type="text" name="classname" id="classname" class="form-control" placeholder="班级的名称"/>
                        </div>
                        <div class="col-md-5" id="classname_id">提示信息</div>
                    </div>
                    <div class="form-group">
                        <label for="classroom" class="col-md-2 control-label">所在教室</label>
                        <div class="col-md-5">
                            <input type="text" id="classroom" name="classroom" class="form-control" placeholder="教室的名称"/>
                        </div>
                        <div class="col-md-5" id="classroom_id"></div>
                    </div>
                    <div class="form-group">
                        <label for="classname" class="col-md-2 control-label">开班日期</label>
                        <div class="col-md-5">
                            <input type="date" id="open_date" name="open_date" class="form-control" placeholder="2015-01-27"/>
                        </div>
                        <div class="col-md-5" id="open_date_id"></div>
                    </div>
                    <div class="form-group">
                        <label for="classname" class="col-md-2 control-label">开班人数</label>
                        <div class="col-md-5">
                            <input type="number" id="class_students" name="count" class="form-control" placeholder="30"/>
                        </div>
                        <div class="col-md-5" id="class_students_id"></div>
                    </div>
                    <div class="form-group">
                        <label for="class_adviser" class="col-md-2 control-label">班主任</label>
                        <div class="col-md-5">
                            <input type="text" id="class_adviser" name="adviser" class="form-control" placeholder="班主任名"/>
                        </div>
                        <div class="col-md-5" id="class_adviser_id"></div>
                    </div>

                    <div class="form-group">
                        <label for="trainer_date" class="col-md-2 control-label">训练营日</label>
                        <div class="col-md-5">
                            <input type="date" id="trainer_date" name="tdate" class="form-control" placeholder="2015-01-18"/>
                        </div>
                        <div class="col-md-5" id="trainer_date_id"></div>
                    </div>

                    <div class="form-group">
                        <label for="teachername" class="col-md-2 control-label">讲师名</label>
                        <div class="col-md-5">
                            <input type="text" id="teachername" name="teachername" class="form-control" placeholder="讲师名"/>
                        </div>
                        <div class="col-md-5" id="teachername_id"></div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                <button type="button" class="btn btn-primary" onclick="update()">更新</button>
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
                <c:forEach var="list" items="${clazzList }" varStatus="v">
                	<tr id="${list.id }">
                		<td>${v.count }</td>
                        <td>${list.name }</td>
                		<td>${list.room }</td>
                		<td><fmt:formatDate value="${list.open }" type="date"></fmt:formatDate></td>
                		<td>${list.count }</td>
                		<td>${list.advisor}</td>
                		<td><fmt:formatDate value="${list.trainingDate }" type="date"></fmt:formatDate></td>
                		<td>${list.lecturer }</td>
                		<td>
                        	<a href="" class="label label-default" data-toggle="modal" data-target="#myClassModal" onclick="transfer('${list.id }','${list.name}','${list.room }','${list.open }','${list.count }','${list.advisor}','${list.trainingDate }','${list.lecturer }')"><span class="glyphicon glyphicon-refresh"></span></a>&nbsp;
                       	 	<a id=${list.id } onclick="del(this)" class="label label-danger"><span class="glyphicon glyphicon-trash"></span></a>&nbsp;
                    	</td>
                	</tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>

	<%--分页模块--%>
    <div class="pull-right">
    	<span style="color: red">当前页</span>${paging.pageNow }/${paging.pageCount}<span style="color: red">总页</span>
        <ul class="pagination">
        	<li></li>
        	<c:if test="${paging.pageNow!=1 }">
        		  <li><a href="" id="up" onclick="back()">&laquo;</a></li>
        	</c:if>
          <c:if test="${paging.pageNow==1 }">
        		  <li><a href="${path }/clazz/list?pageNow=1" id="up2" onclick="back2()">&laquo;</a></li>
        	</c:if>
            <c:if test="${paging.pageNow+3>paging.pageCount }">
            	<c:forEach var="i" begin="${paging.pageNow }" end="${paging.pageCount }" step="1" >
            		<%-- <li><a href="${path }/clazz/list?pageNow=${i}" id="a_"+${i } onclick="goNext(this)">${i}</a></li> --%>
            		<li><a id="${i }" onclick="goNext(this)">${i}</a></li>
            	</c:forEach>
            </c:if>
            <c:if test="${paging.pageNow+3<=paging.pageCount }">
           	 	<c:forEach var="i" begin="${paging.pageNow }" end="${paging.pageNow+3 }" step="1" >
            		<%-- <li><a href="${path }/clazz/list?pageNow=${i}">${i}</a></li> --%>
            		 <li><a onclick="goNext2(this)" id="${i }">${i}</a></li>
            	</c:forEach>
            </c:if>
            <c:if test="${paging.pageNow==paging.pageCount }">
            	<li><a href="" onclick="next2()" id="next2()">&raquo;</a></li>
        	</c:if>
        	<c:if test="${paging.pageNow!=paging.pageCount }">
            	<li><a href="" id="next" onclick="next()">&raquo;</a></li>
        	</c:if>
        	<li>每页显示
        		<select style="height: 34px;width: 36px" id="pageSize" onchange="go()">
        			<option value="2" 
        				<c:if test="${paging.pageSize == 2 }">
        					selected
        				</c:if>>2</option>
        			<option value="4" 
        				<c:if test="${paging.pageSize == 4 }">
        					selected
        				</c:if>
        			>4</option>
        			<option value="8" 
        				<c:if test="${paging.pageSize == 8 }">
        					selected
        				</c:if>
        			>8</option>
        			<option value="16" 
        				<c:if test="${paging.pageSize == 16 }">
        					selected
        				</c:if>
        			>16</option>
        		</select>
        		条
        	</li>
        </ul>
    </div>
   
  </div>


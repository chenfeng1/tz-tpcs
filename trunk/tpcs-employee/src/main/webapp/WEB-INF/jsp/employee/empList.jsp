<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
    $(function(){
        //删除部门按钮
        $("#deleteDept").click(function(){
            if(confirm('确定删除选中的部门吗?')){
                var id = $("#myTree").jstree("get_selected");
                if(id){
                    $.ajax({
                        url: '${path}/departments/'+id,
                        type: 'DELETE',
                        success: function(result) {
                            if(result.success){
                                $("#myTree").jstree("destroy")
                                $("#myTree").jstree({'core': {'multiple':false, 'data': result.obj}});
                                $('#myTree').jstree(true).refresh();
                                //监听部门树点击事件
                                treeSelectedListener();
                            }else{
                                alert(result.obj);
                            }
                        }
                    });
                }else{
                    alert("请先选中部门!");
                }
            }
        });
        //查询按钮点击
        $("#searchBtn").click(function () {
            searchEmp();
            return false;
        });
        //查询表单提交
        $("#searchForm").submit(function(){
            searchEmp();
            return false;
        });
        //empTr双击事件
        $(".empTr").dblclick(function(e){
//            alert(this.id);
            var empId = this.id;
            var deptId = $("#myTree").jstree("get_selected");
            var realname = $("#realname").val();
//            console.log("empId:"+empId+", deptId:"+deptId[0]+", realname:"+realname);
            var page = ${pager.pageNumber};
            var stateObj = {"deptId":deptId[0], "realname":realname, "pageNumber":page};
            history.pushState(stateObj, "state_emp_list_left", "list");
            //发送请求
            var htmlObj=$.ajax({url:"${path}/employees/"+empId, async:false});
            $("#empListDiv").html(htmlObj.responseText);
        });
        //上一页
        $(".previousPage").click(function(){
            //修改页码
            var pageNumber = parseInt($("#pageNumber").val());
            $("#pageNumber").val(pageNumber-1);
            //发送请求
            searchEmp();
        });
        //下一页
        $(".nextPage").click(function(){
            //修改页码
            var pageNumber = parseInt($("#pageNumber").val());
            $("#pageNumber").val(pageNumber+1);
            //发送请求
            searchEmp();
        });
        //新建员工
        $("#initAddBtn").click(function(){
            var nodeSelected = $("#myTree").jstree("get_selected");
            var deptId = nodeSelected[0];
            $.get("${path}/employees/initAdd?deptId="+deptId, function(result){
                //console.log(result);
                $("#empListDiv").html(result);
            });
        });
        //全选
        $("#checkAll").change(function(){
            //console.log("checkAll change "+Date.now());
            $("#empListTable :checkbox").prop("checked", $(this).prop("checked"));
        });
        //删除员工按钮
        $("#empDeleteBtn").click(function(){
            var ids = getSelectedEmpIds();
            $.post("${path}/employees/deleteByArray", {"ids":ids}, function(result){
                $("#pageNumber").val(1);
                searchEmp();
            });
        });
        //禁用员工按钮
        $("#empDisableBtn").click(function(){
            var ids = getSelectedEmpIds();
            $.post("${path}/employees/changeEnableStatus", {"ids":ids, "enableStatus":false}, function(result){
                console.log(result);
                $(result.obj).each(function(){
                    //console.log($("tr[id='"+this+"']"));
                    $("tr[id='"+this+"']").find("span.label-success").parent().html("<span class='label label-danger'>禁用</span>");
                });
            });
        });
        //启用员工按钮
        $("#empEnableBtn").click(function(){
            var ids = getSelectedEmpIds();
            $.post("${path}/employees/changeEnableStatus", {"ids":ids, "enableStatus":true}, function(result){
                console.log(result);
                $(result.obj).each(function(){
                    $("tr[id='"+this+"']").find("span.label-danger").parent().html("<span class='label label-success'>启用</span>");
                });
            });
        });
        //新增部门按钮
        $("#deptAddBtn").click(function () {
            //alert("添加部门");
            var name = $("#dept_name").val();
            if(name.trim() == ""){
                alert("部门名称不能为空!");
                return false;
            }
            var id = $("#myTree").jstree("get_selected");
            //发送请求
            $.post("${path}/departments/add", {"name":name, "parentId":id[0]}, function(result){
                console.log(result);
                if(result.success){
                    //alert("success");
                    //关闭对话框
                    $("#deptAddCloseBtn").click();
                    //更新部门树
                    $("#myTree").jstree("destroy")
                    $("#myTree").jstree({'core': {'multiple':false, 'data': result.obj}});
                    //监听部门树点击事件
                    treeSelectedListener();
                }else{
                    alert(result.obj);
                }
            });
        });
    });

    function getSelectedEmpIds(){
        var array = $("#empListTable :checked");
        if(array.size() == 0){
            alert("请勾选需要操作的员工");
        }else{
            var ids = "";
            array.each(function(){
                if(ids != "")
                    ids += ",";
                ids += $(this).parents("tr").attr("id");
            });
            return ids;
        }
    }


</script>

<div class="row">
    <%-- 部门选择提示 --%>
    <div class="col-md-3" style="padding:25px 0 15px 15px;">
        <strong style="font-size: large">天智教育</strong>
    </div>
    <%-- 搜索框 --%>
    <div class="col-md-offset-6 col-md-3" style="padding-top: 20px;">
        <form id="searchForm" class="form-inline" role="form">
            <div class="form-group">
                <div class="input-group">
                    <input id="realname" type="text" name="realname" value="${form.realname}" placeholder="员工名" class="form-control">
                    <input type="submit" style="display: none;">
                    <div id="searchBtn" class="input-group-addon" style="cursor: pointer">
                        <span class="glyphicon glyphicon-search"></span>
                    </div>
                </div>
            </div>
            <input id="pageNumber" type="hidden" name="pageNumber" value="${form.pageNumber}"/>
            <input id="deptId" type="hidden" name="deptId" value="${form.deptId}"/>
        </form>
    </div>
</div>

<%-- 添加子部门模态框开始 --%>
<div class="modal fade" id="addDeptModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #0a6aa1">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h5 class="modal-title" id="myModalLabel">新增部门</h5>
            </div>
            <div class="modal-body">
                <form id="deptAddForm" class="form-horizontal" role="form" action="#" id="myDeptForm">
                    <div class="form-group">
                        <label for="dept_name" class="col-md-2 control-label">部门名称</label>
                        <div class="col-md-5">
                            <input type="text" id="dept_name" class="form-control" placeholder="部门名"/>
                        </div>
                        <div class="col-md-5" id="classname_id">&nbsp;</div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button id="deptAddBtn" type="button" class="btn btn-primary" onclick="javascript:void(0)">新增</button>
                <button id="deptAddCloseBtn" type="button" class="btn btn-default" data-dismiss="modal" onclick='$("#dept_name").val("")'>关闭</button>
            </div>
        </div>
    </div>
</div>
<%-- 添加子部门模态框结束 --%>

<%-- 更新部门模态框开始 --%>
<div class="modal fade" id="updDeptModel" tabindex="-1" role="dialog" aria-labelledby="myUpdModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #0a6aa1">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h5 class="modal-title" id="myUpdModalLabel">更新部门</h5>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" action="#" id="updDeptForm">
                    <div class="form-group">
                        <label for="name" class="col-md-2 control-label">部门名称</label>
                        <div class="col-md-5">
                            <input type="text" id="updDeptName" class="form-control" value="天智教育"/>
                        </div>
                        <div class="col-md-5" id="updDeptName_id">&nbsp;</div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="通过JS来提交表单">更新</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<%-- 更新部门模态框结束 --%>

<div class="row">
    <div class="col-md-4" style="font-size: small">
        <a href="javaScript:void(0)" data-toggle="modal" data-target="#addDeptModel">添加子部门</a>&nbsp;|&nbsp;
        <a href="javaScript:void(0)" data-toggle="modal" data-target="#updDeptModel">修改部门名</a>&nbsp;|&nbsp;
        <a id="deleteDept" href="javaScript:void(0)">删除</a>
    </div>
</div>
<div class="row" style="margin-top: 5px;margin-bottom: 10px;">
    <%-- 顶部分页区 --%>
    <div class="col-md-12" style="height: 30px;">
        <c:if test="${pager.pageCount > 0}">
            <p class="text-right">
                <c:if test="${pager.pageNumber > 1}">
                    <a class="previousPage" href="javascript:void(0)">
                        <small>上一页</small>
                    </a>
                </c:if>
                <small>${pager.pageNumber} / ${pager.pageCount} 页</small>
                <c:if test="${pager.pageNumber <  pager.pageCount}">
                    <a class="nextPage" href="javascript:void(0)">
                        <small>下一页</small>
                    </a>
                </c:if>
            </p>
        </c:if>
    </div>
</div>

<%-- 操作导航栏  --%>
<div class="row"
     style="background-color: #efefef;border-top: 1px solid #cccccc;border-bottom: 1px solid #cccccc;max-height: 40px;margin: 0 2px;">
    <div class="col-md-11">

        <%--<input type="checkbox" class="checkbox" name="">--%>
        <ul class="nav nav-pills">
            <li role="presentation">
                <div class="checkbox">
                    <label><input id="checkAll" type="checkbox" value="" title="全选/取消"/></label>
                </div>
            </li>
            <li role="presentation" class="dropdown">
                <button type="button" class="btn-xs btn-default" role="button" data-toggle="dropdown"
                        aria-expanded="false" style="margin: 8px 5px;">
                    新增成员
                    <span class="caret"></span>
                </button>
                <ul class="dropdown-menu" role="menu">
                    <li>
                        <a id="initAddBtn" href="javascript:void(0)">新建成员</a>
                    </li>
                    <li>
                        <a href="#">批量导入成员</a>
                    </li>
                    <li class="divider"></li>
                    <li>
                        <a href="#">其它功能[预留]</a>
                    </li>

                </ul>
            </li>
            <li role="presentation" class="dropdown">
                <button id="empDisableBtn" type="button" class="btn-xs btn-default" role="button" style="margin: 8px 5px;" onclick="">
                    禁用
                </button>
            </li>
            <li role="presentation" class="dropdown">
                <button id="empEnableBtn" type="button" class="btn-xs btn-default" role="button" style="margin: 8px 5px;" onclick="">
                    启用
                </button>
            </li>
            <li role="presentation" class="dropdown">
                <button id="empDeleteBtn" type="button" class="btn-xs btn-default" role="button" style="margin: 8px 5px;" onclick="">
                    删除
                </button>
            </li>
        </ul>
    </div>
</div>

<%-- 员工列表区 --%>
<div class="row" style="margin: 0 -7px;">
    <div class="col-md-12" style="margin-top: 10px;">
        <table id="empListTable" class="table table-hover">
            <thead>
            <tr>
                <th width="25px"></th>
                <th width="50px">性别</th>
                <th width="150px">员工名</th>
                <th>邮箱</th>
                <th width="100px">电话</th>
                <th width="100px">状态</th>
                <th width="150px">岗位</th>
            </tr>
            </thead>
            <tbody>
            <c:if test="${empty pager.list}" var="emptyList">
                <tr>
                    <td colspan="7" style="text-align: center;">暂无数据</td>
                </tr>
            </c:if>
            <c:if test="${!emptyList}">
                <c:forEach items="${pager.list}" var="emp">
                    <tr class="empTr" style="cursor: pointer" id="${emp.id}">
                        <td>
                            <label class="checkbox-inline">
                                <input type="checkbox" id="chk1">&nbsp;
                            </label>
                        </td>
                        <td>
                            <%-- 女性用 orange 色，男性用 blue --%>
                            <c:if test='${emp.gender eq "MALE"}' var="isMale">
                                <c:set value="color:blue" var="genderStyle"/>
                            </c:if>
                            <c:if test="${!isMale}">
                                <c:set value="color:orange" var="genderStyle"/>
                            </c:if>
                            <span class="glyphicon glyphicon-user" style="${genderStyle}"></span>
                        </td>
                        <td>${emp.realname}</td>
                        <td>${emp.email}</td>
                        <td>${emp.mobilePhone}</td>
                        <td>
                            <c:if test="${emp.enabled}" var="isEnabled">
                                <span class="label label-success">启用</span>
                            </c:if>
                            <c:if test="${!isEnabled}">
                                <span class="label label-danger">禁用</span>
                            </c:if>
                        </td>
                        <td>${emp.job}</td>
                    </tr>
                </c:forEach>
            </c:if>
            </tbody>
        </table>
    </div>
</div>
<%-- end 员工列表区 --%>

<%-- 底部分页区 --%>
<div class="row">
    <div class="col-md-12" style="height: 30px;">
        <c:if test="${pager.pageCount > 0}">
            <p class="text-right">
                <small>${pager.totalCount} 条记录, </small>
                <c:if test="${pager.pageNumber > 1}">
                    <a class="previousPage" href="javascript:void(0)">
                        <small>上一页</small>
                    </a>
                </c:if>
                <small>${pager.pageNumber} / ${pager.pageCount} 页</small>
                <c:if test="${pager.pageNumber <  pager.pageCount}">
                    <a class="nextPage" href="javascript:void(0)">
                        <small>下一页</small>
                    </a>
                </c:if>
            </p>
        </c:if>
    </div>
</div>


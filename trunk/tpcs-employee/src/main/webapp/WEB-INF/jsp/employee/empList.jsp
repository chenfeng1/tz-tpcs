<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<script type="text/javascript">
    $(function(){
        //删除按钮点击
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
            doSubmit();
            return false;
        });
        //查询表单提交
        $("#searchForm").submit(function(){
            doSubmit();
            return false;
        });
        //处理提交
        function doSubmit() {
//            console.log("submit searchForm")
            var deptId = $("#myTree").jstree("get_selected");
            var realname = $("#realname").val();
            //发送请求，刷新下属员工
            $.post("${path}/employees/search",{"deptId":deptId[0], "realname":realname},function(result){
                $("#empListDiv").html(result);
            });
        }
        //empTr双击事件
        $(".empTr").dblclick(function(e){
//            alert(this.id);
            var empId = this.id;
            var deptId = $("#myTree").jstree("get_selected");
            var realname = $("#realname").val();
            console.log("empId:"+empId+", deptId:"+deptId[0]+", realname:"+realname);
            var page = ${pager.pageNumber};
            var stateObj = {"deptId":deptId[0], "realname":realname, "pageNumber":page};
            history.pushState(stateObj, "state_emp_list_left", "list");
            //发送请求
            var htmlObj=$.ajax({url:"${path}/employees/"+empId, async:false});
            $("#empListDiv").html(htmlObj.responseText);
        });

        $(".previousPage").click(function(){

        });

    });
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
                    <input id="realname" type="text" name="realname" value="${realname}" placeholder="员工名" class="form-control">
                    <input type="submit" style="display: none;">
                    <div id="searchBtn" class="input-group-addon" style="cursor: pointer">
                        <span class="glyphicon glyphicon-search"></span>
                    </div>
                </div>
            </div>
            <input type="text" name="pageNumber" value="${pager.pageNumber}"/>

        </form>
    </div>
</div>

<div class="row">
    <div class="col-md-4" style="font-size: small">
        <a href="#" data-toggle="modal" data-target="#updDeptModel">修改部门名</a>&nbsp;|&nbsp;
        <a href="#" data-toggle="modal" data-target="#addDeptModel">添加子部门</a>&nbsp;|&nbsp;
        <a id="deleteDept" href="javaScript:void (0)">删除</a>
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
                <small>${pager.pageNumber} / ${pager.pageCount}</small>
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
                    <label><input type="checkbox" value="" title="选中/取消选中"/></label>
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
                        <a href="add_employee.html">新建成员</a>
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
                <button type="button" class="btn-xs btn-default" role="button" style="margin: 8px 5px;" onclick="">
                    禁用
                </button>
            </li>
            <li role="presentation" class="dropdown">
                <button type="button" class="btn-xs btn-default" role="button" style="margin: 8px 5px;" onclick="">
                    启用
                </button>
            </li>
            <li role="presentation" class="dropdown">
                <button type="button" class="btn-xs btn-default" role="button" style="margin: 8px 5px;" onclick="">
                    删除
                </button>
            </li>
        </ul>
    </div>
</div>

<%-- 员工列表区 --%>
<div class="row" style="margin: 0 -7px;">
    <div class="col-md-12" style="margin-top: 10px;">
        <table class="table table-hover">
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
                            <%--<c:if test="${emp.birthDate != null}">
                                <fmt:formatDate value="${emp.mobilePhone}" pattern="yyyy-MM-dd"></fmt:formatDate>
                            </c:if>--%>
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
                <a href="#">
                    <small>上一页</small>
                </a>
                <small>${pager.pageNumber} / ${pager.pageCount}</small>
                <a href="#">
                    <small>下一页</small>
                </a>
            </p>
        </c:if>
    </div>
</div>


<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<script type="text/javascript">
    $(function () {
        $("#previous").click(function(){
            var num = parseInt($("#pageNumber").val())-1;
            submitForm(num)
        });
        $("#next").click(function(){
            var num = parseInt($("#pageNumber").val())+1;
            submitForm(num)
        });
    });

    function submitForm(num){
        $("#pageNumber").val(num);
        $("#searchForm").submit();
    }
</script>

<div class="container">
    <!-- 查询区 -->
    <form id="searchForm" class="form-horizontal" role="form" action="${path}/projects/list" method="post">
        <div class="form-group">
            <label for="cname" class="col-md-1 control-label">项目名</label>
            <div class="col-md-3">
                <input type="text" id="cname" name="name" value="${name}" class="form-control" placeholder="根据项目名查询"/>
            </div>

            <label for="sname" class="col-md-1 control-label">项目代码</label>
            <div class="col-md-3">
                <input type="text" id="sname" name="code" value="${code}" class="form-control" placeholder="根据项目代码查询"/>
            </div>

            <div class="col-md-4">
                <button type="submit" class="btn btn-primary">
                    <span class="glyphicon glyphicon-search"></span>
                </button>
            </div>
        </div>
        <input id="pageNumber" type="hidden" name="pageNumber" value="${pager.pageNumber}">
    </form>
    <hr/>

    <!-- 内容区 -->
    <div class="row">
        <div class="col-md-1">
            <button type="button" class="btn btn-primary" onclick="javascript:window.location.href='${path}/projects/initAdd'">
                <span class="glyphicon glyphicon-plus">&nbsp;添加新项目</span>
            </button>
        </div>
    </div>

    <c:forEach items="${pager.list}" var="projectCase">
        <hr/>
        <!-- 此处循环 迭代 -->
        <div class="row">
            <div class="col-md-1">
                <span>1</span>
            </div>
            <div class="col-md-3">
                <span class="label label-default">项目代码:</span>
                <strong>${projectCase.code}</strong>
            </div>
            <div class="col-md-4">
                <span class="label label-default">项目名称:</span>
                <strong>${projectCase.name}</strong>
            </div>
            <div class="col-md-1">
                <a class="label label-default" href="${path}/projects/initUpdate"><span class="glyphicon glyphicon-refresh"></span></a>&nbsp;
                <a class="label label-danger" href="${path}/projects/delete?id=${projectCase.id}" onclick="return confirm('确定要删除吗?');"><span class="glyphicon glyphicon-trash"></span></a>&nbsp;
            </div>
        </div>
        <div class="row">
            <div class="col-md-9">
                <hr style="margin-top: 5px;border: thin dashed dodgerblue"/>
            </div>
        </div>
        <div class="row">
            <div class="col-md-3">
                <a href="" onclick="" class="img-thumbnail">
                    <img src="../upload/img/p-default.jpg"><br/>
                    <center>更换图片</center>
                </a>
            </div>
            <div class="col-md-7">
                <p>${projectCase.name}</p>
                <p>
                    <a href="">更多&gt;&gt;</a>
                </p>
                <!-- 此部份将来链接到项目规格说明书上，以PDF的格式下载或是预览 -->
                <p>
                    <a href="" class="label label-info">项目规格说明书</a>
                    <span class="help-block">以PDF的格式下载或是预览</span>
                </p>
            </div>
        </div> <!-- forEach Over -->
    </c:forEach>

    <!-- 分页模块 -->
    <nav>
        <ul class="pager">
            <c:if test="${pager.pageNumber > 1}">
                <li class="previous"><a id="previous" href="javascript:void(0);"><span aria-hidden="true">&larr;</span> 上一页</a></li>
            </c:if>
            <c:if test="${pager.pageNumber < pager.pageCount}">
                <li class="next"><a id="next" href="javascript:void(0);">下一页 <span aria-hidden="true">&rarr;</span></a></li>
            </c:if>
        </ul>
    </nav>
</div>

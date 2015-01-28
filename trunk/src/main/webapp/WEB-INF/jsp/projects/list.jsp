<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<style type="text/css">
    .myWorkBreak {
        word-break:break-all;
    }
</style>

<script type="text/javascript">
    var jsSubmit = false; //表单由js脚本提交
    $(function () {
        //上一页
        $("#previous").click(function(){
            var num = parseInt($("#pageNumber").val())-1;
            submitForm(num)
        });
        //下一页
        $("#next").click(function(){
            var num = parseInt($("#pageNumber").val())+1;
            submitForm(num)
        });
        //监听手动查询事件，页码重置为1
        $("#searchForm").submit(function () {
            if(!jsSubmit){
                $("#pageNumber").val(1);
            }
        });
        //监听"更多"按钮 点击事件
        $(".moreDesc").click(function(){
            $(this).hide();
            var $row = $(this).parents(".row");
            $row.find(".simpleDesc").hide();
            $row.find(".fullDesc").show();
        });
    });
    //提交查询表单
    function submitForm(num){
        jsSubmit = true;
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
                <a class="label label-default" href="${path}/projects/initUpdate?${projectCase.id}"><span class="glyphicon glyphicon-refresh"></span></a>&nbsp;
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
                    <c:if test="${projectCase.snapshot != null}" var="hasSnapshot">
                        <img width="128px" height="128px" src="${path}/uploadFiles/projectCase/snapshot/${projectCase.snapshot}">
                    </c:if>
                    <c:if test="${!hasSnapshot}">
                        <img width="128px" height="128px" src="../upload/img/p-default.jpg"><br/>
                    </c:if>
                    <center>更换图片</center>
                </a>
            </div>
            <div class="col-md-6">
                <%--如果超出最大长度，显示简化信息--%>
                <c:if test="${fn:length(projectCase.desc) > SIMPLE_DESC_LEN}" var="simple">
                    <p class="myWorkBreak simpleDesc">${fn:substring(projectCase.desc, 0, SIMPLE_DESC_LEN)}...</p>
                    <p class="myWorkBreak fullDesc" style="display:none">${projectCase.desc}</p>
                    <a class="moreDesc" href="javascript:void(0)">更多&gt;&gt;</a>
                </c:if>
                <%--如果内最大长度，直接显示信息--%>
                <c:if test="${!simple}">
                    <p class="myWorkBreak">${projectCase.desc}</p>
                </c:if>
                <!-- 此部份将来链接到项目规格说明书上，以PDF的格式下载或是预览 -->
                <p>
                    <a href="${path}/uploadFiles/projectCase/fs/${projectCase.functionSpec}" target="_blank" class="label label-info">项目规格说明书</a>
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

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
        //点击更换项目案例图片
        $(".changeProCaseImg").click(function(){
            if(msieversion()) {
                alert("亲用的是IE浏览器，请在修改页面进行此操作!")
            }else{
                var id = $(this).attr("pcId");
                console.log("click:"+id +" on "+Date.now());
                $("#hiddenForm #id").val(id);
                $("#fileupload").click();
            }
        });

        //点击更换项目案例图片
        $("#fileupload").change(function(){
            //alert("change!!");
            var formData = new FormData($('#hiddenForm')[0]);
            $.ajax({
                url: '${path}/projects/ajaxUploadImg',  //Server script to process data
                type: 'post',
                //Ajax events
                success: function(result){
                    if(result.success){
                        //alert("上传成功!");
                        //console.log(result.obj);
                        var newPath = "${path}/upload/projectCase/snapshot/" + result.obj;
                        var id = "#SnapshotImg_"+$("#hiddenForm #id").val();
                        $(id).attr("src", newPath);
                    }else{
                        alert("上传失败!");
                    }
                },
                error: function(e){
                    alert("error:"+e);
                },
                // Form data
                data: formData,
                //tell jQuery not to process data or worry about content-type.
                cache: false,
                contentType: false,
                processData: false
            });
        });

    });
    //提交查询表单
    function submitForm(num){
        jsSubmit = true;
        $("#pageNumber").val(num);
        $("#searchForm").submit();
    }

    //判断是否IE浏览器
    function msieversion() {
        var ua = window.navigator.userAgent;
        var msie = ua.indexOf("MSIE ");
        // If Internet Explorer, return version number
        if (msie > 0 || !!navigator.userAgent.match(/Trident.*rv\:11\./)){
            var version = parseInt(ua.substring(msie + 5, ua.indexOf(".", msie)));
            console.log("IE "+version);
            return true;
        }else{
            // If another browser, return 0
            return false;
        }
    }

</script>

<div style="display:none">
    <form id="hiddenForm" enctype="multipart/form-data">
        <input type="text" id="id" name="id" value="xxx" />
        <input id="fileupload" type="file" name="image" />
        <input type="button" value="Upload" />
    </form>
</div>

<div class="container">
    <%-- 查询区 --%>
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

    <%--内容区--%>
    <div class="row">
        <div class="col-md-1">
            <button type="button" class="btn btn-primary" onclick="location.href='${path}/projects/initAdd'">
                <span class="glyphicon glyphicon-plus">&nbsp;添加新项目</span>
            </button>
        </div>
    </div>

    <c:forEach items="${pager.list}" var="projectCase">
        <hr/>
        <%--此处循环 迭代--%>
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
                <a class="label label-default" href="${path}/projects/initUpdate?id=${projectCase.id}"><span class="glyphicon glyphicon-refresh"></span></a>&nbsp;
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
                <c:if test="${projectCase.snapshot != null}" var="hasSnapshot">
                    <c:set value="${path}/upload/projectCase/snapshot/${projectCase.snapshot}" var="imgPath"/>
                </c:if>
                <c:if test="${!hasSnapshot}">
                    <c:set value="${path}/upload/img/p-default.jpg" var="imgPath"/>
                </c:if>

                <a href="javascript:void(0)" pcId="${projectCase.id}" class="img-thumbnail changeProCaseImg">
                    <img id="SnapshotImg_${projectCase.id}" width="128px" height="128px" src="${imgPath}">
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
                <p>
                    <c:if test="${projectCase.functionSpec != null}" var="hasFunctionSpec">
                        <%--此部份将来链接到项目规格说明书上，以PDF的格式下载或是预览--%>
                        <a href="${path}/upload/projectCase/fs/${projectCase.functionSpec}" target="_blank" class="label label-info">项目规格说明书</a>
                        <span class="help-block">以PDF的格式下载或是预览</span>
                    </c:if>
                    <c:if test="${!hasFunctionSpec}">
                        <span class="label label-default">项目规格说明书</span>
                    </c:if>
                </p>
            </div>
        </div>
    </c:forEach>

    <%--分页模块--%>
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

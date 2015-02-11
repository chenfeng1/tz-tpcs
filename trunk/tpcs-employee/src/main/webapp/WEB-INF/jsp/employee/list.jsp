<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!-- 导入jstree相关的文件 -->
<script src="${path}/jstree/jstree.min.js"></script>
<link href="${path}/jstree/themes/default/style.min.css" rel="stylesheet">
<script type="text/javascript">
    $(function () {
        //初始化部门树
        $.getJSON("${path}/departments/list",function(result){
            //console.log(result);
            result[0].state.selected = true; //默认选中根节点
            $("#myTree").jstree({'core': {
                                            'check_callback': true,
                                            'multiple':false,
                                            'data': result
                                        }
//                                ,'plugins':["search", "sort", "json_data"]
                                });
            //事件监听
            treeSelectedListener();
        });
    });

    //ajax查询+显示员工信息
    function searchEmp(jsonData){
        if(!jsonData){
            jsonData = $('#searchForm').serialize();
        }
        $.post("${path}/employees/search", jsonData, function(result){
            $("#empListDiv").html(result);
        });
    }

    //监听左侧部门树的点击事件
    function treeSelectedListener(){
        $("#myTree").on("select_node.jstree", function (e, data) {
            var id = $("#myTree").jstree("get_selected");
            //发送请求，显示该部门下属员工
            $.post("${path}/employees/search",{"deptId":id[0], "realname":""},function(result){
                $("#empListDiv").html(result);
            });
        });
    }
</script>

<%-- 其它内容 --%>
<div class="container">
    <div class="row" style="padding-left: 50px;">
        <%--左边部门树部分--%>
        <div class="col-md-3" style="background-color: #f4f7fa;max-width: 220px;min-height: 450px">
            <div class="row">
                <div class="col-md-offset-3 col-md-6">
                    <h5>
                        <span class="glyphicon glyphicon-home"></span>
                        <strong>组织架构</strong>
                    </h5>
                </div>
            </div>
            <div class="row" style="margin-top: 5px;border-top: 2px solid #cccccc;padding-top: 5px;">
                <div class="col-md-1">
                    <%-- 通过AJAX动态加载出来 --%>
                    <div id="myTree"></div>
                </div>
            </div>
        </div>

        <div id="empListDiv" class="col-md-9" style="background-color: #ffffff;min-height: 450px;">
            <%--右边员工列表部分--%>
            <jsp:include page="/WEB-INF/jsp/employee/empList.jsp"/>
        </div>
    </div><%-- end row --%>
</div><%-- end container --%>

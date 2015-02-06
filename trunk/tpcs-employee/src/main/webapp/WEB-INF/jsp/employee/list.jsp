<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<div class="modal fade" id="addDeptModel" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" style="background-color: #0a6aa1">
                <button type="button" class="close" data-dismiss="modal"><span aria-hidden="true">&times;</span><span class="sr-only">Close</span></button>
                <h5 class="modal-title" id="myModalLabel">新增部门</h5>
            </div>
            <div class="modal-body">
                <form class="form-horizontal" role="form" action="#" id="myDeptForm">
                    <div class="form-group">
                        <label for="deptName" class="col-md-2 control-label">部门名称</label>
                        <div class="col-md-5">
                            <input type="text" id="deptName" class="form-control" placeholder="部门名"/>
                        </div>
                        <div class="col-md-5" id="classname_id">&nbsp;</div>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-primary" onclick="通过JS来提交表单">新增</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
            </div>
        </div>
    </div>
</div>
<%-- //添加子部门的模态框内容结束 --%>

<%-- 更新部门的Modal --%>
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
                        <label for="deptName" class="col-md-2 control-label">部门名称</label>
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
<%-- //添加子部门的模态框内容结束 --%>

<%-- 其它内容 --%>
<div class="container">
    <div class="row" style="padding-left: 50px;">
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
        </div> <%-- end of 左边列 --%>

        <div class="col-md-9" style="background-color: #ffffff;min-height: 450px;">
            <div class="row">
                <%-- 部门选择提示 --%>
                <div class="col-md-3" style="padding:25px 0 15px 15px;">
                    <strong style="font-size: large">天智教育</strong>
                </div>
                <%-- 搜索框 --%>
                <div class="col-md-offset-6 col-md-3" style="padding-top: 20px;">
                    <form class="form-inline" role="form" action="#">
                        <div class="form-group">
                            <div class="input-group">
                                <input type="text" name="realname" placeholder="员工名" class="form-control">
                                <div class="input-group-addon" style="cursor: pointer" onclick="alert('查询')">
                                    <span class="glyphicon glyphicon-search"></span>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
            </div>

            <div class="row">
                <div class="col-md-4" style="font-size: small">
                    <a href="#" data-toggle="modal" data-target="#updDeptModel">修改部门名</a>&nbsp;|&nbsp;
                    <a href="#" data-toggle="modal" data-target="#addDeptModel">添加子部门</a>&nbsp;|&nbsp;
                    <a href="javaScript:confirm('确定删除吗')">删除</a>
                </div>
            </div>
            <div class="row" style="margin-top: 5px;margin-bottom: 10px;">
                <div class="col-md-3">
                    <small>成员5个</small>
                    <small>，禁用8个</small>
                </div>
                <%-- 顶部分页区 --%>
                <div class="col-md-9">
                    <p class="text-right">
                        <a href="#"><small>上一页</small></a>
                        <small>1 / 3</small>
                        <a href="#"><small>下一页</small></a>
                    </p>
                </div>
            </div>

            <%-- 操作导航栏  --%>
            <div class="row" style="background-color: #efefef;border-top: 1px solid #cccccc;border-bottom: 1px solid #cccccc;max-height: 40px;margin: 0 2px;">
                <div class="col-md-11">

                    <%--<input type="checkbox" class="checkbox" name="">--%>
                    <ul class="nav nav-pills">
                        <li role="presentation">
                            <div class="checkbox">
                                <label><input type="checkbox" value="" title="选中/取消选中" /></label>
                            </div>
                        </li>
                        <li role="presentation" class="dropdown">
                            <button type="button" class="btn-xs btn-default" role="button" data-toggle="dropdown" aria-expanded="false" style="margin: 8px 5px;">
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
                            <th></th>
                            <th>性别</th>
                            <th>员工名</th>
                            <th>邮箱</th>
                            <th>电话</th>
                            <th>生日</th>
                            <th>岗位</th>
                        </tr>
                        </thead>
                        <tbody>
                        <tr ondblclick="window.location.href='upd_employee.html'" style="cursor: pointer">
                            <td>
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="chk1">&nbsp;
                                </label>
                            </td>
                            <td>
                                <%-- 女性用 orange 色，男性用 blue --%>
                                <span class="glyphicon glyphicon-user" style="color:blue"></span>
                            </td>
                            <td>张三丰</td>
                            <td>zhangsf@163.com</td>
                            <td>18673469087</td>
                            <td>1989-12-12</td>
                            <td>JAVA讲师</td>
                        </tr>
                        <tr ondblclick="window.location.href='upd_employee.html'" style="cursor: pointer">
                            <td>
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="chk2">&nbsp;
                                </label>
                            </td>
                            <td>
                                <%-- 女性用 orange 色，男性用 blue --%>
                                <span class="glyphicon glyphicon-user" style="color:orange"></span>
                            </td>
                            <td>周芷若</td>
                            <td>zhangsf@163.com</td>
                            <td>18673469087</td>
                            <td>1989-12-12</td>
                            <td>班主任</td>
                        </tr>
                        <tr ondblclick="window.location.href='upd_employee.html'" style="cursor: pointer">
                            <td>
                                <label class="checkbox-inline">
                                    <input type="checkbox" id="chk3">&nbsp;
                                </label>
                            </td>
                            <td>
                                <%-- 女性用 orange 色，男性用 blue --%>
                                <span class="glyphicon glyphicon-user" style="color:blue"></span>
                            </td>
                            <td>张无进</td>
                            <td>zhangsf@163.com</td>
                            <td>18673469087</td>
                            <td>1989-12-12</td>
                            <td>UI讲师</td>
                        </tr>
                        </tbody>
                    </table>
                </div>
            </div><%-- end 员工列表区 --%>

            <%-- 底部分页区 --%>
            <div class="row">
                <div class="col-md-12">
                    <p class="text-right">
                        <a href="#"><small>上一页</small></a>
                        <small>1 / 3</small>
                        <a href="#"><small>下一页</small></a>
                    </p>
                </div>
            </div>
        </div><%-- end of 右边列 --%>
    </div><%-- end row --%>
</div><%-- end container --%>
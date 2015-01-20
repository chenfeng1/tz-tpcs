<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
   <!-- 其它内容 -->
<div class="container">

    <!-- 查询区 -->
    <form class="form-horizontal" role="form" action="#">
        <div class="form-group">
            <label for="cname" class="col-md-1 control-label">班级名：</label>

            <div class="col-md-2">
                <input type="text" id="cname" class="form-control" placeholder="查询班级名"/>
            </div>
            <label for="min_nums" class="col-md-2 control-label">开班人数：</label>

            <div class="col-md-2">
                <input type="text" id="min_nums" class="form-control" placeholder="最少人数"/>
            </div>
            <label for="max_nums" class="col-md-1 control-label">至：</label>

            <div class="col-md-2">
                <input type="text" id="max_nums" class="form-control" placeholder="最多人数"/>
            </div>
            <div class="col-md-2">
                <button type="submit" class="btn btn-primary">
                    <span class="glyphicon glyphicon-search"></span>
                </button>
            </div>
        </div>
    </form>
    <hr/>

    <!-- 内容区 -->
    <div class="row">
        <div class="col-md-1">
            <button type="button" class="btn btn-primary" onclick="javaScript:window.location.href='${pageContext.request.contextPath}/clazz/add'">
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
                <tr>
                    <td>1</td>
                    <td>JSD1410</td>
                    <td>第一教室</td>
                    <td>2014/10/28</td>
                    <td>32人</td>
                    <td>孙伟</td>
                    <td>2014年10月16号</td>
                    <td>孙毅</td>
                    <td>
                        <a href="#" class="label label-default" data-toggle="modal" data-target="#myClassModal"><span class="glyphicon glyphicon-refresh"></span></a>&nbsp;
                        <a href="#" class="label label-danger"><span class="glyphicon glyphicon-trash"></span></a>&nbsp;
                    </td>
                </tr>
                <tr>
                    <td>2</td>
                    <td>JSD1411</td>
                    <td>第四教室</td>
                    <td>2014/11/30</td>
                    <td>37人</td>
                    <td>程静</td>
                    <td>2014年11月21号</td>
                    <td>叶加飞</td>
                    <td>
                        <a href="#" class="label label-default" data-toggle="modal" data-target="#myClassModal"><span class="glyphicon glyphicon-refresh"></span></a>&nbsp;
                        <a href="#" class="label label-danger"><span class="glyphicon glyphicon-trash"></span></a>&nbsp;
                    </td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>JSD1412</td>
                    <td>第三教室</td>
                    <td>2014/12/31</td>
                    <td>43人</td>
                    <td>周萍</td>
                    <td>2014年12月21号</td>
                    <td>孙毅</td>
                    <td>
                        <a href="#" class="label label-default" data-toggle="modal" data-target="#myClassModal"><span class="glyphicon glyphicon-refresh"></span></a>&nbsp;
                        <a href="#" class="label label-danger"><span class="glyphicon glyphicon-trash"></span></a>&nbsp;
                    </td>
                </tr>
                <tr>
                    <td>3</td>
                    <td>JSD1412</td>
                    <td>第三教室</td>
                    <td>2014/12/31</td>
                    <td>43人</td>
                    <td>周萍</td>
                    <td>2014年12月21号</td>
                    <td>孙毅</td>
                    <td>
                        <a href="#" class="label label-default" data-toggle="modal" data-target="#myClassModal"><span class="glyphicon glyphicon-refresh"></span></a>&nbsp;
                        <a href="#" class="label label-danger"><span class="glyphicon glyphicon-trash"></span></a>&nbsp;
                    </td>
                </tr>
                </tbody>
            </table>
        </div>
    </div>

    <!-- 分页模块 -->
    <div class="pull-right">
        <ul class="pagination">
            <li><a href="#">&laquo;</a></li>
            <li><a href="#">1</a></li>
            <li><a href="#">2</a></li>
            <li><a href="#">3</a></li>
            <li><a href="#">4</a></li>
            <li><a href="#">&raquo;</a></li>
        </ul>
    </div>
</div>
  
</html>

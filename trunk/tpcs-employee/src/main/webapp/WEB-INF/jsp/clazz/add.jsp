<%@ page pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
    <form class="form-horizontal" role="form" action="${path}/clazz/save" method="post">
        <div class="form-group">
            <label for="classname" class="col-md-offset-1 col-md-1 control-label">班级名</label>
            <div class="col-md-3">
                <input type="text" id="classname" class="form-control" placeholder="班级的名称" name="name" value="${clazz.name }"/>
            </div>
            <div class="col-md-7" id="classname_id">
            <label class="control-label alert-danger" id="labelClassName">
            <form:errors path="clazzForm.name"></form:errors>
            </label>
            </div>
        </div>
        <div class="form-group">
            <label for="classroom" class="col-md-offset-1 col-md-1 control-label">所在教室</label>
            <div class="col-md-3">
                <input type="text" id="classroom" class="form-control" placeholder="教室的名称" name="room" value="${clazz.room }"/>
            </div>
            <div class="col-md-7" id="classroom_id">
            <label class="control-label alert-danger" id="labelClassRoom">
            <form:errors path="clazzForm.room" ></form:errors>
            </label>
            </div>
        </div>
        <div class="form-group">
            <label for="classname" class="col-md-offset-1 col-md-1 control-label">开班日期</label>
            <div class="col-md-2">
                <input type="date" id="open_date" class="form-control" placeholder="2015-01-27" name="open" value="${clazz.open }"/>
            </div>
            <div class="col-md-8" id="open_date_id">
            <label class="control-label alert-danger" id="labelClassOpen">
            <form:errors path="clazzForm.open" ></form:errors>
            </label>
            </div>
        </div>
        <div class="form-group">
            <label for="classname" class="col-md-offset-1 col-md-1 control-label">开班人数</label>
            <div class="col-md-2">
                <input type="number" id="class_students" class="form-control" placeholder="30" name="count" value="${clazz.count }"/>
            </div>
            <div class="col-md-8" id="class_students_id">
            <label class="control-label alert-danger" id="labelClassCount">
            <form:errors path="clazzForm.count" ></form:errors>
            </label>
            </div>
        </div>
        <div class="form-group">
            <label for="class_adviser" class="col-md-offset-1 col-md-1 control-label">班主任</label>
            <div class="col-md-3">
                <input type="text" id="class_adviser" class="form-control" placeholder="班主任名" name="advisor" value="${clazz.advisor }"/>
            </div>
            <div class="col-md-7" id="class_adviser_id">
            <label class="control-label alert-danger" id="labelClassAdvisor">
            <form:errors path="clazzForm.advisor" ></form:errors>
            </label>
            </div>
        </div>

        <div class="form-group">
            <label for="trainer_date" class="col-md-offset-1 col-md-1 control-label">训练营日</label>
            <div class="col-md-2">
                <input type="date" id="trainer_date" class="form-control" placeholder="2015-01-18" name="trainingDate" value="${clazz.trainingDate }"/>
            </div>
            <div class="col-md-8" id="trainer_date_id">
            <label class="control-label alert-danger" id="labelClassTraining">
            <form:errors path="clazzForm.trainingDate" ></form:errors>
            </label>
            </div>
        </div>

        <div class="form-group">
            <label for="teachername" class="col-md-offset-1 col-md-1 control-label">讲师名</label>
            <div class="col-md-3">
                <input type="text" id="teachername" class="form-control" placeholder="讲师名" name="lecturer" value="${clazz.lecturer }"/>
            </div>
            <div class="col-md-7" id="teachername_id">
            <label class="control-label alert-danger" id="labelClassLecturer">
            <form:errors path="clazzForm.lecturer" ></form:errors>
            </label>
            </div>
        </div>

        <div class="col-md-offset-2">
            <button type="submit" class="btn btn-primary">保存</button>&nbsp;
            <button type="reset" class="btn btn-primary">重填</button>
        </div>
    </form>

</div>

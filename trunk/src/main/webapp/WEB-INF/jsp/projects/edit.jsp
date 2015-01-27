<%@ page pageEncoding="utf-8" %>

<div class="container">
    <form class="form-horizontal" role="form" action="${path}/projects/add" method="post">
        <div class="form-group">
            <label for="name" class="col-md-1 control-label">项目名称<span style="color: red">*</span></label>
            <div class="col-md-4">
                <input type="text" id="name" class="form-control" placeholder="项目名称"/>
            </div>
            <label for="code" class="col-md-1 control-label">项目代码<span style="color: red">*</span></label>
            <div class="col-md-2">
                <input type="text" id="code" class="form-control" placeholder="项目代码"/>
            </div>
        </div>
        <div class="form-group">
            <label for="desc" class="col-md-1 control-label">项目描述<span style="color: red">*</span></label>
            <div class="col-md-7">
                <textarea rows="12" id="desc" class="form-control" placeholder="项目描述"></textarea>
            </div>
        </div>

        <div class="form-group" style="padding-left: 10px;">
            <label for="inputSpecification" class="control-label">上传项目规格说明书&nbsp;</label>
            <input type="file" id="inputSpecification" accept="application/pdf">
            <p class="help-block">项目需求规格说明书，支持pdf格式.</p>
        </div>

        <div class="form-group" style="padding-left: 10px;">
            <label for="snapshot" class="control-label">上传项目截图&nbsp;</label>
            <input type="file" id="snapshot" accept="image/jpeg,image/gif,image/png">
            <p class="help-block">项目截图，支持png,jpg,gif格式.</p>
        </div>

        <div class="col-md-offset-5 col-md-2">
            <button type="submit" class="btn btn-primary">保存</button>&nbsp;
            <button type="reset" class="btn btn-primary">重填</button>
        </div>
    </form>
</div>

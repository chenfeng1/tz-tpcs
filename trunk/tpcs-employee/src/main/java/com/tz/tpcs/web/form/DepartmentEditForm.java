package com.tz.tpcs.web.form;

import com.tz.tpcs.service.DepartmentService;
import com.tz.tpcs.web.validator.FieldUnique;
import com.tz.tpcs.web.validator.StepA;
import com.tz.tpcs.web.validator.StepB;
import com.tz.tpcs.web.validator.StepD;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * 部门 新增 表单封装类
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/9 10:08
 */
@FieldUnique(field = "name",
            service= DepartmentService.class,
            groups = StepB.class,
            message = "{department.name.already.exist}")
public class DepartmentEditForm implements FieldUniqueFormSupport{

    @NotBlank(message = "{department.id.blank}", groups = StepD.class)
    private String id; //ID
    @NotBlank(message = "{department.name.blank}")
    private String name; //部门名称
    @NotBlank(message = "{department.parent.id.blank}", groups = StepA.class)
    private String parentId; //父部门id

    /**
     * 空参构造
     */
    public DepartmentEditForm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    /**
     * 校验排序 标记接口 Add
     */
    @GroupSequence({Default.class, StepA.class, StepB.class})
    public interface Add {}
    /**
     * 校验排序 标记接口 Update
     */
    @GroupSequence({Default.class, StepB.class})
    public interface Update {}
}

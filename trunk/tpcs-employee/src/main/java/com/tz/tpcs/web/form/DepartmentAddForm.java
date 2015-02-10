package com.tz.tpcs.web.form;

import com.tz.tpcs.service.DepartmentService;
import com.tz.tpcs.web.validator.FieldUnique;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.GroupSequence;
import javax.validation.groups.Default;

/**
 * 部门 新增 表单封装类
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/9 10:08
 */
public class DepartmentAddForm {

    @NotBlank(message = "{department.name.blank}")
    @FieldUnique(field = "name", service= DepartmentService.class,
            groups = Extends.class, message = "{department.name.already.exist}")
    private String name; //部门名称
//    @NotNull
    private String parentId; //父部门id

    /**
     * 空参构造
     */
    public DepartmentAddForm() {
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
     * 扩展校验 标记接口
     */
    public interface Extends {}

    /**
     * 校验排序 标记接口
     */
    @GroupSequence({Default.class, Extends.class})
    public interface All {}
}

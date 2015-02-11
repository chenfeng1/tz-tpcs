//package com.tz.tpcs.web.form;
//
//import org.hibernate.validator.constraints.NotBlank;
//
//import javax.validation.GroupSequence;
//import javax.validation.groups.Default;
//
///**
// * 部门 更新 表单封装类
// * @author Hu Jing Ling
// * @version 1.0
// * @since 2015/2/11 10:52
// */
//public class DepartmentUpdateForm implements FieldUniqueFormSupport {
//
//    @NotBlank(message = "{department.id.blank}")
//    private String id; //ID
//    @NotBlank(message = "{department.name.blank}")
//    private String name; //部门名称
//
//    /**
//     * 空参构造
//     */
//    public DepartmentUpdateForm() {
//    }
//
//    public String getId() {
//        return id;
//    }
//
//    public void setId(String id) {
//        this.id = id;
//    }
//
//    public String getName() {
//        return name;
//    }
//
//    public void setName(String name) {
//        this.name = name;
//    }
//
//    /**
//     * 扩展校验 标记接口
//     */
//    public interface Extends {}
//
//    /**
//     * 校验排序 标记接口
//     */
//    @GroupSequence({Default.class, Extends.class})
//    public interface All {}
//}

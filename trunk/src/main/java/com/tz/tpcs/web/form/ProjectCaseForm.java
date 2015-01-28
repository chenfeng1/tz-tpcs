package com.tz.tpcs.web.form;

import org.hibernate.validator.constraints.NotBlank;

/**
 * ProjectCase 表单封装类
 * 用于对参数的封装和使用JSR-303规范
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/28 11:44
 */
public class ProjectCaseForm {

    @NotBlank(message = "{projectCase.name.blank}")
    private String name; //项目名
    @NotBlank(message = "{projectCase.code.blank}")
    private String code; //代码
    @NotBlank(message = "{projectCase.desc.blank}")
    private String desc; //描述

    /** 空参构造 */
    public ProjectCaseForm() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "ProjectCaseForm{" +
                "name='" + name + '\'' +
                ", code='" + code + '\'' +
                ", desc='" + desc + '\'' +
                '}';
    }
}

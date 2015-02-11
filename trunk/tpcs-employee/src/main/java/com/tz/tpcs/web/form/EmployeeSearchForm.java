package com.tz.tpcs.web.form;

/**
 * 员工列表分页_+查询 表单封装类
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/9 10:08
 */
public class EmployeeSearchForm {

    private String deptId; //部门ID
    private String realname; //真实姓名
    private int pageNumber = 1; //页码

    /** 空参构造 */
    public EmployeeSearchForm() {
    }

    public String getDeptId() {
        return deptId;
    }

    public void setDeptId(String deptId) {
        this.deptId = deptId;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public int getPageNumber() {
        return pageNumber;
    }

    public void setPageNumber(int pageNumber) {
        this.pageNumber = pageNumber;
    }
}

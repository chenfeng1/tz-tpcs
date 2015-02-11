package com.tz.tpcs.web.form;

import com.tz.tpcs.entity.Gender;
import com.tz.tpcs.service.EmployeeService;
import com.tz.tpcs.web.validator.FieldUnique;
import com.tz.tpcs.web.validator.StepA;
import com.tz.tpcs.web.validator.StepB;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.ScriptAssert;

import javax.validation.GroupSequence;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;
import java.util.Date;

/**
 * 员工 新增/更新 表单封装类
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/9 10:08
 */
@ScriptAssert(lang = "javascript",
            script = "_this.password.equals(_this.passwordConfirm)",
            message = "{password.not.match}", groups = StepA.class)
@FieldUnique(fields = {"mobilePhone", "number"},
            service= EmployeeService.class,
            groups = StepB.class,
            messagePrefix = "employee")
public class EmployeeEditForm implements FieldUniqueFormSupport{

    @NotBlank(message = "{id.not.blank}", groups = StepB.class)
    private String id; //ID
    @NotBlank(message = "{version.not.blank}", groups = StepB.class)
    private Integer version; //版本锁

//    @NotBlank(message = "{employee.realname.blank}", groups = Default.class)
    @NotBlank(message = "{employee.realname.blank}")
    private String realname; //姓名
    @NotNull(message = "{employee.gender.blank}")
    private Gender gender; //性别
    @NotBlank(message = "{employee.job.blank}")
    private String job; //岗位
    @NotBlank(message = "{employee.mobilePhone.blank}")
    private String mobilePhone; //移动电话

    private Date birthDate; //生日

    @Email(message = "email.invalid")
    private String email; //邮箱地址
    private String remark; //备注

    @NotBlank(message = "{employee.number.blank}")
    private String number; //员工号(登录号)
    @NotBlank(message = "{employee.password.blank}", groups = StepA.class)
    private String password; //密码
    @NotBlank(message = "{employee.passwordConfirm.blank}", groups = StepA.class)
    private String passwordConfirm; //确认密码

    private boolean changePassword; //登录成功后，需要修改密码

    @NotBlank(message = "{employee.departmentId.blank}")
    private String departmentId; //部门ID

    /** 空参构造 */
    public EmployeeEditForm() {
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    public String getMobilePhone() {
        return mobilePhone;
    }

    public void setMobilePhone(String mobilePhone) {
        this.mobilePhone = mobilePhone;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPasswordConfirm() {
        return passwordConfirm;
    }

    public void setPasswordConfirm(String passwordConfirm) {
        this.passwordConfirm = passwordConfirm;
    }

    public boolean isChangePassword() {
        return changePassword;
    }

    public void setChangePassword(boolean changePassword) {
        this.changePassword = changePassword;
    }

    public String getDepartmentId() {
        return departmentId;
    }

    public void setDepartmentId(String departmentId) {
        this.departmentId = departmentId;
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


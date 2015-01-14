package com.tz.tpcs.entity;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * 教职员工 实体类
 *
 * @author 胡荆陵
 * @version 1.0
 * @since 2015-01-14
 */
@Entity
@Table(name = "emp_employee")
public class Employee extends BaseEntity implements UserDetails {

    private String number; //员工号(登录号)
    private String realname; //姓名
    private String image; //头像
    private String password; //密码
    private String job; //岗位
    private String gender; //性别
    private Date birthDate; //生日
    private String email; //邮箱地址
    private String phone; //电话
    private String remark; //备注
    private Set<Role> roles; //角色集合

    //add by Hu Jing Ling: for spring security framework usage
    private boolean accountNonExpired;// 账号未过期-true
    private boolean accountNonLocked;// 账号未锁定-true
    private boolean credentialsNonExpired;//账号未过期-true
    private boolean enabled;//账号已启用-true
    private int loginFailureCount;// 连续登录失败的次数
    private Date lockedDate;// 账号锁定日期
    private Date loginDate;// 最后登录日期
    private String loginIp;// 最后登录IP
    private Set<GrantedAuthority> authorities;// 角色信息

    public Employee() {
    }

    @Column(name = "emp_no",updatable = false, nullable = false, unique = true) //modify by hu jingling
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "emp_real_name")
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Column(name = "emp_img")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Column(name = "emp_password",nullable = false)
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "emp_job")
    public String getJob() {
        return job;
    }

    public void setJob(String job) {
        this.job = job;
    }

    @Column(name = "emp_gender")
    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Column(name = "emp_birth_date")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "emp_email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "emp_phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "emp_remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ManyToMany
    //定义中间表名，字段名
    @JoinTable(name="sys_emp_to_role",
            joinColumns={@JoinColumn(name="emp_id")},
            inverseJoinColumns={@JoinColumn(name="role_id")})
    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }

    /***********
     * add by yejf at 2013/5/10 19:28
     * 添加角色到用户中
     * @param role
     */
    public void addRole(Role role){
        if(roles == null){
            roles = new HashSet<Role>();
        }
        roles.add(role);
    }

    @Override
    @Transient
    public Set<GrantedAuthority> getAuthorities() {
        return authorities;
    }

    public void setAuthorities(Set<GrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    @Override
    @Transient
    public String getUsername() {
        return this.getNumber();
    }

    @Override
    public boolean isAccountNonExpired() {
        return accountNonExpired;
    }

    public void setAccountNonExpired(boolean accountNonExpired) {
        this.accountNonExpired = accountNonExpired;
    }

    @Override
    public boolean isAccountNonLocked() {
        return accountNonLocked;
    }

    public void setAccountNonLocked(boolean accountNonLocked) {
        this.accountNonLocked = accountNonLocked;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return credentialsNonExpired;
    }

    public void setCredentialsNonExpired(boolean credentialsNonExpired) {
        this.credentialsNonExpired = credentialsNonExpired;
    }

    @Override
    public boolean isEnabled() {
        return enabled;
    }

    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public int getLoginFailureCount() {
        return loginFailureCount;
    }

    public void setLoginFailureCount(int loginFailureCount) {
        this.loginFailureCount = loginFailureCount;
    }

    public Date getLockedDate() {
        return lockedDate;
    }

    public void setLockedDate(Date lockedDate) {
        this.lockedDate = lockedDate;
    }

    public Date getLoginDate() {
        return loginDate;
    }

    public void setLoginDate(Date loginDate) {
        this.loginDate = loginDate;
    }

    public String getLoginIp() {
        return loginIp;
    }

    public void setLoginIp(String loginIp) {
        this.loginIp = loginIp;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "number='" + number + '\'' +
                ", realname='" + realname + '\'' +
                ", image='" + image + '\'' +
                ", password='" + password + '\'' +
                ", job='" + job + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", remark='" + remark + '\'' +
                ", roles=" + roles +
                ", accountNonExpired=" + accountNonExpired +
                ", accountNonLocked=" + accountNonLocked +
                ", credentialsNonExpired=" + credentialsNonExpired +
                ", enabled=" + enabled +
                ", loginFailureCount=" + loginFailureCount +
                ", lockedDate=" + lockedDate +
                ", loginDate=" + loginDate +
                ", loginIp='" + loginIp + '\'' +
                ", authorities=" + authorities +
                '}';
    }
}
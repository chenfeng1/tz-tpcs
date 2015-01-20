package com.tz.tpcs.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.util.Date;

/**
 * 学员 实体类
 *
 * @author 胡荆陵
 * @version 1.0
 * @since 2015-01-14
 */
@Entity
@Table(name = "stu_student")
public class Student extends BaseEntity{

    private String number; //学号（登录）
    private String realname; //姓名
    private String password; //密码
    private String image; //头像
    private String gender; //性别
    private Date birthDate; //生日
    private String email; //邮箱地址
    private String phone; //电话
    private String bakPhone; //备用电话
    private String identityCard; //身份证
    private String province; //省份
    private String city; //城市
    private String currentLoc; //当前所在地
    private String workLoc; //工作地
    private String school; //院校
    private String major; //专业
    private String degree; //学历
    private Date graduationDate; //毕业时间
    private int workingYears; //工作年限
    private double paid; //已交费用
    private String feeType; //学费类型
    private String source; //渠道
    private String emergencyContact; //紧急联系人
    private String emergencyPhone; //紧急联系电话
    private boolean needDesign; //是否需要毕设
    private Date designDate; //毕设时间
    //add by yejf
    private double initialScore = 100;      //初始分值，为100分
    private double currentScore;      //当前分值
    private Clazz clazz;  //所在班级
    private int status = 1; //状态，　１表示正常[默认值]，０表示禁用
    private String remark;  //备注
    private String qq; //qq号码

    public Student() {
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ManyToOne
    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    public double getInitialScore() {
        return initialScore;
    }

    public void setInitialScore(double initialScore) {
        this.initialScore = initialScore;
    }

    public double getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(double currentScore) {
        this.currentScore = currentScore;
    }

    @Column(name = "s_num")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getBakPhone() {
        return bakPhone;
    }

    public void setBakPhone(String bakPhone) {
        this.bakPhone = bakPhone;
    }

    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCurrentLoc() {
        return currentLoc;
    }

    public void setCurrentLoc(String currentLoc) {
        this.currentLoc = currentLoc;
    }

    public String getWorkLoc() {
        return workLoc;
    }

    public void setWorkLoc(String workLoc) {
        this.workLoc = workLoc;
    }

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    public String getDegree() {
        return degree;
    }

    public void setDegree(String degree) {
        this.degree = degree;
    }

    public Date getGraduationDate() {
        return graduationDate;
    }

    public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    public int getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(int workingYears) {
        this.workingYears = workingYears;
    }

    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    public String getFeeType() {
        return feeType;
    }

    public void setFeeType(String feeType) {
        this.feeType = feeType;
    }

    @Column(name = "s_source")
    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    public boolean isNeedDesign() {
        return needDesign;
    }

    public void setNeedDesign(boolean needDesign) {
        this.needDesign = needDesign;
    }

    public Date getDesignDate() {
        return designDate;
    }

    public void setDesignDate(Date designDate) {
        this.designDate = designDate;
    }

    @Override
    public String toString() {
        return "Student{" +
                "number='" + number + '\'' +
                ", password='" + password + '\'' +
                ", realname='" + realname + '\'' +
                ", image='" + image + '\'' +
                ", gender='" + gender + '\'' +
                ", birthDate=" + birthDate +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", bakPhone='" + bakPhone + '\'' +
                ", identityCard='" + identityCard + '\'' +
                ", province='" + province + '\'' +
                ", city='" + city + '\'' +
                ", currentLoc='" + currentLoc + '\'' +
                ", workLoc='" + workLoc + '\'' +
                ", school='" + school + '\'' +
                ", major='" + major + '\'' +
                ", degree='" + degree + '\'' +
                ", graduationDate=" + graduationDate +
                ", workingYears=" + workingYears +
                ", paid=" + paid +
                ", feeType='" + feeType + '\'' +
                ", source='" + source + '\'' +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", emergencyPhone='" + emergencyPhone + '\'' +
                ", needDesign=" + needDesign +
                ", designDate=" + designDate +
                '}';
    }
}
package com.tz.tpcs.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 学员 实体类
 * @author 胡荆陵
 * @version 1.0
 * @since 2015-01-14
 *
 * 2015-01-23 add by 胡荆陵:
 * 考虑到后期市场部的使用需求，
 * 追加原 CRM 系统中，Customer 实体类的主要属性
 */
@Entity
@Table(name = "stu_student")
public class Student extends BaseEntity {

    public static final double MAX_SCORE = 100.0D;

	private static final long serialVersionUID = -572371066049960967L;
	
	private String number; //学号（登录）
    private String realname; //姓名
    private String password; //密码 ，用于后期的学员登录页面
    private String image; //头像
    private Gender gender; //性别
    private Date birthDate; //生日
    private String email; //邮箱地址
    private String phone; //电话
    private String bakPhone; //备用电话
    private String identityCard; //身份证号
    private String province; //省份
    private String city; //城市
    private String currentLoc; //当前所在地
    private String workLoc; //期望工作地
    private String school; //院校
    private String major; //专业
    private Degree degree; //学历
    private Date graduationDate; //毕业时间
    private int workingYears; //已有工作年限
    private double paid; //已交费用
    private LoanStatus loanStatus; //贷款状态/还款状态
    //add by yejf
    private Date loanDate; // 贷款合同签约时期[*]

    private Source source; //渠道来源
    private String emergencyContact; //紧急联系人
    private String emergencyPhone; //紧急联系电话
    private boolean needDesign; //是否需要毕设
    private Date designDate; //毕设时间
    //add by yejf
    private double initialScore = MAX_SCORE;      //初始分值，为100分
    private double currentScore;      //当前分值
    private Clazz clazz;  //所在班级
    private String remark;  //备注
    private String qq; //qq号码

    /**
     * 贷款状态枚举类
     * @author guan
     *	
     */
    public enum LoanStatus{
    	LOAN,//已放款
    	UNLOAN,//未放款
    	CASH,//现金
    	CREDIT;//信用卡

        /*****
         * 把中文字符串转换成枚举常量 [add by yejf, 2015/2/11]
         * @param value
         * @return 一个枚举常量
         */
        public static LoanStatus convert(String value){
            LoanStatus instance = null;
            switch (value){
                case "已放款":
                    instance = LOAN;
                    break;
                case "未放款":
                    instance = UNLOAN;
                    break;
                case "现金":
                    instance = CASH;
                    break;
                case "信用卡":
                    instance = CREDIT;
                    break;
                //如果不匹配上面的值，则以 UNLOAN 为结果
                default:
                    instance = UNLOAN;
                    break;
            }
            return instance;
        }
    }
    
    //2015-01-23 add by 胡荆陵 start
    /**
     * 状态
     */
    public enum Status {
        UNSIGNED, //未签约
        SEA, //公海
        UNSIGNED_YICHUSHI, //已初试
        UNSIGNED_YIFUSHI, //已复试
        SIGNED, //服务中签约
        SIGNED_LEAVE //签约后离开
    }

    /**
     * 学员来源渠道
     * 修订于 2015/2/11 16:10 [by yejf]
     */
    public enum Source {
        MARKET,//人才市场
        COLLEGE,//院校
        FORWARD,//转介绍
        NETWORK, //网络
        OTHER; //其它

        /*****
         * 把中文字符串转换成枚举常量
         * @param value
         * @return 一个枚举常量
         */
        public static Source convert(String value){
            Source instance = null;
            switch (value){
                case "人才市场":
                    instance = MARKET;
                    break;
                case "院校":
                    instance = COLLEGE;
                    break;
                case "转介绍":
                    instance = FORWARD;
                    break;
                case "网络":
                    instance = NETWORK;
                    break;
                case "其他":
                    instance = OTHER;
                    break;
                //如果上面的不匹配，则以 OTHER为值
                default:
                    instance = OTHER;
                    break;
            }
            return instance;
        }
    }

    /**
     * 等级
     */
    public enum Level {
        ONE,    //★
        TWO,    //★★
        THREE,  //★★★
        FOUR,  //★★★★
        FIVE,  //★★★★★
    }

    private String address; //联系地址1
    private String address2; //联系地址2
    private Level level; //成熟度 (用于未签约的学员)
    private Employee owner; // 学员所属的服务专员 (这个学员是由市场部哪个人招的)
    private Status status; //状态

    //目前不需要，后期可能需要的部分
//    private Set<VisitRecord> visitRecodeSet;//拜访记录

    //2015-01-23 add by 胡荆陵 end

    /** 空参构造 */
    public Student() {
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "stu_status", nullable = false)
    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Column(name = "stu_qq")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Column(name = "stu_remark")
    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @ManyToOne
    @JoinColumn(name = "clazz_id")
    public Clazz getClazz() {
        return clazz;
    }

    public void setClazz(Clazz clazz) {
        this.clazz = clazz;
    }

    @Column(name = "stu_initial_score")
    public double getInitialScore() {
        return initialScore;
    }

    public void setInitialScore(double initialScore) {
        this.initialScore = initialScore;
    }

    @Column(name = "stu_current_score")
    public double getCurrentScore() {
        return currentScore;
    }

    public void setCurrentScore(double currentScore) {
        this.currentScore = currentScore;
    }

    @Column(name = "stu_number")
    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Column(name = "stu_password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Column(name = "stu_realname", nullable = false)
    public String getRealname() {
        return realname;
    }

    public void setRealname(String realname) {
        this.realname = realname;
    }

    @Column(name = "stu_image")
    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "stu_gender")
    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "stu_birth_date")
    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    @Column(name = "stu_email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Column(name = "stu_phone")
    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    @Column(name = "stu_bak_phone")
    public String getBakPhone() {
        return bakPhone;
    }

    public void setBakPhone(String bakPhone) {
        this.bakPhone = bakPhone;
    }

    @Column(name = "stu_id_card")
    public String getIdentityCard() {
        return identityCard;
    }

    public void setIdentityCard(String identityCard) {
        this.identityCard = identityCard;
    }

    @Column(name = "stu_province")
    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    @Column(name = "stu_city")
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Column(name = "stu_current_loc")
    public String getCurrentLoc() {
        return currentLoc;
    }

    public void setCurrentLoc(String currentLoc) {
        this.currentLoc = currentLoc;
    }

    @Column(name = "stu_work_loc")
    public String getWorkLoc() {
        return workLoc;
    }

    public void setWorkLoc(String workLoc) {
        this.workLoc = workLoc;
    }

    @Column(name = "stu_school", nullable = false)
    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    @Column(name = "stu_major", nullable = false)
    public String getMajor() {
        return major;
    }

    public void setMajor(String major) {
        this.major = major;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "stu_degree")
    public Degree getDegree() {
		return degree;
	}

	public void setDegree(Degree degree) {
		this.degree = degree;
	}

    @Temporal(TemporalType.DATE)
    @Column(name = "stu_graduation_date")
    public Date getGraduationDate() {
        return graduationDate;
    }

	public void setGraduationDate(Date graduationDate) {
        this.graduationDate = graduationDate;
    }

    @Column(name = "stu_working_years")
    public int getWorkingYears() {
        return workingYears;
    }

    public void setWorkingYears(int workingYears) {
        this.workingYears = workingYears;
    }

    @Column(name = "stu_paid")
    public double getPaid() {
        return paid;
    }

    public void setPaid(double paid) {
        this.paid = paid;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "stu_loan_status")
    public LoanStatus getLoanStatus() {
        return loanStatus;
    }

    public void setLoanStatus(LoanStatus loanStatus) {
        this.loanStatus = loanStatus;
    }

    //add by yejf on 2015/02/11 15:04
    @Column(name = "stu_load_date")
    @Temporal(TemporalType.DATE)
    public Date getLoanDate() {
        return loanDate;
    }

    public void setLoanDate(Date loanDate) {
        this.loanDate = loanDate;
    }

    @Enumerated(EnumType.STRING)
    @Column(name = "stu_source")
    public Source getSource() {
        return source;
    }

    public void setSource(Source source) {
        this.source = source;
    }

    @Column(name = "stu_emergency_contact")
    public String getEmergencyContact() {
        return emergencyContact;
    }

    public void setEmergencyContact(String emergencyContact) {
        this.emergencyContact = emergencyContact;
    }

    @Column(name = "stu_emergency_phone")
    public String getEmergencyPhone() {
        return emergencyPhone;
    }

    public void setEmergencyPhone(String emergencyPhone) {
        this.emergencyPhone = emergencyPhone;
    }

    @Column(name = "stu_need_design")
    public boolean isNeedDesign() {
        return needDesign;
    }

    public void setNeedDesign(boolean needDesign) {
        this.needDesign = needDesign;
    }
    
    @Temporal(TemporalType.DATE)
    @Column(name = "stu_design_date")
    public Date getDesignDate() {
        return designDate;
    }

    public void setDesignDate(Date designDate) {
        this.designDate = designDate;
    }

    @Column(name = "stu_address")
    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    @Column(name = "stu_address2")
    public String getAddress2() {
        return address2;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    @Column(name = "stu_level")
    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Column(name = "stu_owner")
    public Employee getOwner() {
        return owner;
    }

    public void setOwner(Employee owner) {
        this.owner = owner;
    }

    @Override
    public String toString() {
        return "Student{" +
                "realname='" + realname + '\'' +
                ", gender=" + gender +
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
                ", degree=" + degree +
                ", graduationDate=" + graduationDate +
                ", workingYears=" + workingYears +
                ", loanStatus=" + loanStatus +
                ", loanDate=" + loanDate +
                ", source=" + source +
                ", emergencyContact='" + emergencyContact + '\'' +
                ", emergencyPhone='" + emergencyPhone + '\'' +
                ", needDesign=" + needDesign +
                ", designDate=" + designDate +
                ", initialScore=" + initialScore +
                ", currentScore=" + currentScore +
                ", remark='" + remark + '\'' +
                ", qq='" + qq + '\'' +
                ", status=" + status +
                '}';
    }
}

package com.tz.tpcs.web.form;

import org.hibernate.validator.constraints.NotBlank;

import com.tz.tpcs.entity.Degree;
import com.tz.tpcs.entity.Gender;
import com.tz.tpcs.entity.Student.Level;
import com.tz.tpcs.entity.Student.LoanStatus;
import com.tz.tpcs.entity.Student.Source;
import com.tz.tpcs.entity.Student.Status;

/**
 * StudentForm
 * @author CF
 *
 */
public class StudentForm {
	/**
	 * baseEntity
	 */
	private String id;
	private String version;
	/**
	 * 基本信息 （班级名未封装） 
	 */
	@NotBlank(message = "{student.realname.blank}")
	private String realname;
	private String birthDate;
	@NotBlank(message = "{student.phone.blank}")
	private String phone;
	@NotBlank(message = "{student.bakPhone.blank}")
	private String bakPhone;
	private String email;
	@NotBlank(message = "{student.school.blank}")
	private String school;
	@NotBlank(message = "{student.major.blank}")
	private String major;
	private Degree degree;
	private LoanStatus loanStatus;
	private Source source;
	private String qq;
	/**
	 * 更多信息
	 */
	private String identityCard;
	private String province;
	private String city;
	private String currentLoc;
	private String workLoc;
	private int workingYears;
	private String graduationDate;
	private boolean needDesign;
	private String designDate;
	private String emergencyContact;
	private String emergencyPhone;
	private String remark;
	private Gender gender;
	/**
	 * crm信息 服务专员未封装
	 */
	private double paid;
	private Status status;
	private String address;
	private String address2;
	private Level level;
	/**
	 * 空参构造
	 */
	public StudentForm() {
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getVersion() {
		return version;
	}
	public void setVersion(String version) {
		this.version = version;
	}
	public String getRealname() {
		return realname;
	}
	public void setRealname(String realname) {
		this.realname = realname;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
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
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
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
	public Degree getDegree() {
		return degree;
	}
	public void setDegree(Degree degree) {
		this.degree = degree;
	}
	public LoanStatus getLoanStatus() {
		return loanStatus;
	}
	public void setLoanStatus(LoanStatus loanStatus) {
		this.loanStatus = loanStatus;
	}
	public Source getSource() {
		return source;
	}
	public void setSource(Source source) {
		this.source = source;
	}
	public String getQq() {
		return qq;
	}
	public void setQq(String qq) {
		this.qq = qq;
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
	public int getWorkingYears() {
		return workingYears;
	}
	public void setWorkingYears(int workingYears) {
		this.workingYears = workingYears;
	}
	public String getGraduationDate() {
		return graduationDate;
	}
	public void setGraduationDate(String graduationDate) {
		this.graduationDate = graduationDate;
	}
	public boolean isNeedDesign() {
		return needDesign;
	}
	public void setNeedDesign(boolean needDesign) {
		this.needDesign = needDesign;
	}
	public String getDesignDate() {
		return designDate;
	}
	public void setDesignDate(String designDate) {
		this.designDate = designDate;
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
	public String getRemark() {
		return remark;
	}
	public void setRemark(String remark) {
		this.remark = remark;
	}
	public Gender getGender() {
		return gender;
	}
	public void setGender(Gender gender) {
		this.gender = gender;
	}
	public double getPaid() {
		return paid;
	}
	public void setPaid(double paid) {
		this.paid = paid;
	}
	public Status getStatus() {
		return status;
	}
	public void setStatus(Status status) {
		this.status = status;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAddress2() {
		return address2;
	}
	public void setAddress2(String address2) {
		this.address2 = address2;
	}
	public Level getLevel() {
		return level;
	}
	public void setLevel(Level level) {
		this.level = level;
	}
	
}

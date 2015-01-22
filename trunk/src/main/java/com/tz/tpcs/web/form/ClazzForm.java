package com.tz.tpcs.web.form;

import java.util.Date;

import com.tz.tpcs.entity.Clazz;

/**
 * 用来封装接受到的班级信息的参数
 * @author 管成功
 * 
 * 
 *
 */
public class ClazzForm extends Clazz{
	
	private String name; //班级名
	private String clazz_name;//所在教室
	private Date open;//开班日期
	private Integer count;//开班人数
	private Date training_date;//训练营日期
	private String lector;//讲师名
	private String advisor;//班主任
	
	public String getAdvisor() {
		return advisor;
	}
	public void setAdvisor(String advisor) {
		this.advisor = advisor;
	}
	public ClazzForm() {
		// TODO Auto-generated constructor stub
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getClazz_name() {
		return clazz_name;
	}
	public void setClazz_name(String clazz_name) {
		this.clazz_name = clazz_name;
	}
	public Date getOpen() {
		return open;
	}
	public void setOpen(Date open) {
		this.open = open;
	}
	public Integer getCount() {
		return count;
	}
	public void setCount(Integer count) {
		this.count = count;
	}
	public Date getTraining_date() {
		return training_date;
	}
	public void setTraining_date(Date training_date) {
		this.training_date = training_date;
	}
	public String getLector() {
		return lector;
	}
	public void setLector(String lector) {
		this.lector = lector;
	}
}

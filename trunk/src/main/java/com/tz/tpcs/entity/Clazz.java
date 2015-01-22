package com.tz.tpcs.entity;

import javax.persistence.*;
import java.util.Date;

/**
 * 班级 实体类
 * 
 * @author 胡荆陵
 * @version 1.0
 * @since 2015-01-14
 */
@Entity
@Table(name = "clz_clazz")
public class Clazz extends BaseEntity {

	// 课程阶段枚举
	public enum ClazzStatus {
		// TRAINING, //训练营
		// SE, //javase
		// DB, //java db
		// WEB, //javaweb
		// FRAMEWORK, //框架
		// CLOSE //毕业
		PHASE1, // 第一阶段
		PHASE2, PHASE3, PHASE4
	}

	private String name; // 班级名
	private Date open; // 开班日期
	private Date close; // 毕业时间
	private String advisor; // 班主任
	private ClazzStatus status; // 状态
	private Integer count;// 开班人数
	private Date training_date;// 训练营日期
	private String lector;// 讲师名
	private String claz_name;// 所在教室

	public Clazz() {
	}
	
	@Column
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Column
	public Date getTraining_date() {
		return training_date;
	}

	public void setTraining_date(Date training_date) {
		this.training_date = training_date;
	}

	@Column
	public String getLector() {
		return lector;
	}

	public void setLector(String lector) {
		this.lector = lector;
	}

	@Column
	public String getClaz_name() {
		return claz_name;
	}

	public void setClaz_name(String claz_name) {
		this.claz_name = claz_name;
	}

	@Column(name = "clazz_name", unique = true, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	public Date getOpen() {
		return open;
	}

	public void setOpen(Date open) {
		this.open = open;
	}

	@Temporal(TemporalType.DATE)
	public Date getClose() {
		return close;
	}

	public void setClose(Date close) {
		this.close = close;
	}

	@Column
	public String getAdvisor() {
		return advisor;
	}

	public void setAdvisor(String advisor) {
		this.advisor = advisor;
	}

	@Enumerated(EnumType.STRING)
	public ClazzStatus getStatus() {
		return status;
	}

	public void setStatus(ClazzStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Clazz{" + "name='" + name + '\'' + ", open=" + open
				+ ", close=" + close + ", advisor=" + advisor + ", status="
				+ status + '}';
	}
}
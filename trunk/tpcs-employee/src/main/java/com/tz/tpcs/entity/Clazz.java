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

	/** 课程阶段枚举 */
	public enum ClazzStatus {
		PHASE1, // 第1阶段
		PHASE2, // 第2阶段
		PHASE3, // 第3阶段
		PHASE4 // 第4阶段
	}

	private String name; // 班级名
	private Date open; // 开班日期
	private Date close; // 毕业时间
	private String advisor; // 班主任
	private ClazzStatus status; // 状态
	private Integer count;// 开班人数
	private Date trainingDate;// 训练营日期
	private String lecturer;// 讲师名
	private String room;// 所在教室

	/** 空参构造 */
	public Clazz() {
	}

	@Column(name = "clazz_count")
	public Integer getCount() {
		return count;
	}

	public void setCount(Integer count) {
		this.count = count;
	}

	@Column(name = "clazz_training_date")
	public Date getTrainingDate() {
		return trainingDate;
	}

	public void setTrainingDate(Date trainingDate) {
		this.trainingDate = trainingDate;
	}

	@Column(name = "clazz_lecturer")
	public String getLecturer() {
		return lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	@Column(name = "clazz_room")
	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	@Column(name = "clazz_name", unique = true, nullable = false)
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "clazz_open")
	public Date getOpen() {
		return open;
	}

	public void setOpen(Date open) {
		this.open = open;
	}

	@Temporal(TemporalType.DATE)
	@Column(name = "clazz_close")
	public Date getClose() {
		return close;
	}

	public void setClose(Date close) {
		this.close = close;
	}

	@Column(name = "clazz_advisor")
	public String getAdvisor() {
		return advisor;
	}

	public void setAdvisor(String advisor) {
		this.advisor = advisor;
	}

	@Enumerated(EnumType.STRING)
	@Column(name = "clazz_status")
	public ClazzStatus getStatus() {
		return status;
	}

	public void setStatus(ClazzStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Clazz{" +
				"name='" + name + '\'' +
				", open=" + open +
				", close=" + close +
				", advisor='" + advisor + '\'' +
				", status=" + status +
				", count=" + count +
				", trainingDate=" + trainingDate +
				", lecturer='" + lecturer + '\'' +
				", room='" + room + '\'' +
				'}';
	}
}

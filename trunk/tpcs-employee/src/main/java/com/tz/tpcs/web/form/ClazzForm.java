package com.tz.tpcs.web.form;

import org.hibernate.validator.constraints.NotBlank;


/**
 * 用来封装接受到的班级信息的参数
 * @author 管成功
 */
public class ClazzForm{
	private String id;
	@NotBlank(message = "{clazz.name.blank}")
	private String name; //班级名
	@NotBlank(message = "{clazz.room.blank}")
	private String room;//所在教室
	@NotBlank(message = "{clazz.open.blank}")
	private String open;//开班日期
	@NotBlank(message = "{clazz.count.blank}")
	private String count;//开班人数
	@NotBlank(message = "{clazz.advisor.blank}")
	private String advisor;//班主任
	@NotBlank(message = "{clazz.trainingDate.blank}")
	private String trainingDate;//训练营日期
	@NotBlank(message = "{clazz.lecturer.blank}")
	private String lecturer;//讲师名
	private String version;
	
	/**
	 * 空参构造
	 */
	public ClazzForm() {
		
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRoom() {
		return room;
	}

	public void setRoom(String room) {
		this.room = room;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getCount() {
		return count;
	}

	public void setCount(String count) {
		this.count = count;
	}

	public String getAdvisor() {
		return advisor;
	}

	public void setAdvisor(String advisor) {
		this.advisor = advisor;
	}

	public String getTrainingDate() {
		return trainingDate;
	}

	public void setTrainingDate(String trainingDate) {
		this.trainingDate = trainingDate;
	}

	public String getLecturer() {
		return lecturer;
	}

	public void setLecturer(String lecturer) {
		this.lecturer = lecturer;
	}

	public String getVersion() {
		return version;
	}

	public void setVersion(String version) {
		this.version = version;
	}
	
}

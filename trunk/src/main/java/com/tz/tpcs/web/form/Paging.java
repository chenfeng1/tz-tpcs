package com.tz.tpcs.web.form;

import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.entity.Student;

import java.util.List;

/**
 * 班级分页
 * 
 * @author 管成功
 * 
 * 
 */
public class Paging {
	private Integer pageSize;// 每页显示大写
	private Integer pageNow;// 当前页
	private Integer pageCount;// 总页数
	private List<Clazz> clazzs;
	private List<Student> students;

	public Integer getPageSize() {
		return pageSize;
	}

	public void setPageSize(Integer pageSize) {
		this.pageSize = pageSize;
	}

	public Integer getPageNow() {
		return pageNow;
	}

	public void setPageNow(Integer pageNow) {
		this.pageNow = pageNow;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public void setPageCount(Integer pageCount) {
		this.pageCount = pageCount;
	}

	public List<Clazz> getClazzs() {
		return clazzs;
	}

	public void setClazzs(List<Clazz> clazzs) {
		this.clazzs = clazzs;
	}

	public List<Student> getStudents() {
		return students;
	}

	public void setStudents(List<Student> students) {
		this.students = students;
	}
}

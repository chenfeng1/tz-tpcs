package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Student;
import com.tz.tpcs.web.form.Paging;

/**
 * StudentDao 自定义扩展类
 * @author guan
 * @since 2015-01-26
 *
 */
public interface StudentDaoCustom {

	/**
	 * 多条件分页查询
	 * @param clazz_name 班级名称
	 * @param realname 学员真实名称
	 * @param degree 学历
	 * @param loanStatus 贷款状态
	 * @param pageNow 当前页
	 * @author pageSize 每页显示多少条
	 */
	Paging getStudentByCondition(String clazz_name,String realname,String degree,String loanStatus,Integer pageNow,Integer pageSize);
	
	/**
	 * 更新学生信息
	 * @param student
	 */
	void update(Student student);
}

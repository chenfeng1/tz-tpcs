package com.tz.tpcs.service;

import com.tz.tpcs.entity.Student;
import com.tz.tpcs.web.form.Pager;

/**
 *student 服务层 
 *
 */
public interface StudentService {
	/**
	 * 分页+查询
	 * @param clazzname 班级名称
	 * @param realname 学生真实姓名
	 * @param degree 学历
	 * @param loanStatus 贷款状态
	 * @param pager 分页信息 含数据
	 * @return 分页信息
	 */
	Pager<Student> findByPager(String clazzname,String realname,String degree,String loanStatus,Pager<Student> pager);
	
	/**
	 * 保存 
	 * @param student
	 */
	void save(Student student);
	/**
	 * 更新
	 * @param student
	 */
	void update(Student student);
	/**
	 * 根据ID查询
	 * @param id
	 * @return
	 */
	Student getById(String id);
	/**
	 * 根据ID删除
	 * @param id
	 */
	void deleteById(String id);
	
}

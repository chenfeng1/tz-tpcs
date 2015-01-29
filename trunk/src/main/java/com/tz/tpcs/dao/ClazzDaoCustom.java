package com.tz.tpcs.dao;

import java.util.List;

import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.web.form.Paging;

/**
 * Clazz 自定义扩展类
 * 
 * @author 管成功
 * 
 */
public interface ClazzDaoCustom {

	/**
	 * 多条件分页查询
	 * 
	 * @param name
	 *            班级名称
	 * @param count1
	 *            开班min人数
	 * @param count2开班max人数
	 */
	List<Clazz> getAllByCondition(String name, Integer min, Integer max);

	/**
	 * 多条件分页查询
	 * 
	 * @param name
	 *            班级名称
	 * @param min
	 *            开班min人数
	 * @param max
	 *            开班max人数
	 * @param pageSize
	 *            每页显示大小
	 * @param pageNow
	 *            当前页
	 * @return Paging
	 * @author 管成功
	 */
	Paging getAll(String name, Integer min, Integer max, Integer pageSize,
			Integer pageNow);

	/**
	 * 根据id来删除
	 */
	void delById(String id);

	/**
	 * 根据id来修改
	 */
	void update(Clazz clazz);

	Long getRowCount(String name, Integer min, Integer max);

	/**
	 * 根据名称来查找班级名
	 */
	Clazz getByName(String name);
	
	String getByName2(String name);
}

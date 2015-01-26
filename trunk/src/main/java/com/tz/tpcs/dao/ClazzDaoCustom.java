package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.web.form.Paging;

import java.util.List;

/**
 * Clazz 自定义扩展类
 * @author 管成功
 *
 */
public interface ClazzDaoCustom {

	/**
	 * 多条件分页查询
	 * @param name 班级名称
	 * @param min 开班min人数
	 * @param max 开班max人数
	 * @return Clazz List
	 */
	List<Clazz> getAllByCondition(String name,Integer min,Integer max);
	
	/**
	 * 多条件分页查询
	 * @param name 班级名称
	 * @param min 开班min人数
	 * @param max 开班max人数
	 * @param pageSize 每页显示大小
	 * @param pageNow 当前页
	 * @return Paging
	 */
	Paging getAll(String name,Integer min,Integer max,Integer pageSize,Integer pageNow);
	
	/**
	 * 根据id来删除
	 * @param id class id
	 */
	void delById(String id);

	/**
	 * 根据id来修改
	 * @param clazz Clazz
	 */
	void update(Clazz clazz);

	/**
	 * getRowCount
	 * @param name name
	 * @param min min
	 * @param max max
	 * @return Long
	 */
	Long getRowCount(String name,Integer min,Integer max);
	
}

package com.tz.tpcs.service;

import com.tz.tpcs.entity.Clazz;

import java.util.List;

/**
 * Clazz Service 接口类
 * @author 管成功
 *
 */
public interface ClazzService {
		
	/**
	 * 新增班级
	 */
	void save(Clazz clazz);
	
	/**
	 * 分页多条件查询
	 */
	List<Clazz> selectAllByCondition(String name, Integer min, Integer max);
	
	/**
	 * 根据id来删除
	 */
	void deleteById(String id);
	
	/**
	 * 根据id来查询
	 */
	Clazz getById(String id);
}

package com.tz.tpcs.service;

import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.web.form.Pager;

/**
 * Clazz Service 接口类
 * @author 管成功
 *
 */
public interface ClazzService {
		
	/**
	 * 查询+分页功能
	 * @param name 班级名
	 * @param min 班级最少人数
	 * @param max 班级最大人数
	 * @param pager 分页信息
	 * @return 分页信息 含数据
	 */
	Pager<Clazz> findByPager(String name, Integer min, Integer max, Pager<Clazz> pager);
	
	/**
	 * 新增班级
	 */
	void save(Clazz clazz);
	
	/**
	 * 根据id来删除
	 */
	void deleteById(String id);
	
	/**
	 * 根据id来查询
	 */
	Clazz getById(String id);
	
	/**
	 * 根据name查询
	 */
	Clazz findByName(String name);
	
	/**
	 * 更新
	 */
	void update(Clazz clazz);
	
}

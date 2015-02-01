package com.tz.tpcs.dao;

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
	 * @param name 班级名称
	 * @param min 开班min人数
	 * @param max 开班max人数
	 * @param pageSize 每页显示大小
	 * @param pageNow 当前页
	 * @return Paging
	 */
	Paging getAll(String name, Integer min, Integer max, Integer pageSize,
			Integer pageNow);

}

package com.tz.tpcs.dao;

import java.util.List;
import java.util.Set;

import com.tz.tpcs.entity.Area;

/**
 * AreaDao 扩展类
 * @author guan
 *
 */
public interface AreaDaoCustom {
	/**
	 * 根据level来查
	 */
	List<Area> getAll(int level);
	
	/**
	 * 根据code来得到ID
	 */
	String getAreaId(String code);
	
	/**
	 * 根据id来查找
	 */
	List<Area> getAreaByParentId(String id);
	
	/**
	 * 根据code查找city
	 */
	String getName(String code);
}

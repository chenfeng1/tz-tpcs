package com.tz.tpcs.dao;

import org.springframework.data.repository.CrudRepository;

import com.tz.tpcs.entity.Area;
/**
 * 省市 接口类
 * @author guan
 * @since 2015-1-27
 *
 */
public interface AreaDao extends CrudRepository<Area, String>,AreaDaoCustom{

}

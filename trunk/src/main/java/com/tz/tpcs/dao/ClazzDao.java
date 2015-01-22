package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.web.form.ClazzForm;

import org.springframework.data.repository.CrudRepository;

/**
 * 班级 Dao 接口类
 */
public interface ClazzDao extends CrudRepository<Clazz,String>,ClazzDaoCustom {
	
}

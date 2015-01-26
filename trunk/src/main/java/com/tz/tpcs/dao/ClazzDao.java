package com.tz.tpcs.dao;

import java.util.Map;

import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.web.form.ClazzForm;

import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

/**
 * 班级 Dao 接口类
 */
public interface ClazzDao extends CrudRepository<Clazz,String>,ClazzDaoCustom,JpaSpecificationExecutor<Clazz>{
	
}

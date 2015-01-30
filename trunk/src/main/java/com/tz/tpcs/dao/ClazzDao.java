package com.tz.tpcs.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.tz.tpcs.entity.Clazz;

/**
 * 班级 Dao 接口类
 */

public interface ClazzDao extends CrudRepository<Clazz,String>, ClazzDaoCustom, JpaSpecificationExecutor<Clazz> {
}

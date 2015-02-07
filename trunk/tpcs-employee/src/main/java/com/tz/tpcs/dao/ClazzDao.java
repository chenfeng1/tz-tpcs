package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Clazz;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * 班级 Dao 接口类
 * @amender 胡荆陵
 */

public interface ClazzDao extends CrudRepository<Clazz,String>, JpaSpecificationExecutor<Clazz> {
	/**
	 * 根据名字查询唯一clazz
	 * @param name
	 * @return
	 */
    Clazz findByName(String name);

}

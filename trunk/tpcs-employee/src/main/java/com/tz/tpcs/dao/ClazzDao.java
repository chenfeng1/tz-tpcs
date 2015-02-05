package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Clazz;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

/**
 * 班级 Dao 接口类
 * @amender 胡荆陵
 */

public interface ClazzDao extends CrudRepository<Clazz,String>, ClazzDaoCustom, JpaSpecificationExecutor<Clazz> {

    /**
     * 根据name查询
     * @param name name
     * @return Clazz
     */
    Clazz findByName(String name);

}

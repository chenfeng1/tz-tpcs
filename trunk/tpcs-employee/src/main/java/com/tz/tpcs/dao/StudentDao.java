package com.tz.tpcs.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.CrudRepository;

import com.tz.tpcs.entity.Student;

/**
 * 学生Dao接口类
 * @author guan
 * @since 2015-1-26
 */
public interface StudentDao extends CrudRepository<Student, String>,JpaSpecificationExecutor<Student>  {

}

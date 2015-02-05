package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Department;
import org.springframework.data.repository.CrudRepository;

/**
 * 部门 Dao 接口类
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/4 18:04
 */
public interface DepartmentDao extends CrudRepository<Department, String> {
}

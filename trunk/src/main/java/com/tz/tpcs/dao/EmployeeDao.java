package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Employee;
import org.springframework.data.repository.CrudRepository;

/**
 * EmployeeDao 接口类
 */
public interface EmployeeDao extends CrudRepository<Employee, String>, EmployeeDaoCustom {

}


package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Employee;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * EmployeeDao 接口类
 */
public interface EmployeeDao extends CrudRepository<Employee, String> {
	/**
	 * 根据电话号码查询
	 * @param mobilePhone
	 * @return
	 */
	@Query("select emp from Employee as emp join fetch emp.roles where emp.mobilePhone =:mp")
	Employee findByMobilePhone(@Param("mp") String mobilePhone);
	/**
	 * 根据员工号查询
	 * @param number
	 * @return
	 */
	@Query("select emp from Employee as emp join fetch emp.roles where emp.number =:number")
	Employee findByNumber(@Param("number") String number);
	/**
	 * 根据email查询
	 * @param email
	 * @return
	 */
	@Query("select emp from Employee as emp join fetch emp.roles where emp.email =:email")
	Employee findByEmail(@Param("email") String email);
}


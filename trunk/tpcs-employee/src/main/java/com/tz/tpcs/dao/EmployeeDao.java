package com.tz.tpcs.dao;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.tz.tpcs.entity.Employee;

/**
 * EmployeeDao 接口类
 */
public interface EmployeeDao extends CrudRepository<Employee, String>, JpaSpecificationExecutor<Employee> {
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
	@Query("select emp from Employee as emp left join fetch emp.roles where emp.number =:number")
	Employee findByNumber(@Param("number") String number);
	/**
	 * 根据email查询
	 * @param email
	 * @return
	 */
	@Query("select emp from Employee as emp join fetch emp.roles where emp.email =:email")
	Employee findByEmail(@Param("email") String email);
	
	/**
     * 根据部门ID查询员工数
     * @param deptId 部门ID
     * @return 员工数
     */
    @Query("select count(emp) from Employee as emp where emp.department.id =:deptId")
    int getCountByDeptId(@Param("deptId") String deptId);
}

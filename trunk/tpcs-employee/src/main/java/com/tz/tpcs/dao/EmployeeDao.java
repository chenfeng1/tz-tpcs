package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Employee;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

/**
 * EmployeeDao 接口类
 */
public interface EmployeeDao extends CrudRepository<Employee, String>, EmployeeDaoCustom, JpaSpecificationExecutor<Employee> {

    /**
     * 根据部门ID查询员工数
     * @param deptId 部门ID
     * @return 员工数
     */
    @Query("select count(emp) from Employee as emp where emp.department.id =:deptId")
    int getCountByDeptId(@Param("deptId") String deptId);

}

package com.tz.tpcs.service;

import com.tz.tpcs.entity.Employee;

/**
 * Employee Service 接口类
 */
public interface EmployeeService {

    /**
     * 更新功能
     * @param employee
     */
    void update(Employee employee);

    /**
     * 根据 number、email、mobilePhone 查询员工
     * @param str 可能是 手机号、员工号、邮箱
     * @return 如果存在，返回员工实例；如果不存在，返回null
     */
    Employee getByPhoneNumberEmail(String str);
}

package com.tz.tpcs.service;

import com.tz.tpcs.entity.Employee;

/**
 * Employee Service 接口类
 */
public interface EmployeeService {


    /**
     * 员工登录功能
     * @param str 可能是number、email、mobilePhone
     * @param pass 密码
     * @return 员工实例
     */
    Employee login(String str, String pass);
}

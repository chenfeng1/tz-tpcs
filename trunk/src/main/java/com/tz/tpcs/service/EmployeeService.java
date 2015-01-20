package com.tz.tpcs.service;

import com.tz.tpcs.entity.Employee;

/**
 * Employee Service 接口类
 */
public interface EmployeeService {


    /**
     * 员工登录功能
     * @deprecated 已在 {@link com.tz.tpcs.service.security.MyDetailsServiceImpl MyDetailsServiceImpl} 中实现
     * @param str 可能是number、email、mobilePhone
     * @param pass 密码
     * @return 员工实例
     */
//    @Deprecated
//    Employee login(String str, String pass);
}

package com.tz.tpcs.service;

import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.web.form.Pager;

/**
 * Employee Service 接口类
 * @author 胡荆陵
 */
public interface EmployeeService extends FieldUniqueValidatorService {

    /**
     * 根据 number、email、mobilePhone 查询员工
     * @param str 可能是 手机号、员工号、邮箱
     * @return 如果存在，返回员工实例；如果不存在，返回null
     */
    Employee findByPhoneNumberEmail(String str);

    /**
     * 分页+查询
     * @param departmentId  部门ID
     * @param realname 员工姓名
     * @param pager 分页类
     * @return 分页类
     */
    Pager<Employee> findByPager(String departmentId, String realname, Pager<Employee> pager);
    
    /**
     * 保存 
     * @param employee
     */
    void save(Employee employee);

    /**
     * 根据id数组批量删除
     * @param ids id数组
     */
    void delete(String[] ids);

    /**
     * 根据id数组批量更新enable状态
     * @param ids
     * @param enableStatus
     */
    void updateEnableStatus(String[] ids, boolean enableStatus);

    /**
     * 更新功能
     * 检查密码是否需要修改
     * @param employee
     */
    void checkPasswordAndUpdate(Employee employee);
}

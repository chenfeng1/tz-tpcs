package com.tz.tpcs.service.impl;

import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.service.EmployeeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * Created by Administrator on 2015/1/16.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @Resource
    private EmployeeDao employeeDao;

    @Override
    public void update(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public Employee findByPhoneNumberEmail(String str) {
        //先根据手机号查询
        Employee emp = employeeDao.findSingleByProp("mobilePhone", str);
        if(emp != null){
            return emp;
        }else{
            //再根据员工号查询
            emp = employeeDao.findSingleByProp("number", str);
            if(emp != null){
                return emp;
            }else {
                //最后根据邮箱查询
                emp = employeeDao.findSingleByProp("email", str);
                return emp != null? emp:null;
            }
        }
    }
}

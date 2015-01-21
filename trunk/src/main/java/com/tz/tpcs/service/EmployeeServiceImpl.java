package com.tz.tpcs.service;

import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Employee;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * Created by Administrator on 2015/1/16.
 */
@Service
@Transactional
public class EmployeeServiceImpl implements EmployeeService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private EmployeeDao employeeDao;

    @Override
    public void update(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public Employee getByPhoneNumberEmail(String str) {
        //先根据手机号查询
        Employee emp = employeeDao.getSingleByProp("mobilePhone", str);
        if(emp != null){
            return emp;
        }else{
            //再根据员工号查询
            emp = employeeDao.getSingleByProp("number", str);
            if(emp != null){
                return emp;
            }else {
                //最后根据邮箱查询
                emp = employeeDao.getSingleByProp("email", str);
                return emp != null? emp:null;
            }
        }
    }

}

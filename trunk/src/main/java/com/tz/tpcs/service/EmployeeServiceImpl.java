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

//    @Resource
//    private EmployeeDao employeeDao;

    /*@Override
    public Employee login(String str, String pass) {
        //增加对大小写不区分的支持 todo
        //根据 number 匹配
        Employee emp = employeeDao.getSingleByProp("number", str);
        if(emp != null){
            if(emp.getPassword().equals(pass)){
                return emp;
            }else{
                return null;
            }
        }
        return null;
    }*/

}

package com.tz.tpcs.service.impl;

import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.service.EmployeeService;
import com.tz.tpcs.util.IConstant;
import com.tz.tpcs.web.form.Pager;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * Created by Administrator on 2015/1/16.
 */
@Service
@Transactional
@Profile(IConstant.PROFILE_PRODUCTION)
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);

    @Resource
    private EmployeeDao employeeDao;

    /**
     * empty constructor
     */
    public EmployeeServiceImpl() {
        LOGGER.debug("EmployeeServiceImpl empty constructor...");
    }

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

    @Override
    public Pager<Employee> findByPager(String departmentId, String employeeName, Pager<Employee> pager) {
        return null;
    }
}

package com.tz.tpcs.service;

import com.tz.WebAppConfig;
import com.tz.tpcs.dao.BaseTest;
import com.tz.tpcs.entity.Employee;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.transaction.Transactional;

/**
 * Created by Administrator on 2015/1/16.
 */
@TransactionConfiguration(defaultRollback = true)//自动回滚测试数据
public class TestEmployeeService extends BaseTest{

    @Resource
    private EmployeeService employeeService;

    @Test
    public void test1login(){
        String str = "EMP_001";
        String pass = "123";
        Employee emp = employeeService.login(str, pass);
        System.out.println(emp);
    }

}

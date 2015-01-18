package com.tz.tpcs.service;

import com.tz.WebAppConfig;
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
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebAppConfig.class)
@TransactionConfiguration(defaultRollback = false)//自动回滚测试数据
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEmployeeService {

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

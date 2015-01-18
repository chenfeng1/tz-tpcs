package com.tz.tpcs.dao;

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
public class TestEmployeeDao {

    @Resource
    private EmployeeDao employeeDao;

    @Test
    public void test01Save(){
        Employee employee = new Employee();
        employee.setNumber("EMP_001");
        employee.setEmail("user@website.com");
        employee.setMobilePhone("13812345678");
        employee.setPassword("123");
        employeeDao.save(employee);
    }

    @Test
    public void test02GetByNumber(){
        String prop = "number";
        String value = "EMP_001";
        Employee emp = employeeDao.getSingleByProp(prop, value);
        System.out.println(emp);
    }

    @Test
    public void test03GetByEmail(){
        String prop = "email";
        String value = "user@website.com";
        Employee emp = employeeDao.getSingleByProp(prop, value);
        System.out.println(emp);
    }

    @Test
    public void test04GetByMobilePhone(){
        String prop = "mobilePhone";
        String value = "13812345678";
        Employee emp = employeeDao.getSingleByProp(prop, value);
        System.out.println(emp);
    }

}

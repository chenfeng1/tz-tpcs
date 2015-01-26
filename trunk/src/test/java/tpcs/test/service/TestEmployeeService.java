package tpcs.test.service;

import com.tz.tpcs.service.EmployeeService;
import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;
import tpcs.test.dao.BaseTest;

import javax.annotation.Resource;

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
//        Employee emp = employeeService.login(str, pass);
//        System.out.println(emp);
    }

}

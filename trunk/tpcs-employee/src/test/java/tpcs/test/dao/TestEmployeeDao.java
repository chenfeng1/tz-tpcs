package tpcs.test.dao;

import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Employee;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/1/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEmployeeDao extends AbstractDaoTxTest {

    @Resource
    private EmployeeDao employeeDao;

    @Test
    public void test01Save(){
        Employee employee = new Employee();
        employee.setNumber("EMP_001");
        employee.setEmail("user2@website.com");
        employee.setMobilePhone("15812345678");
        employee.setPassword("123");
        employeeDao.save(employee);
    }

    @Test
    public void test02GetByNumber(){
        String prop = "number";
        String value = "EMP_001";
        Employee emp = employeeDao.findSingleByProp(prop, value);
        System.out.println(emp);
    }

    @Test
    public void test03GetByEmail(){
        String prop = "email";
        String value = "user@website.com";
        Employee emp = employeeDao.findSingleByProp(prop, value);
        System.out.println(emp);
    }

    @Test
    public void test04GetByMobilePhone(){
        String prop = "mobilePhone";
        String value = "13812345678";
        Employee emp = employeeDao.findSingleByProp(prop, value);
        System.out.println(emp);
    }
}

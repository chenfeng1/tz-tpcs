package tpcs.test.dao;

import com.tz.tpcs.dao.DepartmentDao;
import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Department;
import com.tz.tpcs.entity.Employee;
import org.junit.Assert;
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
    @Resource
    private DepartmentDao departmentDao;

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

    @Test
    public void test05GetCountByDeptId(){
        Department department = new Department("testDepartment11", null, 0, 1);
        departmentDao.save(department);
        Department department2 = new Department("testDepartment22", null, 0, 2);
        departmentDao.save(department2);

        for (int i = 0; i < 5; i++) {
            Employee emp1 = new Employee();
            emp1.setNumber("test05GetCountByDeptIdUser"+i);
            emp1.setEmail("test05GetCountByDeptIdUser"+i+"@website.com");
            emp1.setMobilePhone("1581234567"+i);
            emp1.setPassword("123");
            emp1.setDepartment(department);
            employeeDao.save(emp1);
        }

        int count = employeeDao.getCountByDeptId(department.getId());
        Assert.assertEquals(5, count);
        int count2 = employeeDao.getCountByDeptId(department2.getId());
        Assert.assertEquals(0, count2);
    }

}

package tpcs.test.service;

import com.tz.tpcs.dao.DepartmentDao;
import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Department;
import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.service.DepartmentService;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/6 11:32
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDepartmentService extends AbstractServiceTxTest {

    @Resource
    private DepartmentService departmentService;
    @Resource
    private DepartmentDao departmentDao;
    @Resource
    private EmployeeDao employeeDao;

    @Test
    public void test01GetDeptTree(){
        List<Department> list = departmentService.getDeptTree();
        Assert.assertNotNull(list);
    }

    @Test
    public void test02CheckAndDelete(){
        Department department1 = new Department("testDepartment11", null, 0, 1);
        departmentDao.save(department1);
        Department department2 = new Department("testDepartment22", null, 0, 2);
        departmentDao.save(department2);
        Department department3 = new Department("testDepartment33", department2, 0, 3);
        departmentDao.save(department3);

        for (int i = 0; i < 5; i++) {
            Employee emp1 = new Employee();
            emp1.setNumber("test05GetCountByDeptIdUser"+i);
            emp1.setEmail("test05GetCountByDeptIdUser"+i+"@website.com");
            emp1.setMobilePhone("1581234567"+i);
            emp1.setPassword("123");
            emp1.setDepartment(department1);
            employeeDao.save(emp1);
        }

        String result1 = departmentService.checkAndDelete(department1.getId());
        Assert.assertEquals("该部门下面有员工，删除失败!" , result1);
    }

    @Test
    public void test03GetSubDepartmentIds(){
        Set<String> ids = departmentService.getSubDepartmentIds("691b45e9-0435-4df2-8eeb-2790b8c3260b");
        System.out.println(ids);
    }

}

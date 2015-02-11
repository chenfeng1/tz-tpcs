package tpcs.test.dao;

import com.tz.tpcs.dao.DepartmentDao;
import com.tz.tpcs.entity.Department;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/6 11:16
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestDepartmentDao extends AbstractDaoTxTest {

    @Resource
    private DepartmentDao departmentDao;

    @Test
    public void test01save(){
        Department department = new Department("testDepartment", null, 0, 1);
        departmentDao.save(department);
    }

    @Test
    public void test02FindByLevel(){
        List<Department> departmentList = departmentDao.findByLevel(0);
        System.out.println(departmentList);
    }

    @Test
    public void test03FindByName(){
        Department department = departmentDao.findByName("教学部");
        System.out.println(department);
    }

    @Test
    public void test04FindNameById(){
        String name = departmentDao.findNameById("f135d708-12b3-44a2-b590-e955bcb41299");
        System.out.println(name);
    }

}

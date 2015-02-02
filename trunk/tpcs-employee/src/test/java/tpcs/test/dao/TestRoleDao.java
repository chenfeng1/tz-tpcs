package tpcs.test.dao;

import com.tz.tpcs.dao.RoleDao;
import com.tz.tpcs.entity.Role;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Hu Jing Ling on 2015/1/20.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestRoleDao extends AbstractDaoTxTest {

    @Resource
    private RoleDao roleDao;

    @Test
    public void test1(){
        Role role = new Role();
        role.setName("testRole");
        role.setCode("testCode");
        role.setSystem(false);
        roleDao.save(role);
    }

    @Test
    public void test2(){
        Role role = roleDao.findByCode("admin");
        System.out.println(role);
    }

    @Test
    public void test3(){
        String value = "/clazz/list";
        List<String> list = roleDao.findCodesByResValue(value);
        System.out.println(list);
    }

}

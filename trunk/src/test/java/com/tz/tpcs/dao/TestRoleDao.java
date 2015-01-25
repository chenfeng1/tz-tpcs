package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Role;
import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.util.List;

/**
 * Created by Hu Jing Ling on 2015/1/20.
 */
@TransactionConfiguration(defaultRollback = true)//是否回滚测试数据
public class TestRoleDao extends BaseTest {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private RoleDao roleDao;

    @Resource
    private EntityManager em;

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
        //1.
        String value = "/clazz/list";
        //2.
        List<String> list = roleDao.findCodesByResValue(value);
        //3.
        System.out.println(list);
    }

}

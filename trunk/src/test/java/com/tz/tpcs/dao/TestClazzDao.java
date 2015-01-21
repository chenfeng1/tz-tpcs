package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Clazz;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * ClazzDao 单元测试类
 */
@TransactionConfiguration(defaultRollback = true)//是否回滚测试数据
public class TestClazzDao extends BaseTest{

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private ClazzDao clazzDao;

    private Clazz clazz;

    @Before
    public void initCustomer(){
        clazz = new Clazz();
        clazz.setName("testClazz");
    }

    @Test
    public void test01findOne(){
        clazzDao.save(clazz);
        Clazz c = clazzDao.findOne(clazz.getId());
        Assert.assertEquals(c, clazz);
    }

    @Test
    public void test02update(){
        clazzDao.save(clazz);
        Clazz c = clazzDao.findOne(clazz.getId());
        c.setStatus(Clazz.ClazzStatus.CLOSE);
        clazzDao.save(c);
    }

    @Test
    public void test03delete(){
        clazzDao.save(clazz);
        clazzDao.delete(clazz);
    }

    @Test
    public void test04batchSave(){
        DataFactory df = new DataFactory();
        List<Clazz> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Clazz c = new Clazz();
            c.setName("JSD" + df.getNumberBetween(1000, 9999));
            c.setOpen(df.getBirthDate());
            list.add(c);
        }
        clazzDao.save(list);
    }
}

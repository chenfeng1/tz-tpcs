package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Resources;
import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;

/**
 * Created by Hu Jing Ling on 2015/1/19.
 */
@TransactionConfiguration(defaultRollback = true)//是否回滚测试数据
public class TestResourcesDao extends BaseTest {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private ResourcesDao resourcesDao;

    @Test
    public void test1(){
        Resources res = new Resources();
        res.setCode("test code");
        res.setType(Resources.Type.FOLDER);
        resourcesDao.save(res);
    }

    @Test
    public void test2(){
        Resources res = resourcesDao.findByCodeWithChildren("menu_clazz");
        System.out.println(res);
    }



}

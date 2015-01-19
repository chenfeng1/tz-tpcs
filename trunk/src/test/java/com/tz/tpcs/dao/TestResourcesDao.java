package com.tz.tpcs.dao;

import com.tz.tpcs.entity.Resources;
import org.junit.Test;

import javax.annotation.Resource;

/**
 * Created by Hu Jing Ling on 2015/1/19.
 */
public class TestResourcesDao extends BaseTest {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private ResourcesDao resourcesDao;

    @Test
    public void test1(){
        Resources res = new Resources();
        res.setCode("test code");
//        res.setType();
    }

}

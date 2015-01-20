package com.tz.tpcs.service;

import com.tz.tpcs.dao.BaseTest;
import com.tz.tpcs.entity.Resources;
import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Hu Jing Ling on 2015/1/20.
 */
@TransactionConfiguration(defaultRollback = false)//自动回滚测试数据
public class TestResourcesService extends BaseTest {

    @Resource
    private ResourcesService resourcesService;

    @Test
    public void test1(){
        List<Resources> list = resourcesService.findByCodes(new String[]{"menu_clazz", "menu_student"});
        System.out.println(list.size());
        for(Resources res : list){
            System.out.println(res);
        }
    }

}

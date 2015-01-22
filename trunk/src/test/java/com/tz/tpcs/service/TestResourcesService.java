package com.tz.tpcs.service;

import com.tz.tpcs.dao.BaseTestNoTx;
import com.tz.tpcs.entity.Resources;
import org.junit.Test;

import javax.annotation.Resource;
import java.util.List;
import java.util.Set;

/**
 * Created by Hu Jing Ling on 2015/1/20.
 */
public class TestResourcesService extends BaseTestNoTx {

    @Resource
    private ResourcesService resourcesService;

    @Test
    public void test1FindResByEmployee(){
        //1.
        String[] array = new String[]{"menu_clazz", "menu_student"};
        //2.
        List<Resources> list = resourcesService.findByCodes(array);
        //3.
        System.out.println(list.size());
        for(Resources res : list){
            System.out.println(res);
        }
    }

    @Test
    public void test2FindResByEmployee(){
        //1.
        String employeeId = "833c0e7c-ed89-4655-ae1e-9f2940ebc2bb";
        //2.
        Set<Resources> resourcesSet = resourcesService.findResByEmployeeId(employeeId);
        //3.
        System.out.println(resourcesSet);
    }

}

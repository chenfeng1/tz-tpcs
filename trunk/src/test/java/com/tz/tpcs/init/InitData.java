package com.tz.tpcs.init;

import com.tz.WebAppConfig;
import com.tz.tpcs.dao.ResourcesDao;
import com.tz.tpcs.entity.Resources;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import com.tz.tpcs.entity.Resources.Type;

import java.util.ArrayList;
import java.util.List;

/**
 * 自动创建数据库 数据
 * Created by Hu Jing Ling on 2015/1/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebAppConfig.class)
@TransactionConfiguration(defaultRollback = false)//自动回滚测试数据
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InitData {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private ResourcesDao resourcesDao;

    @Test
    public void test01resources(){
        List<Resources> list = new ArrayList<>();
        //客户管理
        Resources r1 = new Resources("班级", "menu_clazz",Type.FOLDER, "", null, null,1);
        Resources r11 = new Resources("班级列表", "menu_clazz_list",Type.URL, "/classes/initList", r1, null,11);
        Resources r12 = new Resources("导入班级", "menu_clazz_import",Type.URL, "/classes/initImport", r1, null,12);
        Resources r13 = new Resources("班级活动", "menu_clazz_campaign",Type.URL, "/classesCampaign/initList", r1, null,12);
        list.add(r1);
        list.add(r11);
        list.add(r12);
        list.add(r13);

        Resources r2 = new Resources("学员", "menu_student",Type.FOLDER, "", null, null,2);
        Resources r21 = new Resources("学员列表", "menu_student_list",Type.URL, "/students/initList", r2, null,11);
        Resources r22 = new Resources("导入学员", "menu_student_import",Type.URL, "/students/initImport", r2, null,11);
        list.add(r2);
        list.add(r21);
        list.add(r22);

        Resources r3 = new Resources("知识库", "menu_student",Type.FOLDER, "", null, null,2);
        Resources r31 = new Resources("课程列表", "menu_student_list",Type.URL, "/students/initList", r2, null,11);
        Resources r32 = new Resources("查看课程", "menu_student_import",Type.URL, "/students/initImport", r2, null,11);
        Resources r33 = new Resources("知识中心", "menu_student_import",Type.URL, "/students/initImport", r2, null,11);
        list.add(r2);
        list.add(r21);
        list.add(r22);

        resourcesDao.save(list);
    }

}

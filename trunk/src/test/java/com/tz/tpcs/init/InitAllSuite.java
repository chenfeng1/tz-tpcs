package com.tz.tpcs.init;

import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.junit.runners.Suite;

/**
 * 单元测试套件
 * 包含需要执行的一组单元测试类
 * Created by Hu Jing Ling on 2015/1/20.
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({
        InitTable.class, //先创建表
        InitData.class   //再插入数据
})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InitAllSuite {
}

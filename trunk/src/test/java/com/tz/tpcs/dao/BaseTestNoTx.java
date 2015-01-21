package com.tz.tpcs.dao;

import com.tz.WebAppConfig;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * 基础单元测试类 (无事务支持)
 * 如有可能发生 org.hibernate.LazyInitializationException 的接口或方法，
 * 在继承此父类后测试并运行正常后，
 * 再到控制器中调用。
 * Created by hu jing ling on 2015/1/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebAppConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BaseTestNoTx {
}

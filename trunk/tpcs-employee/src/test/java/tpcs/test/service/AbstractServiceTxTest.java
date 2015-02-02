package tpcs.test.service;

import com.tz.tpcs.DaoConfig;
import com.tz.tpcs.ServiceConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.transaction.Transactional;

/**
 * 抽象Service单元测试类
 * 如需测试Service层方法，且必须回滚数据，则继承此类。
 * Created by hu jing ling on 2015/1/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = {DaoConfig.class, ServiceConfig.class})
@Transactional
@TransactionConfiguration(defaultRollback = true)//是否回滚测试数据
public class AbstractServiceTxTest {
}

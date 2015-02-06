package tpcs.test.service;

import com.tz.tpcs.DaoConfig;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * 抽象Service单元测试类
 * 如有可能发生 org.hibernate.LazyInitializationException 的接口或方法，
 * 则继承此类，并在单元测试中运行正常后，
 * 再到控制器中调用。
 * Created by hu jing ling on 2015/1/21.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(
        classes = DaoConfig.class,
        locations = "classpath:spring/service-config.xml")
public abstract class AbstractServiceTest {
}

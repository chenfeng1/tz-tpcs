package tpcs.test.dao;

import com.tz.WebAppConfig;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.transaction.Transactional;

/**
 * 基础单元测试类 (有事务支持)
 * 提供spring容器的初始化， 依赖注入， 顺序执行等常用配置。
 * 如果单元测试的数据，需要在测试成功后回滚/撤销的，
 * 在继承此父类后测试和运行。
 * Created by hu jing ling on 2015/1/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebAppConfig.class)
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class BaseTest {
}

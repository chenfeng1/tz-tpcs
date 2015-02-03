package tpcs.test.web;

import com.tz.tpcs.DaoConfig;
import com.tz.tpcs.util.IConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * EmployeeController 单元测试类
 * Created by hjl on 2015/1/18.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DaoConfig.class,
        locations={"spring/service-config.xml", "spring/web-config.xml"})
@WebAppConfiguration
@ActiveProfiles(IConstant.PROFILE_PRODUCTION)
public class TestEmployeeController {

    @Test
    public void test01LoginSuccess() throws Exception {
        //...
    }

}

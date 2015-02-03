package tpcs.test.web;

import com.tz.tpcs.DaoConfig;
import com.tz.tpcs.util.IConstant;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * ClazzController 单元测试类
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DaoConfig.class,
        locations={"spring/service-config.xml", "spring/web-config.xml"})
@WebAppConfiguration
@ActiveProfiles(IConstant.PROFILE_PRODUCTION)
public class TestClazzController {

    @Test
    public void test1getAll() throws Exception {
//        MvcResult result = mockMvc.perform(get("/classes"))
//                .andExpect(MockMvcResultMatchers.status().isOk())
//                .andExpect(MockMvcResultMatchers.content().contentType(MyMediaType.APPLICATION_JSON_UTF8))
//                .andReturn();
//        System.out.println(result.getResponse().getContentAsString());
    }

}

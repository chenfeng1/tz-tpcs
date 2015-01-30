package tpcs.test.web;

import com.tz.tpcs.util.IConstant;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;

import javax.servlet.http.HttpSession;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * SpringSecurity 单元测试类
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/26 16:56
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSpringSecurity extends BaseController {

    /**
     * 模拟未登录 直接访问内部资源
     * @throws Exception
     */
    @Test
    public void test01RequiresAuthentication() throws Exception {
        mockMvc.perform(get("/clazz/list"))
                .andExpect(redirectedUrl("http://localhost/login.jsp"));
    }

    /**
     * 模拟登录失败
     * @throws Exception
     */
    @Test
    public void test02LoginFailed() throws Exception {
        String username = "EMP_001";
        String password = "invalid";
        mockMvc.perform(post("/loginVerify")
                .param("j_username", username)
                .param("j_password", password))
                .andExpect(forwardedUrl("/login.jsp"))
                .andExpect(new ResultMatcher() {
                    @Override
                    public void match(MvcResult result) throws Exception {
                        HttpSession session = result.getRequest().getSession();
                        Assert.assertNull(session.getAttribute(IConstant.LOGIN_USER));
                        Assert.assertNull(session.getAttribute(IConstant.LOGIN_USER_RES));
                    }
                });
    }

    /**
     * 模拟登录成功
     * @throws Exception
     */
    @Test
    public void test03LoginSuccess() throws Exception {
        String username = "EMP_001";
        String password = "123";
        ResultActions resultActions = mockMvc.perform(post("/loginVerify")
                .param("j_username", username)
                .param("j_password", password));
        resultActions.andExpect(redirectedUrl("/loginSuccess"));
        resultActions.andExpect(new ResultMatcher() {
            @Override
            public void match(MvcResult result) throws Exception {
                HttpSession session = result.getRequest().getSession();
                Assert.assertNotNull(session.getAttribute(IConstant.LOGIN_USER));
                Assert.assertNotNull(session.getAttribute(IConstant.LOGIN_USER_RES));
            }
        });
    }

    /**
     * 模拟访问授权资源
     * @throws Exception
     */
    @Test
    public void test04AccessUrlSuccess() throws Exception {
        mockMvc.perform(get("/clazz/list").session(makeAuthSession("EMP_001", "123")))
                .andExpect(view().name("clazz.list"));
    }

    /**
     * 模拟访问未授权资源
     * @throws Exception
     */
    @Test
    public void test05AccessUrlFailed() throws Exception {
        mockMvc.perform(get("/employees/list").session(makeAuthSession("EMP_002", "456")))
                .andExpect(status().is(403))
                .andExpect(forwardedUrl("/html/accessDeniedPage.jsp"));
    }

}

package tpcs.test.web.security;

import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.service.EmployeeService;
import com.tz.tpcs.util.IConstant;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;
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
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(locations={"applicationContext.xml",
        "spring/web-config.xml"}, classes = MockServiceConfig.class)
@WebAppConfiguration
@ActiveProfiles(IConstant.PROFILE_MOCK)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestSpringSecurity {

    @Resource
    private FilterChainProxy springSecurityFilterChain;
    @Resource
    private WebApplicationContext wac;
    @Resource
    private AuthenticationManager authenticationManager;
    @Resource
    private EmployeeService employeeService;

    protected MockMvc mockMvc;

    @Before
    public void setup() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
                .addFilters(this.springSecurityFilterChain).build();
    }

    /**
     * 模拟用户在尚未登录的情况下，
     * 直接访问必须登录才可以访问的资源，
     * 系统会跳转到登录页面
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
        String username = "testUser";
        String password = "invalid";
//        String password = "testPassword";
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
        String username = "testUser";
        String password = "testPassword";
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
     * 模拟登录后，访问授权资源
     * @throws Exception
     */
    @Test
    public void test04AccessUrlSuccess() throws Exception {
        mockMvc.perform(get("/clazz/list").session(makeAuthSession("testUser", "testPassword")))
                .andExpect(view().name("clazz.list"));
    }

    /**
     * 模拟登录后，访问未授权资源
     * @throws Exception
     */
    @Test
    public void test05AccessUrlFailed() throws Exception {
        mockMvc.perform(get("/employees/list").session(makeAuthSession("testUser", "testPassword")))
                .andExpect(status().is(403))
                .andExpect(forwardedUrl("/html/accessDeniedPage.jsp"));
    }

    private MockHttpSession makeAuthSession(String number, String password) {
        MockHttpSession session = new MockHttpSession();
        SecurityContext securityContext = SecurityContextHolder.getContext();
        //init mock authToken with number
        Employee principal = new Employee();
        principal.setNumber(number);
        //init Authentication
        Authentication authToken = new UsernamePasswordAuthenticationToken(principal, password);
        securityContext.setAuthentication(authenticationManager.authenticate(authToken));
        session.setAttribute(HttpSessionSecurityContextRepository.SPRING_SECURITY_CONTEXT_KEY, securityContext);
        return session;
    }

}

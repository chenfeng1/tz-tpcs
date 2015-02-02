package tpcs.test.web;

import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.service.EmployeeService;
import com.tz.tpcs.util.IConstant;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.Mockito;
import org.springframework.security.authentication.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;
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
//public class TestSpringSecurity extends BaseController {
public class TestSpringSecurity extends BaseController {

    @Resource
    private AuthenticationManager authenticationManager;

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
        TestingAuthenticationToken token = new TestingAuthenticationToken(
                "testUser", "testPassword", new String[]{"User", "Administrator"});

        //mock employeeService
//        employeeService = Mockito.mock(EmployeeService.class);

        //mock method invoke
//        Employee mockEmployee = new Employee();
//        mockEmployee.setNumber("testUser");
//        mockEmployee.setPassword("testPassword");
//        when(employeeService.findByPhoneNumberEmail("testUser")).thenReturn(mockEmployee);

//        List<AuthenticationProvider> authenticationProviderList = new ArrayList<>();
//        authenticationProviderList.add(new TestingAuthenticationProvider());
//        providerManager = new ProviderManager(authenticationProviderList);

        SecurityContext securityContext = SecurityContextHolder.getContext();
//        SecureContextImpl secureContext = new SecureContextImpl();
        securityContext.setAuthentication(token);
        SecurityContextHolder.setContext(securityContext);


        String username = "testUser";
        String password = "testPassword";
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
     * 模拟登录失败
     * @throws Exception
     */
    @Test
    public void test02LoginFailed_2() throws Exception {
        TestingAuthenticationToken token = new TestingAuthenticationToken(
                "testUser", "testPassword", new String[]{"User", "Administrator"});

        System.out.println(authenticationManager);

        List<AuthenticationProvider> authenticationProviderList = new ArrayList<>();
        authenticationProviderList.add(new TestingAuthenticationProvider());
//        providerManager = new ProviderManager(authenticationProviderList);

        authenticationManager = new ProviderManager(authenticationProviderList);
        SecurityContext securityContext = SecurityContextHolder.getContext();
//        SecureContextImpl secureContext = new SecureContextImpl();
        securityContext.setAuthentication(token);
        SecurityContextHolder.setContext(securityContext);


        String username = "testUser";
        String password = "testPassword";
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

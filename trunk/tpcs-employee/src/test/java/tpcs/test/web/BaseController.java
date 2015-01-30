package tpcs.test.web;

import com.tz.tpcs.entity.Employee;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.FilterChainProxy;
import org.springframework.security.web.context.HttpSessionSecurityContextRepository;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import javax.annotation.Resource;

import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(locations={"classpath:spring-app.xml"})
@WebAppConfiguration
public class BaseController {

	@Resource
	private FilterChainProxy springSecurityFilterChain;
	@Resource
	private WebApplicationContext wac;
	@Resource
	private AuthenticationManager authenticationManager;

	protected MockMvc mockMvc;

	@Before
	public void setup() {
		this.mockMvc = MockMvcBuilders.webAppContextSetup(this.wac)
				.addFilters(this.springSecurityFilterChain).build();
	}
	/**
	 * 根据用户名，密码，获得一个测试专用的MockSession
	 * @param number 员工号
	 * @param password 密码
	 * @return MockHttpSession
	 */
	protected MockHttpSession makeAuthSession(String number, String password) {
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

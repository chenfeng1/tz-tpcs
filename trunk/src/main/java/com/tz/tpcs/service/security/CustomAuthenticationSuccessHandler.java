package com.tz.tpcs.service.security;

import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Employee;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;

/**
 * @author : Hu jing ling
 * @since : 2015-1-19
 * 自定义 Authentication Success Handler 类
 * 写登录成功的逻辑
 */
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private EmployeeDao employeeDao;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        //登录成功：更新登录IP和日期, 并重置登录失败次数
        String loginIp = ((WebAuthenticationDetails) authentication.getDetails()).getRemoteAddress();
        Employee emp = (Employee) authentication.getPrincipal();
        emp.setLoginIp(loginIp);
        emp.setLoginDate(new Date());
        emp.setLoginFailureCount(0);
        employeeDao.save(emp);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}

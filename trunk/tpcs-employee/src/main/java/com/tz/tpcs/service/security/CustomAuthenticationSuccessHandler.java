package com.tz.tpcs.service.security;

import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.entity.Resources;
import com.tz.tpcs.service.ResourcesService;
import com.tz.tpcs.util.IConstant;
import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Date;
import java.util.Set;

/**
 * @author : Hu jing ling
 * @since : 2015-1-19
 * 自定义 Authentication Success Handler 类
 * 写登录成功的逻辑
 */
public class CustomAuthenticationSuccessHandler extends SimpleUrlAuthenticationSuccessHandler {

    private static final Logger LOGGER = Logger.getLogger(CustomAuthenticationSuccessHandler.class);

    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private ResourcesService resourcesService;

    @Override
    @Transactional
    public void onAuthenticationSuccess(HttpServletRequest request,
                                        HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        LOGGER.debug("onAuthenticationSuccess()...");
        //登录成功：更新登录IP和日期, 并重置登录失败次数
        String loginIp = ((WebAuthenticationDetails) authentication.getDetails()).getRemoteAddress();
        Employee emp = (Employee) authentication.getPrincipal();
        emp.setLoginIp(loginIp);
        emp.setLoginDate(new Date());
        emp.setLoginFailureCount(0);
        employeeDao.save(emp);
        //在session中存入登录员工的实例
        HttpSession session = request.getSession();
        session.setAttribute(IConstant.LOGIN_USER, emp);
        //在session中存入员工可访问资源的集合
        Set<Resources> resourcesSet = resourcesService.findResByEmployeeId(emp.getId());
        session.setAttribute(IConstant.LOGIN_USER_RES, resourcesSet);

        super.onAuthenticationSuccess(request, response, authentication);
    }
}

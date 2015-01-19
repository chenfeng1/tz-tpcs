package com.tz.tpcs.service.security;

import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Employee;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author : Hu jing ling
 * @since : 2013-7-2
 * 自定义 Authentication Failure Handler类
 */
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "j_username";
    public static final String USERNAME_ERR_MSG = "usernameErrMsg";
    public static final String PASSWORD_ERR_MSG = "passwordErrMsg";

    private String usernameParameter = SPRING_SECURITY_FORM_USERNAME_KEY;

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private EmployeeDao employeeDao;

    private String passwordFailureUrl;

    private String accountDisabledUrl;

    private String accountExpiredUrl;

    private String accountLockedUrl;

    private String credentialsExpiredUrl;

    private Integer maxLoginFailureCount; //最大密码错误次数

    public void setCredentialsExpiredUrl(String credentialsExpiredUrl) {
        this.credentialsExpiredUrl = credentialsExpiredUrl;
    }

    public void setAccountLockedUrl(String accountLockedUrl) {
        this.accountLockedUrl = accountLockedUrl;
    }

    public void setPasswordFailureUrl(String passwordFailureUrl) {
        this.passwordFailureUrl = passwordFailureUrl;
    }

    public void setAccountExpiredUrl(String accountExpiredUrl) {
        this.accountExpiredUrl = accountExpiredUrl;
    }

    public void setAccountDisabledUrl(String accountDisabledUrl) {
        this.accountDisabledUrl = accountDisabledUrl;
    }

    public void setMaxLoginFailureCount(Integer maxLoginFailureCount) {
        this.maxLoginFailureCount = maxLoginFailureCount;
    }

    //从 HttpServletRequest 获取用户名
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(usernameParameter).trim();
    }

    private void addErrorMessage(HttpServletRequest request,String key, String message){
        request.setAttribute(key, message);
    }

    @Transactional
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String number = obtainUsername(request);
        Employee employee = employeeDao.getSingleByProp("number", number);

        //默认密码输出错误次数
        if (maxLoginFailureCount == null) {
            maxLoginFailureCount = 3; //如果未设置，默认=3
        }

        //根据错误类型, 返回不同错误页面
        if (exception instanceof BadCredentialsException) {
            addErrorMessage(request, PASSWORD_ERR_MSG, "用户名或密码错误");
            //如果用户不为空，且未被锁住
            if (employee != null && employee.isAccountNonLocked()) {
                Integer failureCount = employee.getLoginFailureCount();
                failureCount++;
                if (failureCount >= maxLoginFailureCount) {
                    //账号锁定
                    employee.setAccountNonLocked(false);
                }
                employee.setLoginFailureCount(failureCount);
                employeeDao.save(employee);
            }
        } else if (exception instanceof AccountExpiredException) {
            addErrorMessage(request, USERNAME_ERR_MSG, "账号已过期");
        } else if (exception instanceof LockedException) {
            if(employee.getRoles().size() == 0){
                addErrorMessage(request, USERNAME_ERR_MSG, "此账号未分配角色");
            }else{
                addErrorMessage(request, USERNAME_ERR_MSG, "账号已锁定");
            }
        } else if (exception instanceof CredentialsExpiredException) {
            addErrorMessage(request, USERNAME_ERR_MSG, "授权已过期");
        } else if(exception instanceof DisabledException){
            addErrorMessage(request, USERNAME_ERR_MSG, "账号已禁用");
        } else{
            addErrorMessage(request, USERNAME_ERR_MSG, "未知错误");
        }
        super.onAuthenticationFailure(request, response, exception);
    }
}

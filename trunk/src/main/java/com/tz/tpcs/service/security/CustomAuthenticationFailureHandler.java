package com.tz.tpcs.service.security;

import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.service.EmployeeService;
import org.springframework.context.MessageSource;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationFailureHandler;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.servlet.LocaleResolver;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Locale;

/**
 * @author : Hu jing ling
 * @since : 2013-7-2
 * 自定义 Authentication Failure Handler类
 * 写登录失败的逻辑
 */
public class CustomAuthenticationFailureHandler extends SimpleUrlAuthenticationFailureHandler {

    public static final String SPRING_SECURITY_FORM_USERNAME_KEY = "j_username";
    public static final String USERNAME_ERR_MSG = "usernameErrMsg";
    public static final String PASSWORD_ERR_MSG = "passwordErrMsg";

    @Resource
    private EmployeeService employeeService;
    @Resource
    private MessageSource messageSource;
    @Resource
    private LocaleResolver localeResolver;

    private Integer maxLoginFailureCount; //最大密码错误次数
    public void setMaxLoginFailureCount(Integer maxLoginFailureCount) {
        this.maxLoginFailureCount = maxLoginFailureCount;
    }

    //从 HttpServletRequest 获取用户名
    protected String obtainUsername(HttpServletRequest request) {
        return request.getParameter(SPRING_SECURITY_FORM_USERNAME_KEY).trim();
    }

    private void addErrorMessage(HttpServletRequest request,String key, String message){
        request.setAttribute(key, message);
    }

    @Transactional
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        String username = obtainUsername(request);
        Employee employee = employeeService.findByPhoneNumberEmail(username);

        //默认密码输出错误次数
        if (maxLoginFailureCount == null) {
            maxLoginFailureCount = 3; //如果未设置，默认=3
        }

        //获取国际化语种
        Locale locale = localeResolver.resolveLocale(request);

        //根据错误类型, 返回不同错误页面
        if (exception instanceof BadCredentialsException) {
            String message = messageSource.getMessage("error.invalid.username.or.password", null, locale);
            addErrorMessage(request, PASSWORD_ERR_MSG, message);
            //如果用户不为空，且未被锁住
            if (employee != null && employee.isAccountNonLocked()) {
                Integer failureCount = employee.getLoginFailureCount();
                failureCount++;
                if (failureCount >= maxLoginFailureCount) {
                    //账号锁定
                    employee.setAccountNonLocked(false);
                }
                employee.setLoginFailureCount(failureCount);
                employeeService.update(employee);
                String msg = "连续失败3次将会锁定账户";
                if(failureCount>=3){
                    msg = "账号已锁定，请联系管理员!";
                }
                addErrorMessage(request, USERNAME_ERR_MSG, msg);
            }
            //todo...其余错误提示信息
        } else if (exception instanceof AccountExpiredException) {
            addErrorMessage(request, USERNAME_ERR_MSG, "账号已过期，请联系管理员!");
        } else if (exception instanceof LockedException) {
            if(employee.getRoles().size() == 0){
                addErrorMessage(request, USERNAME_ERR_MSG, "此账号未分配角色，请联系管理员!");
            }else{
                addErrorMessage(request, USERNAME_ERR_MSG, "账号已锁定，请联系管理员!");
            }
        } else if (exception instanceof CredentialsExpiredException) {
            addErrorMessage(request, USERNAME_ERR_MSG, "授权已过期");
        } else if(exception instanceof DisabledException){
            addErrorMessage(request, USERNAME_ERR_MSG, "账号已禁用，请联系管理员!");
        } else{
            addErrorMessage(request, USERNAME_ERR_MSG, "未知错误，请联系管理员!");
        }
        super.onAuthenticationFailure(request, response, exception);
    }
}

package com.tz.tpcs.service.security;

import com.tz.tpcs.entity.Employee;
import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;

import java.util.Collection;
import java.util.Iterator;

/**
 * 判断用户是否拥有所请求URL的角色
 * @author Hu Jing Ling
 * @since 2015/1/22 21:58
 * @version 1.0
 */
public class CustomAccessDecisionManager implements AccessDecisionManager {

    private static final Logger LOGGER = Logger.getLogger(CustomAccessDecisionManager.class);

    @Override
    public void decide(Authentication authentication,
                       Object filter,
                       Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {
        if (configAttributes == null) {
            return;
        }
        //所请求的资源拥有的权限(一个URL资源对应多个角色)
        Iterator<ConfigAttribute> iterator = configAttributes.iterator();
        while (iterator.hasNext()) {
            ConfigAttribute configAttribute = iterator.next();
            //访问所请求资源所需要的权限
            String needPermission = configAttribute.getAttribute();
            //用户所拥有的权限authentication
            for (GrantedAuthority ga : authentication.getAuthorities()) {
                if (needPermission.equals(ga.getAuthority())) {
                    return;
                }
            }
        }
        //没有权限
        Object obj = authentication.getPrincipal();
        if(obj instanceof Employee){
            Employee employee = (Employee) obj;
            String realname = employee.getRealname();
            String requestResource = ((FilterInvocation) filter).getRequestUrl();
            LOGGER.warn(realname + "没有权限访问资源: " + requestResource);
            throw new AccessDeniedException("没有权限访问!");
        }else{
            LOGGER.warn("没有权限访问资源");
            throw new AccessDeniedException("没有权限访问!");
        }
    }

    @Override
    public boolean supports(ConfigAttribute attribute) {
        return true;
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }
}

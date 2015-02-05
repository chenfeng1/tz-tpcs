package com.tz.tpcs.service.security;

import com.tz.tpcs.service.ResourcesService;
import org.apache.log4j.Logger;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.util.Assert;

import javax.annotation.Resource;
import java.util.*;

/**
 * 维护URL与角色的映射关系
 * @author Hu Jing Ling
 * @since 2015/1/22 22:21
 * @version 1.0
 */
public class CustomSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

    private static final Logger LOGGER = Logger.getLogger(CustomSecurityMetadataSource.class);

    @Resource
    private ResourcesService resourcesService;

    private static Map<String, Collection<ConfigAttribute>> resourceMap = null;

    /**
     * empty constructor
     */
    public CustomSecurityMetadataSource() {
        LOGGER.debug("CustomSecurityMetadataSource empty constructor...");
    }

    @Override
    public Collection<ConfigAttribute> getAllConfigAttributes() {
        return new ArrayList<ConfigAttribute>();//这个方法未用到
    }

    @Override
    public boolean supports(Class<?> clazz) {
        return true;
    }

    /**
     * 加载URL与资源名的映射关系
     */
    private void loadResourceDefine() {
        resourceMap = new HashMap<>();
        Map<String,Set<String>> map = resourcesService.getRes2RoleMap();
        for(Map.Entry<String,Set<String>> entry : map.entrySet()){
            String key = entry.getKey();
            Set<String> value = entry.getValue();
            Collection<ConfigAttribute> configAttributes = new HashSet<>();
            for(String code : value){
                //以权限名封装为Spring的security Object
                ConfigAttribute configAttribute = new SecurityConfig(code);
                configAttributes.add(configAttribute);
            }
            resourceMap.put(key, configAttributes);
        }
    }

    /**
     * 返回所请求资源所需要的权限
     */
    @Override
    public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
        String requestUrl = ((FilterInvocation) object).getRequestUrl();
        LOGGER.debug("requestUrl is " + requestUrl);
        Assert.notNull(resourceMap);
        /*Collection<ConfigAttribute> requiredRoles = resourceMap.get(requestUrl);
        if(requiredRoles == null){
            //如果为空，则表示用户访问的是系统未提供的URL路径，即不存在的资源。
            throw new ResourceNotFoundException();
        }*/
        return resourceMap.get(requestUrl);
    }
}

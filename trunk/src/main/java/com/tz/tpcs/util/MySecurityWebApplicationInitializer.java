package com.tz.tpcs.util;

import com.tz.tpcs.SecurityConfig;
import org.apache.log4j.Logger;
import org.springframework.security.web.context.AbstractSecurityWebApplicationInitializer;

/**
 * 自定义 SecurityWebApplicationInitializer
 * 作用：
 * 1)在项目启动时，加载 Spring Security 配置信息; (相当于一个简化了的 ServletContextListener! )
 * 2)配置 Spring Security FilterChain Filter，将对每个 URL 资源进行安全过滤
 */
public class MySecurityWebApplicationInitializer extends AbstractSecurityWebApplicationInitializer {

    private static final Logger logger = Logger.getLogger(MySecurityWebApplicationInitializer.class);

    public MySecurityWebApplicationInitializer() {
        super(SecurityConfig.class);
        logger.debug("MySecurityWebApplicationInitializer empty constructor...");
    }

}

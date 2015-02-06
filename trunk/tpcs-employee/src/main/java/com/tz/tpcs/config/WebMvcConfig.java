package com.tz.tpcs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * 初始化 SpringMVC 控制层和相关组件
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/6 19:19
 */
@EnableWebMvc //启用注解 @Controller
@Configuration
@ImportResource("classpath:dispatcher-mvc.xml")
public class WebMvcConfig extends WebMvcConfigurerAdapter {
}

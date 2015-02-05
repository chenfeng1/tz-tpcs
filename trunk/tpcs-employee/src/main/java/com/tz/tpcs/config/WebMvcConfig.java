package com.tz.tpcs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

/**
 * Enable Web Mvc Config
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/5 16:41
 */
@EnableWebMvc
@Configuration
@ImportResource("classpath:dispatcher-mvc.xml")
public class WebMvcConfig extends WebMvcConfigurerAdapter {

}

package com.tz.tpcs.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
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
@ComponentScan(basePackages = "com.tz.tpcs.web")
public class WebMvcConfig extends WebMvcConfigurerAdapter {
}

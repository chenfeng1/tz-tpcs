//package com.tz.tpcs;
//
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
//import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.config.annotation.web.servlet.configuration.EnableWebMvcSecurity;
//
///**
//* Spring Security 配置信息类
//* 通过继承 WebSecurityConfigurerAdapter 获得默认配置，
//* 也可以 Override 方法，实现自定义配置内容
//*/
//@EnableWebMvcSecurity  //启用SpringMVC支持
//@EnableGlobalMethodSecurity(prePostEnabled = true) //提供对方法级别的 authorization 支持
//public class SecurityConfig extends WebSecurityConfigurerAdapter {
//
//    private static final Logger logger = Logger.getLogger(SecurityConfig.class);
//
//    /**
//     * defines which URL paths should be secured and which should not
//     * @param http
//     * @throws Exception
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        logger.debug("configure() run...");
//        http .csrf().disable();
//        http.authorizeRequests()
//                .antMatchers("/css*/**").permitAll() //配置各种公开资源
//                .antMatchers("/font/**").permitAll()
//                .antMatchers("/html/**").permitAll()
//                .antMatchers("/icon/**").permitAll()
//                .antMatchers("/images/**").permitAll()
//                .antMatchers("/js*/**").permitAll()
//                .anyRequest().authenticated()//其余所有 URL 必须 authentication 后才能访问
//                .and()
//            .formLogin()//提供登录页面
//                .loginPage("/login")//when authentication is required, redirect the browser to /login, or rendering the login page when /login is requested
//                .permitAll()  //但登录/登出页面，不必 authentication
//                .and()
//            .logout().permitAll()
//                .and()
//                .httpBasic();
//    }
//
//    /**
//     * sets up an in-memory user store with a single user.
//     * That user is given a username of "user",
//     * a password of "password",
//     * and a role of "USER".
//     * @param auth
//     * @throws Exception
//     */
//    @SuppressWarnings("SpringJavaAutowiringInspection")
//    @Autowired
//    public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
//        logger.debug("configureGlobal() run...");
//        auth.inMemoryAuthentication()
//                .withUser("user").password("password").roles("USER");
//    }
//
//}

package com.tz.tpcs.web;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.ModelAndViewAssert;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerAdapter;
import org.springframework.web.servlet.mvc.method.annotation.RequestMappingHandlerMapping;

import javax.annotation.Resource;

/**
* EmployeeController 单元测试类
* Created by hjl on 2015/1/18.
*/
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations={"file:src/main/webapp/WEB-INF/spring-mvc.xml"})
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEmployeeController {

    @Resource
    private RequestMappingHandlerAdapter handlerAdapter;
    @Resource
    private RequestMappingHandlerMapping handlerMapping;

    @Test
    public void test01LoginSuccess() throws Exception{
        String str = "EMP_001";
        String password = "123";
        ModelAndViewAssert.assertViewName(doLogin(str, password), "baseLayout");
    }

    @Test
    public void test02LoginFailed() throws Exception{
        String str = "EMP_001";
        String password = "1234";
        ModelAndViewAssert.assertViewName(doLogin(str, password), "login");
    }

    private ModelAndView doLogin(String str, String password) throws Exception {
        MockHttpServletRequest request = new MockHttpServletRequest();
        MockHttpServletResponse response = new MockHttpServletResponse();
        request.setRequestURI("/employees/login");
        request.setMethod("POST");
        request.setParameter("str", str);
        request.setParameter("password",password);

        Object handler = handlerMapping.getHandler(request).getHandler();
        ModelAndView mav = handlerAdapter.handle(request, response, handler);
        return mav;
    }

}

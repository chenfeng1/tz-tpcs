package com.tz.tpcs.web;

import org.junit.Test;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

/**
 * EmployeeController 单元测试类
 * Created by hjl on 2015/1/18.
 */
public class TestEmployeeController2 extends AbstractContextControllerTests {

    @Test
    public void test01LoginSuccess() throws Exception {
        mockMvc.perform(post("/employees/login")
                .param("str", "EMP_001")
                .param("password", "123"))
                .andExpect(view().name("baseLayout"));
    }

}

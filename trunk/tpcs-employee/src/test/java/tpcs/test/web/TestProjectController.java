package tpcs.test.web;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/26 16:14
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectController extends AbstractControllerTest {

    @Test
    public void test01List() throws Exception {
        mockMvc.perform(get("/projects/list").session(makeAuthSession("EMP_001", "123")))
                .andExpect(view().name("projects.list"))
                .andExpect(model().attributeExists("pager"));
    }

    @Test
    public void test02Delete() throws Exception {
        mockMvc.perform(get("/projects/delete?id=26bbdaec-3bca-41fa-8d87-5935e95c71ed").session(makeAuthSession("EMP_001", "123")))
                .andExpect(redirectedUrl("/projects/list"));
    }

    @Test
    public void test03Pager() throws Exception {
        mockMvc.perform(post("/projects/list")
                .param("name", "")
                .param("code", "")
                .param("pageNumber", "2")
                .session(makeAuthSession("EMP_001", "123")))
                .andExpect(view().name("projects.list"))
                .andExpect(model().attributeExists("name"))
                .andExpect(model().attributeExists("code"))
                .andExpect(model().attributeExists("pager"));
    }

}

package tpcs.test.service;

import com.tz.tpcs.entity.ProjectCase;
import com.tz.tpcs.service.ProjectCaseService;
import com.tz.tpcs.web.form.Pager;
import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;
import tpcs.test.dao.BaseTest;
import tpcs.test.dao.BaseTestNoTx;

import javax.annotation.Resource;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/27 14:20
 */
public class TestProjectCaseService extends BaseTestNoTx {

    @Resource
    private ProjectCaseService projectCaseService;

    @Test
    public void test01page(){
        //1.
        String name = "";
        String code = "";
        Pager<ProjectCase> pager = new Pager<>();
        pager.setPageNumber(2);
        //2.
        pager = projectCaseService.findByPager(name, code, pager);
        //3.
        for(ProjectCase pc : pager.getList()){
            System.out.println(pc);
        }
    }

    @Test
    public void test02CheckName(){
        //1.
        String name = "testProject1";
        //2.
        ProjectCase projectCase = projectCaseService.findByName(name);
        //3.
        System.out.println(projectCase);
    }

}

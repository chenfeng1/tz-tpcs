package tpcs.test.service;

import com.tz.tpcs.dao.ProjectCaseDao;
import com.tz.tpcs.entity.ProjectCase;
import com.tz.tpcs.service.ProjectCaseService;
import com.tz.tpcs.web.form.Pager;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.annotation.Resource;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/27 14:20
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectCaseService extends AbstractServiceTxTest {

    @Resource
    private ProjectCaseService projectCaseService;
    @Resource
    private ProjectCaseDao projectCaseDao;

    private ProjectCase projectCase;

    @Before
    public void before(){
        projectCase = new ProjectCase();
        projectCase.setName("testProject");
        projectCase.setCode("testCode");
        projectCase.setDesc("test desc");
    }

    @Test
    public void test01page(){
        DataFactory dataFactory = new DataFactory();
        for (int i = 1; i <= 10; i++) {
            ProjectCase projectCase = new ProjectCase();
            projectCase.setName("unitTestProject"+i);
            projectCase.setCode("unitTestCode"+i);
            if(i%2==0){
                projectCase.setDesc(dataFactory.getRandomText(300, 400));
            }else{
                projectCase.setDesc(dataFactory.getRandomText(10, 20));
            }
            projectCase.setSeq(i);
            projectCaseDao.save(projectCase);
        }

        //1.
        String name = "unittest";
        String code = "unittest";
        Pager<ProjectCase> pager = new Pager<>();
        pager.setPageNumber(1);
        //2.
        pager = projectCaseService.findByPager(name, code, pager);
        //3.
        Assert.assertEquals(pager.getList().size(), 2);
        Assert.assertEquals(new Integer(5), pager.getPageCount());
        for(ProjectCase pc : pager.getList()){
            System.out.println(pc);
        }
    }

    @Test
    public void test02CheckName(){
        projectCaseService.save(projectCase);
        ProjectCase projectCase = projectCaseService.findByName("testProject");
        Assert.assertEquals("testCode", projectCase.getCode());
    }

}

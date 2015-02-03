package tpcs.test.dao;

import com.tz.tpcs.dao.ProjectCaseDao;
import com.tz.tpcs.entity.ProjectCase;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.annotation.Resource;
import java.util.List;

/**
 * ProjectCaseDao 单元测试类
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/26 16:11
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestProjectCaseDao extends AbstractDaoTxTest {

    @Resource
    private ProjectCaseDao projectCaseDao;

    private DataFactory dataFactory = new DataFactory();

    @Test
    public void test01Save(){
        ProjectCase projectCase = new ProjectCase();
        projectCase.setName("testProject");
        projectCase.setCode("testCode");
        projectCase.setDesc("test desc");
        projectCaseDao.save(projectCase);
    }

    @Test
    public void test02List(){
        List<ProjectCase> list = (List<ProjectCase>) projectCaseDao.findAll();
        System.out.println(list);
    }

    @Test
    public void test03SaveBatch(){
        for (int i = 1; i <= 10; i++) {
            ProjectCase projectCase = new ProjectCase();
            projectCase.setName("testProject"+i);
            projectCase.setCode("testCode"+i);
            if(i%2==0){
                projectCase.setDesc(dataFactory.getRandomText(300, 400));
            }else{
                projectCase.setDesc(dataFactory.getRandomText(10, 20));
            }
            projectCase.setSeq(i);
            projectCaseDao.save(projectCase);
        }
    }

}

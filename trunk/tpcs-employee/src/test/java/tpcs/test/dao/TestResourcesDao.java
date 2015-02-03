package tpcs.test.dao;

import com.tz.tpcs.dao.ResourcesDao;
import com.tz.tpcs.entity.Resources;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.annotation.Resource;

/**
 * Created by Hu Jing Ling on 2015/1/19.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestResourcesDao extends AbstractDaoTxTest {

    @Resource
    private ResourcesDao resourcesDao;

    @Test
    public void test1(){
        Resources res = new Resources();
        res.setCode("test code");
        res.setType(Resources.Type.FOLDER);
        resourcesDao.save(res);
    }

    @Test
    public void test2(){
        Resources res = resourcesDao.findResEager("menu_clazz");
        System.out.println(res);
    }

}

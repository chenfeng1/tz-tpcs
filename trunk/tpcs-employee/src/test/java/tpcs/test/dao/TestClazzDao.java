package tpcs.test.dao;


import com.tz.tpcs.dao.ClazzDao;
import com.tz.tpcs.entity.Clazz;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * ClazzDao 单元测试类
 * @author 胡荆陵
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestClazzDao extends AbstractDaoTxTest {

    @Resource
    private ClazzDao clazzDao;
    
    private Clazz clazz;
    
    @Before
    public void initCustomer(){
        clazz = new Clazz();
        clazz.setName("testClazz");
    }

    @Test
    public void test01findOne(){
        clazzDao.save(clazz);
        Clazz c = clazzDao.findOne(clazz.getId());
        Assert.assertEquals(c, clazz);
    }

    @Test
    public void test02update(){
        clazzDao.save(clazz);
        Clazz c = clazzDao.findOne(clazz.getId());
        c.setStatus(Clazz.ClazzStatus.PHASE1);
        clazzDao.save(c);
    }

    @Test
    public void test03delete(){
        clazzDao.save(clazz);
        clazzDao.delete(clazz);
    }

    @Test
    public void test04BatchSave(){
        DataFactory df = new DataFactory();
        List<Clazz> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Clazz clazz = new Clazz();
            clazz.setName("JSD140" + i);
            clazz.setOpen(df.getBirthDate());
            clazz.setCount(df.getNumberBetween(10, 50));
            list.add(clazz);
        }
        clazzDao.save(list);
    }

    /**
     * 根据班级名来查找班级
     */
    @Test
    public void test06FindByName(){
        clazzDao.save(clazz);
    	Clazz c = clazzDao.findByName("testClazz");
    	System.out.println(c);
    }
}

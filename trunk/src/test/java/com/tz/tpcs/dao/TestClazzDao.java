package com.tz.tpcs.dao;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.web.form.Paging;

/**
 * ClazzDao 单元测试类
 */
@TransactionConfiguration(defaultRollback = true)//是否回滚测试数据
public class TestClazzDao extends BaseTest{

    @SuppressWarnings("SpringJavaAutowiringInspection")
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
    public void test04batchSave(){
        DataFactory df = new DataFactory();
        List<Clazz> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            Clazz c = new Clazz();
            c.setName("JSD" + df.getNumberBetween(1000, 9999));
            c.setOpen(df.getBirthDate());
            list.add(c);
        }
        clazzDao.save(list);
    }

    /**
     * 查所有
     */
    @Test
    public void testGetAll(){
    	List<Clazz> list = clazzDao.getAllByCondition(null,null,null);
    	System.out.println(list.size());
    }
    
    
    /**
     * 多条件总条数
     */
    @Test
    public void testPageSize(){
    	Long count = clazzDao.getRowCount("JSD1312", null, null);
    	System.out.println(count);
    }
    /**
     * 分页多条件查询
     */
    @Test
    public void testPage(){
    	Paging p = clazzDao.getAll(null, null, null, 4, 2);
    	List<Clazz> c = p.getClazzs();
    	for (Clazz clazz : c) {
			System.out.println(clazz);
		}
    }
    
    /**
     * 根据ID来删除
     */
    @Test
    public void testDel(){
    	clazzDao.delById("f074e503-e7a7-479e-a11a-98b4a43025b6");
    }
    /**
     * 根据ID来修改
     */
    @Test
    public void testUpdate(){
    	//Clazz clazz  = em.find(Clazz.class, "e4a7aba9-ccff-4550-a016-41a0d59e3932");
    	Clazz clazz = clazzDao.findOne("e4a7aba9-ccff-4550-a016-41a0d59e3932");
    	//Clazz clazz = new Clazz();
    	//clazz.setId("e4a7aba9-ccff-4550-a016-41a0d59e3932");
    	
    	//clazz.setAdvisor("曹妍");
    	clazz.setClaz_name("AAB");
    	//clazz.setName("JSD1111");
    	clazzDao.update(clazz);
    	
    }
    
    /**
     * 根据ID来查询
     */
    @Test
    public void testGetById(){
    	Clazz c = clazzDao.findOne("e4a7aba9-ccff-4550-a016-41a0d59e3932");
    	System.out.println(c);
    	
    }

}

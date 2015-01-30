package tpcs.test.dao;


import com.tz.tpcs.dao.ClazzDao;
import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.web.form.Paging;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import java.util.ArrayList;
import java.util.List;

/**
 * ClazzDao 单元测试类
 */
@TransactionConfiguration(defaultRollback = true)//是否回滚测试数据
public class TestClazzDao extends BaseTest{

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private ClazzDao clazzDao;
    
    private Clazz clazz;
    
    @Resource
    private EntityManager em;

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
    	Long count = clazzDao.getRowCount(null, null, null);
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
    	Clazz clazz = clazzDao.findOne("c2858af1-ca2d-43e4-ab34-37356eea73f9");
    	//Clazz clazz = new Clazz();
    	//clazz.setId("e4a7aba9-ccff-4550-a016-41a0d59e3932");
    	
    	//clazz.setAdvisor("曹妍");

    	clazz.setClaz_name("AABccc");

    	clazz.setRoom("AAB");

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
    
    @Test
    public void testPage2(){
    	
    	 //初始化
        CriteriaBuilder cb = em.getCriteriaBuilder();
        CriteriaQuery cq = cb.createQuery(Clazz.class);
        //同一组条件(name like %1%)
        Root<Clazz> root = cq.from(Clazz.class);
        Path<String> name = root.get("name");
        cq.where(cb.like(name, "%1%"));
        //后面有其他条件再可以添加...

        //1.先做分页查询
        List<Clazz> list = em.createQuery(cq)
                            .setFirstResult(0)
                            .setMaxResults(1)
                            .getResultList();
        System.out.println(list);
        //2.再做投影查询
        cq.select(cb.countDistinct(root));
        Long total = (Long) em.createQuery(cq).getSingleResult();
        System.out.println("total:"+total);
    	
    	//---------------------
    	
    	/*CriteriaBuilder cb = em.getCriteriaBuilder();
    	CriteriaQuery cq = cb.createQuery(Clazz.class);
    	Root<Clazz> root = cq.from(Clazz.class);
    	
    	Path<String> nameExp = root.get("name");
		cq.where(cb.like(nameExp, "%1%"));
		
		//1.projection
		cq.select(cb.count(root));
		int count = ((Long) em.createQuery(cq).getSingleResult()).intValue(); 
    	System.out.println("count:"+count);
    	
		//2.page
    	CriteriaQuery<Clazz> cq2 = cb.createQuery(Clazz.class);
    	//Root<Clazz> root2 = cq2.from(Clazz.class);
		Query query = em.createQuery(cq2);
		query.setFirstResult(0);
		query.setMaxResults(1);
		List list = query.getResultList();
		System.out.println(list);*/
    	//----------------
//    	Page<Clazz> page = clazzDao.findAll(new Specification<Clazz>() {
//			@Override
//			public Predicate toPredicate(Root<Clazz> root,
//					CriteriaQuery<?> query, CriteriaBuilder cb) {
//				// TODO Auto-generated method stub
//				//root = query.from(Clazz.class);//做关联查询
//				Path<String> nameExp = root.get("name");
//				Path<Date> d = root.get("open");
//				Path<String> nameExp2 = root.get("lector");
//				query.where(cb.like(nameExp, "%1%"));
//				//query.where(cb.like(nameExp2, "%sun%"));
//				query.orderBy(cb.desc(d));//按照日期降序
//				return null;
//			}
//		},new PageRequest(0,1));
//    	
//    	
//    	
//    	List<Clazz> list = page.getContent();
//    	for (Clazz clazz : list) {
//			System.out.println(clazz);
//		}
    }
    /**
     * 根据班级名来查找班级
     */
    @Test
    public void testFindByName(){
    	Clazz c = clazzDao.getByName("JSD1312");
    	System.out.println(c);
    }
}

package tpcs.test.service;

import com.tz.tpcs.dao.ClazzDao;
import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.service.ClazzService;
import com.tz.tpcs.web.form.Pager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.annotation.Resource;
import java.util.Date;

/**
 * clazzService 测试类
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestClazzService extends AbstractServiceTxTest {
	@Resource
	private ClazzService clazzService;
	
	@Resource
	private ClazzDao clazzDao;
	
	private Clazz clazz;
	
	@Before
	public void before(){
		clazz = new Clazz();
		clazz.setName("jsd");
		clazz.setRoom("110");
		clazz.setAdvisor("zp");
		clazz.setOpen(new Date());
		clazz.setLecturer("sy");
		clazz.setCount(10);
		clazz.setTrainingDate(new Date());
	}
	@Test
	public void test01getByName(){
		clazz=clazzService.findByName("aaa");
		System.out.println(clazz);
		
	}
	@Test
	public void test02save(){
		clazzService.save(clazz);
		Clazz clazz = clazzService.findByName("jsd");
		Assert.assertEquals("zp", clazz.getAdvisor());
	}
	
	@Test
	public void test03getById(){
		clazz=clazzService.getById("f738bd19-99f5-49ba-8a73-440ff83290bd");
		System.out.println(clazz);
	}
	
	@Test
	public void test04deleteById(){
		clazzDao.save(clazz);
		clazzService.deleteById(clazz.getId());
		Clazz clazz = clazzDao.findByName("jsd");
		Assert.assertNull(clazz);
	}
	
	@Test
	public void test05update(){
		clazzService.save(clazz);
		Clazz clazz = clazzService.findByName("jsd");
		clazz.setAdvisor("aaa");
		clazzService.update(clazz);
		Clazz temp = clazzService.findByName("jsd");
		System.out.println(temp.getAdvisor());
	}
	
	@Test
	public void test06page(){
		for(int i=0;i<20;i++){
			Clazz clazz = new Clazz();
			clazz.setName("test"+i);
			clazz.setCount(i);
			clazzService.save(clazz);
		}
		String name="te";
		Integer min = 10;
		Integer max =18;
		Pager<Clazz> pager = new Pager<>();
		pager.setPageSize(4);
		pager.setPageNumber(1);
		pager=clazzService.findByPager(null, min, max, pager);
		for(Clazz c :pager.getList()){
			System.out.println(c);
		}
	}
}

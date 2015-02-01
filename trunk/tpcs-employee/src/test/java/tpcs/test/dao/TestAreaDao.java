package tpcs.test.dao;

import com.tz.tpcs.dao.AreaDao;
import com.tz.tpcs.entity.Area;
import org.junit.Assert;
import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import java.util.List;

/**
 * @amender Hu Jing Ling
 * @since 2015-2-1
 */
@TransactionConfiguration(defaultRollback = true)//是否回滚测试数据
public class TestAreaDao extends BaseTest {

	@Resource
	private AreaDao areaDao;
	
	@Test
	public void testFindByLevel(){
		List<Area> areas = areaDao.findByLevel(1);
		Assert.assertNotNull(areas);
		for (Area area : areas) {
			System.out.println(area);
		}
	}
	
	@Test
	public void testFindByParentCode(){
		List<Area> areas = areaDao.findByParentCode("320000");
		Assert.assertNotNull(areas);
		for (Area area : areas) {
			System.out.println(area);
		}
	}

	@Test
	public void testFindNameByCode(){
		String name = areaDao.findNameByCode("110000");
		System.out.println(name);
	}
}

package tpcs.test.dao;

import com.tz.tpcs.dao.AreaDao;
import com.tz.tpcs.entity.Area;
import org.junit.Assert;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import javax.annotation.Resource;
import java.util.List;

/**
 * @amender Hu Jing Ling
 * @since 2015-2-1
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestAreaDao extends AbstractDaoTxTest {

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

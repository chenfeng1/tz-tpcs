package com.tz.tpcs.dao;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.tz.tpcs.entity.Area;

@TransactionConfiguration(defaultRollback = true)//是否回滚测试数据
public class TestAreaDao extends BaseTest{

	@Resource
	private AreaDao areaDao;
	
	@Test
	public void testFindBylevel(){
		List<Area> areas = areaDao.getAll(1);
		for (Area area : areas) {
			System.out.println(area);
		}
	}
	
	@Test
	public void testgetAreaId(){
		String areaId = areaDao.getAreaId("150000");
		System.out.println(areaId);
	}
	@Test
	public void testGetAreaByParentId(){
		List<Area> areas = areaDao.getAreaByParentId("89a61f0f-bdb3-4776-adc7-6da52a436df5");
		for (Area area : areas) {
			System.out.println(area);
		}
	}
	
	@Test
	public void testGetName(){
		System.out.println(areaDao.getName("110000"));
	}
}

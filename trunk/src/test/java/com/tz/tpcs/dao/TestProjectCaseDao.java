package com.tz.tpcs.dao;

import java.util.Date;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.tz.WebAppConfig;
import com.tz.tpcs.entity.ProjectCase;



/**
 * 项目案例单元测试类
 * @author guan
 * @since 2015-01-23
 */

/*@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebAppConfig.class)
@TransactionConfiguration(defaultRollback = false)//自动回滚测试数据
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@SuppressWarnings("SpringJavaAutowiringInspection")*/
@TransactionConfiguration(defaultRollback = false)//自动回滚测试数据
public class TestProjectCaseDao extends BaseTest{

	@Resource
	private ProjectCaseDao projectCaseDao;
	
    @Test
	public void test01Save(){
		System.out.println(projectCaseDao);
		ProjectCase p1 = new ProjectCase();
		p1.setCreateDate(new Date());
		p1.setModifyDate(new Date());
	    p1.setVersion(1);
		p1.setCode("book004");
		p1.setDesc("完成CRUD");
		p1.setName("电商1");
		p1.setSnapshot("/images/003.png");
		projectCaseDao.save(p1);
	}
}

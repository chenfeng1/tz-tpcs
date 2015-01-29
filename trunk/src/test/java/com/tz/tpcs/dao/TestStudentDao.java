package com.tz.tpcs.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Root;

import org.junit.Test;
import org.springframework.test.context.transaction.TransactionConfiguration;

import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.entity.Degree;
import com.tz.tpcs.entity.Gender;
import com.tz.tpcs.entity.Student;
import com.tz.tpcs.entity.Student.Level;
import com.tz.tpcs.entity.Student.LoanStatus;
import com.tz.tpcs.entity.Student.Source;
import com.tz.tpcs.entity.Student.Status;
import com.tz.tpcs.web.form.Paging;

/**
 * StudentDao 测试类
 * @author guan
 *
 */
@TransactionConfiguration(defaultRollback = false)//是否回滚测试数据
public class TestStudentDao extends BaseTest{
	@Resource
	private StudentDao studentDao;
	
	 @Resource
	 private EntityManager em;
	
	@Resource
	private ClazzDao clazzDao;
	
	@Test
	public void testFindAll(){
		List<Student> students = (List<Student>) studentDao.findAll();
		for (Student student : students) {
			System.out.println(student);
		}
	}
	@Test
	public void testSave(){
		List<Student> students = new ArrayList<>();
		Student s1 = new Student();
		s1.setBakPhone("15906128571");
		s1.setBirthDate(new Date());
		s1.setProvince("110000");
		s1.setCity("110100");
		s1.setCurrentScore(100.00);
		s1.setDegree(Degree.HIGH);
		s1.setCurrentLoc("江苏泰州");
		s1.setDesignDate(new Date());
		s1.setEmail("sfdf@qq.com");
		s1.setEmergencyContact("曹艳");
		s1.setEmergencyPhone("110");
		s1.setPhone("21313131");
		s1.setGender(Gender.MALE);
		s1.setIdentityCard("212121313131331");
		s1.setLevel(Level.FIVE);
		s1.setInitialScore(100.00);
		s1.setLoanStatus(LoanStatus.LOAN);
		s1.setMajor("计算机");
		s1.setSchool("常州大学");
		s1.setNeedDesign(true);
		s1.setPaid(0.0);
		s1.setQq("23423423423");
		s1.setRealname("管1");
		Clazz c = clazzDao.getByName("JSD1312");
		s1.setClazz(c);
		s1.setWorkingYears(1);
		s1.setSource(Source.VISIT);
		s1.setStatus(Status.SIGNED);
		s1.setWorkLoc("上海");
		s1.setGraduationDate(new Date());
		
		Student s2 = new Student();
		s2.setBakPhone("15906128571");
		s2.setBirthDate(new Date());
		s2.setProvince("120000");
		s2.setCity("120100");
		s2.setCurrentScore(100.00);
		s2.setDegree(Degree.DOCTOR);
		s2.setCurrentLoc("江苏泰州");
		s2.setDesignDate(new Date());
		s2.setEmail("sfdf@qq.com");
		s2.setEmergencyContact("曹艳");
		s2.setEmergencyPhone("110");
		s2.setPhone("21313131");
		s2.setGender(Gender.MALE);
		s2.setIdentityCard("212121313131331");
		s2.setLevel(Level.FIVE);
		s2.setInitialScore(100.00);
		s2.setLoanStatus(LoanStatus.CASH);
		s2.setMajor("计算机");
		s2.setSchool("常州大学");
		s2.setNeedDesign(true);
		s2.setPaid(0.0);
		s2.setQq("23423423423");
		s2.setRealname("管1");
		Clazz c1 = clazzDao.getByName("JSD2323");
		s2.setClazz(c1);
		s2.setWorkingYears(1);
		s2.setSource(Source.VISIT);
		s2.setStatus(Status.SIGNED);
		s2.setWorkLoc("上海");
		s2.setGraduationDate(new Date());
		
		Student s3 = new Student();
		s3.setBakPhone("15906128571");
		s3.setBirthDate(new Date());
		s3.setProvince("320000");
		s3.setCity("320100");
		s3.setCurrentScore(100.00);
		s3.setDegree(Degree.JUNIOR);
		s3.setCurrentLoc("江苏泰州");
		s3.setDesignDate(new Date());
		s3.setEmail("sfdf@qq.com");
		s3.setEmergencyContact("曹艳");
		s3.setEmergencyPhone("110");
		s3.setPhone("21313131");
		s3.setGender(Gender.MALE);
		s3.setIdentityCard("212121313131331");
		s3.setLevel(Level.FIVE);
		s3.setInitialScore(100.00);
		s3.setLoanStatus(LoanStatus.LOAN);
		s3.setMajor("计算机");
		s3.setSchool("常州大学");
		s3.setNeedDesign(true);
		s3.setPaid(0.0);
		s3.setQq("23423423423");
		s3.setRealname("管2");
		Clazz c2 = clazzDao.getByName("JSD2323");
		s3.setClazz(c2);
		s3.setWorkingYears(1);
		s3.setSource(Source.VISIT);
		s3.setStatus(Status.SIGNED);
		s3.setWorkLoc("上海");
		s3.setGraduationDate(new Date());
		students.add(s1);
		students.add(s2);
		students.add(s3);
		studentDao.save(students);
	}
	
	/**
	 * 测试多条件查询
	 */
	@Test
	public void testPaging(){
		Paging paging = studentDao.getStudentByCondition(null, null, null,null, null, null);
		List<Student> students = paging.getStudents();
		for (Student student : students) {
			System.out.println(student);
		}
	}
	
	/**
	 * 测试根据ID来查找Student
	 */
	@Test
	public void testGetById(){
			Student s =studentDao.findOne("51ac208f-e4f3-4cf5-bd1b-ed589f6837b4");
			System.out.println(s);
			String clazz_name = s.getClazz().getName();
			System.out.println(clazz_name);
			System.out.println(s.isNeedDesign());
	}
	/**
	 * 测试更新
	 */
	@Test
	public void testUpate(){
		Student s = em.find(Student.class, "7d50fe86-c472-43b9-9d63-388a565d7e08");
	//	em.clear();
		s.setRealname("管DD");
		em.merge(s);
		//em.getTransaction().commit();
		//studentDao.update(s);
	}
}


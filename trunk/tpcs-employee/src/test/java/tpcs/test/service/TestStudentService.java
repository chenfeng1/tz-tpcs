package tpcs.test.service;

import java.util.List;

import javax.annotation.Resource;

import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;

import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.entity.Degree;
import com.tz.tpcs.entity.ProjectCase;
import com.tz.tpcs.entity.Student;
import com.tz.tpcs.entity.Student.LoanStatus;
import com.tz.tpcs.entity.Student.Status;
import com.tz.tpcs.service.ClazzService;
import com.tz.tpcs.service.StudentService;
import com.tz.tpcs.web.form.Pager;

/**
 *studentService 测试类 
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestStudentService extends AbstractServiceTxTest {
	@Resource
	private StudentService studentService;
	
	@Resource
	private ClazzService clazzService;
	
	private Student student;
	
	@Before
	public void before(){
		student = new Student();
		student.setRealname("test");
		student.setStatus(Status.UNSIGNED);
		student.setMajor("aaa");
		student.setSchool("bbb");
		student.setDegree(Degree.HIGH);
		student.setLoanStatus(LoanStatus.CASH);
		Clazz clazz = clazzService.findByName("JSD1410");
		student.setClazz(clazz);
	}
	
	@Test
	public void test01findByPager(){
		Student student=null;
		for(int i=0;i<20;i++){
			student = new Student();
			student.setRealname("test"+i);
			student.setStatus(Status.UNSIGNED);
			student.setMajor("aaa");
			student.setSchool("bbb");
			student.setDegree(Degree.HIGH);
			student.setLoanStatus(LoanStatus.CASH);
			Clazz clazz = clazzService.findByName("JSD1410");
			student.setClazz(clazz);
			studentService.save(student);
		}
		Pager<Student> pager = new Pager<>();
	    pager.setPageNumber(1);
	    System.out.println(studentService.getById(student.getId()));
		pager=studentService.findByPager(null, null, null, null, null);
		System.out.println(pager.getList().size());
		for(Student s:pager.getList()){
			System.out.println(s);
		}
	}
	
	@Test
	public void test02update(){
		studentService.save(student);
		System.out.println(student.getId());
		Student s = studentService.getById(student.getId());
		s.setRealname("jack");
		studentService.update(s);
		Student temp = studentService.getById(s.getId());
		System.out.println(temp.getRealname());
	}
	
}

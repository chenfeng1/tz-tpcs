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
		Clazz clazz = clazzService.findByName("test");
		student.setClazz(clazz);
	}
	
	@Test
	public void test01findByPager(){
		for(int i=0;i<20;i++){
			Student student = new Student();
			student.setRealname("test"+i);
			student.setStatus(Status.UNSIGNED);
			student.setMajor("aaa");
			student.setSchool("bbb");
			student.setDegree(Degree.HIGH);
			student.setLoanStatus(LoanStatus.CASH);
			Clazz clazz = clazzService.findByName("test");
			student.setClazz(clazz);
			studentService.save(student);
		}
		Pager<Student> pager = new Pager<>();
	    pager.setPageNumber(2);
		pager=studentService.findByPager("te", "te", "HIGH", "CASH", pager);
		System.out.println(pager.getList().size());
		for(Student s:pager.getList()){
			System.out.println(s);
		}
	}
	
	@Test
	public void test02update(){
		studentService.save(student);
		student.setRealname("jack");
		studentService.update(student);
		Student temp = studentService.getById(student.getId());
		System.out.println(temp.getRealname());
	}
	
}

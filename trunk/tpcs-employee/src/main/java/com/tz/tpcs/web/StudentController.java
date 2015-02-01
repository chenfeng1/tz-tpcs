package com.tz.tpcs.web;

import com.tz.tpcs.dao.AreaDao;
import com.tz.tpcs.dao.ClazzDao;
import com.tz.tpcs.dao.StudentDao;
import com.tz.tpcs.entity.Area;
import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.entity.Degree;
import com.tz.tpcs.entity.Student;
import com.tz.tpcs.entity.Student.Level;
import com.tz.tpcs.entity.Student.LoanStatus;
import com.tz.tpcs.entity.Student.Source;
import com.tz.tpcs.entity.Student.Status;
import com.tz.tpcs.util.ResolveDate;
import com.tz.tpcs.web.form.Paging;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Student 控制器类
 * 
 * @author 管成功
 * @since 2015-01-26
 */
@RestController
@RequestMapping("/students")
public class StudentController {

	private static final Double SCORE=100.00;
	
	@Resource
	private AreaDao areaDao;
	
	@Resource
	private ClazzDao clazzDao;
	
	@Resource
	private StudentDao studentDao;
	
	/**
	 * 
	 * @param clazzname2
	 * @param realname2
	 * @param degree2
	 * @param loanStatus2
	 * @param pageSize
	 * @param pageNow
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/initList", method = RequestMethod.GET)
	public ModelAndView initList(String clazzname2,String realname2,String degree2,String loanStatus2,Integer pageSize,Integer pageNow,
								HttpServletRequest request) {
	
		Degree[] degree = Degree.values();
		
		Paging paging = studentDao.getStudentByCondition(clazzname2, realname2, degree2, loanStatus2, pageNow, pageSize);
		request.setAttribute("paging", paging);
		if(null!=clazzname2){
			request.setAttribute("clazz_name2", clazzname2);
		}
		if(null!=realname2){
			request.setAttribute("realname2", realname2);
		}
		if(null!=degree2 && !("-1").equals(degree2)){
			request.setAttribute("degree2", degree2);
		}
		if(null!=loanStatus2){
			request.setAttribute("loanStatus2", loanStatus2);
		}
		request.setAttribute("degree", degree);
		return new ModelAndView("student.list");
	}

	/**
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/initAdd", method = RequestMethod.GET)
	public ModelAndView initAdd(HttpServletRequest request) {
		
		Degree[] degree = Degree.values();//获取学历枚举常量的数组
		LoanStatus[] loanStatus = LoanStatus.values();//获取贷款状态的枚举常量的数组
		Source[] source = Source.values();//获取渠道来源的枚举常量的数组
		Level[] level = Level.values();//获取用户等级的枚举常量的数组
		Status[] status = Status.values();//获取签约状态的枚举常量的数组
		
		List<Area> areas = areaDao.findByLevel(1);
		Map<String, String> map = new LinkedHashMap<>();
		for (Area area : areas) {
			map.put(area.getCode(), area.getName());
		}
		request.setAttribute("degree", degree);
		request.setAttribute("loanStatus", loanStatus);
		request.setAttribute("map", map);
		request.setAttribute("source", source);
		request.setAttribute("level",level);
		request.setAttribute("status", status);
		return new ModelAndView("student.add");
	}

	/**
	 * 此处 日期类型不知为何无法自动封装到实体中？？
	 * 
	 * @param student
	 * @param clazzname
	 * @param email2
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public ModelAndView save(Student student, String clazzname, String email2,
			HttpServletRequest request) {
		
		// 根据班级名来查找班级
		Clazz clazz = clazzDao.findByName(clazzname);
		student.setClazz(clazz);
		// 生日
		student.setBirthDate(ResolveDate.stringToDate(request, "birthDate2"));
		// 毕业时间
		student.setGraduationDate(ResolveDate.stringToDate(request,
				"graduationDate2"));
		// 毕设时间
		student.setDesignDate(ResolveDate.stringToDate(request, "designDate2"));
		// 邮箱
		student.setEmail(email2);
		// 咨询专员--根据专员名称来查找Employee---待定！！！
		student.setInitialScore(SCORE);
		student.setCurrentScore(SCORE);
		studentDao.save(student);
		return new ModelAndView("/students/initList");
	}
	/**
	 * 
	 * @param sid
	 * @return
	 */
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public ModelAndView del(String sid) {
		studentDao.delete(sid);
		return new ModelAndView("/students/initList");
	}
	/**
	 * 
	 * @param sid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/initUpdate", method = RequestMethod.GET)
	public ModelAndView initUpdate(String sid,HttpServletRequest request) {
		
		Degree[] degree = Degree.values();
		LoanStatus[] loanStatus = LoanStatus.values();
		Source[]  source = Source.values();
		Level[] level = Level.values();
		Status[] status = Status.values();
		Student student = studentDao.findOne(sid);
		List<Area> areas = areaDao.findByLevel(1);
		Map<String, String> map = new LinkedHashMap<>();
		for (Area area : areas) {
			map.put(area.getCode(), area.getName());
		}
		request.setAttribute("map", map);
		request.setAttribute("student", student);
		if(student.getCity()!=null){
			request.setAttribute("city", areaDao.findNameByCode(student.getCity()));
		}else{
			request.setAttribute("city", "");
		}
		request.setAttribute("loanStatus", loanStatus);
		request.setAttribute("degree", degree);
		request.setAttribute("source", source);
		request.setAttribute("level", level);
		request.setAttribute("status", status);
		return new ModelAndView("student.update");
	}
	/**
	 * 
	 * @param student
	 * @param request
	 * @param clazzname3
	 * @param email3
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(Student student,HttpServletRequest request,
								String clazzname3,
								String email3) {
		Student s = studentDao.findOne(student.getId());
		student.setVersion(s.getVersion());
		Clazz c = clazzDao.findByName(clazzname3);
		student.setClazz(c);
		student.setEmail(email3);
		student.setGraduationDate(ResolveDate.stringToDate(request, "graduationDate3"));
		student.setBirthDate(ResolveDate.stringToDate(request, "birthDate3"));
		student.setDesignDate(ResolveDate.stringToDate(request, "designDate3"));
		student.setCurrentScore(s.getCurrentScore());
		studentDao.update(student);
		request.setAttribute("student", student);
		return new ModelAndView("/students/initList");
	}
}

package com.tz.tpcs.web;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.apache.commons.lang3.StringUtils;
import org.dozer.Mapper;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.tpcs.dao.AreaDao;
import com.tz.tpcs.dao.StudentDao;
import com.tz.tpcs.entity.Area;
import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.entity.Degree;
import com.tz.tpcs.entity.Student;
import com.tz.tpcs.entity.Student.Level;
import com.tz.tpcs.entity.Student.LoanStatus;
import com.tz.tpcs.entity.Student.Source;
import com.tz.tpcs.entity.Student.Status;
import com.tz.tpcs.service.ClazzService;
import com.tz.tpcs.service.StudentService;
import com.tz.tpcs.util.ResolveDate;
import com.tz.tpcs.web.form.Pager;
import com.tz.tpcs.web.form.StudentForm;

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
	private ClazzService clazzService;
	
	
	@Resource
	private StudentService studentService;
	
	@Resource
    private Mapper mapper;
	
	@Resource
	private MessageSource messageSource;
	
	@Resource
	private StudentDao studentDao;
	
	/**
	 * 列表
	 * @param modelmap
	 * @return
	 */
	@RequestMapping(value = "/initList", method = RequestMethod.GET)
	public ModelAndView initList(ModelMap modelmap) {
		Pager<Student> pager = studentService.findByPager(null, null, null, null, null);
		Degree[] degrees = Degree.values();
		modelmap.addAttribute("pager", pager);
		modelmap.addAttribute("degrees", degrees);
		return new ModelAndView("student.list",modelmap);
	}
	/**
	 * 分页+查询
	 * @param clazzName
	 * @param realName
	 * @param degree
	 * @param loanStatus
	 * @param modelmap
	 * @return
	 */
	@RequestMapping(value = "/initList", method = RequestMethod.POST)
	public ModelAndView listByPager(String clazzName,
									String realName,
									String degree,
									String loanStatus,
									Pager<Student> pager,
									ModelMap modelmap) {
		if("-1".equals(degree)){
			degree=null;
		}
		Degree[] degrees = Degree.values();
		Pager<Student> result = studentService.findByPager(clazzName, realName, degree, loanStatus, pager);
		modelmap.addAttribute("clazzName", clazzName);
		modelmap.addAttribute("realName", realName);
		modelmap.addAttribute("degree", degree);
		modelmap.addAttribute("loanStatus", loanStatus);
		modelmap.addAttribute("pager", result);
		modelmap.addAttribute("degrees", degrees);
		return new ModelAndView("student.list",modelmap);
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
			map.put(area.getDivisionCode(), area.getName());
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
	public ModelAndView save(ModelMap model,
							 @Valid @ModelAttribute("studentForm") StudentForm form,
							 BindingResult bindingResult,
							 String clazzName, 
							 String employeeName,
							 Locale locale
							 ) {
		//班级名的非空和存在验证
		Clazz clazz =null;
		if(StringUtils.isBlank(clazzName)){
			String msg = messageSource.getMessage("student.calzzName.blank", null,
					locale);
			bindingResult.addError(new FieldError("studentForm", "clazzName", msg));
		}else{
			clazz = clazzService.findByName(clazzName);
			if(clazz==null){
				String msg = messageSource.getMessage("student.calzzName.notFound", null,
						locale);
				bindingResult.addError(new FieldError("studentForm", "clazzName", msg));
			}
		}
		//日期格式验证
		if(StringUtils.isNotBlank(form.getBirthDate())){
			if(ResolveDate.formatDate(form.getBirthDate())==null){
				String msg = messageSource.getMessage("student.birth.format", null,
						locale);
				bindingResult.addError(new FieldError("studentForm", "birth", msg));
			}
		}
		if(StringUtils.isNotBlank(form.getDesignDate())){
			if(ResolveDate.formatDate(form.getDesignDate())==null){
				String msg = messageSource.getMessage("student.design.format", null,
						locale);
				bindingResult.addError(new FieldError("studentForm", "design", msg));
			}
		}
		if(StringUtils.isNotBlank(form.getGraduationDate())){
			if(ResolveDate.formatDate(form.getGraduationDate())==null){
				String msg = messageSource.getMessage("student.graduation.format", null,
						locale);
				bindingResult.addError(new FieldError("studentForm", "graduation", msg));
			}
		}
		
		
		if (bindingResult.hasErrors()) {
			model.addAttribute("student", form);
			model.addAttribute("errors", bindingResult.getAllErrors());
			List<Area> areas = areaDao.findByLevel(1);
			Map<String, String> map = new LinkedHashMap<>();
			for (Area area : areas) {
				map.put(area.getDivisionCode(), area.getName());
			}
			model.addAttribute("map", map);
			if(form.getCity()!=null){
				model.addAttribute("city", areaDao.findNameByCode(form.getCity()));
			}else{
				model.addAttribute("city", "");
			}
			model.addAttribute("clazzName", clazzName);
			return new ModelAndView("/students/initAdd",model);
		}
		
		Student student = mapper.map(form, Student.class);
		
		student.setClazz(clazz);
		// 咨询专员--根据专员名称来查找Employee---待定！！！
		student.setInitialScore(SCORE);
		student.setCurrentScore(SCORE);
		//如果有ID值 执行更新操作，如果没有则更新。
		if(StringUtils.isNotBlank(student.getId())){
			studentService.update(student);
		}else{
			studentService.save(student);
		}
		return new ModelAndView("redirect:/students/initList");
	}
	/**
	 * 
	 * @param sid
	 * @return
	 */
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public ModelAndView del(String sid) {
		studentService.deleteById(sid);
		return new ModelAndView("/students/initList");
	}
	/**
	 * 
	 * @param sid
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/initUpdate", method = RequestMethod.GET)
	public ModelAndView initUpdate(String sid,ModelMap model) {
		
		Student student = studentService.getById(sid);
		List<Area> areas = areaDao.findByLevel(1);
		Map<String, String> map = new LinkedHashMap<>();
		for (Area area : areas) {
			map.put(area.getDivisionCode(), area.getName());
		}
		model.addAttribute("student", student);
		if(student.getCity()!=null){
			model.addAttribute("city", areaDao.findNameByCode(student.getCity()));
		}else{
			model.addAttribute("city", "");
		}
		model.addAttribute("clazzName",student.getClazz().getName());
		
		return new ModelAndView("/students/initAdd",model);
	}

}

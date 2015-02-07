package com.tz.tpcs.web;

import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.regex.Pattern;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.service.ClazzService;
import com.tz.tpcs.util.ResolveDate;
import com.tz.tpcs.web.form.ClazzForm;
import com.tz.tpcs.web.form.Pager;

/**
 * Clazz 控制器类
 * 
 * @author 管成功
 * @since 2015-01-22
 */
@RestController
@RequestMapping("/clazz")
public class ClazzController {

	private static final int DURATION = 4; // 毕业时间---正式开班之后大约四个月

	@Resource
	private ClazzService classService;

	@Resource
	private MessageSource messageSource;
	
	@Resource
    private Mapper mapper;

	/**
	 * 调到新增班级的页面
	 * 
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/initAdd", method = RequestMethod.GET)
	public ModelAndView initAdd() {
		return new ModelAndView("clazz.add");
	}

	/**
	 * 列表
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list(ModelMap modelMap) {
		Pager<Clazz> pager = classService.findByPager(null, null, null, null);
		modelMap.addAttribute("pager", pager);
		return new ModelAndView("clazz.list",modelMap);
	}
	/**
	 * 分页+查询
	 * @param name
	 * @param min
	 * @param max
	 * @param pager
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.POST)
	public ModelAndView listByPager(String name,
									  String min,
									  String max,
									  Pager<Clazz> pager,
									  ModelMap modelMap) {
		Integer minCount =null;
		if(StringUtils.isNotBlank(min)&&StringUtils.isNumeric(min)){
			minCount = Integer.parseInt(min);
		}
		Integer maxCount =null;
		if(StringUtils.isNotBlank(max)&&StringUtils.isNumeric(max)){
			maxCount = Integer.parseInt(max);
		}
		Pager<Clazz> result = classService.findByPager(name, minCount, maxCount, pager);
		modelMap.addAttribute("name", name);
		modelMap.addAttribute("min", min);
		modelMap.addAttribute("max", max);
		modelMap.addAttribute("pager", result);
		return new ModelAndView("clazz.list",modelMap);
	}

	/**
	 * 保存一个班级信息
	 * @param model
	 * @param form
	 * @param bindingResult
	 * @param locale
	 * @return
	 */
	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public ModelAndView save(ModelMap model, @Valid @ModelAttribute("clazzForm") ClazzForm form,
			BindingResult bindingResult, Locale locale) {
		/**
		 * 班级名称的唯一性判断
		 */
		if (!bindingResult.hasFieldErrors("name")) {
			if (classService.findByName(form.getName()) != null) {
				String msg = messageSource.getMessage("clazz.name.used", null,
						locale);
				bindingResult.addError(new FieldError("clazzForm", "name", msg));
			}
		}
		/**
		 * 班级人数正整数判断
		 */
		if (!bindingResult.hasFieldErrors("count")) {
			if (!Pattern.matches("^[0-9]*[1-9][0-9]*$", form.getCount())) {
				String msg = messageSource.getMessage("clazz.count.reg", null,
						locale);
				bindingResult.addError(new FieldError("clazzForm", "count", msg));
			}
		}
		/**
		 * 开班日期的格式判断
		 */
		if (!bindingResult.hasFieldErrors("open")) {
			Date open=ResolveDate.formatDate(form.getOpen());
			if(open==null){
				String msg = messageSource.getMessage("clazz.open.format", null,
						locale);
				bindingResult.addError(new FieldError("clazzForm", "open", msg));
			}
		}
		/**
		 * 训练营日期的格式判断
		 */
		if (!bindingResult.hasFieldErrors("trainingDate")) {
			Date trainingDate =ResolveDate.formatDate(form.getTrainingDate());
			if(trainingDate==null){
				String msg = messageSource.getMessage("clazz.trainingDate.format", null,
						locale);
				bindingResult.addError(new FieldError("clazzForm", "trainingDate", msg));
			}
		}
		/**
		 * 如果有错误信息，返回add页面并且显示错误信息
		 */
		if (bindingResult.hasErrors()) {
			model.addAttribute("clazz", form);
			model.addAttribute("errors", bindingResult.getAllErrors());
			return new ModelAndView("clazz.add",model);
		}
		Clazz clazz = mapper.map(form, Clazz.class);
		classService.save(clazz);
		return new ModelAndView("redirect:/clazz/list");
	}

	/**
	 * 根据页面传过来的id值进行删除
	 * 
	 * @param request
	 *            HttpServletRequest
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public ModelAndView del(HttpServletRequest request) {
		String cid = request.getParameter("cid");
		classService.deleteById(cid);
		return new ModelAndView("/clazz/list");
	}
	/**
	 * 初始化跟新信息 AJAX实现
	 * @param id
	 * @return
	 */
	@RequestMapping(value = "/initUpdate", method = RequestMethod.POST)
	public Clazz initUpdate(@RequestParam String id){
		return classService.getById(id);
	}
	/**
	 * 更新前验证 AJAX实现
	 * @param form
	 * @param bindingResult
	 * @param usedname
	 * @param locale
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/validUpdate", method = RequestMethod.POST)
	public List<FieldError> validUpdate(@Valid ClazzForm form,
			BindingResult bindingResult, 
			@RequestParam String usedname,
			Locale locale,
			ModelMap model){
		/**
		 * 班级名称的唯一性判断
		 */
		if (!bindingResult.hasFieldErrors("name")) {
			if(!usedname.equals(form.getName())){
				if (classService.findByName(form.getName()) != null) {
					String msg = messageSource.getMessage("clazz.name.used",
							null, locale);
					bindingResult.addError(new FieldError("clazzForm", "name",
							msg));
				}
			}
		}
		/**
		 * 班级人数正整数判断
		 */
		if (!bindingResult.hasFieldErrors("count")) {
			if (!Pattern.matches("^[0-9]*[1-9][0-9]*$", form.getCount())) {
				String msg = messageSource.getMessage("clazz.count.reg", null,
						locale);
				bindingResult.addError(new FieldError("clazzForm", "count", msg));
			}
		}
		/**
		 * 开班日期的格式判断
		 */
		if (!bindingResult.hasFieldErrors("open")) {
			Date open=ResolveDate.formatDate(form.getOpen());
			if(open==null){
				String msg = messageSource.getMessage("clazz.open.format", null,
						locale);
				bindingResult.addError(new FieldError("clazzForm", "open", msg));
			}
		}
		/**
		 * 训练营日期的格式判断
		 */
		if (!bindingResult.hasFieldErrors("trainingDate")) {
			Date trainingDate =ResolveDate.formatDate(form.getTrainingDate());
			if(trainingDate==null){
				String msg = messageSource.getMessage("clazz.trainingDate.format", null,
						locale);
				bindingResult.addError(new FieldError("clazzForm", "trainingDate", msg));
			}
		}
		if (bindingResult.hasErrors()) {
			model.addAttribute("errors", bindingResult.getAllErrors());
			return bindingResult.getFieldErrors();
		}
		
		return null;
	}
	/**
	 * 更新
	 * @param form
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@Valid ClazzForm form
			) {
		Clazz clazz=mapper.map(form, Clazz.class);
		classService.update(clazz);
		return new ModelAndView("redirect:/clazz/list");
	}

	/**
	 * 查看班级是否存在
	 * 
	 * @param request
	 * @return 0代表班级不存在 1 代表存在的班级
	 */
	@RequestMapping(value = "/checkClazz", method = RequestMethod.POST)
	public String initAdd(HttpServletRequest request) {
		Clazz c=classService.findByName(request.getParameter("clazz_name"));
		String result = "";
		if (null == c) {
			result = "0";
		} else {
			result = "1";
		}
		return result;
	}
}

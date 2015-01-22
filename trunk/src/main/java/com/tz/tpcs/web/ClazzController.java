package com.tz.tpcs.web;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.tz.tpcs.dao.ClazzDao;
import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.entity.Clazz.ClazzStatus;
import com.tz.tpcs.service.ClazzService;
import com.tz.tpcs.web.form.ClazzForm;
import com.tz.tpcs.web.form.Paging;

/**
 * Clazz 控制器类
 * 
 * @author 管成功
 */
@RestController
@RequestMapping("/clazz")
public class ClazzController {

	@Resource
	private ClazzDao clazzDao;

	@Resource
	private ClazzService clazzSce;

	public ClazzDao getClazzDao() {
		return clazzDao;
	}

	public void setClazzDao(ClazzDao clazzDao) {
		this.clazzDao = clazzDao;
	}

	public ClazzService getClazzSce() {
		return clazzSce;
	}

	public void setClazzSce(ClazzService clazzSce) {
		this.clazzSce = clazzSce;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String hello() {
		return "Hello World!";
	}

	@RequestMapping(value = "/classes/{id}")
	public Clazz get(@PathVariable String id) {
		Clazz clazz = clazzDao.findOne(id);
		return clazz;
	}

	/*
	 * @RequestMapping(value = "/classes", method= RequestMethod.GET) public
	 * List<Clazz> list(){ List<Clazz> customerList = (List<Clazz>)
	 * clazzDao.findAll(); return customerList; }
	 */

	@RequestMapping(value = "/tiles", method = RequestMethod.GET)
	public ModelAndView demos() {
		return new ModelAndView("baseLayout");
	}

	@RequestMapping(value = "/tiles/test1", method = RequestMethod.GET)
	public ModelAndView demo1() {
		return new ModelAndView("demo.test1");
	}

	@RequestMapping(value = "/tiles/test2", method = RequestMethod.GET)
	public ModelAndView demo2() {
		return new ModelAndView("demo.test2");
	}

	/**
	 * 调到新增班级的页面
	 * 
	 * @return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public ModelAndView add() {
		// System.out.println("ddd");
		return new ModelAndView("addClass");
	}

	/**
	 * 展示所有班级信息(分页多条件查询)
	 * 
	 * @return
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list2(HttpServletRequest request) {
		String pageSize = request.getParameter("pageSize");
		pageSize = pageSize == null ? "2" : pageSize;
		String pageNow = request.getParameter("pageNow");
		pageNow = pageNow == null ? "1" : pageNow;

		String name2 = request.getParameter("name");
		String name = name2==null?"":name2;
		
		String min = request.getParameter("min");
		String max = request.getParameter("max");
		List<Clazz> list = null;
		Paging paging = null;
		if (min == null && max == null) {
			paging = clazzDao.getAll(name, null, null,
					Integer.valueOf(pageSize), Integer.valueOf(pageNow));

		} else if (min == "" && max == "") {
			paging = clazzDao.getAll(name, null, null,
					Integer.valueOf(pageSize), Integer.valueOf(pageNow));
		} else {
			paging = clazzDao.getAll(name, Integer.valueOf(min),
					Integer.valueOf(max), Integer.valueOf(pageSize),
					Integer.valueOf(pageNow));
		}
		list = paging.getClazzs();
		request.setAttribute("paging", paging);

		request.setAttribute("clazzList", list);
		if (min == null && max == null) {
			request.setAttribute("min", "");
			request.setAttribute("max", "");
		} else {
			request.setAttribute("min", min);
			request.setAttribute("max", max);
		}
		if (null == name || name.trim().length() < 0) {
			request.setAttribute("name", "");
		} else {
			request.setAttribute("name", name);
		}
		return new ModelAndView("listClass");
	}

	/**
	 * 保存一个班级信息
	 */
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public ModelAndView save(HttpServletRequest request) {

		Clazz c = new Clazz();
		c.setName(request.getParameter("ccname"));// 设置班级名称
		c.setClaz_name(request.getParameter("clazz_name"));// 设置所在教室
		String open_date = request.getParameter("open");
		Date open = null;
		try {
			open = new SimpleDateFormat("yyyy-MM-dd").parse(open_date);// 设置开班日期
			c.setOpen(open);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		c.setCount(Integer.valueOf(request.getParameter("count")));// 设置开班人数
		c.setAdvisor(request.getParameter("advisor"));// 设置班主任
		String training = request.getParameter("training_date");
		Date training_date = null;
		try {
			training_date = new SimpleDateFormat("yyyy-MM-dd").parse(training);
			c.setTraining_date(training_date);// 设置训练营开始日期
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		c.setLector(request.getParameter("lector"));
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + 4,
				cal.get(Calendar.DATE));
		c.setClose(cal.getTime());// 毕业时间---正式开班之后大约四个月
		c.setStatus(ClazzStatus.PHASE1);// 默认为第一阶段
		clazzSce.save(c);

		return new ModelAndView("/clazz/list");
	}

	/**
	 * 根据页面传过来的id值进行删除
	 */
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public ModelAndView del(HttpServletRequest request) {
		String cid = request.getParameter("cid");
		clazzDao.delById(cid);
		return new ModelAndView("/clazz/list");
	}

	@RequestMapping(value = "/listAll", method = RequestMethod.GET)
	public ModelAndView listAll(HttpServletRequest request) {
		List<Clazz> list = clazzDao.getAllByCondition(null, null, null);
		request.setAttribute("clazzList", list);
		return new ModelAndView("listClass");
	}

	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam String hid,
			@RequestParam String adviser, @RequestParam String classname,
			@RequestParam String count, @RequestParam String classroom,
			@RequestParam String teachername,

			HttpServletRequest request) {
		Clazz clazz = clazzDao.findOne(hid);
		clazz.setName(classname);
		clazz.setClaz_name(classroom);
		String open = request.getParameter("open_date");
		Date open_date;
		try {
			open_date = new SimpleDateFormat("yyyy-MM-dd").parse(open);
			String s = new SimpleDateFormat("yyyy-MM-dd").format(open_date);
			Date op = new SimpleDateFormat("yyyy-MM-dd").parse(s);
			clazz.setOpen(op);
			System.out.println(op);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		clazz.setCount(Integer.valueOf(count));
		clazz.setAdvisor(adviser);
		String tdate = request.getParameter("tdate");
		Date tt_date;
		try {
			tt_date = new SimpleDateFormat("yyyy-MM-dd").parse(tdate);

			clazz.setTraining_date(tt_date);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		clazz.setLector(teachername);
		clazzDao.update(clazz);
		return new ModelAndView("/clazz/list");
	}

}

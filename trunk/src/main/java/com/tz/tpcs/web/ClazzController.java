package com.tz.tpcs.web;
import com.tz.tpcs.dao.ClazzDao;
import com.tz.tpcs.entity.Clazz;
import com.tz.tpcs.entity.Clazz.ClazzStatus;
import com.tz.tpcs.service.ClazzService;
import com.tz.tpcs.web.form.Paging;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;


/**
 * Clazz 控制器类
 * @author 管成功
 * @since 2015-01-22
 */
@RestController
@RequestMapping("/clazz")
public class ClazzController {

	private static final int DURATION = 4; //毕业时间---正式开班之后大约四个月

	@SuppressWarnings("SpringJavaAutowiringInspection")
	@Resource
	private ClazzDao clazzDao;

	@Resource
	private ClazzService classService;

	 /**
     * 调到新增班级的页面
     * @return ModelAndView
     */
    @RequestMapping(value = "/initAdd", method= RequestMethod.GET)
    public ModelAndView initAdd(){
        return new ModelAndView("clazz.add");
    }

	/**
	 * 展示所有班级信息(分页多条件查询)
	 * @param request HttpServletRequest
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/list", method = RequestMethod.GET)
	public ModelAndView list2(HttpServletRequest request) {
		String pageSize = request.getParameter("pageSize");
		pageSize = null == pageSize ? "2" : pageSize;
		String pageNow = request.getParameter("pageNow");
		pageNow = null == pageNow ? "1" : pageNow;

		String name2 = request.getParameter("name");
		String name = null==name2?"":name2;
		
		String min = request.getParameter("min");
		String max = request.getParameter("max");
		List<Clazz> list = null;
		Paging paging = null;
		if (null == min && null == max) {
			paging = clazzDao.getAll(name, null, null,
					Integer.valueOf(pageSize), Integer.valueOf(pageNow));

		} else if (("").equals(min) && ("").equals(max)) {
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
		if (null == min && null == max) {
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
		return new ModelAndView("clazz.list");
	}

	/**
	 * 保存一个班级信息
	 * @param request HttpServletRequest
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/save", method = RequestMethod.GET)
	public ModelAndView save(HttpServletRequest request) {

		Clazz c = new Clazz();
		c.setName(request.getParameter("name"));// 设置班级名称
		c.setRoom(request.getParameter("room"));// 设置所在教室
		String openDate = request.getParameter("open");
		Date open = null;
		try {
			open = new SimpleDateFormat("yyyy-MM-dd").parse(openDate);// 设置开班日期
			c.setOpen(open);
		} catch (ParseException e) {
			e.printStackTrace();
		}

		c.setCount(Integer.valueOf(request.getParameter("count")));// 设置开班人数
		c.setAdvisor(request.getParameter("advisor"));// 设置班主任
		String training = request.getParameter("trainingDate");
		Date trainingDate = null;
		try {
			trainingDate = new SimpleDateFormat("yyyy-MM-dd").parse(training);
			c.setTrainingDate(trainingDate);// 设置训练营开始日期
		} catch (ParseException e) {
			e.printStackTrace();
		}
		c.setLecturer(request.getParameter("lecturer"));
		Calendar cal = Calendar.getInstance();
		cal.set(cal.get(Calendar.YEAR), cal.get(Calendar.MONTH) + DURATION,
				cal.get(Calendar.DATE));
		c.setClose(cal.getTime());// 毕业时间---正式开班之后大约四个月
		c.setStatus(ClazzStatus.PHASE1);// 默认为第一阶段
		classService.save(c);
		return new ModelAndView("/clazz/list");
	}

	/**
	 * 根据页面传过来的id值进行删除
	 * @param request HttpServletRequest
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/del", method = RequestMethod.GET)
	public ModelAndView del(HttpServletRequest request) {
		String cid = request.getParameter("cid");
		clazzDao.delById(cid);
		return new ModelAndView("/clazz/list");
	}

	/**
	 * 更新
	 * @param hid class id
	 * @param adviser adviser
	 * @param classname classname
	 * @param count count
	 * @param classroom classroom
	 * @param teachername teachername
	 * @param request HttpServletRequest
	 * @return ModelAndView
	 */
	@RequestMapping(value = "/update", method = RequestMethod.GET)
	public ModelAndView update(@RequestParam String hid,
			@RequestParam String adviser, @RequestParam String classname,
			@RequestParam String count, @RequestParam String classroom,
			@RequestParam String teachername,
			HttpServletRequest request) {
		Clazz clazz = clazzDao.findOne(hid);
		clazz.setName(classname);
		clazz.setRoom(classroom);
		String open = request.getParameter("open_date");
		Date openDate;
		try {
			openDate = new SimpleDateFormat("yyyy-MM-dd").parse(open);
			String s = new SimpleDateFormat("yyyy-MM-dd").format(openDate);
			Date op = new SimpleDateFormat("yyyy-MM-dd").parse(s);
			clazz.setOpen(op);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		clazz.setCount(Integer.valueOf(count));
		clazz.setAdvisor(adviser);
		String tdate = request.getParameter("tdate");
		Date ttDate;
		try {
			ttDate = new SimpleDateFormat("yyyy-MM-dd").parse(tdate);
			clazz.setTrainingDate(ttDate);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		clazz.setLecturer(teachername);
		clazzDao.update(clazz);
		return new ModelAndView("/clazz/list");
	}
	  /**
	   * 查看班级是否存在
	   * @param request
	   * @return 0代表班级不存在   1 代表存在的班级
	   */
	  @RequestMapping(value = "/checkClazz", method= RequestMethod.POST)
	  public String initAdd(HttpServletRequest request){
		  	String msg = clazzDao.getByName2(request.getParameter("clazz_name"));
		  	String result="";
		  	if(null!=msg){
		  		result="0";
		  	}else{
		  		result="1";
		  	}
		  	return result;
	  }
}

package com.tz.tpcs.web;

import com.tz.tpcs.dao.ClazzDao;
import com.tz.tpcs.entity.Clazz;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import java.util.List;

/**
 * Clazz 控制器类
 */
@RestController
@RequestMapping("/clazz")
public class ClazzController {

    @Resource
    private ClazzDao clazzDao;
    
    public ClazzDao getClazzDao() {
		return clazzDao;
	}

	public void setClazzDao(ClazzDao clazzDao) {
		this.clazzDao = clazzDao;
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
    public String hello(){
        return "Hello World!";
    }

    @RequestMapping(value = "/classes/{id}")
    public Clazz get(@PathVariable String id){
        Clazz clazz = clazzDao.findOne(id);
        return clazz;
    }

    @RequestMapping(value = "/classes", method= RequestMethod.GET)
    public List<Clazz> list(){
        List<Clazz> customerList = (List<Clazz>) clazzDao.findAll();
        return customerList;
    }

    @RequestMapping(value = "/tiles", method= RequestMethod.GET)
    public ModelAndView demos(){
        return new ModelAndView("baseLayout");
    }

    @RequestMapping(value = "/tiles/test1", method= RequestMethod.GET)
    public ModelAndView demo1(){
        return new ModelAndView("demo.test1");
    }

    @RequestMapping(value = "/tiles/test2", method= RequestMethod.GET)
    public ModelAndView demo2(){
        return new ModelAndView("demo.test2");
    }
    
    /**
     * 调到新增班级的页面
     * @return 
     */
    @RequestMapping(value = "/add", method= RequestMethod.GET)
    public ModelAndView add(){
    	//System.out.println("ddd");
        return new ModelAndView("addClass");
    }
    
    /**
     * 展示所有班级信息
     * @return
     */
    @RequestMapping(value = "/list", method= RequestMethod.GET)
    public ModelAndView list2(){
    	//System.out.println("ddd");
        return new ModelAndView("listClass");
    }
    
    /**
     * 保存一个班级信息
     */
    @RequestMapping(value="/save",method = RequestMethod.POST)
    public ModelAndView save(@ModelAttribute Clazz clazz,HttpServletRequest request,HttpServletResponse response){
    	//clazz = new Clazz();
    	System.out.println(clazz.getAdvisor());
    	System.out.println(clazz.getName());
    	return new ModelAndView("baseLayout");
    }
    
}

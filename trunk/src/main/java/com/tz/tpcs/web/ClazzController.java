package com.tz.tpcs.web;

import com.tz.tpcs.dao.ClazzDao;
import com.tz.tpcs.entity.Clazz;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

/**
 * Clazz 控制器类
 */
@RestController
@RequestMapping("/clazz")
public class ClazzController {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private ClazzDao clazzDao;

    @RequestMapping(value = "/", method= RequestMethod.GET)
    public List<Clazz> list(){
        List<Clazz> customerList = (List<Clazz>) clazzDao.findAll();
        return customerList;
    }

    @RequestMapping(value = "/clazz/{id}")
    public Clazz get(@PathVariable String id){
        Clazz clazz = clazzDao.findOne(id);
        return clazz;
    }

    /**
     * 展示所有班级信息
     * @return
     */
    @RequestMapping(value = "/list", method= RequestMethod.GET)
    public ModelAndView list2(){
        return new ModelAndView("listClass");
    }

    /**
     * 调到新增班级的页面
     * @return
     */
    @RequestMapping(value = "/initAdd", method= RequestMethod.GET)
    public ModelAndView add(){
        return new ModelAndView("addClass");
    }

}

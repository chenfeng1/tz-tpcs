package com.tz.tpcs.web;

import com.tz.tpcs.dao.DepartmentDao;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * 部门 控制器
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/4 18:02
 */
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    @Resource
    private DepartmentDao departmentDao;

    /**
     * 列表
     * @return ModelAndView
     */
    @RequestMapping(value = "/list", method= RequestMethod.GET)
    public ModelAndView list(ModelMap modelMap){
        departmentDao.findAll();
        return new ModelAndView("department.list", modelMap);
    }

}

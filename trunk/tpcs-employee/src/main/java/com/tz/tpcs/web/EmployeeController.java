package com.tz.tpcs.web;

import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.service.EmployeeService;
import com.tz.tpcs.web.form.Pager;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Employee Controller
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger LOGGER = Logger.getLogger(EmployeeController.class);

    @Resource
    private Mapper mapper;
    @Resource
    private MessageSource messageSource;
    @Resource
    private EmployeeService employeeService;

    /**
     * 列表
     * @return ModelAndView
     */
    @RequestMapping(value = "/list", method= RequestMethod.GET)
    public ModelAndView list(ModelMap modelMap){
        Pager<Employee> pager = employeeService.findByPager(null, null, null);
        modelMap.addAttribute("pager", pager);
        return new ModelAndView("employee.list", modelMap);
    }

}

package com.tz.tpcs.web;

import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.service.EmployeeService;
import com.tz.tpcs.web.form.Pager;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * Employee Controller
 */
@Controller
@RequestMapping("/employees")
public class EmployeeController {

    private static final Logger LOGGER = Logger.getLogger(EmployeeController.class);

    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private EmployeeService employeeService;


    /**
     * 列表
     * @return ModelAndView
     */
    @RequestMapping(value = "/list", method= RequestMethod.GET)
    public ModelAndView list(ModelMap modelMap){
        LOGGER.debug("list() run...");
        Pager<Employee> pager = employeeService.findByPager(null, null, null);
        modelMap.addAttribute("pager", pager);
        return new ModelAndView("employee.list", modelMap);
    }

    /**
     * 根据ID查询
     * @return ModelAndView
     */
    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public String id(@PathVariable String id, Model model){
        LOGGER.debug("id() run, id:"+id);
        model.addAttribute("emp", employeeDao.findOne(id));
        model.addAttribute("label", "编辑员工");
        return "/WEB-INF/jsp/employee/edit.jsp";
    }

    /**
     * 列表
     * @return ModelAndView
     */
    @RequestMapping(value = "/search", method= RequestMethod.POST)
    public String search(@RequestParam String deptId,
                         @RequestParam String realname,
                         Pager pager,
                         Model model){
        LOGGER.debug("search() run...");
//        Pager<Employee> pager = new Pager<>();
//        pager.setPageNumber(page);
        pager = employeeService.findByPager(deptId, realname, pager);
        model.addAttribute("pager", pager);
        model.addAttribute("realname", realname);
        model.addAttribute("deptId", deptId);
        return "/WEB-INF/jsp/employee/empList.jsp";
    }

}

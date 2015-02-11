package com.tz.tpcs.web;

import com.tz.tpcs.dao.DepartmentDao;
import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Department;
import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.entity.Gender;
import com.tz.tpcs.service.EmployeeService;
import com.tz.tpcs.web.form.AjaxResult;
import com.tz.tpcs.web.form.EmployeeEditForm;
import com.tz.tpcs.web.form.EmployeeSearchForm;
import com.tz.tpcs.web.form.Pager;
import com.tz.tpcs.web.validator.StepA;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import java.util.List;

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
    private DepartmentDao departmentDao;
    @Resource
    private EmployeeService employeeService;
    @Resource
    private Mapper mapper;

    /**
     * 列表
     * @return ModelAndView
     */
    @RequestMapping(value = "/list", method= RequestMethod.GET)
    public ModelAndView list(ModelMap modelMap){
        LOGGER.debug("list() run...");
        Pager<Employee> pager = employeeService.findByPager(null, null, null);
        modelMap.addAttribute("pager", pager);
        modelMap.addAttribute("form", new EmployeeSearchForm());
        return new ModelAndView("employee.list", modelMap);
    }

    /**
     * 根据ID查询
     * @return ModelAndView
     */
    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public String id(@PathVariable String id, Model model){
        LOGGER.debug("id() run, id:" + id);
        model.addAttribute("genderArray", Gender.values());
        model.addAttribute("form", employeeDao.findOne(id));
        List<Department> deptList = (List<Department>) departmentDao.findAll();
        model.addAttribute("deptList", deptList);
        return "/WEB-INF/jsp/employee/edit.jsp";
    }

    /**
     * 列表
     * @return ModelAndView
     */
    @RequestMapping(value = "/search", method= RequestMethod.POST)
    public String search(EmployeeSearchForm form,
                         Model model){
        LOGGER.debug("search() run...");
        Pager<Employee> pager = new Pager<>();
        pager.setPageNumber(form.getPageNumber());
        pager = employeeService.findByPager(form.getDeptId(), form.getRealname(), pager);
        model.addAttribute("pager", pager);
        model.addAttribute("form", form);
        return "/WEB-INF/jsp/employee/empList.jsp";
    }

    /**
     * 进入新增页面
     * @return String
     */
    @RequestMapping(value = "/initAdd", method= RequestMethod.GET)
    public String initAdd(@RequestParam String deptId,
                          Model model){
        LOGGER.debug("initAdd() run...");
        EmployeeEditForm form = new EmployeeEditForm();
        form.setGender(Gender.MALE);
        form.setDepartmentId(deptId);
        model.addAttribute("form", form);
        model.addAttribute("genderArray", Gender.values());
        model.addAttribute("deptList", departmentDao.findAll());
        return "/WEB-INF/jsp/employee/add.jsp";
    }

    /**
     * 新增
     * @return String
     */
    @RequestMapping(value = "/add", method= RequestMethod.POST)
    public String add(@Validated({EmployeeEditForm.Add.class, StepA.class}) EmployeeEditForm form,
                      BindingResult bindingResult,
                      Model model){
        LOGGER.debug("add() run...");
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("form", form);
            model.addAttribute("genderArray", Gender.values());
            model.addAttribute("deptList", departmentDao.findAll());
            return "/WEB-INF/jsp/employee/add.jsp";
        }else {
            Employee employee = mapper.map(form, Employee.class);
            String deptId = form.getDepartmentId();
            employee.setDepartment(departmentDao.findOne(deptId));
            employeeService.save(employee);
            Pager<Employee> pager = new Pager<>();
            pager = employeeService.findByPager(null, null, pager);
            model.addAttribute("pager", pager);
            model.addAttribute("form", new EmployeeSearchForm());
            return "/WEB-INF/jsp/employee/empList.jsp";
        }
    }

    /**
     * 根据ID查询
     * @return ModelAndView
     */
    @RequestMapping(value = "/update", method= RequestMethod.POST)
    public String update(Employee employee, Model model){
        LOGGER.debug("update() run...");

//        model.addAttribute("emp", employeeDao.findOne(id));
//        model.addAttribute("label", "编辑员工");
//        List<Department> deptList = (List<Department>) departmentDao.findAll();
//        model.addAttribute("deptList", deptList);
        return "redirect:/employees/list";
    }

    /**
     * 根据id数组批量删除
     * @param ids id数组
     * @return AjaxResult
     */
    @RequestMapping(value = "/deleteByArray", method= RequestMethod.POST, produces = IMediaType.APPLICATION_JSON_UTF8)
    @ResponseBody
    public AjaxResult deleteByArray(@RequestParam String[] ids){
        LOGGER.debug("deleteByArray() run...");
        employeeService.delete(ids);
        return new AjaxResult(true, null);
    }

    /**
     * 批量修改员工 Enable 状态
     * @param ids id数组
     * @param enableStatus Enable 状态
     * @return
     */
    @RequestMapping(value = "/changeEnableStatus", method= RequestMethod.POST, produces = IMediaType.APPLICATION_JSON_UTF8)
    @ResponseBody
    public AjaxResult changeEnableStatus(@RequestParam String[] ids, boolean enableStatus){
        LOGGER.debug("changeEnableStatus() run...");
        employeeService.updateEnableStatus(ids, enableStatus);
        return new AjaxResult(true, ids);
    }

}

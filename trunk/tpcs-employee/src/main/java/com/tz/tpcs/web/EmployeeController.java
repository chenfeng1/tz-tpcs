package com.tz.tpcs.web;

import com.tz.tpcs.dao.DepartmentDao;
import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Department;
import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.entity.Gender;
import com.tz.tpcs.service.EmployeeService;
import com.tz.tpcs.util.IConstant;
import com.tz.tpcs.web.form.AjaxResult;
import com.tz.tpcs.web.form.EmployeeEditForm;
import com.tz.tpcs.web.form.EmployeeSearchForm;
import com.tz.tpcs.web.form.Pager;
import com.tz.tpcs.web.validator.StepA;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Locale;

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
    @Resource
    private MessageSource messageSource;

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
     * 根据ID查询，进入更新页面
     * @return ModelAndView
     */
    @RequestMapping(value = "/{id}", method= RequestMethod.GET)
    public String id(@PathVariable String id, Model model){
        LOGGER.debug("id() run, id:" + id);
        model.addAttribute("genderArray", Gender.values());
        Employee employee = employeeDao.findOne(id);
        EmployeeEditForm form = mapper.map(employee, EmployeeEditForm.class);
        form.setPassword(null);
        form.setPasswordConfirm(null);
        form.setChangePassword(false);
        form.setDepartmentId(employee.getDepartment().getId());
        model.addAttribute("form", form);
        List<Department> deptList = (List<Department>) departmentDao.findAll();
        model.addAttribute("deptList", deptList);
        return "/WEB-INF/jsp/employee/edit.jsp";
    }

    /**
     * 根据ID查询
     * @return ModelAndView
     */
    @RequestMapping(value = "/update", method= RequestMethod.POST)
    public String update(@Validated(EmployeeEditForm.Update.class) EmployeeEditForm form,
                         BindingResult bindingResult,
                         Model model){
        LOGGER.debug("update() run...");
        if(bindingResult.hasErrors()){
            model.addAttribute("errors", bindingResult.getAllErrors());
            model.addAttribute("form", form);
            model.addAttribute("genderArray", Gender.values());
            model.addAttribute("deptList", departmentDao.findAll());
            return "/WEB-INF/jsp/employee/edit.jsp";
        }else {
            Employee employee = mapper.map(form, Employee.class);
            String deptId = form.getDepartmentId();
            employee.setDepartment(departmentDao.findOne(deptId));
            employeeService.checkPasswordAndUpdate(employee);
            Pager<Employee> pager = new Pager<>();
            pager = employeeService.findByPager(null, null, pager);
            model.addAttribute("pager", pager);
            model.addAttribute("form", new EmployeeSearchForm());
            return "/WEB-INF/jsp/employee/empList.jsp";
        }
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

    /**
     * 修改密码
     * @param form EmployeeEditForm
     * @param bindingResult BindingResult
     * @param session HttpSession
     * @return AjaxResult
     */
    @RequestMapping(value = "/changePassword", method= RequestMethod.POST, produces = IMediaType.APPLICATION_JSON_UTF8)
    @ResponseBody
    public AjaxResult changePassword(@Validated({EmployeeEditForm.ChangePassword.class})
                                    EmployeeEditForm form, BindingResult bindingResult,
                                    HttpSession session, Locale locale){
        LOGGER.debug("changeEnableStatus() run...");
        if(bindingResult.hasErrors()){
            return new AjaxResult(false, bindingResult.getAllErrors());
        }else {
            //获取session员工
            Employee sessionEmp = (Employee) session.getAttribute(IConstant.LOGIN_USER);
            //获取数据库员工
            Employee tempEmp = employeeDao.findOne(sessionEmp.getId());
            //判断是否和原密码一致
            if(tempEmp.getPassword().equals(form.getPassword())){
                String msg = messageSource.getMessage("new.password.equals.old.password", null, locale);
                bindingResult.addError(new ObjectError("password", msg));
                return new AjaxResult(false, bindingResult.getAllErrors());
            }else{
                //更新密码
                tempEmp.setPassword(form.getPassword());
                tempEmp.setChangePassword(false);
                employeeDao.save(tempEmp);
                //修改session标记
                sessionEmp.setChangePassword(false);
                return new AjaxResult(true, null);
            }
        }
    }

}

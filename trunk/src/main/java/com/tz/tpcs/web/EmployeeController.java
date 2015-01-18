package com.tz.tpcs.web;

import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.service.EmployeeService;
import com.tz.tpcs.util.IConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.support.RequestContext;
import org.springframework.web.servlet.support.RequestContextUtils;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

/**
 * Employee Controller
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;
    @Resource
    private MessageSource messageSource;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String str,
                              @RequestParam String password,
                              HttpSession session,
                              HttpServletRequest request,
                              ModelMap model,
                              Locale locale){
        Employee emp = employeeService.login(str, password);
        if(emp != null){
            session.setAttribute(IConstant.LOGIN_USER, emp);
            return new ModelAndView("baseLayout");
        }else{
            //获取国际化信息
            String msg = messageSource.getMessage("error.invalid.username.or.password", null, locale);
            model.addAttribute("passwordErrorMsg", msg);
            return new ModelAndView("forward:/login.jsp", model);
        }
    }

}

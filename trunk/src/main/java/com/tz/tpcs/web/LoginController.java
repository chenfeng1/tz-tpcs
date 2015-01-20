package com.tz.tpcs.web;

import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.service.EmployeeService;
import com.tz.tpcs.util.IConstant;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Locale;

/**
 * Created by hjl on 2015/1/18.
 */
@RestController
public class LoginController {

    @Resource
    private EmployeeService employeeService;
    @Resource
    private MessageSource messageSource;

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public ModelAndView initLogin(HttpServletRequest request){
        String path = (String) request.getServletContext().getAttribute(IConstant.PATH);
        return new ModelAndView("forward:"+path+"/login.jsp");
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String str,
                              @RequestParam String password,
                              HttpSession session,
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

    @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
    public ModelAndView loginSuccess(HttpServletRequest request){
        String path = (String) request.getServletContext().getAttribute(IConstant.PATH);
        return new ModelAndView("forward:"+path+"/classes");
    }

}

package com.tz.tpcs.web;

import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.service.EmployeeService;
import com.tz.tpcs.util.IConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.MessageSource;
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
 * Created by Administrator on 2015/1/16.
 */
@RestController
@RequestMapping("/employees")
public class EmployeeController {

    @Resource
    private EmployeeService employeeService;

    @Autowired
    private MessageSource messageSource;

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView login(@RequestParam String str,
                              @RequestParam String password,
                              HttpSession session,
                              Locale locale,
                              HttpServletRequest request){
        Employee emp = employeeService.login(str, password);
        if(emp != null){
            session.setAttribute(IConstant.LOGIN_USER, emp);
            return new ModelAndView("baseLayout");
        }else{
            ApplicationContext ctx = RequestContextUtils.getWebApplicationContext(request);
            Locale loc = RequestContextUtils.getLocale(request);
            Object[] arg = null;    //替换变量参数
            String mailmessage = messageSource.getMessage("error.invalid.username.or.password", arg, locale);
            Object o = Locale.CHINA;
            String msg = ctx.getMessage("error.invalid.username.or.password", arg, locale);
            Map<String,Object> model = new HashMap<>();
            model.put("errorMsg", msg);
            //...
            //从后台代码获取国际化信息
            RequestContext requestContext = new RequestContext(request);
            String sss = requestContext.getMessage("error.invalid.username.or.password");
            System.out.println(sss);
            return new ModelAndView("login", model);
        }
    }

}

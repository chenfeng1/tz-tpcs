package com.tz.tpcs.web;

import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.entity.Resources;
import com.tz.tpcs.entity.Role;
import com.tz.tpcs.service.ResourcesService;
import com.tz.tpcs.service.RoleService;
import com.tz.tpcs.util.IConstant;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 专门负责登录 / 登出的控制器
 * Created by hjl on 2015/1/18.
 */
@RestController
public class LoginController {

    @Resource
    private ResourcesService resourcesService;

    /**
     * 进入登录页面
     * @return
     */
    @RequestMapping(value = "/initLogin", method = RequestMethod.GET)
    public ModelAndView initLogin(){
        //todo...初始化  登录页面可能用到 所需数据...
        return new ModelAndView("forward:/login.jsp");
    }

    /**
     * 登录成功
     * @return
     */
    @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
    public ModelAndView loginSuccess(HttpSession session){
        //todo...初始化  登录成功后的欢迎页面 所需数据...
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        Employee employee = (Employee) auth.getPrincipal();
        Set<Resources> resourcesSet = resourcesService.findResByEmployeeId(employee.getId());
        session.setAttribute(IConstant.LOGIN_USER, employee);
        session.setAttribute(IConstant.LOGIN_USER_RES, resourcesSet);
        return new ModelAndView("baseLayout");
    }

    /**
     * 处理登录成功后直接访问根目录的情况
     * @return
     */
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public ModelAndView webRoot(){
        return new ModelAndView("redirect:/loginSuccess");
    }

    /**
     * 登出
     * @param session
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpSession session){
        session.invalidate();//清空 session
        return new ModelAndView("redirect:/login.jsp");
    }

}

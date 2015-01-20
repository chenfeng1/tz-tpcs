package com.tz.tpcs.web;

import com.tz.tpcs.service.ResourcesService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

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
        //todo...初始化登录页面可能用到的数据...
        return new ModelAndView("forward:/login.jsp");
    }

    /**
     * 登录成功
     * @return
     */
    @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
    public ModelAndView loginSuccess(){
        //todo...初始化欢迎页面所需数据...
        return new ModelAndView("baseLayout");
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

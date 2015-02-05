package com.tz.tpcs.web;

import com.tz.tpcs.service.ResourcesService;
import org.apache.log4j.Logger;
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

    private static final Logger LOGGER = Logger.getLogger(LoginController.class);

    @Resource
    private ResourcesService resourcesService;

    /**
     * empty constructor
     */
    public LoginController() {
        LOGGER.debug("LoginController empty constructor...");
    }

    /**
     * 登录成功
     * @return
     */
    @RequestMapping(value = "/loginSuccess", method = RequestMethod.GET)
    public ModelAndView loginSuccess(){
        LOGGER.debug("loginSuccess() run...");
        //todo...初始化  登录成功后的欢迎页面 所需数据...
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

package com.tz.tpcs.web;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

/**
 * ProjectCase 控制器类
 * @author guan
 * @since
 */
@RestController
@RequestMapping("/projects")
public class ProjectCaseController {
	
	 /**
     * 调到所有页面
     * @return
     */
    @RequestMapping(value = "/initList", method= RequestMethod.GET)
    public ModelAndView initAdd(){
        return new ModelAndView("case.list");
    }
}

package com.tz.tpcs.web;

import com.tz.tpcs.entity.ProjectCase;
import com.tz.tpcs.service.ProjectCaseService;
import com.tz.tpcs.web.form.Pager;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;

/**
 * ProjectCase 控制器
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/26 15:38
 */
@RestController
@RequestMapping("/projects")
public class ProjectCaseController {

    @Resource
    private ProjectCaseService projectCaseService;

    /**
     * 列表
     * @return ModelAndView
     */
    @RequestMapping(value = "/list", method= RequestMethod.GET)
    public ModelAndView list(ModelMap modelMap){
        Pager<ProjectCase> pager = projectCaseService.findByPager(null, null, null);
        modelMap.addAttribute("pager", pager);
        return new ModelAndView("projects.list", modelMap);
    }

    /**
     * 分页+查询
     * @param name 项目名
     * @param code 项目代号
     * @param pager 分页信息
     * @param modelMap 返回给视图的数据map
     * @return ModelAndView
     */
    @RequestMapping(value = "/list", method= RequestMethod.POST)
    public ModelAndView listByPager(String name,
                                    String code,
                                    Pager pager,
                                    ModelMap modelMap){
        Pager<ProjectCase> result = projectCaseService.findByPager(name, code, pager);
        modelMap.addAttribute("name", name);
        modelMap.addAttribute("code", code);
        modelMap.addAttribute("pager", result);
        return new ModelAndView("projects.list", modelMap);
    }
    /**
     * 跳转到新增页面
     * @return ModelAndView
     */
    @RequestMapping(value = "/initAdd", method= RequestMethod.GET)
    public ModelAndView initAdd(){
        return new ModelAndView("projects.edit");
    }

    /**
     * 新增
     * @return ModelAndView
     */
    @RequestMapping(value = "/add", method= RequestMethod.POST)
    public ModelAndView add(){
        return new ModelAndView("redirect:/projects/list");
    }

    /**
     * 删除
     * @return ModelAndView
     */
    @RequestMapping(value = "/delete", method= RequestMethod.GET)
    public ModelAndView remove(@RequestParam String id){
        projectCaseService.delete(id);
        return new ModelAndView("redirect:/projects/list");
    }

    /**
     * 跳转到更新页面
     * @return ModelAndView
     */
    @RequestMapping(value = "/initUpdate", method= RequestMethod.GET)
    public ModelAndView initUpdate(){
        return new ModelAndView("projects.edit");
    }

    /**
     * 更新
     * @return ModelAndView
     */
    @RequestMapping(value = "/update", method= RequestMethod.GET)
    public ModelAndView update(){
        return new ModelAndView("redirect:/projects/list");
    }
}



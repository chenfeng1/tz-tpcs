package com.tz.tpcs.web;

import com.tz.tpcs.entity.ProjectCase;
import com.tz.tpcs.service.ProjectCaseService;
import com.tz.tpcs.util.FileUtil;
import com.tz.tpcs.web.form.Pager;
import com.tz.tpcs.web.form.ProjectCaseForm;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.context.MessageSource;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.Locale;

/**
 * ProjectCase 控制器
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/1/26 15:38
 */
@RestController
@RequestMapping("/projects")
public class ProjectCaseController {

    private static final int SIMPLE_DESC_LEN = 100; //简单信息最大长度

    private static final Logger LOGGER = Logger.getLogger(ProjectCaseController.class);

    @Resource
    private ProjectCaseService projectCaseService;
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
        Pager<ProjectCase> pager = projectCaseService.findByPager(null, null, null);
        modelMap.addAttribute("pager", pager);
        modelMap.addAttribute("SIMPLE_DESC_LEN", SIMPLE_DESC_LEN);
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
        modelMap.addAttribute("SIMPLE_DESC_LEN", SIMPLE_DESC_LEN);
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
    public ModelAndView add(ModelMap model,
                            @Valid ProjectCaseForm form,
                            BindingResult bindingResult,
                            @RequestParam MultipartFile functionSpec,
                            @RequestParam MultipartFile snapshot,
                            Locale locale,
                            HttpServletRequest request){
        if(!bindingResult.hasFieldErrors("name")){
            //数据库检查项目名
            if(projectCaseService.existName(form.getName())){
                String msg = messageSource.getMessage("projectCase.name.used", null, locale);
                bindingResult.addError(new ObjectError("name", msg));
            }
        }
        if(!bindingResult.hasFieldErrors("code")){
            //数据库检查项目代号
            if(projectCaseService.existCode(form.getCode())){
                String msg = messageSource.getMessage("projectCase.code.used", null, locale);
                bindingResult.addError(new ObjectError("code", msg));
            }
        }
        if(bindingResult.hasErrors()){
            model.addAttribute("projectCase", form);
            model.addAttribute("errors", bindingResult.getAllErrors());
            return new ModelAndView("projects.edit");
        }
        ProjectCase projectCase = mapper.map(form, ProjectCase.class);
        //判断包含有上传的文件1:项目规格说明书
        if(functionSpec.getSize()>0){
            String realPath = request.getServletContext().getRealPath("/upload/projectCase/fs");
            String savedFileName = FileUtil.copyFileToPath(functionSpec, realPath);
            projectCase.setFunctionSpec(savedFileName);
        }
        //判断包含有上传的文件2:项目截图
        if(snapshot.getSize()>0){
            String realPath = request.getServletContext().getRealPath("/upload/projectCase/snapshot");
            String savedFileName = FileUtil.copyFileToPath(snapshot, realPath);
            projectCase.setSnapshot(savedFileName);
        }
        //保存
        projectCaseService.save(projectCase);
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
    public ModelAndView initUpdate(@RequestParam String id,
                                   ModelMap model){
        ProjectCase projectCase = projectCaseService.findById(id);
        model.addAttribute("projectCase", projectCase);
        return new ModelAndView("projects.edit", model);
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



package com.tz.tpcs.web;

import com.tz.tpcs.dao.DepartmentDao;
import com.tz.tpcs.entity.Department;
import com.tz.tpcs.service.DepartmentService;
import com.tz.tpcs.web.form.AjaxResult;
import com.tz.tpcs.web.form.DepartmentEditForm;
import com.tz.tpcs.web.json.DepartmentJson;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.context.MessageSource;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

/**
 * 部门 控制器
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/4 18:02
 */
@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private static final Logger LOGGER = Logger.getLogger(DepartmentController.class);

    @Resource
    private DepartmentDao departmentDao;
    @Resource
    private DepartmentService departmentService;
    @Resource
    private Mapper mapper;
    @Resource
    private MessageSource messageSource;

    /**
     * 列表
     * @return List<Department>
     */
    @RequestMapping(value = "/list", method= RequestMethod.GET, produces = IMediaType.APPLICATION_JSON_UTF8)
    public List<DepartmentJson> list(){
        LOGGER.debug("list() run...");
        List<Department> srcList = departmentService.getDeptTree();
        List<DepartmentJson> trgList = new ArrayList<>();
        for(Department dept : srcList){
            trgList.add(mapper.map(dept, DepartmentJson.class));
        }
        return trgList;
    }

    /**
     * 列表
     * @return AjaxResult
     */
    @RequestMapping(value = "/{id}", method= RequestMethod.DELETE, produces = IMediaType.APPLICATION_JSON_UTF8)
    public AjaxResult delete(@PathVariable String id){
        LOGGER.debug("delete() run, id:"+id);
        String result = departmentService.checkAndDelete(id);
        if("SUCCESS".equals(result)){
            List<Department> srcList = departmentService.getDeptTree();
            List<DepartmentJson> trgList = new ArrayList<>();
            for(Department dept : srcList){
                trgList.add(mapper.map(dept, DepartmentJson.class));
            }
            return new AjaxResult(true, trgList);
        }else{
            return new AjaxResult(false, result);
        }
    }

    /**
     * 新增
     * @return AjaxResult
     */
    @RequestMapping(value = "/add", method= RequestMethod.POST, produces = IMediaType.APPLICATION_JSON_UTF8)
    public AjaxResult add(@Validated(DepartmentEditForm.Add.class) DepartmentEditForm form,
                          BindingResult bindingResult,
                          Model model){
        LOGGER.debug("add() run...");
        if(bindingResult.hasErrors()){
            return new AjaxResult(false, bindingResult.getAllErrors());
        }else {
            Department department = new Department();
            department.setName(form.getName());
            if(StringUtils.isNotBlank(form.getParentId())){
                Department parent = departmentDao.findOne(form.getParentId());
                department.setParent(parent);
                department.setLevel(parent.getLevel()+1);
            }
            departmentDao.save(department);
            //显示最新的部门树，查询数据
            List<Department> srcList = departmentService.getDeptTree();
            List<DepartmentJson> trgList = new ArrayList<>();
            for(Department dept : srcList){
                trgList.add(mapper.map(dept, DepartmentJson.class));
            }
            return new AjaxResult(true, trgList);
        }
    }

    /**
     * 修改部门名称
     * @param form 表单数据
     * @param bindingResult 错误信息
     * @param model 数据模型
     * @return AjaxResult
     */
    @RequestMapping(value = "/updateName", method= RequestMethod.POST, produces = IMediaType.APPLICATION_JSON_UTF8)
    public AjaxResult updateName(@Validated(DepartmentEditForm.Update.class) DepartmentEditForm form,
                          BindingResult bindingResult,
                          Model model, Locale locale){
        LOGGER.debug("updateName() run...");
        if(bindingResult.hasErrors()){
            return new AjaxResult(false, bindingResult.getAllErrors());
        }else {
            //检查部门名称唯一(根据id)
            boolean b = departmentService.validateFieldWithId(form.getId(), form.getName());
            if(!b){
                String msg = messageSource.getMessage("department.name.already.exist", null, locale);
                bindingResult.addError(new ObjectError("code", msg));
                return new AjaxResult(false, bindingResult.getAllErrors());
            }else{
                Department department = departmentDao.findOne(form.getId());
                department.setName(form.getName());
                departmentDao.save(department);
                //获得部门树
                List<Department> srcList = departmentService.getDeptTree();
                List<DepartmentJson> trgList = new ArrayList<>();
                for(Department dept : srcList){
                    trgList.add(mapper.map(dept, DepartmentJson.class));
                }
                return new AjaxResult(true, trgList);
            }
        }
    }

}

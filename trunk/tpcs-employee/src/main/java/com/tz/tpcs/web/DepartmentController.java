package com.tz.tpcs.web;

import com.tz.tpcs.entity.Department;
import com.tz.tpcs.service.DepartmentService;
import com.tz.tpcs.web.form.AjaxResult;
import com.tz.tpcs.web.json.DepartmentJson;
import org.apache.log4j.Logger;
import org.dozer.Mapper;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

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
    private DepartmentService departmentService;
    @Resource
    private Mapper mapper;

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
     * @return List<Department>
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

}

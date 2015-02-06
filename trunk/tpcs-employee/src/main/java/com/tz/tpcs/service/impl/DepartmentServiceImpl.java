package com.tz.tpcs.service.impl;

import com.tz.tpcs.dao.DepartmentDao;
import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Department;
import com.tz.tpcs.service.DepartmentService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Set;

/**
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/6 11:30
 */
@Service
@Transactional
public class DepartmentServiceImpl implements DepartmentService {

    @Resource
    private DepartmentDao departmentDao;
    @Resource
    private EmployeeDao employeeDao;

    @Override
    public List<Department> getDeptTree() {
        List<Department> list = departmentDao.findByLevel(0);
        for(Department dept : list){
            recursiveInitChildren(dept);
        }
        return list;
    }

    @Override
    public String checkAndDelete(String id) {
        //1.检查是否有下属员工
        int count = employeeDao.getCountByDeptId(id);
        if(count > 0){
            return "该部门下面有员工，删除失败!";
        }
        //2.检查是否有下属部门
        Department department = departmentDao.findOne(id);
        if(department.getChildren().size() > 0){
            return "该部门下面有子部门，删除失败!";
        }
        //3.删除部门
        departmentDao.delete(id);
        return "SUCCESS";
    }

    /**
     * 递归初始化下属部门信息
     * @param dept 父部门对象
     */
    private void recursiveInitChildren(Department dept) {
        Set<Department> children = dept.getChildren();
        if(children != null && children.size() > 0){
            for(Department subDept : children){
                recursiveInitChildren(subDept);
            }
        }
    }
}
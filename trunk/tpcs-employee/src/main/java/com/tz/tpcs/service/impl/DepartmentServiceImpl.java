package com.tz.tpcs.service.impl;

import com.tz.tpcs.dao.DepartmentDao;
import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Department;
import com.tz.tpcs.service.DepartmentService;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;
import java.util.HashSet;
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

    private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);

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
    public Set<String> getSubDepartmentIds(String id) {
        Department department = departmentDao.findOne(id);
        Set<String> idSet = new HashSet<>();
        idSet.add(department.getId());
        recursiveAddChildrenId(department, idSet);
        return idSet;
    }

    /**
     * 递归添加子部门ID到集合中
     * @param department
     * @param idSet
     */
    private void recursiveAddChildrenId(Department department, Set<String> idSet) {
        Set<Department> children = department.getChildren();
        if(children != null && children.size() > 0){
            for(Department subDept : children){
                idSet.add(subDept.getId());
                recursiveAddChildrenId(subDept, idSet);
            }
        }
    }

    @Override
    public String checkAndDelete(String id) {
        //1.检查是否有下属部门
        Department department = departmentDao.findOne(id);
        if(department.getChildren() != null
                &&department.getChildren().size() > 0){
            return "该部门下面有子部门，删除失败!";
        }
        //2.检查是否有下属员工
        int count = employeeDao.getCountByDeptId(id);
        if(count > 0){
            return "该部门下面有员工，删除失败!";
        }
        //3.检查是否是根目录
        Department parent = department.getParent();
        if(parent == null){
            return "该部门是根目录，无法删除!";
        }
        //4.删除部门
        departmentDao.delete(id);
        return "SUCCESS";
    }

    @Override
    public boolean validateFieldWithId(String id, String name) {
        Department department = departmentDao.findByName(name);
        if(department == null){
            return true;
        }else{
            return department.getId().equals(id);
        }
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

    @Override
    public boolean validateField(final String fieldName, final String fieldValue, final String id) {
        LOGGER.debug("validateField() run...");
        Department department = departmentDao.findOne(new Specification<Department>() {
            @Override
            public Predicate toPredicate(Root<Department> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                //添加指定的属性条件
                Path<String> fieldPath = root.get(fieldName);
                Predicate p = cb.equal(cb.lower(fieldPath), fieldValue.toLowerCase());
                if(StringUtils.isNotBlank(id)){
                    //如果id不为空，则排除这个id
                    Path<String> idPath = root.get("id");
                    p = cb.and(p, cb.notEqual(idPath, id));
                }
                return p;
            }
        });
        return department == null;
    }
}

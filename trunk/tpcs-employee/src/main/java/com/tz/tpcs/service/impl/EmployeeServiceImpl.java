package com.tz.tpcs.service.impl;

import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Department;
import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.service.EmployeeService;
import com.tz.tpcs.util.IConstant;
import com.tz.tpcs.web.form.Pager;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.context.annotation.Profile;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.persistence.criteria.*;
import javax.transaction.Transactional;

/**
 * Created by Administrator on 2015/1/16.
 */
@Service
@Transactional
@Profile(IConstant.PROFILE_PRODUCTION)
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);

    @Resource
    private EmployeeDao employeeDao;

    /**
     * empty constructor
     */
    public EmployeeServiceImpl() {
        LOGGER.debug("EmployeeServiceImpl empty constructor...");
    }

    @Override
    public void update(Employee employee) {
        employeeDao.save(employee);
    }

    @Override
    public Employee findByPhoneNumberEmail(String str) {
        //先根据手机号查询
        Employee emp = employeeDao.findSingleByProp("mobilePhone", str);
        if(emp != null){
            return emp;
        }else{
            //再根据员工号查询
            emp = employeeDao.findSingleByProp("number", str);
            if(emp != null){
                return emp;
            }else {
                //最后根据邮箱查询
                emp = employeeDao.findSingleByProp("email", str);
                return emp != null? emp:null;
            }
        }
    }

    @Override
    public Pager<Employee> findByPager(final String departmentId, final String realname, Pager<Employee> pager) {
        if(pager == null){
            pager = new Pager<>();
        }

        Page<Employee> page = employeeDao.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p = cb.conjunction();
                if(StringUtils.isNotBlank(departmentId)){
                    // 条件1：根据部门ID查询
                    Join<Employee, Department> empJoinDept =
                            root.join(root.getModel()
                                    .getSingularAttribute("department", Department.class), JoinType.LEFT);
                    p = cb.and(p, cb.equal(empJoinDept.get("id").as(String.class), departmentId));
                }
                if(StringUtils.isNotBlank(realname)){
                    // 条件2：根据部门ID查询
                    Path<String> realnamePath = root.get("realname");
                    p = cb.and(p, cb.like(cb.lower(realnamePath), "%" + realname.toLowerCase() + "%"));
                }
                return p;
            }
        }, new PageRequest(pager.getPageNumber()-1, pager.getPageSize(), new Sort(Sort.Direction.DESC, "modifyDate")));

        pager.setList(page.getContent());
        Long lTotal = page.getTotalElements();
        pager.setTotalCount(lTotal.intValue());
        return pager;
    }
}

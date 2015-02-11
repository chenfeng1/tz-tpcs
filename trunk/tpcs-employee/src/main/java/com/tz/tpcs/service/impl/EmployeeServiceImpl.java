package com.tz.tpcs.service.impl;

import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Department;
import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.service.DepartmentService;
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
import java.util.Set;

/**
 * EmployeeService 实现类
 * Created by Administrator on 2015/1/16.
 */
@Service
@Transactional
@Profile(IConstant.PROFILE_PRODUCTION)
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger LOGGER = Logger.getLogger(EmployeeServiceImpl.class);

    /**
     * 分页+查询, 每页显示记录条数
     */
    private static final int PAGE_SIZE = 5;

    /**
     * 查询时包含下属子部门
     */
    private static final boolean INCLUDE_SUB_DEPARTMENT = true;

    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private DepartmentService departmentService;

    @Override
    public Employee findByPhoneNumberEmail(String str) {
        //先根据手机号查询
        Employee emp = employeeDao.findByMobilePhone(str);
        if(emp != null){
            return emp;
        }else{
            //再根据员工号查询
            emp = employeeDao.findByNumber(str);
            if(emp != null){
                return emp;
            }else {
                //最后根据邮箱查询
                emp = employeeDao.findByEmail(str);
                return emp != null? emp:null;
            }
        }
    }

    @Override
    public Pager<Employee> findByPager(final String departmentId, final String realname, Pager<Employee> pager) {
        if(pager == null){
            pager = new Pager<>();
        }
        pager.setPageSize(PAGE_SIZE);

        Page<Employee> page = employeeDao.findAll(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                Predicate p = cb.conjunction();
                if(StringUtils.isNotBlank(departmentId)){
                    // 条件1：根据部门ID查询
                    Join<Employee, Department> empJoinDept =
                            root.join(root.getModel()
                                    .getSingularAttribute("department", Department.class), JoinType.LEFT);
                    if(INCLUDE_SUB_DEPARTMENT){
                        //查询子部门ID集合
                        Set<String> idSet = departmentService.getSubDepartmentIds(departmentId);
                        p = cb.and(p, empJoinDept.get("id").as(String.class).in(idSet));
                    }else{
                        //仅匹配当前这个部门的ID
                        p = cb.and(p, cb.equal(empJoinDept.get("id").as(String.class), departmentId));
                    }
                }
                if(StringUtils.isNotBlank(realname)){
                    // 条件2：根据姓名查询
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

	@Override
	public void save(Employee employee) {
        //业务逻辑部分
        employee.setEnabled(true);
        employee.setAccountNonExpired(true);
        employee.setAccountNonLocked(true);
        employee.setCredentialsNonExpired(true);
		employeeDao.save(employee);
	}

    @Override
    public void delete(String[] ids) {
        //后期需要加入业务逻辑，比如：查询和员工相关的数据，是否有外键引用。。。
        for(String id : ids){
            employeeDao.delete(id);
        }
    }

    @Override
    public void updateEnableStatus(String[] ids, boolean enableStatus) {
        for(String id:ids){
            Employee emp = employeeDao.findOne(id);
            emp.setEnabled(enableStatus);
            employeeDao.save(emp);
        }
    }

    @Override
    public void checkPasswordAndUpdate(Employee employee) {
        //业务逻辑部分, todo...后期要完善
        Employee temp = employeeDao.findOne(employee.getId());
        //如果密码为空,说明无需更新,则仍然使用旧密码
        if(StringUtils.isNotBlank(employee.getPassword())){
            employee.setPassword(temp.getPassword());
        }
        //目前系统还无法给员工分配角色，所以角色信息仍然保留
        employee.setRoles(temp.getRoles());
        //剩余安全权限部份

        employeeDao.save(employee);
    }

    @Override
    public boolean validateField(final String fieldName, final String fieldValue, final String id) {
        LOGGER.debug("validateField() run...");
        Employee employee = employeeDao.findOne(new Specification<Employee>() {
            @Override
            public Predicate toPredicate(Root<Employee> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
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
        return employee == null;
    }
}

package com.tz.tpcs.service.security;

import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.entity.Role;
import com.tz.tpcs.service.EmployeeService;
import org.apache.log4j.Logger;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.HashSet;
import java.util.Set;

/**
 * Spring Security 自定义验证类
 */
@Service("myDetailsServiceImpl")
@Transactional
public class MyDetailsServiceImpl implements UserDetailsService {

    private static final Logger LOGGER = Logger.getLogger(MyDetailsServiceImpl.class);

    @Resource
    private EmployeeService employeeService;

    /** 空参构造 */
    public MyDetailsServiceImpl() {
        LOGGER.trace("MyDetailsServiceImpl empty constructor");
    }

    /**
     * 根据员工号匹配验证
     * @param username
     * @return
     * @throws org.springframework.security.core.userdetails.UsernameNotFoundException
     * @throws org.springframework.dao.DataAccessException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException, DataAccessException {
        Employee employee = employeeService.findByPhoneNumberEmail(username);
        if (employee == null) {
            throw new UsernameNotFoundException("Employee [" + username + "] not found!");
        }
        employee.setAuthorities(getGrantedAuthorities(employee));
        return employee;
    }

    /**
     * 获得管理角色数组
     * @param employee
     * @return
     */
    private Set<GrantedAuthority> getGrantedAuthorities(Employee employee) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        for (Role role : employee.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getCode()));
        }
        return grantedAuthorities;
    }

}

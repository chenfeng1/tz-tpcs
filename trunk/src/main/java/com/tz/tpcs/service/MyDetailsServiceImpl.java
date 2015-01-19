package com.tz.tpcs.service;

import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.entity.Role;
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
@Service
@Transactional
public class MyDetailsServiceImpl implements UserDetailsService {

    @SuppressWarnings("SpringJavaAutowiringInspection")
    @Resource
    private EmployeeDao employeeDao;

    @Override
    public UserDetails loadUserByUsername(String number) throws UsernameNotFoundException, DataAccessException {
        Employee employee = null;
        try {
            employee = employeeDao.getSingleByProp("number", number);
        } catch (Exception e) {
            throw new UsernameNotFoundException("Employee [" + number + "] not found");
        }
        if (employee == null) {
            throw new UsernameNotFoundException("Employee [" + number + "] not found!");
        }
        employee.setAuthorities(getGrantedAuthorities(employee));
        return employee;
    }

    // 获得管理角色数组
    private Set<GrantedAuthority> getGrantedAuthorities(Employee employee) {
        Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
        for (Role role : employee.getRoles()) {
            grantedAuthorities.add(new SimpleGrantedAuthority(role.getCode()));
        }
        return grantedAuthorities;
    }

}

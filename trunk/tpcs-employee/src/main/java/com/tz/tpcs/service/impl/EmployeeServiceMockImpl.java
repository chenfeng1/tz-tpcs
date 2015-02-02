package com.tz.tpcs.service.impl;

import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.service.EmployeeService;
import com.tz.tpcs.util.IConstant;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/1/16.
 */
@Service
@Profile(IConstant.PROFILE_TEST)
public class EmployeeServiceMockImpl implements EmployeeService {

    @Override
    public void update(Employee employee) {
        //empty implements
    }

    @Override
    public Employee findByPhoneNumberEmail(String str) {
        System.out.println("hehe");
        return null;
    }
}

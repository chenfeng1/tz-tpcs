package tpcs.test.service;

import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.service.EmployeeService;
import com.tz.tpcs.util.IConstant;
import com.tz.tpcs.web.form.Pager;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.springframework.test.context.ActiveProfiles;

import javax.annotation.Resource;

/**
 * Created by Administrator on 2015/1/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles(IConstant.PROFILE_PRODUCTION)
public class TestEmployeeService2 extends AbstractServiceTest{

    @Resource
    private EmployeeService employeeService;

    @Test
    public void test01FindByPager() {
        String departmentId = "691b45e9-0435-4df2-8eeb-2790b8c3260b";
        String employeeName = "测试";
        Pager<Employee> pager = employeeService.findByPager(departmentId, employeeName, null);
        System.out.println(pager);
    }

}

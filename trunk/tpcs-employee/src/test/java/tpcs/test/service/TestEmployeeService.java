package tpcs.test.service;

import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.service.impl.EmployeeServiceImpl;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.when;

/**
 * Created by Administrator on 2015/1/16.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestEmployeeService {

    @Mock
    private EmployeeDao employeeDao;
    @InjectMocks
    private EmployeeServiceImpl employeeService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void test01FindByPhoneNumberEmail() throws NoSuchFieldException {
        Employee emp1 = new Employee();
        emp1.setNumber("EMP_001");
        Employee emp2 = new Employee();
        emp2.setMobilePhone("13812341234");
        Employee emp3 = new Employee();
        emp3.setEmail("123@asd.com");

        when(employeeDao.findSingleByProp("number", "EMP_001"))
                .thenReturn(emp1);
        when(employeeDao.findSingleByProp("mobilePhone", "13812341234"))
                .thenReturn(emp2);
        when(employeeDao.findSingleByProp("email", "123@asd.com"))
                .thenReturn(emp3);

        assert emp1 == employeeService.findByPhoneNumberEmail("EMP_001");
        assert emp2 == employeeService.findByPhoneNumberEmail("13812341234");
        assert emp3 == employeeService.findByPhoneNumberEmail("123@asd.com");
    }

}

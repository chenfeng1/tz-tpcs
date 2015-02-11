package tpcs.test.web.security;

import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.entity.Role;
import com.tz.tpcs.service.EmployeeService;
import com.tz.tpcs.util.IConstant;
import com.tz.tpcs.web.form.Pager;
import org.apache.commons.lang3.StringUtils;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/1/16.
 */
@Service
@Profile(IConstant.PROFILE_MOCK)
public class EmployeeServiceMockImpl implements EmployeeService {

    @Override
    public void update(Employee employee) {
        //empty implements
    }

    @Override
    public Employee findByPhoneNumberEmail(String str) {
        if(StringUtils.isNotBlank(str)
                && str.equals("testUser")){
            Employee employee = new Employee();
            employee.setNumber("testUser");
            employee.setPassword("testPassword");

            employee.setEmail("EMP_001@sz-tz.com");
            employee.setMobilePhone("13811111111");
            employee.setRealname("mockUser");
            employee.setEnabled(true);
            employee.setAccountNonExpired(true);
            employee.setAccountNonLocked(true);
            employee.setCredentialsNonExpired(true);

            Role role = new Role("I'm a mock role", "mockRole", "mock测试用角色", true, 1);
            employee.addRole(role);

            return employee;
        }else
            return null;
    }

    @Override
    public Pager<Employee> findByPager(String DepartmentId, String EmployeeName, Pager<Employee> pager) {
        return null;
    }

	@Override
	public void save(Employee employee) {
		// TODO Auto-generated method stub
		
	}

    @Override
    public void delete(String[] ids) {

    }

    @Override
    public void updateEnableStatus(String[] ids, boolean enableStatus) {

    }

    @Override
    public boolean validateField(String fieldName, String fieldValue, String id) {
        return false;
    }
}

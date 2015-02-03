package tpcs.test.service;

import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.dao.ResourcesDao;
import com.tz.tpcs.dao.RoleDao;
import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.entity.Resources;
import com.tz.tpcs.entity.Role;
import com.tz.tpcs.service.impl.ResourcesServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runners.MethodSorters;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.*;

import static org.mockito.Mockito.when;

/**
 * Created by Hu Jing Ling on 2015/1/20.
 */
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class TestResourcesService {

    @Mock
    private ResourcesDao resourcesDao;
    @Mock
    private EmployeeDao employeeDao;
    @Mock
    private RoleDao roleDao;
    @InjectMocks
    private ResourcesServiceImpl resourcesService;

    @Before
    public void before(){
        MockitoAnnotations.initMocks(this);
        //init mock data
        Resources r1 = new Resources("TestName1", "res1", Resources.Type.FOLDER, "", null, null,1,true);
        Resources r11 = new Resources("TestName11", "res11", Resources.Type.URL, "/url11", r1, null,11,true);
        r1.getChildren().add(r11);
        Resources r2 = new Resources("TestName2", "res2", Resources.Type.FOLDER, "", null, null,2,true);
        Resources r21 = new Resources("TestName21", "res21", Resources.Type.URL, "/url21", r2, null,21,true);
        Resources r22 = new Resources("TestName22", "res22", Resources.Type.URL, "/url22", r2, null,22,true);
        r2.getChildren().add(r21);
        r2.getChildren().add(r22);
        Resources r3 = new Resources("TestName3", "res3", Resources.Type.FOLDER, "", null, null,3,true);
        Resources r31 = new Resources("TestName31", "res31", Resources.Type.URL, "/url31", r3, null,31,true);
        r3.getChildren().add(r31);

        Role role1 = new Role("TestRole1", "role1", "role1 description", true, 1);
        role1.getResources().add(r1);
        role1.getResources().add(r2);

        Role role2 = new Role("TestRole2", "role2", "role2 description", true, 2);
        role2.getResources().add(r2);
        role2.getResources().add(r3);

        Employee employee = new Employee();
        employee.addRole(role1).addRole(role2);
        //设定 mock stub
        when(employeeDao.findOne("testId")).thenReturn(employee);
        when(resourcesDao.findResEager("res1")).thenReturn(r1);
        when(resourcesDao.findResEager("res2")).thenReturn(r2);
        when(resourcesDao.findResEager("res3")).thenReturn(r3);
        Set<Resources> set = new HashSet<>();
        set.add(r22);
        when(resourcesDao.findResByType(Resources.Type.URL)).thenReturn(set);
        List<String> list = new ArrayList<>();
        list.add("role1");
        list.add("role2");
        when(roleDao.findCodesByResValue("/url22")).thenReturn(list);
    }

    @Test
    public void test1FindResByEmployee(){
        String[] array = new String[]{"res1", "res2"};
        List<Resources> list = resourcesService.findByCodes(array);
        Assert.assertEquals(5, list.size());
    }

    @Test
    public void test2FindResByEmployee(){
        Set<Resources> resourcesSet = resourcesService.findResByEmployeeId("testId");
        Assert.assertEquals(3, resourcesSet.size());
    }

    @Test
    public void test3FindResByEmployee(){
        Map<String,Set<String>> map = resourcesService.getRes2RoleMap();
        Assert.assertEquals(1, map.entrySet().size());
        Assert.assertEquals(2, map.get("/url22").size());
    }

}

package tpcs.test.init;

import java.io.InputStream;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;
import javax.transaction.Transactional;

import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import tpcs.test.json.JsonUtil;
import tpcs.test.xml.AreaDomParser;

import com.alibaba.fastjson.JSON;
import com.tz.tpcs.DaoConfig;
import com.tz.tpcs.dao.AreaDao;
import com.tz.tpcs.dao.DepartmentDao;
import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.dao.ProjectCaseDao;
import com.tz.tpcs.dao.ResourcesDao;
import com.tz.tpcs.dao.RoleDao;
import com.tz.tpcs.entity.Area;
import com.tz.tpcs.entity.Department;
import com.tz.tpcs.entity.Employee;
import com.tz.tpcs.entity.ProjectCase;
import com.tz.tpcs.entity.Resources;
import com.tz.tpcs.entity.Role;
import com.tz.tpcs.service.ResourcesService;
import com.tz.tpcs.service.RoleService;
import com.tz.tpcs.util.IConstant;

/**
 * 自动创建数据库 数据
 * Created by Hu Jing Ling on 2015/1/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DaoConfig.class, 
							locations = "classpath:spring/service-config.xml")
@Transactional
@TransactionConfiguration(defaultRollback = false)//自动回滚测试数据
@ActiveProfiles(IConstant.PROFILE_PRODUCTION)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InitData {
	
	private DataFactory dataFactory = new DataFactory();
	
	@Resource
	private ProjectCaseDao projectCaseDao;
	@Resource
	private AreaDao areaDao;
	@Resource
    private ResourcesDao resourcesDao;
    @Resource
    private ResourcesService resourcesService;
    @Resource
    private RoleDao roleDao;
    @Resource
    private RoleService roleService;
    @Resource
    private EmployeeDao employeeDao;
    @Resource
    private DepartmentDao departmentDao;

    @Test
    public void init01SaveResources(){
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("init/resources.json");
        String jsonStr = JsonUtil.getJsonFromResource(in);
        List<Resources> list = JSON.parseArray(jsonStr, Resources.class);
        resourcesDao.save(list);
    }

    @Test
    public void init02SaveRoles(){
    	InputStream in = this.getClass().getClassLoader().getResourceAsStream("init/roles.json");
    	String jsonStr = JsonUtil.getJsonFromResource(in);
    	List<Role> roles  = JSON.parseArray(jsonStr,Role.class);
    	for(Role r:roles){
    		if(r.getResourcesArray()!=null){
    			List<Resources> resourcesList =resourcesService.findByCodes(r.getResourcesArray());
    			r.getResources().addAll(resourcesList);
    		}
    	}
    	roleDao.save(roles);
    }

    @Test
    public void init03SaveDepartments(){
    	InputStream in = this.getClass().getClassLoader().getResourceAsStream("init/department.json");
    	String jsonStr = JsonUtil.getJsonFromResource(in);
    	List<Department> departments  = JSON.parseArray(jsonStr,Department.class);
    	departmentDao.save(departments);
    }    
    @Test
    public void init04SaveEmployees(){
    	InputStream in = this.getClass().getClassLoader().getResourceAsStream("init/employee.json");
    	String jsonStr = JsonUtil.getJsonFromResource(in);
    	List<Employee> employees  = JSON.parseArray(jsonStr,Employee.class);
    	for(Employee e :employees){
    		if(e.getDeptName()!=null){
    			Department dept = departmentDao.findByName(e.getDeptName());
    	        e.setDepartment(dept);
    		}
    		if(e.getRolesArray()!=null){
    			List<Role> roles = roleService.findByCodes(e.getRolesArray());
    			e.setRoles(new HashSet<Role>(roles));
    		}
    	}
    	employeeDao.save(employees);
    }
    
    @Test
    public void init05ProjectCase(){
        for (int i = 1; i <= 10; i++) {
            ProjectCase projectCase = new ProjectCase();
            projectCase.setName("testProject"+i);
            projectCase.setCode("testCode"+i);
            if(i%2==0){
                projectCase.setDesc(dataFactory.getRandomText(300, 400));
            }else{
                projectCase.setDesc(dataFactory.getRandomText(10, 20));
            }
            projectCase.setSeq(i);
            projectCaseDao.save(projectCase);
        }
    }
    
    @Test
    public void init06Area(){
    	AreaDomParser ad = new AreaDomParser();
		List<Area> areas = ad.getAreaFromXML("xml/area.xml");
		for (Area a : areas) {
			Area area = new Area();
			area.setName(a.getName());
			area.setZipCode(a.getZipCode());
			area.setDivisionCode(a.getDivisionCode());
			area.setLevel(1);
			areaDao.save(area);
            for (Area c : a.getChildren()) {
				Area area1 = new Area();
				area1.setName(c.getName());
				area1.setZipCode(c.getZipCode());
				area1.setDivisionCode(c.getDivisionCode());
				area1.setLevel(2);
				area1.setParent(area);
				areaDao.save(area1);
				if(c.getChildren() != null){
					for (Area l : c.getChildren()){
						Area area2 = new Area();
						area2.setName(l.getName());
						area2.setZipCode(l.getZipCode());
						area2.setDivisionCode(l.getDivisionCode());
						area2.setLevel(3);
						area2.setParent(area1);
						areaDao.save(area2);
					}
				}
			}
		}
    }
}

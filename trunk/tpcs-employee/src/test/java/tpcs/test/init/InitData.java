package tpcs.test.init;

import com.tz.tpcs.DaoConfig;
import com.tz.tpcs.dao.*;
import com.tz.tpcs.entity.*;
import com.tz.tpcs.entity.Resources.Type;
import com.tz.tpcs.service.ResourcesService;
import com.tz.tpcs.util.IConstant;
import org.fluttercode.datafactory.impl.DataFactory;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import tpcs.test.xml.AreaDomParser;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动创建数据库 数据
 * Created by Hu Jing Ling on 2015/1/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DaoConfig.class, locations = "classpath:spring/service-config.xml")
@TransactionConfiguration(defaultRollback = false)//自动回滚测试数据
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@ActiveProfiles(IConstant.PROFILE_PRODUCTION)
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
    private EmployeeDao employeeDao;
    @Resource
    private DepartmentDao departmentDao;

    @Test
    public void init01SaveResources(){
        List<Resources> list = new ArrayList<>();
        Resources r1 = new Resources("班级", "menu_clazz",Type.FOLDER, "", null, null,1,true);
        Resources r11 = new Resources("班级列表", "menu_clazz_list",Type.URL, "/clazz/list", r1, null,11,true);
        Resources r12 = new Resources("添加班级", "menu_clazz_initAdd",Type.URL, "/clazz/initAdd", r1, null,12,false);
        Resources r13 = new Resources("导入班级", "menu_clazz_import",Type.URL, "/clazz/initImport", r1, null,13,true);
        Resources r14 = new Resources("班级活动", "menu_clazz_campaign",Type.URL, "/clazzCampaign/list", r1, null,14,true);
//        Resources r15 = new Resources("测试用子资源1", "menu_clazz_test1",Type.URL, "/clazzCampaign/initList", r14, null,15,true);
//        Resources r16 = new Resources("测试用子资源2", "menu_clazz_test2",Type.URL, "/clazzCampaign/initList", r15, null,16,true);
        list.add(r1);
        list.add(r11);
        list.add(r12);
        list.add(r13);
        list.add(r14);
//        list.add(r15);
//        list.add(r16);

        Resources r2 = new Resources("学员", "menu_student",Type.FOLDER, "", null, null,2,true);
        Resources r21 = new Resources("学员列表", "menu_student_list",Type.URL, "/students/initList", r2, null,21,true);
        Resources r22 = new Resources("导入学员", "menu_student_import",Type.URL, "/students/initImport", r2, null,22,true);
        list.add(r2);
        list.add(r21);
        list.add(r22);

        Resources r3 = new Resources("知识库", "menu_knowledge",Type.FOLDER, "", null, null,3,true);
        Resources r31 = new Resources("课程列表", "menu_course_list",Type.URL, "/knowledge/list", r3, null,31,true);
        Resources r32 = new Resources("查看课程", "menu_view_course",Type.URL, "/knowledge/initView", r3, null,32,true);
        Resources r33 = new Resources("知识中心", "menu_knowledge_center",Type.URL, "/knowledge/initCenter", r3, null,33,true);
        list.add(r3);
        list.add(r31);
        list.add(r32);
        list.add(r33);

        Resources r4 = new Resources("项目案例", "menu_project",Type.FOLDER, "", null, null,4,true);
        Resources r41 = new Resources("项目列表", "menu_project_list",Type.URL, "/projects/list", r4, null,41,true);
        Resources r42 = new Resources("分配项目", "menu_assign_project",Type.URL, "/projects/initAssign", r4, null,42,true);
        Resources r43 = new Resources("进行中的项目", "menu_project_pending",Type.URL, "/projects/initPending", r4, null,43,true);
        Resources r44 = new Resources("项目追踪", "menu_track",Type.URL, "/knowledge/initTrack", r4, null,44,true);
        list.add(r4);
        list.add(r41);
        list.add(r42);
        list.add(r43);
        list.add(r44);

        Resources r5 = new Resources("员工", "menu_employee",Type.FOLDER, "", null, null,5,true);
        Resources r51 = new Resources("员工列表", "menu_employee_list",Type.URL, "/employees/list", r5, null,51,true);
        Resources r52 = new Resources("新增员工", "menu_add_employee",Type.URL, "/employees/initAdd", r5, null,52,true);
        Resources r53 = new Resources("通讯录", "menu_employee_addressBook",Type.URL, "/employees/addressBook", r5, null,53,true);
        list.add(r5);
        list.add(r51);
        list.add(r52);
        list.add(r53);

        resourcesDao.save(list);
    }

    @Test
    public void init02SaveRoles(){
        Role role1 = new Role("管理员", "admin", "系统所有模块权限", true, 1);
        Role role2 = new Role("班主任", "classTeacher", "班级、学员等模块", true, 2);
        Role role3 = new Role("讲师", "lecturer", "班级、学员、知识库、项目案例等模块", true, 3);

        List<Resources> resourcesList = (List<Resources>) resourcesDao.findAll();
        role1.getResources().addAll(resourcesList);
        roleDao.save(role1);

        List<Resources> resourcesList2 = resourcesService.findByCodes(new String[]{"menu_clazz", "menu_student"});
        role2.getResources().addAll(resourcesList2);
        roleDao.save(role2);

        List<Resources> resourcesList3 = resourcesService.findByCodes(new String[]{"menu_clazz", "menu_student", "menu_knowledge", "menu_project"});
        role3.getResources().addAll(resourcesList3);
        roleDao.save(role3);
    }

    @Test
    public void init04ProjectCase(){
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
    public void init05Area(){
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

    @Test
    public void init06Department(){
        List<Department> list = new ArrayList<>();
        Department dept1 = new Department("天智教育", null, 0, 1);
        list.add(dept1);
        list.add(new Department("教学部", dept1, 1, 1));
        list.add(new Department("市场招生部", dept1, 1, 2));
        list.add(new Department("渠道招生部", dept1, 1, 3));
        list.add(new Department("人力资源部", dept1, 1, 4));
        list.add(new Department("财务部", dept1, 1, 5));
        list.add(new Department("就业部", dept1, 1, 6));
        list.add(new Department("总经办", dept1, 1, 7));

        departmentDao.save(list);
    }

    @Test
    public void init07SaveEmployees(){
        Employee emp1 = new Employee();
        emp1.setNumber("EMP_001");
        emp1.setEmail("EMP_001@sz-tz.com");
        emp1.setMobilePhone("13811111111");
        emp1.setPassword("123");
        emp1.setRealname("测试管理员");
        emp1.setEnabled(true);
        emp1.setJob("测试管理员");
        emp1.setGender(Gender.MALE);
        emp1.setBirthDate(dataFactory.getBirthDate());
        emp1.setRemark(dataFactory.getRandomText(10,50));
        emp1.setAccountNonExpired(true);
        emp1.setAccountNonLocked(true);
        emp1.setCredentialsNonExpired(true);

        Role role1 = roleDao.findByCode("admin");
        emp1.addRole(role1);
        Department dept1 = departmentDao.findByName("天智教育");
        emp1.setDepartment(dept1);
        employeeDao.save(emp1);

        Employee emp2 = new Employee();
        emp2.setNumber("EMP_002");
        emp2.setEmail("EMP_002@sz-tz.com");
        emp2.setMobilePhone("13822222222");
        emp2.setPassword("456");
        emp2.setRealname("测试班主任");
        emp2.setEnabled(true);
        emp2.setJob("测试班主任");
        emp2.setGender(Gender.FEMALE);
        emp2.setBirthDate(dataFactory.getBirthDate());
        emp2.setRemark(dataFactory.getRandomText(10,50));
        emp2.setAccountNonExpired(true);
        emp2.setAccountNonLocked(true);
        emp2.setCredentialsNonExpired(true);

        Role role2 = roleDao.findByCode("classTeacher");
        emp2.addRole(role2);
        Department dept2 = departmentDao.findByName("教学部");
        emp2.setDepartment(dept2);
        employeeDao.save(emp2);


        Employee emp3 = new Employee();
        emp3.setNumber("EMP_003");
        emp3.setEmail("EMP_003@sz-tz.com");
        emp3.setMobilePhone("13833333333");
        emp3.setPassword("789");
        emp3.setRealname("测试讲师");
        emp3.setEnabled(true);
        emp3.setJob("测试讲师");
        emp3.setGender(Gender.MALE);
        emp3.setBirthDate(dataFactory.getBirthDate());
        emp3.setRemark(dataFactory.getRandomText(10,50));
        emp3.setAccountNonExpired(true);
        emp3.setAccountNonLocked(true);
        emp3.setCredentialsNonExpired(true);

        Role role3 = roleDao.findByCode("lecturer");
        emp3.addRole(role3);
        emp3.setDepartment(dept2);
        employeeDao.save(emp3);

        Employee emp4 = new Employee();
        emp4.setNumber("EMP_004");
        emp4.setEmail("");
        emp4.setMobilePhone("13844444444");
        emp4.setPassword("456789");
        emp4.setRealname("测试讲师兼职班主任");
        emp4.setEnabled(true);
        emp4.setJob("测试讲师兼职班主任");
        emp4.setGender(Gender.FEMALE);
        emp4.setBirthDate(dataFactory.getBirthDate());
        emp4.setRemark(dataFactory.getRandomText(10,50));
        emp4.setAccountNonExpired(true);
        emp4.setAccountNonLocked(true);
        emp4.setCredentialsNonExpired(true);

//        emp4.addRole(role1);
        emp4.addRole(role2);
        emp4.addRole(role3);
        emp4.setDepartment(dept2);
        employeeDao.save(emp4);
    }
}

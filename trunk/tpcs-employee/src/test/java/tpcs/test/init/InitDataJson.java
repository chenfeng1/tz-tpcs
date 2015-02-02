package tpcs.test.init;

import com.alibaba.fastjson.JSON;
import com.tz.tpcs.DaoConfig;
import com.tz.tpcs.dao.EmployeeDao;
import com.tz.tpcs.dao.ResourcesDao;
import com.tz.tpcs.dao.RoleDao;
import com.tz.tpcs.entity.Resources;
import com.tz.tpcs.service.ResourcesService;
import org.junit.FixMethodOrder;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.context.annotation.ImportResource;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import tpcs.test.json.JsonUtil;

import javax.annotation.Resource;
import javax.transaction.Transactional;
import java.io.InputStream;
import java.util.List;

/**
 * 自动创建数据库 数据
 * Created by Hu Jing Ling on 2015/1/20.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DaoConfig.class)
@ImportResource("classpath:spring/service-config.xml")
@TransactionConfiguration(defaultRollback = false)//自动回滚测试数据
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
@Ignore
public class InitDataJson {

    @Resource
    private ResourcesDao resourcesDao;
    @Resource
    private ResourcesService resourcesService;
    @Resource
    private RoleDao roleDao;
    @Resource
    private EmployeeDao employeeDao;

    @Test
    public void test01SaveResources(){
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("init/resources.json");
        String jsonStr = JsonUtil.getJsonFromResource(in);
        List<Resources> list = JSON.parseArray(jsonStr, Resources.class);
        resourcesDao.save(list);
    }

    @Test
    public void test02SaveRoles(){

    }

    @Test
    public void test03SaveEmployees(){

    }

}

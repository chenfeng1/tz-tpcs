package tpcs.test.init;

import com.tz.tpcs.DaoConfig;
import com.tz.tpcs.util.ConfigurationUtil;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import java.io.IOException;
import java.io.InputStream;

/**
 * 自动创建数据库表结构
 * Created by Hu Jing Ling on 2015/1/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = DaoConfig.class, locations = "classpath:spring/service-config.xml")
public class InitTable {

    @Resource
    private EntityManager em;

    @Test
    public void test01CreateTable() throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        Configuration cfg = ConfigurationUtil.init(em, in);
        SchemaExport schemaExport = new SchemaExport(cfg);
        schemaExport.create(true, true);
    }

}

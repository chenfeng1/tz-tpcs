package com.tz.tpcs.init;

import com.tz.WebAppConfig;
import com.tz.tpcs.util.ConfigurationUtil;
import org.hibernate.cfg.Configuration;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;

/**
 * 自动创建数据库表结构
 * Created by Hu Jing Ling on 2015/1/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebAppConfig.class)
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InitTable {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void test01CreateTable() throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        Configuration cfg = ConfigurationUtil.init(em, in);
        SchemaExport schemaExport = new SchemaExport(cfg);
//        schemaExport.create(true, false);
        schemaExport.create(true, true);
        Date date = new Date();
        date.toLocaleString();
        date.toGMTString();
    }

}

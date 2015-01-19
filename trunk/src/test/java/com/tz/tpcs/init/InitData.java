package com.tz.tpcs.init;

import com.tz.WebAppConfig;
import com.tz.tpcs.util.ConfigurationUtil;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.internal.SessionFactoryImpl;
import org.hibernate.tool.hbm2ddl.SchemaExport;
import org.junit.FixMethodOrder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.MethodSorters;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;

import javax.annotation.Resource;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.metamodel.ManagedType;
import javax.transaction.Transactional;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Administrator on 2015/1/19.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = WebAppConfig.class)
@TransactionConfiguration(defaultRollback = false)//自动回滚测试数据
@Transactional
@FixMethodOrder(MethodSorters.NAME_ASCENDING)
public class InitData {

    @PersistenceContext
    private EntityManager em;

    @Test
    public void test01CreateTable() throws IOException {
        InputStream in = this.getClass().getClassLoader().getResourceAsStream("application.properties");
        Configuration cfg = ConfigurationUtil.init(em, in);
        SchemaExport schemaExport = new SchemaExport(cfg);
//        schemaExport.create(true, false);
        schemaExport.create(true, true);
    }

    @Test
    public void test02InitResource(){
        
    }

}

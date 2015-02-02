package com.tz.tpcs.util;

import org.hibernate.cfg.Configuration;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.metamodel.ManagedType;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Set;

/**
 * Created by Administrator on 2015/1/19.
 */
public final class ConfigurationUtil {

    /** 私有空参构造 */
    private ConfigurationUtil() {
    }

    /**
     * 根据 EntityManager 和 配置文件输入流，初始化 Hibernate Configuration 实例
     * @param em
     * @param in
     * @return
     */
    public static Configuration init(EntityManager em, InputStream in) {
        Configuration cfg = new Configuration();
        EntityManagerFactory entityManagerFactory = em.getEntityManagerFactory();
        final Set<ManagedType<?>> managedTypes =
                entityManagerFactory.getMetamodel().getManagedTypes();
        for (ManagedType<?> managedType : managedTypes) {
            final Class<?> javaType = managedType.getJavaType();
            cfg.addAnnotatedClass(javaType);
        }
        Properties properties = new Properties();
        try {
            properties.load(in);
        } catch (IOException e) {
            e.printStackTrace();
        }

        String driver = properties.getProperty("spring.datasource.driver-class-name");
        String url = properties.getProperty("spring.datasource.url");
        String username = properties.getProperty("spring.datasource.username");
        String password = properties.getProperty("spring.datasource.password");
        String dialect = properties.getProperty("spring.jpa.properties.hibernate.dialect");

        cfg.setProperty("hibernate.connection.driver_class", driver)
                .setProperty("hibernate.connection.url" , url)
                .setProperty("hibernate.connection.username" , username)
                .setProperty("hibernate.connection.password", password)
                .setProperty("hibernate.dialect", dialect);
        return cfg;
    }
}

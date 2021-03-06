package com.tz.tpcs;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/** .
 *User: Hu Jing Ling
 * Date: 2014-09-24
 */
@Configuration  //当前类含有配置信息
@ComponentScan({"com.tz.tpcs.entity", "com.tz.tpcs.dao"})  //需要扫描的包
@EnableAutoConfiguration  //尽可能智能的对各种组件进行初始化，Entity EntityManager
@EnableJpaRepositories//提供 JPA 支持
@PropertySource("classpath:application.properties") //配置文件路径
public class DaoConfig {
}

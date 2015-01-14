package com.tz.tpcs;

import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * User: Hu Jing Ling
 * Date: 2014-09-24
 */
@Configuration
@ComponentScan(value = "com.tz.tpcs")
@EnableAutoConfiguration//used to search for @Entity items and entityManagerFactory ect.
@EnableJpaRepositories//auto generate table
@PropertySource("classpath:application.properties")
public class AppConfig {

}

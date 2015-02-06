package com.tz.tpcs.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * Spring Root Context
 * 负责加载Dao, Service, Security 这三个层的组件
 * @author Hu Jing Ling
 * @version 1.0
 * @since 2015/2/6 19:09
 */
@Configuration
@ImportResource("classpath:applicationContext.xml")
public class RootContextConfig {
}

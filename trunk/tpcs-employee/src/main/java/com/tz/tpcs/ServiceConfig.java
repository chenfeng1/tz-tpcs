package com.tz.tpcs;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/** .
 *User: Hu Jing Ling
 * Date: 2014-09-24
 */
@Configuration  //当前类含有配置信息
@ComponentScan("com.tz.tpcs.service")  //需要扫描的包
public class ServiceConfig {

}

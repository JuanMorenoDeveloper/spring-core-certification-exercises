package com.proitc.container.dependency.ioc.javaconfig;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;

@Configuration
@EnableScheduling
@ComponentScan(basePackages = { "com.proitc.bean.scheduled" })
public class ScheduledConfig {

}

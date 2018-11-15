package com.proitc.test;

import com.proitc.bean.DataSource;
import com.proitc.bean.MailService;
import com.proitc.container.dependency.ioc.javaconfig.JavaConfig;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

//How do you configure a profile. What are possible use cases where they might be useful? 

@ExtendWith(SpringExtension.class)
@ContextConfiguration(classes = JavaConfig.class) //JavaConfig class
@ActiveProfiles(profiles = "development")
//@ActiveProfiles(profiles="production") //comment out this one to get production DataSource
public class ConfigProfileIntegrationTest {
  private static final Logger log = LoggerFactory.getLogger(ConfigProfileIntegrationTest.class);
  @Autowired private ApplicationContext context;

  @Test
  public void tessDataSourceType() {

    //We will get TEST Database using profile
    DataSource dataSource = context.getBean(DataSource.class);
    assertThat(dataSource).isNotNull();
    log.debug("URL DataSource " + dataSource.getUrl());
    log.debug("Username DataSource " + dataSource.getUsername());
    log.debug("Password DataSource " + dataSource.getPassword());

    //Regular beans in XML Config
    MailService mailService = context.getBean(MailService.class);
    assertThat(mailService).isNotNull();
    mailService.sendMessage("Test Mail Service Email XML Profile");

  }

}

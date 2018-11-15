package com.proitc.test;

import com.proitc.bean.MailService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

//How are you going to create an ApplicationContext in an integration test or a JUnit test? 

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:application-context.xml" })
public class MailServiceIntegrationTest /*implements ApplicationContextAware*/ {

  @Autowired private ApplicationContext context;

	/*public void setApplicationContext(ApplicationContext context)
			throws BeansException {
		mailService = context.getBean(MailService.class);
		Assert.assertNotNull(mailService);
	}
	*/

  @Test
  public void testEmail() {
    MailService mailService = context.getBean(MailService.class);
    assertThat(mailService).isNotNull();
    mailService.sendMessage("Test Mail Service Email");

  }

}

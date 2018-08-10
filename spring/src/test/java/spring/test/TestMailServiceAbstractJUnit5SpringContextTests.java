package spring.test;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import spring.bean.MailService;

//How are you going to create an ApplicationContext in an integration test or a JUnit test? 
@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:application-context.xml"})
public class TestMailServiceAbstractJUnit5SpringContextTests  implements ApplicationContextAware {
  private ApplicationContext context;

  public void setApplicationContext(ApplicationContext context)
    throws BeansException {
    this.context = context;

  }
  @Test
  public void testEmail() {
    MailService mailService = context.getBean(MailService.class);
    assertThat(mailService).isNotNull();
    mailService.sendMessage("Test Mail Service Email");

  }


}

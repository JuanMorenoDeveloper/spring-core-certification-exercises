package com.proitc.container.dependency.ioc.config.xml;

import static org.assertj.core.api.Assertions.assertThat;

import com.proitc.bean.User;
import com.proitc.bean.UserManager;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;

@ContextConfiguration("classpath:application-context.xml")
public class DependencyInjectionXMLConfigIntegrationTest extends AbstractJUnit4SpringContextTests {
  private static final Logger log = LoggerFactory.getLogger(DependencyInjectionXMLConfigIntegrationTest.class);
  // With AbstractJUnit4SpringContextTests we need to use org.junit.Test JUnit 4 annotations
  // We need to add the junit-vintage-engine library dependency
  @Test
  public void givenUser_whenChangeNameWithUserManager_thenGetNewName() {
    //no bean id required if the type of requested object is unique
    //hence context does not know the type casting is required with the following approach
    UserManager userManager = (UserManager) applicationContext.getBean("userManager");
    User user = new User();
    user.setUsername("Tom");

    User userUpdated = userManager.updateUserName(user, "John");

    assertThat(userUpdated.getUsername()).isEqualTo("John");
  }
}

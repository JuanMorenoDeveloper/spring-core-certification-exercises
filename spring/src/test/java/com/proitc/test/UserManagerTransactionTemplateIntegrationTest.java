package com.proitc.test;

import com.proitc.jdbc.transactions.orm.UserManagerTransactionTemplate;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:application-context-jdbc.xml" })
public class UserManagerTransactionTemplateIntegrationTest {

  @Autowired private ApplicationContext context;

  @Test
  public void testDoItInTransactionUserManager() {

    UserManagerTransactionTemplate userManagerTransactionTemplate = context.getBean(UserManagerTransactionTemplate.class);
    assertThat(userManagerTransactionTemplate).isNotNull();
    userManagerTransactionTemplate.doItInTransactionUserManager();

  }

}

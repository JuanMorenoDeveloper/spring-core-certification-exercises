package com.proitc.test;


import static org.assertj.core.api.Assertions.assertThat;

import com.proitc.persistence.User;
import com.proitc.persistence.UserManager;
import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:application-context-persistence.xml"})
@Transactional
public class JPAPersistenceIntegrationTest {
  private static final Logger log = LoggerFactory.getLogger(JPAPersistenceIntegrationTest.class);
  @Autowired
  private ApplicationContext context;

  @BeforeAll
  public static void setUp() throws Exception {
    new EmbeddedDatabaseBuilder().build();
  }

  @Test
  public void testQueryfindAllUsersJPA() {

    log.debug("testQueryfindAllUsersJPA is called");
    UserManager userManager = context.getBean(UserManager.class);
    assertThat(userManager);
    List<User> users = userManager.queryfindAllUsersJPA();
    assertThat(users);
    for (User user : users) {
      log.debug("IDUSER : " + user.getIdUser());
      log.debug("USERNAME : " + user.getUsername());
      log.debug("PASSWORD : " + user.getPassword());
      log.debug("ACTIVE : " + user.isActive());
    }

  }

  @Test
  public void testQueryCountTAllUsersJPA() {

    log.debug("testQueryCountTAllUsersJPA is called");
    UserManager userManager = context.getBean(UserManager.class);
    assertThat(userManager).isNotNull();
    int count = userManager.queryCountAllUsersJPA();
    log.debug("Users count: " + count);

  }

  @Test
  public void testFindByIdUser() {

    log.debug("testFindByIdUSer is called");
    UserManager userManager = context.getBean(UserManager.class);
    assertThat(userManager).isNotNull();
    User user = userManager.queryFindByIdUser(2);
    log.debug("User: " + user.getUsername());
  }

  @Test
  public void testInsertUserByIdUser() {
    log.debug("testInsertUserByIdUser is called");
    UserManager userManager = context.getBean(UserManager.class);
    assertThat(userManager).isNotNull();
    userManager.insertUserByIdUser("testInsert@outlook.com", "123456", true);

    List<User> users = userManager.queryfindAllUsersJPA();
    assertThat(users).isNotNull();
    ;

    for (User user : users) {
      log.debug("IDUSER : " + user.getIdUser());
      log.debug("USERNAME : " + user.getUsername());
      log.debug("PASSWORD : " + user.getPassword());
      log.debug("ACTIVE : " + user.isActive());
    }
  }

  @Test
  public void testDeleteUserByIdUser() {
    log.debug("testDeleteUserByIdUser is called");
    UserManager userManager = context.getBean(UserManager.class);
    assertThat(userManager).isNotNull();
    userManager.deleteUserByIdUser(2);
    List<User> users = userManager.queryfindAllUsersJPA();
    assertThat(users).isNotNull();

    for (User user : users) {
      log.debug("IDUSER : " + user.getIdUser());
      log.debug("USERNAME : " + user.getUsername());
      log.debug("PASSWORD : " + user.getPassword());
      log.debug("ACTIVE : " + user.isActive());
    }
  }


}

package com.proitc.test;

import com.proitc.jdbc.transactions.orm.DBLog;
import com.proitc.jdbc.transactions.orm.DBLogService;
import com.proitc.jdbc.transactions.orm.User;
import com.proitc.jdbc.transactions.orm.UserManager;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

import java.util.Iterator;
import java.util.List;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = { "classpath:application-context-jdbc.xml" })
public class JDBCIntegrationTest {

  private static final Logger log = LoggerFactory.getLogger(JDBCIntegrationTest.class);
  @Autowired private ApplicationContext context;

  @Test
  @Transactional
  @Rollback(value = false)
  public void testDBLogServiceLogMethod() {

    DBLogService dbLogService = context.getBean(DBLogService.class);
    assertThat(dbLogService).isNotNull();
    dbLogService.log("1 MainAppJDBCTransactionsORM TEST LOG INSERT SRTING IN LOG TABLE");
    dbLogService.log("2 MainAppJDBCTransactionsORM TEST LOG INSERT SRTING IN LOG TABLE");
    dbLogService.log("3 MainAppJDBCTransactionsORM TEST LOG INSERT SRTING IN LOG TABLE");
    dbLogService.log("4 MainAppJDBCTransactionsORM TEST LOG INSERT SRTING IN LOG TABLE");

    List<DBLog> dbLogs = dbLogService.queryAllDBLogs();
    for (Iterator<DBLog> iterator = dbLogs.iterator(); iterator.hasNext(); ) {
      DBLog dbLog = iterator.next();
      log.debug("IDLOG: " + dbLog.getIDLOG());
      log.debug("LOGSTRING: " + dbLog.getLOGSTRING());
    }

  }

  @Test
  public void testUserManagerQueryUserWithInternalRowMapper() {

    UserManager userManager = context.getBean(UserManager.class);
    assertThat(userManager).isNotNull();
    User u = userManager.queryUserWithInternalRowMapper(2);
    assertThat(u).isNotNull();
    log.debug("testUserManagerQueryUserWithInternalRowMapper: " + u.getUsername());
  }

  @Test
  public void testUserManagerUpdateUserName() {

    UserManager userManager = context.getBean(UserManager.class);
    assertThat(userManager).isNotNull();
    User u = new User();
    u.setUsername("test@outlook.com");
    userManager.updateUserName(u, "testspring@outlook.com");

  }

  @Test
  @Rollback(value = false)
  public void testUserManagerAddUSER() {

    UserManager userManager = context.getBean(UserManager.class);
    assertThat(userManager).isNotNull();
    User user = new User();
    user.setUsername("testuser12345@outlook.com");
    user.setPassword("12345");
    user.setActive(true);
    userManager.addUSER(user);

  }

  @Test
  public void testUserManagerCountAllUsers() {

    UserManager userManager = context.getBean(UserManager.class);
    assertThat(userManager).isNotNull();
    log.debug(String.valueOf(userManager.countAllUsers()));

  }

  @Test
  public void testUserManagerLogAllUserInfo() {

    UserManager userManager = context.getBean(UserManager.class);
    assertThat(userManager).isNotNull();
    userManager.logAllUserInfo();

  }

  @Test
  public void testUserManagerQueryUserWithResultSetExtractor() {

    UserManager userManager = context.getBean(UserManager.class);
    assertThat(userManager).isNotNull();
    List<User> testUsers = userManager.queryUserWithResultSetExtractor();
    for (User u : testUsers) {
      log.debug("Test users");
      log.debug("testUserManagerQueryUserWithResultSetExtractor : " + u.getUsername());

    }

  }

  @Test
  public void testQueryForListOfUsers() {
    log.debug("testQueryForListOfUsers");
    UserManager userManager = context.getBean(UserManager.class);
    assertThat(userManager).isNotNull();
    List<Map<String, Object>> users = userManager.queryForListOfUsers();
    log.debug(users.toString());
    for (Map<String, Object> row : users) {
      log.debug("MAP VALUE: " + row.get("IDUSER"));
      log.debug("MAP VALUE: " + row.get("USERNAME"));
    }
  }

  @Test
  public void testQueryForMapUser() {
    log.debug("testQueryForMapUser");
    UserManager userManager = context.getBean(UserManager.class);
    assertThat(userManager).isNotNull();
    Map<String, Object> user = userManager.queryForMapUser("2");
    log.debug(user.toString());
    log.debug("MAP VALUE: " + user.get("IDUSER"));
    log.debug("MAP VALUE: " + user.get("USERNAME"));

  }
}

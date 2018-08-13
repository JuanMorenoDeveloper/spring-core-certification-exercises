package spring.test;


import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;
import spring.persistence.User;
import spring.persistence.UserManager;

@ExtendWith(SpringExtension.class)
@ContextConfiguration(locations = {"classpath:application-context-persistence.xml"})
@Transactional
public class JPAPersistenceIntegrationTest {

  @Autowired
  private ApplicationContext context;

  @BeforeAll
  public static void setUp() throws Exception {
    new EmbeddedDatabaseBuilder().build();
  }

  @Test
  public void testQueryfindAllUsersJPA() {

    System.out.println("testQueryfindAllUsersJPA is called");
    UserManager userManager = context.getBean(UserManager.class);
    assertThat(userManager);
    List<User> users = userManager.queryfindAllUsersJPA();
    assertThat(users);
    for (User user : users) {
      System.out.println("IDUSER : " + user.getIdUser());
      System.out.println("USERNAME : " + user.getUsername());
      System.out.println("PASSWORD : " + user.getPassword());
      System.out.println("ACTIVE : " + user.isActive());
    }

  }

  @Test
  public void testQueryCountTAllUsersJPA() {

    System.out.println("testQueryCountTAllUsersJPA is called");
    UserManager userManager = context.getBean(UserManager.class);
    assertThat(userManager).isNotNull();
    int count = userManager.queryCountAllUsersJPA();
    System.out.println("Users count: " + count);

  }

  @Test
  public void testFindByIdUser() {

    System.out.println("testFindByIdUSer is called");
    UserManager userManager = context.getBean(UserManager.class);
    assertThat(userManager).isNotNull();
    User user = userManager.queryFindByIdUser(2);
    System.out.println("User: " + user.getUsername());
  }

  @Test
  public void testInsertUserByIdUser() {
    System.out.println("testInsertUserByIdUser is called");
    UserManager userManager = context.getBean(UserManager.class);
    assertThat(userManager).isNotNull();
    userManager.insertUserByIdUser("testInsert@outlook.com", "123456", true);

    List<User> users = userManager.queryfindAllUsersJPA();
    assertThat(users).isNotNull();
    ;

    for (User user : users) {
      System.out.println("IDUSER : " + user.getIdUser());
      System.out.println("USERNAME : " + user.getUsername());
      System.out.println("PASSWORD : " + user.getPassword());
      System.out.println("ACTIVE : " + user.isActive());
    }
  }

  @Test
  public void testDeleteUserByIdUser() {
    System.out.println("testDeleteUserByIdUser is called");
    UserManager userManager = context.getBean(UserManager.class);
    assertThat(userManager).isNotNull();
    userManager.deleteUserByIdUser(2);
    List<User> users = userManager.queryfindAllUsersJPA();
    assertThat(users).isNotNull();

    for (User user : users) {
      System.out.println("IDUSER : " + user.getIdUser());
      System.out.println("USERNAME : " + user.getUsername());
      System.out.println("PASSWORD : " + user.getPassword());
      System.out.println("ACTIVE : " + user.isActive());
    }
  }


}

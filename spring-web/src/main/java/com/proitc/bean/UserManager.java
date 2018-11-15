package com.proitc.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.ResultSetExtractor;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Transactional
public class UserManager {
  private static final Logger log = LoggerFactory.getLogger(UserManager.class);
  private JdbcTemplate jdbcTemplate;
  //@Autowired
  public User user;

  public UserManager(DatabaseService databaseService) {
    this.jdbcTemplate = databaseService.getJdbcTemplate();
  }

  //queryForObject example
  public User queryUserWithInternalRowMapper(int idUser) throws Exception {
    log.debug("UserManager queryUserWithInternalRowMapper called");
    final String QUERY_SQL = "SELECT * FROM USER WHERE IDUSER=?";
    return jdbcTemplate.queryForObject(QUERY_SQL, new Object[] { idUser }, new UserMapper());
  }

  //update UPDATE example
  public boolean updateUserName(User u, String newName) {
    log.debug("UserManager updateUserName called");
    final String UPDATE_SQL = "UPDATE USER SET USERNAME = ? WHERE USERNAME = ?";
    int result = jdbcTemplate.update(UPDATE_SQL, new Object[] { newName, u.getUsername() });
    if (result > 0) {
      return true;
    } else {
      return false;
    }
  }

  //update INSERT example
  @Transactional(propagation = Propagation.REQUIRES_NEW, timeout = 50)
  public boolean addUSER(User user) {
    log.debug("UserManager addUSER called");
    final String INSERT_SQL = "INSERT INTO USER (USERNAME,PASSWORD,ENABLED) VALUES (?,?,?)";
    int result = jdbcTemplate.update(INSERT_SQL, new Object[] { user.getUsername(), user.getPassword(), user.isEnabled() });
    if (result > 0) {
      return true;
    } else {
      return false;
    }
  }

  //queryForObject(QUERY_SQL, Integer.class) Example
  public int countAllUsers() {
    log.debug("UserManager countAllUsers called");
    final String QUERY_SQL = "SELECT COUNT(*) FROM USER";
    return jdbcTemplate.queryForObject(QUERY_SQL, Integer.class);
  }

  public void logAllUserInfo() {
    log.debug("UserManager logAllUserInfo called");
    final String QUERY_SQL = "SELECT * FROM USER";
    jdbcTemplate.query(QUERY_SQL, new UserProcessor());
  }

  //override class level @Transactional
  @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRES_NEW)
  public List<User> queryUserWithResultSetExtractor() {
    log.debug("UserManager queryUserWithResultSetExtractor called");
    final String QUERY_SQL = "SELECT * FROM USER";
    return jdbcTemplate.query(QUERY_SQL, new UserResultSetExtractor());
  }

  //How does the JdbcTemplate support generic queries? How does it return objects and lists/maps of objects?
  //queryForList
  public List<Map<String, Object>> queryForListOfUsers() {
    log.debug("UserManager queryForListListOfUsers called");
    final String QUERY_SQL = "SELECT * FROM USER";
    return jdbcTemplate.queryForList(QUERY_SQL);
  }

  //queryForMap
  public Map<String, Object> queryForMapUser(String idUser) {
    log.debug("UserManager queryForMapUser called");
    final String QUERY_SQL = "SELECT * FROM USER WHERE IDUSER= ?";
    return jdbcTemplate.queryForMap(QUERY_SQL, idUser);
  }

  //RowMapper inside class
  //good when returning an object from ResultSet
  //Mapping each one row at a time
  private static final class UserMapper implements RowMapper<User> {

    public User mapRow(ResultSet rs, int rowNum) throws SQLException {
      User user = new User();
      user.setIdUser(rs.getInt("IDUSER"));
      user.setUsername(rs.getString("USERNAME"));
      user.setPassword(rs.getString("PASSWORD"));
      user.setEnabled(rs.getBoolean("ENABLED"));
      return user;
    }
  }

  //RowMapper inside class
  //good when returning an object from ResultSet
  //Mapping entire result set (multiple rows) to an object
  //It is your responsibility to iterate in resultset
  private static final class UserResultSetExtractor implements ResultSetExtractor<List<User>> {

    public List<User> extractData(ResultSet rs) throws SQLException, DataAccessException {
      List<User> testUsers = new ArrayList<User>();
      while (rs.next()) {
        User user = null;
        if (rs
          .getString("USERNAME")
          .contains("test")) {
          user = new User();
          user.setIdUser(rs.getInt("IDUSER"));
          user.setUsername(rs.getString("USERNAME"));
          user.setPassword(rs.getString("PASSWORD"));
          user.setEnabled(rs.getBoolean("ENABLED"));
          testUsers.add(user);
        }
      }
      return testUsers;
    }

  }

  //RowCallbackHandler proccessRow example
  //good for while not returning an object
  //bulk row processing
  //there is no return object
  //result set processing
  private static final class UserProcessor implements RowCallbackHandler {

    public void processRow(ResultSet resultSet) throws SQLException {
      while (resultSet.next()) {
        log.debug("IDUSER: " + resultSet.getString("IDUSER"));
        log.debug("USERNAME: " + resultSet.getString("USERNAME"));
        log.debug("PASSWORD: " + resultSet.getString("PASSWORD"));
        log.debug("ENABLED: " + resultSet.getString("ENABLED"));
      }
    }
  }

}

package com.proitc.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.sql.PreparedStatement;
import java.util.List;

public class DBLogService implements Log {

  private static final Logger logger = LoggerFactory.getLogger(DBLogService.class);
  private JdbcTemplate jdbcTemplate;

  public DBLogService(DatabaseService databaseService) {
    this.jdbcTemplate = databaseService.getJdbcTemplate();
  }

  //JdbcTemplate INSERT example
  @Transactional(timeout = 60)
  public boolean log(final String log) {
    logger.info("DBLogService : " + log);
    final String INSERT_SQL = "INSERT INTO LOG (LOGSTRING) VALUES (?)";
    try {
      jdbcTemplate.update(connection -> {
        PreparedStatement ps = connection.prepareStatement(INSERT_SQL);
        ps.setString(1, log);
        return ps;
      });
    } catch (Exception e) {
      logger.error("Error writing data", e);
    }
    return false;
  }

  //JdbcTemplate query with in method RowMapper example
  public List<DBLog> queryAllDBLogs() {
    logger.info("DBLogService : queryAllDBLogs() is called");
    final String QUERY_SQL = "SELECT * FROM LOG";
    List<DBLog> dbLogs = this.jdbcTemplate.query(QUERY_SQL, (rs, rowNum) -> {
      DBLog dbLog = new DBLog();
      dbLog.setIDLOG(rs.getInt("IDLOG"));
      dbLog.setLOGSTRING(rs.getString("LOGSTRING"));
      return dbLog;
    });
    return dbLogs;
  }
}

package com.proitc.jdbc.transactions.orm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jdbc.core.JdbcTemplate;

import javax.sql.DataSource;

public class DatabaseService {

  private static final Logger log = LoggerFactory.getLogger(DatabaseService.class);
  private JdbcTemplate jdbcTemplate;

  public DatabaseService(DataSource dataSource) {
    log.debug("Database Service: " + dataSource);
    //Setting data source through constructor
    this.setJdbcTemplate(new JdbcTemplate(dataSource));
  }

  public JdbcTemplate getJdbcTemplate() {
    return jdbcTemplate;
  }

  public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

}

package com.proitc.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

@Repository
public class DatabaseService {

  private static final Logger log = LoggerFactory.getLogger(DatabaseService.class);

  @Autowired
  public DatabaseService(DataSource dataSource) {
    log.info("Datasource through Constructor injection in DatabaseService");
    log.info("DataSource: " + dataSource.getDriverClassName());
    log.info("DataSource: " + dataSource.getUrl());
  }

  //javax.annotation approach
  @PostConstruct
  public void init() {
    //initialization work
    log.info("@PostConstruct :" + "is called");
  }

  //javax.annotation approach
  @PreDestroy
  public void destroy() {
    //destruction work
    log.info("@PreDestroy :" + "is called");
  }

}

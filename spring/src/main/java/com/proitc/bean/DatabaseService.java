package com.proitc.bean;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;


@Repository
public class DatabaseService {

  private static final Logger log = LoggerFactory.getLogger(DatabaseService.class.getName());

  @Autowired
  public DatabaseService(DataSource dataSource) {
    log.debug("Datasource through Constructor injection in DatabaseService");
    System.out.println("DataSource: " + dataSource.getDriverClassName());
    System.out.println("DataSource: " + dataSource.getUrl());
  }


  //javax.annotation approach
  @PostConstruct
  public void init() {
    //initialization work
    System.out.println("@PostConstruct :" + "is called");
  }

  //javax.annotation approach
  @PreDestroy
  public void destroy() {
    //destruction work
    System.out.println("@PreDestroy :" + "is called");
  }

}

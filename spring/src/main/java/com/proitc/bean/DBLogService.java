package com.proitc.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("DBLogService")
public class DBLogService implements Log {

  private static final Logger logger = LoggerFactory.getLogger(DBLogService.class);

  public boolean log(String log) {
    logger.info("DBLogService : " + log);
    return false;
  }

}

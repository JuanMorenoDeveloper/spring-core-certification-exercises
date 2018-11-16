package com.proitc.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LogService implements Log {
  private static final Logger logger = LoggerFactory.getLogger(LogService.class);

  public boolean log(String log) {
    logger.info("LogService : " + log);
    return true;
  }

}


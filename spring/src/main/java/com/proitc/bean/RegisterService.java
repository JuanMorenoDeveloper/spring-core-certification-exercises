package com.proitc.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class RegisterService {

  private static final Logger logger = LoggerFactory.getLogger(RegisterService.class);
  // @Autowired and @Resource provide dependency injection to private fields
  @Autowired
  @Qualifier("DBLogService")
  private Log log;

  public boolean registerUser(User user) {
    logger.debug("RegisterService registerUser(User u) is called");
    return true;
  }

  public Log getLog() {
    return log;
  }

  public void setLog(Log log) {
    this.log = log;
  }


}

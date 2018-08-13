package com.proitc.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class UserManager {
  private static final Logger log = LoggerFactory.getLogger(UserManager.class);
  public User user;

  public User updateUserName(User u, String newName) {
    u.setUsername(newName);
    return u;
  }

  public void throwUserUpdateExceptionMethod() throws Exception {
    log.debug("UserManager throwUserUpdateExceptionMethod() is called");
    throw new Exception();
  }

  public boolean throwNotUserUpdateExceptionMethod() throws Exception {
    log.debug("UserManager throwNotUserUpdateExceptionMethod() is called");
    return true;
  }

  public boolean deleteUser(User user) {
    log.debug("UserManager deleteUser(User user) is called");
    return false;
  }

}

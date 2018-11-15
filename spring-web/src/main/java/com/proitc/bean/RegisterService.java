package com.proitc.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RegisterService {
  private static final Logger log = LoggerFactory.getLogger(RegisterService.class);

  public boolean registerUser(User user) {
    log.debug("RegisterService registerUser(User u) is called");
    return !user
      .getUsername()
      .equals("") && !user
      .getPassword()
      .equals("");
  }

}

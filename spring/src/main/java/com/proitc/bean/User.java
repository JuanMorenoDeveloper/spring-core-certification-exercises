package com.proitc.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class User {
  private static final Logger log = LoggerFactory.getLogger(User.class);
  private String username;
  private String password;
  private boolean active;

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    log.debug("User setUsername is called");
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    log.debug("User setPassword is called");
    this.password = password;
  }

  public boolean isActive() {
    return active;
  }

  public void setActive(boolean active) {
    this.active = active;
  }


}

package com.proitc.persistence;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Entity
@Table(name = "USER")
public class User {
  private static final Logger log = LoggerFactory.getLogger(User.class);
  @Id
  @GeneratedValue
  @Column(name = "IDUSER")
  private int idUser;

  @Column(name = "USERNAME")
  private String username;

  @Column(name = "PASSWORD")
  private String password;

  @Column(name = "ACTIVE")
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

  public int getIdUser() {
    return idUser;
  }

  public void setIdUser(int idUser) {
    this.idUser = idUser;
  }


}

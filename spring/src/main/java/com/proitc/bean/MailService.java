package com.proitc.bean;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

public class MailService {
  private static final Logger log = LoggerFactory.getLogger(MailService.class);
  private LogService logService;
  private MailServiceConfig serviceConfig;
  private String mailServer;
  private String port;
  private String protocol;

  //${...} property place holder syntax
  //#{...} SpEl syntax
  @Value("${mail.username}")
  private String username;

  @Value("${mail.password}")
  private String password;

  public boolean sendMessage(String message) {
    log.debug("MailService sendMessage(String message) is called");
    return logService.log("MailService content: " + message);
  }

  public String getMailServer() {
    return mailServer;
  }

  public void setMailServer(String mailServer) {
    this.mailServer = mailServer;
  }

  public String getPort() {
    return port;
  }

  public void setPort(String port) {
    this.port = port;
  }

  public String getProtocol() {
    return protocol;
  }

  public void setProtocol(String protocol) {
    this.protocol = protocol;
  }

  public LogService getLogService() {
    return logService;
  }

  @Autowired
  public void setLogService(LogService logService) {
    log.debug("through setter injection in MailService");
    this.logService = logService;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public MailServiceConfig getServiceConfig() {
    return serviceConfig;
  }

  public void setServiceConfig(MailServiceConfig serviceConfig) {
    this.serviceConfig = serviceConfig;
  }


}

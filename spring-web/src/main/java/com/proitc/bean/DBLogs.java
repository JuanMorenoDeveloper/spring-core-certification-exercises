package com.proitc.bean;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "LOGS")
public class DBLogs {

  private List<DBLog> logList;

  @XmlElement(name = "LOG")
  public List<DBLog> getLogList() {
    return logList;
  }

  public void setLogList(List<DBLog> logList) {
    this.logList = logList;
  }
}

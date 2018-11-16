package com.proitc.controller;

import com.proitc.bean.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Controller
public class AdminRestController {
  private static final Logger log = LoggerFactory.getLogger(AdminRestController.class);
  @Autowired private RestTemplate restTemplate;

  @Autowired private UserManager userManager;

  @Autowired private DBLogService dbLogService;

  @RequestMapping(value = "/md5/text/{text}", method = RequestMethod.GET)
  public @ResponseBody
  String md5String(@PathVariable(value = "text") String text) {

    // fortmat JSON http://md5.jsontest.com/?text=example_text
    ResponseEntity<String> response = restTemplate.getForEntity("http://md5.jsontest.com/?text={text}", String.class, text);

    log.info("AdminRestController md5String return response: " + response);
    return response.toString();
  }

  @RequestMapping(value = "/getAllUsersJSON", method = RequestMethod.GET)
  public @ResponseBody
  List<User> getAllUsersJSON() {

    List<User> users = null;
    try {
      users = userManager.queryUserWithResultSetExtractor();
    } catch (Exception e) {
      log.error("Error reading data", e);
    }

    log.info(users.toString());
    return users;
  }

  @RequestMapping(value = "/getAllDBLogsXML", method = RequestMethod.GET)
  public @ResponseBody
  DBLogs getAllDBLogsXML() {

    List<DBLog> dbLogList = null;
    try {
      dbLogList = dbLogService.queryAllDBLogs();
    } catch (Exception e) {
      log.error("Error reading data", e);
    }
    log.info(dbLogList.toString());
    DBLogs dbLogs = new DBLogs();
    dbLogs.setLogList(dbLogList);
    return dbLogs;
  }

}

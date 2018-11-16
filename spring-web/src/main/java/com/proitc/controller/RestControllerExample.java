package com.proitc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
//will add automatically the @ResponseBody annotation to all methods // replacement for @Controller and @ResponseBody
public class RestControllerExample {
  private static final Logger log = LoggerFactory.getLogger(RestControllerExample.class);
  @Autowired private RestTemplate restTemplate;

  //JSON echo service call site: http://www.jsontest.com/
  @RequestMapping(value = "/echo/person1/{person1}/person2/{person2}", method = RequestMethod.GET)
  public String echo(@PathVariable(value = "person1") String person1, @PathVariable(value = "person2") String person2) {

    //JSON http://echo.jsontest.com/person1/tunatore/person2/testuser
    ResponseEntity<String> response = restTemplate.getForEntity("http://echo.jsontest.com/person1/{person1}/person2/{person2}", String.class, person1, person2);

    log.info("RestControllerExample echoPersons return response: " + response);
    return response.toString();
  }

  @RequestMapping(value = "/responseStatusCode", method = RequestMethod.GET)
  public String responseStatusTest() throws Exception {

    //responseStatusTest
    log.info("RestControllerExample responseStatusTest");
    throw new Exception("responseStatusTest exception");
  }

  @ExceptionHandler
  @ResponseStatus(value = HttpStatus.BAD_GATEWAY, reason = "PROVIDE YOUR REASON HERE")
  public void handleException(Exception ex) {
    log.error("", ex);
  }

}

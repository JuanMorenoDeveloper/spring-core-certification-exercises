package com.proitc.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class JMSListener1 {
  private static final Logger log = LoggerFactory.getLogger(JMSListener1.class);

  @SuppressWarnings("unchecked")
  public void receive(Object message) {

    log.info(message.toString());
    log.info("JMSListener1: Asynchronous onMessage is called");
    final Map<String, String> mapMessage = (Map<String, String>) message;

    String key1 = mapMessage.get("key1");
    String key2 = mapMessage.get("key2");
    String key3 = mapMessage.get("key3");
    String key4 = mapMessage.get("key4");

    log.info("JMSListener1 mapMessage key1: " + key1);
    log.info("JMSListener1 mapMessage key2: " + key2);
    log.info("JMSListener1 mapMessage key3: " + key3);
    log.info("JMSListener1 mapMessage key4: " + key4);

  }
}

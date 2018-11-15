package com.proitc.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.jms.JMSException;
import javax.jms.MapMessage;
import javax.jms.Message;
import javax.jms.MessageListener;

public class JMSListener2 implements MessageListener {
  private static final Logger log = LoggerFactory.getLogger(JMSListener2.class);

  public void onMessage(Message message) {
    log.debug(message.toString());
    log.debug("JMSListener2: Asynchronous onMessage is called");
    final MapMessage mapMessage = (MapMessage) message;
    try {
      String key1 = mapMessage.getString("key1");
      String key2 = mapMessage.getString("key2");
      String key3 = mapMessage.getString("key3");
      String key4 = mapMessage.getString("key4");

      log.debug("JMSListener2 mapMessage key1: " + key1);
      log.debug("JMSListener2 mapMessage key2: " + key2);
      log.debug("JMSListener2 mapMessage key3: " + key3);
      log.debug("JMSListener2 mapMessage key4: " + key4);

      log.debug("JMSListener2 message idJMS: " + message.getStringProperty("idJMS"));
      log.debug("JMSListener2 message JMSCorrelationID: " + message.getJMSCorrelationID());
    } catch (JMSException e) {
      log.error("Error processing message", e);
    }

  }

}

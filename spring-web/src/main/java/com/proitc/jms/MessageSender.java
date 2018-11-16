package com.proitc.jms;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;

import java.util.HashMap;
import java.util.Map;

public class MessageSender {
  private static final Logger log = LoggerFactory.getLogger(MessageSender.class);
  private JmsTemplate queueTemplate;
  private JmsTemplate topicTemplate;

  public void setQueueTemplate(JmsTemplate queueTemplate) {
    this.queueTemplate = queueTemplate;
  }

  public void setTopicTemplate(JmsTemplate topicTemplate) {
    this.topicTemplate = topicTemplate;
  }

  public void send(final Map<String, String> Object) {
    queueTemplate.convertAndSend("queue", Object);
  }

  public void sendTopic(final Map<String, String> Object) {
    topicTemplate.convertAndSend("topic", Object);
  }

  public void sendTopicWithCallBack() {

    //access message object and change it with callback
    log.info("Message sender sendTopicWithCallBack is called");
    Map<String, String> mapMessage = new HashMap<>();
    mapMessage.put("key1", "callback1");
    mapMessage.put("key2", "callback2");
    mapMessage.put("key3", "callback3");
    mapMessage.put("key4", "callback4");

    //JMSListener1 and JMSListener2 will be called
    topicTemplate.convertAndSend("topic", mapMessage, message -> {
      //MessagePostProccessor Callback for accessing the message properties
      message.setStringProperty("idJMS", "1234");
      message.setJMSCorrelationID("test-1234");
      return message;
    });
  }
}

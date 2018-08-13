package com.proitc.jms;

import java.util.HashMap;
import java.util.Map;
import javax.jms.JMSException;
import javax.jms.Message;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.core.MessagePostProcessor;


public class MessageSender {
  private static final Logger log = LoggerFactory.getLogger(MessageSender.class);
  private JmsTemplate jmsTemplate;
  private JmsTemplate jmsTopicTemplate;

  public void setJmsTemplate(JmsTemplate jmsTemplate) {
    this.jmsTemplate = jmsTemplate;
  }

  public void setJmsTopicTemplate(JmsTemplate jmsTopicTemplate) {
    this.jmsTopicTemplate = jmsTopicTemplate;
  }

  public void send(final Map<String, String> Object) {
    jmsTemplate.convertAndSend("queue", Object);
  }

  public void sendTopic(final Map<String, String> Object) {
    jmsTopicTemplate.convertAndSend("topic", Object);
  }

  public void SendTopicWithCallBack() {

    //access message object and change it with callback
    log.debug("Message sender SendTopicWithCallBack is called");
    Map<String, String> mapMessage = new HashMap<String, String>();
    mapMessage.put("key1", "callback1");
    mapMessage.put("key2", "callback2");
    mapMessage.put("key3", "callback3");
    mapMessage.put("key4", "callback4");

    //JMSListener1 and JMSListener2 will be called
    jmsTopicTemplate.convertAndSend("topic", mapMessage, new MessagePostProcessor() {
      public Message postProcessMessage(Message message) throws JMSException {
        //MessagePostProccessor Callback for accessing the message properties
        message.setStringProperty("idJMS", "1234");
        message.setJMSCorrelationID("test-1234");
        return message;
      }
    });
  }
}

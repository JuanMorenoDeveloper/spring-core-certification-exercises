package com.proitc.jms;

import org.springframework.jms.core.JmsTemplate;

public class MessageReceiver {

  private JmsTemplate queueTemplate;

  public JmsTemplate getQueueTemplate() {
    return queueTemplate;
  }

  public void setQueueTemplate(JmsTemplate queueTemplate) {
    this.queueTemplate = queueTemplate;
  }
}

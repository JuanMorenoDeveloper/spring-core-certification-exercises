package com.proitc.controller;

import com.proitc.jms.MessageReceiver;
import com.proitc.jms.MessageSender;
import java.util.HashMap;
import java.util.Map;
import javax.annotation.security.RolesAllowed;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


@Controller
public class MessageController {
  private static final Logger log = LoggerFactory.getLogger(MessageController.class);
  @Autowired
  private MessageSender messageSender;

  @Autowired
  private MessageReceiver messageReceiver;

  @RolesAllowed(value = {"ROLE_ADMIN"})
  @RequestMapping(value = "/sendJMSMessage", method = RequestMethod.GET)
  public ModelAndView sendJMSMessage() {

    log.debug("MessageController sendJMSMessage is called with ADMIN ROLE");
    Map<String, String> messageMap = new HashMap<String, String>();
    messageMap.put("key1", "tunatore@gmail.com");
    messageMap.put("key2", "tunatore@outlook.com");
    messageMap.put("key3", "spring framework");
    messageMap.put("key4", "udemy");
    messageSender.send(messageMap);

    return new ModelAndView("/admin/adminsecured");

  }

  @RolesAllowed(value = {"ROLE_ADMIN"})
  @RequestMapping(value = "/sendJMSMessageAsync", method = RequestMethod.GET)
  public ModelAndView sendJMSMessageAsync() {

    log.debug("MessageController sendJMSMessageAsync is called with ADMIN ROLE");
    Map<String, String> messageMap2 = new HashMap<String, String>();
    messageMap2.put("key1", "topic tunatore@gmail.com");
    messageMap2.put("key2", "topic tunatore@outlook.com");
    messageMap2.put("key3", "topic spring framework");
    messageMap2.put("key4", "topic udemy");
    messageSender.sendTopic(messageMap2);

    return new ModelAndView("/admin/adminsecured");

  }

  @RolesAllowed(value = {"ROLE_ADMIN"})
  @RequestMapping(value = "/sendJMSMessageCallback", method = RequestMethod.GET)
  public ModelAndView sendJMSMessageCallback() {

    log.debug("MessageController sendJMSMessageCallback is called with ADMIN ROLE");
    messageSender.SendTopicWithCallBack();
    return new ModelAndView("/admin/adminsecured");

  }

  @RolesAllowed(value = {"ROLE_ADMIN"})
  @RequestMapping(value = "/getJMSMessage", method = RequestMethod.GET)
  public ModelAndView getJMSMessage() {
    log.debug(
      "Message Controller getJMSMessage synchronous " + messageReceiver.getJmsTemplate()
        .receiveAndConvert("queue"));
    log.debug(messageReceiver.getJmsTemplate().receiveAndConvert("queue").toString());
    return new ModelAndView("/admin/adminsecured");
  }
}

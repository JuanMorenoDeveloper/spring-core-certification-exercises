package com.proitc.container.dependency.ioc.config.xml;

import org.junit.jupiter.api.Test;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class XMLConfigIntegrationTest {

  @Test
  public void whenCreateContextFromXML_getNotErrors() {
    ApplicationContext context = new ClassPathXmlApplicationContext("application-context.xml");
  }
}

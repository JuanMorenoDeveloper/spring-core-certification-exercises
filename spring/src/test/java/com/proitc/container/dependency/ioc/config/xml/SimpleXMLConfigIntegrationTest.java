package com.proitc.container.dependency.ioc.config.xml;

import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class SimpleXMLConfigIntegrationTest {

  @Test
  public void whenCreateAndCloseContextFromXML_thenGetNotErrors() {
    ApplicationContext context = new ClassPathXmlApplicationContext("simple-context.xml");
    //close the application and release all sources and locks
    ((ConfigurableApplicationContext) (context)).close();

    // or Register a shutdown hook with the JVM runtime, will close the context on JVM shutdown
    //((ConfigurableApplicationContext)(context)).registerShutdownHook();
  }
}

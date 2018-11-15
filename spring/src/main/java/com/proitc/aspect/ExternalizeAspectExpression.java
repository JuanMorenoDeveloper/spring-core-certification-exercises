package com.proitc.aspect;

import org.aspectj.lang.annotation.Pointcut;

public class ExternalizeAspectExpression {

  @Pointcut("execution(* com.proitc.bean.MailService.*(*))")
  public void mailServiceMethods() {
  }

}

package com.proitc.aspect;

import com.proitc.bean.UserManager;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class AspectManager {

  private static final Logger log = LoggerFactory.getLogger(AspectManager.class);
  boolean loggingRequired = true;

  @Before("execution(* com.proitc.bean.MailService.send*(*))")
  public void monitorMailServiceSendMethod() {
    //Before Advice: It is a code that executes before a joint point and cannot change the execution flow unless there is an exception inside that advice
    log.debug("@Aspect @Before MailService sendMessage(String message) method is called");
  }

  @After("execution(* com.proitc.bean.RegisterService.register*(*))")
  public void monitorRegisterServiceRegisterMethod() {
    log.debug("@Aspect @After RegisterService registerUser(User user) method is called");
  }

  @AfterThrowing(value = "execution(void com.proitc.bean.UserManager.throw*())", throwing = "e")
  public void afterThrowingUserManagerThrow(JoinPoint jp, Exception e) {
    //After Throwing Advice: Advice to be executed after target object's method throws an exception
    log.debug(
      "@Aspect @AfterThrowing UserManager throwUserUpdateExceptionMethod method is called");
    log.debug("Target Class: " + jp.getClass());
    log.debug("Target Kind: " + jp.getKind());
    log.debug("Target Signature: " + jp.getSignature());
    log.debug("Target Target: " + jp.getTarget());
    UserManager userManager = (UserManager) jp.getTarget();
    //comment out
    //e.printStackTrace();
  }

  @AfterReturning(value = "execution(boolean com.proitc.bean.UserManager.throw*())", returning = "result")
  public void afterReturningUserManagerThrow(JoinPoint jp, boolean result) {
    //After Returning Advice: Advice to be executed after successful return from a method a call inside target object
    log.debug(
      "@Aspect @AfterReturning UserManager throwUserUpdateExceptionMethod method is called");
    log.debug("result: " + result);
  }

  @After("execution(void com.proitc.bean.UserManager.throw*())")
  public void afterThrowUserUpdateExceptionMethod() {
    //After(finally) Advice: Advice to be executed even if the target object's method throws an exception or not
    log.debug("@Aspect @After UserManager throwUserUpdateExceptionMethod method is called");
  }

  @Around("execution(boolean com.proitc.bean.UserManager.deleteUser(*))")
  public boolean aroundDeleteUser(ProceedingJoinPoint point) throws Throwable {
    log.debug(
      "@Aspect @Around UserManager deleteUser(User user) method before proceed() is called");

    if (loggingRequired == true) {
      log.debug("AspectManager aroundDeleteUser log active");
      point.proceed();
    } else {
      point.proceed();
    }
    //Around Advice: Advice to be executed before of after the target object's method run and able to change the execution flow using the proceed method in ProceedingJointPoint parameter
    log.debug(
      "@Aspect @Around UserManager deleteUser(User user) method after proceed() is called");
    return true;
  }


  //named pointcut example
  @After("userManagerMethods() || registerServiceMethods()")
  public void namedPointCutExample(JoinPoint jp) {
    log.debug(" ***** namePointCut ***** " + jp.getSignature() + " is called");
  }

  //named point externalization example
  @After("com.proitc.aspect.ExternalizeAspectExpression.mailServiceMethods()")
  public void namedPointCutExternalizationExample(JoinPoint jp) {
    log.debug(
      " ***** namePointCut externalization example ***** " + jp.getSignature() + " is called");
  }

  @Pointcut("execution(* com.proitc.bean.UserManager.*(*))")
  public void userManagerMethods() {
  }

  @Pointcut("execution(* com.proitc.bean.RegisterService.*(*))")
  public void registerServiceMethods() {
  }

}

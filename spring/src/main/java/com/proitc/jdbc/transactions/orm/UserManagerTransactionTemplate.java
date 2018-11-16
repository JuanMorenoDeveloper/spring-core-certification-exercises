package com.proitc.jdbc.transactions.orm;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.TransactionCallbackWithoutResult;
import org.springframework.transaction.support.TransactionTemplate;

public class UserManagerTransactionTemplate {
  private static final Logger log = LoggerFactory.getLogger(UserManagerTransactionTemplate.class);
  private TransactionTemplate transactionTemplate;
  private UserManager userManager;

  public UserManagerTransactionTemplate(PlatformTransactionManager transactionManager) {
    this.transactionTemplate = new TransactionTemplate(transactionManager);
    transactionTemplate.setIsolationLevel(TransactionDefinition.ISOLATION_READ_COMMITTED);
    transactionTemplate.setTimeout(30);
  }

  public void doItInTransactionUserManager() {

    transactionTemplate.execute(new TransactionCallbackWithoutResult() {
      @Override
      protected void doInTransactionWithoutResult(TransactionStatus status) {
        log.info("status.isNewTransaction(): " + status.isNewTransaction());
        User user = new User();
        user.setUsername("testTransactionTemplate");
        user.setPassword("111");
        user.setActive(true);

        userManager.addUSER(user);
        log.info("status.isCompleted(): " + status.isCompleted());
        userManager.logAllUserInfo();

      }
    });
  }

  public UserManager getUserManager() {
    return userManager;
  }

  public void setUserManager(UserManager userManager) {
    this.userManager = userManager;
  }
}

package com.proitc.persistence;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Transactional
@Repository //for exception translation
public class DBLogService implements Log {

  private EntityManager entityManager;

  @PersistenceContext //EntityManager injection
  public void setEntityManager(EntityManager entityManager) {
    this.entityManager = entityManager;
  }

  public boolean log(String log) {
    return false;
  }

}


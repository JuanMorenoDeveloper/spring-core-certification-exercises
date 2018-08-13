package com.proitc.persistence;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository //for exception translation
@Transactional
public class UserManager {
  private static final Logger log = LoggerFactory.getLogger(UserManager.class);
  @PersistenceContext
  private EntityManager entityManager;

  public List<User> queryfindAllUsersJPA() {

    log.debug("UserManager queryfindAllUsersJPA is called");
    String qlString = "from User";
    TypedQuery<User> query = entityManager.createQuery(qlString, User.class);
    return query.getResultList();
  }

  public int queryCountAllUsersJPA() {

    log.debug("UserManager queryCountAllUsersJPA is called");
    String qlString = "from User";
    TypedQuery<User> query = entityManager.createQuery(qlString, User.class);
    return query.getResultList().size();
  }

  public User queryFindByIdUser(int idUser) {
    log.debug("UserManager queryFindByIdUser is called");
    return entityManager.find(User.class, idUser);
  }

  @Transactional
  public boolean insertUserByIdUser(String username, String password, boolean active) {

    log.debug("UserManager queryFindByIdUser is called");
    String qlString = "insert into user (username,password,active) values (?,?,?)";
    entityManager.joinTransaction();
    ;
    Query query = entityManager.createNativeQuery(qlString);
    query.setParameter(1, username);
    query.setParameter(2, password);
    query.setParameter(3, active);
    int result = query.executeUpdate();
    if (result > 0) {
      return true;
    } else {
      return false;
    }
  }

  @Transactional
  public boolean deleteUserByIdUser(int idUser) {

    log.debug("UserManager insertUserByIdUser is called");
    String qlString = "delete from user where iduser=?";
    entityManager.joinTransaction();
    Query query = entityManager.createNativeQuery(qlString);
    query.setParameter(1, idUser);
    int result = query.executeUpdate();
    if (result > 0) {
      return true;
    } else {
      return false;
    }
  }
}

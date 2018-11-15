package com.proitc.controller;

import com.proitc.bean.User;
import com.proitc.bean.UserManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.security.RolesAllowed;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
public class UserController {
  private static final Logger log = LoggerFactory.getLogger(UserController.class);
  @Autowired private UserManager userManager;

  private User user;

  @RequestMapping(value = "/getUserById", method = RequestMethod.POST)
  public ModelAndView getUserByID(@RequestParam(value = "IDUSER") int idUser) {
    log.debug("UserController getUserById is called");
    user = null;
    try {
      user = userManager.queryUserWithInternalRowMapper(idUser);
    } catch (Exception e) {
      log.error("Error reading data", e);
    }
    if (user != null) {
      return new ModelAndView("user", "userModelObject", user);
    } else {
      return new ModelAndView("error", "message", "Error while getting UserModelObject");
    }
  }

  @RequestMapping(value = "/getUserByIdPathVariable/IDUSER/{idUser}", method = RequestMethod.GET)
  public ModelAndView getUserByIDPathVariable(@PathVariable(value = "idUser") int idUser) {
    log.debug("UserController getUserByIDPathVariable is called");
    log.debug("UserController getUserByIDPathVariable idUser: " + idUser);
    user = null;
    try {
      user = userManager.queryUserWithInternalRowMapper(idUser);
    } catch (Exception e) {
      log.error("Error reading data", e);
    }
    if (user != null) {
      return new ModelAndView("user", "userModelObject", user);
    } else {
      return new ModelAndView("error", "message", "Error while getting UserModelObject");
    }
  }

  @RequestMapping(value = "/getAllUsers", method = RequestMethod.GET)
  public String queryAllUsers(Model model) {
    log.debug("UserController queryAllUsers is called");

    List<User> users = new ArrayList<>();
    try {
      users = userManager.queryUserWithResultSetExtractor();
    } catch (Exception e) {
      log.error("Error reading data", e);
    }
    model.addAttribute("users", users);

    if (users.isEmpty()) {
      return "error";
    } else {
      return "user";
    }
  }

  @RequestMapping(value = "/testUserSessionAttribute/IDUSER/{idUser}", method = RequestMethod.GET)
  public ModelAndView testUserSessionAttribute(@PathVariable(value = "idUser") int idUser, HttpSession session) {
    log.debug("UserController testUserSessionAttribute is called");
    log.debug("UserController testUserSessionAttribute sessionObject: " + session.getAttribute("sessionObject"));
    user = null;
    try {
      user = userManager.queryUserWithInternalRowMapper(idUser);
      session.setAttribute("sessionObject", user.getIdUser() + " - " + user.getUsername());
    } catch (Exception e) {
      log.error("Error reading data", e);
    }
    if (user != null) {
      return new ModelAndView("user", "userModelObject", user);
    } else {
      return new ModelAndView("error", "message", "Error while getting UserModelObject");
    }
  }

  @RolesAllowed(value = { "ROLE_ADMIN" })
  @RequestMapping(value = "/adminMethodJSR", method = RequestMethod.GET)
  public ModelAndView adminMethodJSR() {
    log.debug("UserController adminMethodJSR is called with ADMIN ROLE");
    return new ModelAndView("/admin/adminsecured");

  }

  //SpEL usage at method level security
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  @RequestMapping(value = "/adminMethodSecuredSpEL", method = RequestMethod.GET)
  public ModelAndView preAuthorize() {
    log.debug("UserController preAuthorize is called for ROLE_ADMIN");
    return new ModelAndView("/admin/adminsecured");
  }

  @Secured(value = { "ROLE_ADMIN" })
  @RequestMapping(value = "/adminMethodSecured", method = RequestMethod.GET)
  public ModelAndView adminMethodSecured() {
    log.debug("UserController adminMethodSecured is called with ADMIN ROLE");
    return new ModelAndView("/admin/adminsecured");

  }
}

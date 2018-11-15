package com.proitc.controller;

import com.proitc.bean.RegisterService;
import com.proitc.bean.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;


//Controller is a component so it has to be scanned with
// <context:component-scan base-package="spring.controller" />
@Controller
public class RegisterController {
  private static final Logger log = LoggerFactory.getLogger(RegisterController.class);
  @Autowired
  private RegisterService registerService;

  @RequestMapping(value = "/registeruser", method = RequestMethod.POST)
  public ModelAndView register(
    @RequestParam(required = false, defaultValue = "") String email,
    @RequestParam(required = false, defaultValue = "") String password,
    HttpServletRequest request, HttpServletResponse response)
    throws Exception {

    log.debug("RegisterController register is called");
    log.debug("RegisterController register email: " + email);
    log.debug("RegisterController register email: " + password);

    User user = new User();
    user.setUsername(email);
    user.setPassword(password);

    boolean result = registerService.registerUser(user);

    if (result) {
      return new ModelAndView("success", "message", "Successful registration");
    } else {
      return new ModelAndView("error", "message", "Error while registration");
    }

  }


}

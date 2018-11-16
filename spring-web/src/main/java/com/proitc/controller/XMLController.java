package com.proitc.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class XMLController extends AbstractController {
  private static final Logger log = LoggerFactory.getLogger(XMLController.class);

  @Override
  protected ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) {
    log.info("XMLController is called");
    log.info("XMLController request: " + request.getParameter("test"));
    return new ModelAndView("xml", "xmlModelObject", request.getParameter("test"));

  }
}

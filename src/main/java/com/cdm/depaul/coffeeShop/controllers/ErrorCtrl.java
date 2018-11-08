package com.cdm.depaul.coffeeShop.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Controller
public class ErrorCtrl implements ErrorController {

  @RequestMapping("/error")
  public String myErrorView(HttpServletRequest request) {
    int statusCode = Integer.parseInt(request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE).toString());
    if (statusCode == HttpStatus.NOT_FOUND.value()) {
      return "error-404";
    }
    // it is a 500.
    return "error";
  }

  /**
   * Returns the path of the error page.
   *
   * @return the error path
   */
  @Override
  public String getErrorPath() {
    return "/error";
  }
}

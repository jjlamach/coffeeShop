package com.cdm.depaul.coffeeShop.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@Component
@Scope("singleton")
public class HomeController {

    @RequestMapping("/home")
    public String home () {
        return "home";
    }
}

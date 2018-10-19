package com.cdm.depaul.coffeeShop.controllers;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Component
@Scope("singleton")
public class CoffeeShopController {


//    @Autowired
//    private CustomerService customerService;

    @RequestMapping(value = {"/", "/home"})
    public String home (HttpServletRequest request, HttpServletResponse response, Model model) {
        return "home";
    }


    @RequestMapping(value = {"/products"})
    public String products (HttpServletRequest request, HttpServletResponse response, Model model) {
        return "products";
    }


    @RequestMapping(value = {"/shoppingcart"})
    public String shoppingCart (HttpServletRequest request, HttpServletResponse response, Model model) {
        return "shopping";
    }
}

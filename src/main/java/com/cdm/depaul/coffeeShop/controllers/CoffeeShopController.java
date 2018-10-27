package com.cdm.depaul.coffeeShop.controllers;

import com.cdm.depaul.coffeeShop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
@Component
@Scope("singleton")
public class CoffeeShopController {

    @Autowired
    private CustomerService customerService;

    public CoffeeShopController() {}

    @RequestMapping(value = {"/", "/home"}, method = RequestMethod.GET)
    public String home (HttpServletRequest request, HttpServletResponse response, Model model) {
        return "home";
    }


    @RequestMapping(value = {"/products"}, method = RequestMethod.GET)
    public String products (HttpServletRequest request, HttpServletResponse response, Model model) {
        return "products";
    }


    @RequestMapping(value = {"/shoppingcart"}, method = RequestMethod.GET)
    public String shoppingCart (HttpServletRequest request, HttpServletResponse response, Model model) {
        return "shopping";
    }

    @RequestMapping(value = {"/coffee"}, method = RequestMethod.GET)
    public String coffeeProducts(HttpServletResponse response, HttpServletRequest request, Model model) {
        return "coffeeProducts";
    }

    @RequestMapping(value = "/sweets", method = RequestMethod.GET)
    public String sweetProducts (HttpServletResponse response, HttpServletRequest request, Model model) {
        return "sweets";
    }

    @RequestMapping(value = {"/amenities"}, method = RequestMethod.GET)
    public String amenities (HttpServletResponse response, HttpServletRequest resquest, Model model) {
        return "amenities";
    }
}

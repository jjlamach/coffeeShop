package com.cdm.depaul.coffeeShop.controllers;

import com.cdm.depaul.coffeeShop.entities.Customer;
import com.cdm.depaul.coffeeShop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@Component
@Scope("singleton")
public class CoffeeShopController {

    @Autowired
    private CustomerService customerService;

    public CoffeeShopController() {}

    @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
    public String home (HttpServletRequest request, HttpServletResponse response, Model model) {
        return "home";
    }


    @RequestMapping(value = {"/products"}, method = RequestMethod.GET)
    public String products (HttpServletRequest request, HttpServletResponse response, Model model) {
        return "products";
    }


    @RequestMapping(value = {"/shoppingcart"}, method = RequestMethod.GET)
    public String addToShoppingCart(HttpServletRequest request, HttpServletResponse response, Model model) {
        return "shopping";
    }

    /*
        TODO: Save a session for each user that registers.
        @CookieValue("cookieName") String cookieValue
                     "cookieName" = cookie value.
     */
    @RequestMapping(value = {"/login"}, method = RequestMethod.GET)
    public String login (HttpServletResponse response, HttpServletRequest request,
                         @RequestParam(value = "firstName", required = false) String firstName,
                         @RequestParam(value = "lastName", required = false) String lastName,
                         @RequestParam(value = "address", required = false) String address,
                         Model model) {


        // Gets the session or creates if there is none with (true)
        HttpSession session = request.getSession(true);

        Customer findCustomer = customerService.findByFirstAndLastAndAddress(firstName, lastName, address);

        if (findCustomer != null) {
            session.setAttribute("customerSession", findCustomer);
            System.out.println("Session name:" + session.getAttribute("customerSession"));
            return "redirect:/home";
        }
        return "login";
    }

    @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
    public String logout(HttpSession session,
                         HttpServletRequest request,
                         HttpServletResponse response,
                         Model model) throws IOException {
        Customer currentSession = (Customer) session.getAttribute("customerSession");
        if (currentSession != null) {
            System.out.println("Current session:" + "\n" + currentSession.toString());
            System.out.println("Deleting session");
            session.invalidate();
        }
        return "/login";
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

    @RequestMapping(value = {"/createAccount"})
    // not required when making the request: required = false
    public String createAnAccount(HttpServletRequest request, HttpServletResponse response,
                                  @RequestParam(value = "fName", required = false) String firstName,
                                  @RequestParam(value = "lName", required = false) String lastName,
                                  @RequestParam(value = "cAddress", required = false) String cAddress,
                                  Model model) {
        Customer customer = new Customer(firstName, lastName, cAddress);
        customerService.saveCustomer(customer);
        return "createAccount";
    }

}

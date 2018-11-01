package com.cdm.depaul.coffeeShop.controllers;

import com.cdm.depaul.coffeeShop.entities.Customer;
import com.cdm.depaul.coffeeShop.entities.Order;
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

  public CoffeeShopController() {

  }

  @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
  public String home (HttpServletRequest request, HttpServletResponse response, Model model) {
    return "home";
  }


  @RequestMapping(value = {"/products"}, method = RequestMethod.GET)
  public String products (HttpServletRequest request, HttpServletResponse response, Model model) {
    return "products";
  }


  @RequestMapping(value = {"/shoppingcart"}, method = RequestMethod.GET)
  public String addToShoppingCart(HttpSession session,
                                  HttpServletRequest request,
                                  HttpServletResponse response,
                                  Model model) {

//         get the current customer in this session.
    Customer customer = (Customer) session.getAttribute("customerSession");

    // find it in the database.
    Customer findCustomer = customerService.findCustomerById(customer.getId());

    Order order = new Order();
    order.setName("Coffee");
    order.setDescription("Regular coffee");
    order.setPrice(1.50);
    order.setCustomer(findCustomer);
    findCustomer.addOrder(order);

    customerService.saveCustomer(findCustomer);
    return "shopping";
  }

  /*
      @CookieValue("cookieName") String cookieValue
                   "cookieName" = cookie value.
   */
  @RequestMapping(value = {"/login"})
  public String login (HttpSession session, HttpServletResponse response, HttpServletRequest request,
                       Model model) {

    // supposed to be binded to the login.jsp
    Customer customer = new Customer();

    model.addAttribute("incomingCustomer", customer);



//    String firstName = request.getParameter("firstName");
//    String lastName = request.getParameter("lastName");
//    String password = request.getParameter("password");
//
//    if (firstName == null && lastName == null && password == null) {
//      System.out.println("Enter inputs");
//    } else {
//      if (customerService.findByFirstAndLastAndPassword(firstName, lastName, password) != null) {
//        return "redirect:/home";
//      }
//    }
    return "login";
  }

  @RequestMapping(value = {"/logout"}, method = RequestMethod.GET)
  public String logout(HttpSession session,
                       HttpServletRequest request,
                       HttpServletResponse response,
                       Model model) throws IOException {
    Customer currentSession = (Customer) session.getAttribute("customerSession");
    boolean wasDeleted = false;
    if (currentSession != null) {
      System.out.println("Current session:" + "\n" + currentSession.toString());
      System.out.println("Deleting session");
      wasDeleted = true;
      System.out.println("Deleted session?, " + wasDeleted);
      session.invalidate();
    }
    return "redirect:/login";
  }



  @RequestMapping(value = {"/coffee"}, method = RequestMethod.GET)
  public String coffeeProducts(HttpSession session, HttpServletResponse response,
                               HttpServletRequest request,
                               Model model) {
    return "coffeeProducts";
  }

  /*
  Link to update a customer with an order.
 */
  @PostMapping(value = "/addCoffeeToCart")
  public void addCoffee(){
    System.out.println("/addCoffeeToCart was called");
  }




  @RequestMapping(value = "/sweets", method = RequestMethod.GET)
  public String sweetProducts (HttpSession session,
                               HttpServletResponse response,
                               HttpServletRequest request,
                               Model model) {
    return "sweets";
  }



  @RequestMapping(value = {"/amenities"}, method = RequestMethod.GET)
  public String amenities (HttpSession session,
                           HttpServletResponse response,
                           HttpServletRequest request,
                           Model model) {
    return "amenities";
  }


  @RequestMapping(value = "/createAccount")
  public String createAnAccount(HttpSession session, HttpServletRequest request, HttpServletResponse response,
                                Model model) {
    Customer theCustomer = new Customer();
    model.addAttribute("customerToRegister", theCustomer);
    return "createAccount";
  }



  /* This is the mapping for saving a customer to the database.
   *  The controller will call this function when you use this path: "/saveCustomer" . */
  @PostMapping(value = "/saveCustomer")
  public String saveCustomer (@ModelAttribute(name = "customerToRegister")
                                Customer theCustomer) {
    customerService.saveCustomer(theCustomer);
    /* after calling this method redirect to: /createAccount */
    return "redirect:/createAccount";
  }
}

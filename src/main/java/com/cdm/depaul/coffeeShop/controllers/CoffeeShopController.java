package com.cdm.depaul.coffeeShop.controllers;

import com.cdm.depaul.coffeeShop.entities.Customer;
import com.cdm.depaul.coffeeShop.entities.Order;
import com.cdm.depaul.coffeeShop.services.CustomerService;
import com.cdm.depaul.coffeeShop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.Collection;
import java.util.List;
import java.util.Map;

@Controller
@Component
@Scope("singleton")
public class CoffeeShopController {

  @Autowired
  private CustomerService customerService;

  @Autowired
  private OrderService orderService;

  // The currentCustomer that is currently in the session
  @Autowired
  private Customer currentCustomer;


  public CoffeeShopController() { }

  /**
   *
   * @param model
   * @return the home view
   */
  @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
  public String home(Model model) {
    model.addAttribute("currentCustomer", this.currentCustomer);
    return "home";
  }


  @RequestMapping(value = {"/products"}, method = RequestMethod.GET)
  public String products(HttpServletRequest request, HttpServletResponse response, Model model) {
    return "products";
  }


  /**
   *
   * @param customer
   * @param redirectAttributes
   * @return the login view or home.
   */
  @RequestMapping(value = {"/login", "/authenticateUser"}, method = {RequestMethod.GET, RequestMethod.POST})
  public String login(@ModelAttribute("verifyIncomingCustomer") Customer customer,
                      RedirectAttributes redirectAttributes) {

    if (customer.getUsername() != null && customer.getPassword() != null)
    {
      Customer customer1 = customerService.getOneCustomerByUsername(customer.getUsername());
      if (customer1.getUsername().equals(customer.getUsername()) && customer1.getPassword().equals(customer.getPassword())) {
        // Store the customer into the session.
        this.currentCustomer = customer;
        redirectAttributes.addFlashAttribute("currentCustomer", this.currentCustomer);
        return "redirect:/home";
      }
    }
    return "login";
  }

  /**
   *
   * @param session
   * @return logout view.
   */
  @RequestMapping(value = "/logout", method = {RequestMethod.POST, RequestMethod.GET})
  public String logout(HttpSession session) {
    System.out.println("Session time created:" + session.getCreationTime() + " " + session.getId() +
      session.getLastAccessedTime());
    session.invalidate();
    return "logout";
  }


  @RequestMapping(value = {"/coffee"}, method = RequestMethod.GET)
  public String coffeeProducts(HttpSession session, HttpServletResponse response,
                               HttpServletRequest request,
                               Model model) {
    return "coffeeProducts";
  }

  /**
   *
   * @return the coffee purchased.
   */
  @PostMapping(value = "/addCoffeeToCart")
  public String addCoffee(RedirectAttributes redirectAttributes) {
    // Find the user that is already in the database
    Customer customer = customerService.getOneCustomerByUsername(this.currentCustomer.getUsername());

    Order coffee = new Order();
    coffee.setCustomer(customer);
    coffee.setName("Small Coffee");
    coffee.setPrice(1.50);
    coffee.setDescription("Small coffee");

    customer.addOrder(coffee);


    customerService.saveCustomer(customer);
    orderService.saveOrder(coffee);
    // We send attributes to another URL.
    redirectAttributes.addFlashAttribute(coffee);
    redirectAttributes.addFlashAttribute(customer);

    return "redirect:/shopping";
  }


  @RequestMapping(value = "/removeFromCart", method = RequestMethod.POST)
  public String removeFromCart(Model model, RedirectAttributes redirectAttributes) {
    Customer currentCustomer = customerService.getOneCustomerByUsername(this.currentCustomer.getUsername());
    List<Order> orderList = currentCustomer.getAllOrders();
    orderService.deleteOrderById(orderList.get( orderList.size() - 1).getId());

    return "redirect:/shopping";
  }

  /**
   *
   * @param model
   * @return the view and models of things purchased
   */
  @RequestMapping(value = "/shoppingCart", method = RequestMethod.GET)
  public String shoppingCart(Model model) {
//    Customer customer = new Customer();
    Order order = new Order();
    Map <String, Object> model_map = model.asMap();
    Collection model_values = model_map.values();
    for (Object value : model_values) {
      if (value instanceof Order) {
        order = (Order) value;
      }
    }
    model.addAttribute("customer", this.currentCustomer);
    return "shopping";
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


  /**
   *
   * @return the view for creating an account.
   *
   */
  @RequestMapping(value = "/confirmation")
  public String confirmationView(Model model, HttpServletRequest request, HttpServletResponse response) {
    Map <String, Object> savedCustomerModel = model.asMap();
    Customer customer = new Customer();
    Collection model_values = savedCustomerModel.values();
    for (Object value : model_values) {
      if (value instanceof  Customer) {
        // populate this currentCustomer with the flash attributes sent by registration().
        customer = (Customer) value;
      }
    }
    model.addAttribute("savedCustomer", customer);
    return "confirmation";
  }


  /**
   *
   * @param customer
   * @param redirectAttributes:
   *          the attributes to save and send to another URL if we added a Customer.
   *          redirectAttributes does not work if we are not redirecting to another URL.
   *
   *          After the redirect, flash attributes are automatically added to the model of the
   *          controller that serves the target URL.
   *
   * @return a view of registration or confirmation.
   * A Controller can be a POST or GET
   */
  @RequestMapping(value = {"/registration", "/registerCustomer"}, method = {RequestMethod.GET, RequestMethod.POST})
  public String registration(@ModelAttribute("incomingCustomer") Customer customer,
                             RedirectAttributes redirectAttributes, HttpSession session) {
    // If this is a new currentCustomer.
    if (!(customer.getFirstName() == null && customer.getLastName() == null
      &&customer.getUsername() == null && customer.getPassword() == null && customer.getAddress() == null
      && customer.getAllOrders().isEmpty())) {

      // we save the currentCustomer
      customerService.saveCustomer(customer);
      // the new currentCustomer inside a Session.
      this.currentCustomer = customer;

      // Make the attributes persist when redirecting to another URL.
      // We sent the currentCustomer that is in the session to another url.
      redirectAttributes.addFlashAttribute(this.currentCustomer);
      return "redirect:/confirmation";
    }
    return "registration";
  }
}

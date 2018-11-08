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
      if (customer1.getUsername().equals(customer.getUsername()) &&
        customer1.getPassword().equals(customer.getPassword())) {
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



  @RequestMapping(value = {"/coffeeProducts"}, method = {RequestMethod.GET})
  public String coffeeProducts(@ModelAttribute("order") Order order, Model model) {
    return "coffeeProducts";
  }


  @PostMapping(value = {"/purchase"})
  public String purchase(HttpServletRequest request) {
    String getOrderName = request.getParameter("orderType");
    String orderDescription = request.getParameter("orderDescription");
    double orderPrice = Double.parseDouble(request.getParameter("price").toString());

    Customer customer = customerService.getOneCustomerByUsername(this.currentCustomer.getUsername());

    Order order = new Order();
    order.setName(getOrderName);
    order.setDescription(orderDescription);
    order.setPrice(orderPrice);

    customer.addOrder(order);

    order.setCustomer(customer);

    customerService.saveCustomer(customer);
    orderService.saveOrder(order);

    return "redirect:/shoppingCart";
  }


  @RequestMapping(value = {"/removeFromCart"}, method = {RequestMethod.GET})
  public String removeFromCart (Model model, @RequestParam(name = "order_number") long order_number) {
    Order orderToDelete = orderService.getOneOrder(order_number);
    orderService.deleteOrder(orderToDelete);
    return "redirect:/shoppingCart";
  }

  /**
   *
   * @param model
   * @return the view and models of things purchased
   */
  @RequestMapping(value = "/shoppingCart", method = RequestMethod.GET)
  public String shoppingCart(Model model) {

    double total = 0.0;
    List <Order> orderList = orderService.findAll();
    for (Order order : orderList) {
      total = total + order.getPrice();
    }
    model.addAttribute("orderList", orderList);
    model.addAttribute("currentCustomer", this.currentCustomer);
    model.addAttribute("total", total);

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

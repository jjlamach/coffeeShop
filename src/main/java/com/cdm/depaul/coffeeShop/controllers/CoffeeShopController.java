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

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@Component
@Scope("singleton")
public class CoffeeShopController {

  @Autowired
  private CustomerService customerService;

  @Autowired
  private OrderService orderService;



  public CoffeeShopController() {

  }

  @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
  public String home (HttpServletRequest request,
                      HttpServletResponse response,
                      Model model) {
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
    return "shopping";
  }

  /**
   *
   * @return the view for the login page
   */
  @RequestMapping(value = {"/login"})
  public String login () {
    return "login";
  }


  @RequestMapping(value = {"/coffee"}, method = RequestMethod.GET)
  public String coffeeProducts(HttpSession session, HttpServletResponse response,
                               HttpServletRequest request,
                               @ModelAttribute(value = "customer") Customer customer,
                               Model model) {
    return "coffeeProducts";
  }

  /*
    TODO: How do we identify which customer purchased which thing???
   */
  @RequestMapping(value = "/addCoffeeToCart", method = RequestMethod.GET)
  public String addCoffee(Model model) {
//   Customer customer = new Customer();
//   Order order = new Order();
//    customer = customerService.getOneCustomer(8L);
//    order.setName("Coffee");
//    order.setPrice(1.50);
//    order.setCustomer(customer);
//    order.setDescription("Small coffee");
//    order.setCustomer(customer);
//    customer.addOrder(order);
//    customerService.saveCustomer(customer);
    return "shopping";
  }

  @RequestMapping(value = "/removeFromCart", method = RequestMethod.GET)
  public String removeFromCart(Model model) {
    List<Order> orderList = customerService.getOneCustomer(8L).getAllOrders();
    int size = orderList.size();
    for (int i = 0; i <= size; i++) {
      orderService.deleteOrderById(orderList.get(i).getId());
    }
    return "shopping";
  }




  @RequestMapping(value = "/shoppingCart", method = RequestMethod.GET)
  public String shoppingCart() {
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
   */
  @RequestMapping(value = "/confirmation")
  public String confirmationView(Model model) {
    Customer customer = new Customer();
    model.addAttribute("customer", customer);
    return "confirmation";
  }

  /**
   *
   * @param model
   * @param response
   * @return
   */
  @RequestMapping(value = {"/registration"})
  public String registrationView(Model model, HttpServletResponse response) {
    Customer customer = new Customer();
    model.addAttribute("customer", customer);
    return "registration";
  }

  /**
   *
   * @param customer it is the model to be bind by the input fields values of the form registration.jsp
   * @return the Spring Bean / Java Bean "Customer" to the registration view.
   *
   * @ModelAttribute without this annotation, we would have to manually do the request.getParam("paramName") for each
   *                 form field.
   */
  @PostMapping(value = {"/registerCustomer"})
  public String registerCustomer (@ModelAttribute ("customer") Customer customer, Model model) {
    customerService.saveCustomer(customer);

    model.addAttribute("customer", customer);
    return "redirect:/confirmation";
  }
}

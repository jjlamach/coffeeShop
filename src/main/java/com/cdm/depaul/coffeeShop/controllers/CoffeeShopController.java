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
import java.util.List;

@Controller
@Component
@Scope("session")
public class CoffeeShopController {

  @Autowired
  private CustomerService customerService;

  @Autowired
  private OrderService orderService;


  public CoffeeShopController() { }

  /**
   *
   * @param model
   * @param session
   * @return home view
   */
  @RequestMapping(value = {"/home"}, method = RequestMethod.GET)
  public String home(Model model, HttpSession session) {
    Customer customer = (Customer) session.getAttribute("currentCustomer");
    model.addAttribute("currentCustomer", customer);
    return "home";
  }

  /**
   *
   * @param customer
   * @param redirectAttributes
   * @return the login view or home.
   */
  @RequestMapping(value = {"/login", "/authenticateUser"}, method = {RequestMethod.GET, RequestMethod.POST})
  public String login(@ModelAttribute("verifyIncomingCustomer") Customer customer,
                      RedirectAttributes redirectAttributes,
                      HttpServletRequest request) {


    if (customer.getUsername() != null && customer.getPassword() != null)
    {
      Customer customer1 = customerService.getOneCustomerByUsername(customer.getUsername());
      if (customer1.getUsername().equals( customer.getUsername())
        && customer1.getPassword().equals(customer.getPassword())){

        HttpSession session = request.getSession(true);
        session.setAttribute("currentCustomer", customer);
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
    session.invalidate();
    return "logout";
  }

  /**
   * Makes a purchase.
   * @param request
   * @param redirectAttributes
   * @param session
   * @return
   */
  @PostMapping(value = {"/purchase"})
  public String purchase(HttpServletRequest request, RedirectAttributes redirectAttributes, HttpSession session) {

    String getOrderName = request.getParameter("orderType");
    String orderDescription = request.getParameter("orderDescription");
    double orderPrice = Double.parseDouble(request.getParameter("price").toString());

    Customer customerInSession = (Customer) session.getAttribute("currentCustomer");


    Customer customer = customerService.getOneCustomerByUsername(customerInSession.getUsername());


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

  /**
   * Removes from the cart.
   * @param model
   * @param order_number
   * @return
   */
  @RequestMapping(value = {"/removeFromCart"}, method = {RequestMethod.GET})
  public String removeFromCart (Model model, @RequestParam(name = "order_number") long order_number) {
    Order orderToDelete = orderService.getOneOrderById(order_number);
    orderService.deleteOrder(orderToDelete);
    return "redirect:/shoppingCart";
  }

  /**
   *
   * @param model
   * @return the view and models of things purchased
   */
  @RequestMapping(value = "/shoppingCart", method = RequestMethod.GET)
  public String shoppingCart(Model model, HttpSession session) {

    Customer customer = (Customer) session.getAttribute("currentCustomer");

    Customer customerToFind = customerService.getOneCustomerByUsername(customer.getUsername());

    double total = 0.0;

    List <Order> orderList = orderService.getAllOrdersOfCustomerById(customerToFind.getId());
    for (Order order : orderList) {
      total = total + order.getPrice();
    }
    model.addAttribute("orderList", orderList);
    model.addAttribute("currentCustomer", customer);
    model.addAttribute("total", total);

    return "shopping";
  }

  /**
   *
   * @return the view for creating an account.
   *
   */
  @RequestMapping(value = "/confirmation")
  public String confirmationView(Model model, HttpSession session) {

    Customer currentCustomer = (Customer) session.getAttribute("currentCustomer");
    model.addAttribute("currentCustomer", currentCustomer);

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
                             HttpServletRequest request, HttpServletResponse response, HttpSession session, RedirectAttributes redirectAttributes) {
    /* If this is a new currentCustomer. */
    if (!(customer.getFirstName() == null && customer.getLastName() == null
      &&customer.getUsername() == null && customer.getPassword() == null && customer.getAddress() == null
      && customer.getAllOrders().isEmpty())) {

      // we save the currentCustomer
      customerService.saveCustomer(customer);

      session = request.getSession(true);


      Customer currentCustomer = customerService.getOneCustomerById(customer.getId());

      session.setAttribute("currentCustomer", currentCustomer);

      return "redirect:/confirmation";
    }
    return "registration";
  }
}

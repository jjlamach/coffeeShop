package com.cdm.depaul.coffeeShop;

import com.cdm.depaul.coffeeShop.entities.Customer;
import com.cdm.depaul.coffeeShop.entities.Order;
import com.cdm.depaul.coffeeShop.services.CustomerService;
import com.cdm.depaul.coffeeShop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.List;
import java.util.logging.Logger;


@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
public class CoffeeShopApplication implements CommandLineRunner {

  static Logger applicationLogger = Logger.getLogger(CoffeeShopApplication.class.getName());


  @Autowired
  CustomerService customerService;
  @Autowired
  OrderService orderService;

  public static void main(String[] args) {
    SpringApplication.run(CoffeeShopApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {
//    Customer customer = new Customer();
//    customer.setUsername("jlama");
//    customer.setPassword("julio123");
//    customer.setFirstName("Julio");
//    customer.setLastName("Lama");
//
//    Order order = new Order();
//    order.setCustomer(customer);
//    order.setDescription("Small coffee");
//    order.setName("Coffee");
//    order.setPrice(1.50);

//    customer.addOrder(order);

//    order.setCustomer(customer);

//    customerService.saveCustomer(customer);
//    orderService.saveOrder(order);


//      Customer currentCustomer = customerService.getOneCustomerByUsername("jlama");
//      List<Order> orderList = currentCustomer.getAllOrders();
//      System.out.println(orderList.size() + " " + orderList.get(orderList.size() - 1).getName());


//      orderService.deleteOrderById(orderList.get(orderList.size() - 1).getId());


//    orderService.deleteOrderById(customer.getAllOrders().get(0).getId());

  }
}

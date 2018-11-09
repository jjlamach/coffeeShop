package com.cdm.depaul.coffeeShop;

import com.cdm.depaul.coffeeShop.entities.Customer;
import com.cdm.depaul.coffeeShop.entities.Order;
import com.cdm.depaul.coffeeShop.services.CustomerService;
import com.cdm.depaul.coffeeShop.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
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
//    List<Customer> customerList = customerService.getAllCustomers();
//
//    for (Customer customer : customerList) {
//      System.out.println(customer.getId());
//    }
//    Customer customer = customerService.getOneCustomerByUsername("jlama");
//    List<Order> orderList = customer.getAllOrders();
//    for (Order order : orderList) {
//      System.out.println(order.getId() + " " + order.getName());
//    }
//    System.out.println(customer.getUsername() + "---> " + customer.getId());
  }
}

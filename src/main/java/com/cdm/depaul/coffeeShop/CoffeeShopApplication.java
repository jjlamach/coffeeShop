package com.cdm.depaul.coffeeShop;

import com.cdm.depaul.coffeeShop.entities.Customer;
import com.cdm.depaul.coffeeShop.entities.Order;
import com.cdm.depaul.coffeeShop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.logging.Level;
import java.util.logging.Logger;


@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
public class CoffeeShopApplication implements CommandLineRunner {

    static Logger applicationLogger = Logger.getLogger(CoffeeShopApplication.class.getName());


    @Autowired
    CustomerService customerService;
    
    public static void main(String[] args) {
		SpringApplication.run(CoffeeShopApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
//        Customer customer = new Customer("Julio", "Lama", "1730 North Clark Street");
////        customerService.saveCustomer(customer);
//        Customer findOne = customerService.findByFirstAndLast(customer.getFirstName(), customer.getLastName());
//        System.out.println("Found it:" + findOne.toString());
    }
}

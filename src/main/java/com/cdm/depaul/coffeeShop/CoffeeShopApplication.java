package com.cdm.depaul.coffeeShop;

import com.cdm.depaul.coffeeShop.entities.Customer;
import com.cdm.depaul.coffeeShop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

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
      Customer customer = customerService.getOneCustomer(1L);
      System.out.println(customer.getId());
    }
}

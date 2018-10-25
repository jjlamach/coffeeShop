package com.cdm.depaul.coffeeShop;

import com.cdm.depaul.coffeeShop.entities.Customer;
import com.cdm.depaul.coffeeShop.entities.Order;
import com.cdm.depaul.coffeeShop.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
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
        try {
            Customer customer = new Customer("Julio",
                    "Lama",
                    "1730 North Clark Street");

            Order order = new Order("Chocolate cake",
                    "Big chocolate cake",
                    7.00);

            customer.addOrder(order);
            order.setCustomer(customer);

            // Ask the Service object to save the customer.
            customerService.saveCustomer(customer);

        } catch (Exception exception) {
            applicationLogger.log(Level.SEVERE, "Could not add customer and order to tables.", exception);
        }
    }
}

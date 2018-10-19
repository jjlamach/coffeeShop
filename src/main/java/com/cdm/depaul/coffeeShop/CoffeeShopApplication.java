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


@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
public class CoffeeShopApplication implements CommandLineRunner {

    @Autowired
    CustomerService customerService;
    
    public static void main(String[] args) {
		SpringApplication.run(CoffeeShopApplication.class, args);
	}

    @Override
    public void run(String... args) throws Exception {
        Customer customer = new Customer("Julio", "Lama", "1730 North clark Street");

        Order order = new Order("Vanilla Cold Brew", "Iced coffee", 3.00);
        customer.addOrder(order);
        order.setCustomer(customer);

//        customerService.saveCustomer(customer);
        customerService.deleteCustomerById(1L);
    }
}

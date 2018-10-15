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

        Customer customer = new Customer();
        customer.setFirstName("Jeffrey");
        customer.setLastName("Taksas");
        customer.setAddress("1730 North clark Street");

        Order order = new Order();
        order.setName("Pumpkin pie");
        order.setPrice(5.00);

        customer.addOrder(order);
        customerService.saveCustomer(customer);

        Customer findOne = customerService.findCustomer(3L);
        System.out.println(findOne.getFirstName() + " " + findOne.getLastName());
    }
}

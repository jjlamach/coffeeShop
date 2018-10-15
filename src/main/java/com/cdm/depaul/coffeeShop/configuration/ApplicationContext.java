package com.cdm.depaul.coffeeShop.configuration;

import com.cdm.depaul.coffeeShop.entities.Customer;
import com.cdm.depaul.coffeeShop.entities.Order;
import com.cdm.depaul.coffeeShop.services.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * ApplicationContext.xml, where you register for your Spring Beans here.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.cdm.depaul.coffeeShop" })
public class ApplicationContext {

    @Bean
    public Customer customer () {
        return new Customer();
    }

    @Bean
    public Order order () {
        return new Order();
    }

    @Bean
    public CustomerService customerService() {return new CustomerService(); }


    @Bean
    public ViewResolver viewResolver () {
        InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
        viewResolver.setPrefix("WEB-INF/jsp/");
        viewResolver.setSuffix(".jsp");
        return viewResolver;
    }
}

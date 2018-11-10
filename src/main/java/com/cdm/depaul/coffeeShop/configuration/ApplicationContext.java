package com.cdm.depaul.coffeeShop.configuration;

import com.cdm.depaul.coffeeShop.entities.Customer;
import com.cdm.depaul.coffeeShop.entities.Order;
import com.cdm.depaul.coffeeShop.repositories.CustomerRepository;
import com.cdm.depaul.coffeeShop.repositories.OrderRepository;
import com.cdm.depaul.coffeeShop.services.CustomerService;
import com.cdm.depaul.coffeeShop.services.OrderService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

/**
 * ApplicationContext.xml. You register your Spring Beans here and configure your app.
 *
 * - Defines callback methods to customize the Java-based configuration for Spring MVC enabled via @EnableWebMvc.
 *
 * - @EnableWebMvc-annotated configuration classes may implement this interface
 *      to be called back and given a chance to customize the default configuration.
 */
@Configuration
@EnableWebMvc
@ComponentScan(basePackages = { "com.cdm.depaul.coffeeShop" })
public class ApplicationContext implements WebMvcConfigurer {

  @Bean
  public Customer customer () {
    return new Customer();
  }

  @Bean
  public Order order () {
    return new Order();
  }



  @Bean
  public CustomerService customerService(CustomerRepository customerRepository) {
    return new CustomerService(customerRepository);
  }

  @Bean
  public OrderService orderService(OrderRepository orderRepository) {
    return new OrderService(orderRepository);
  }


  /**
   * Redirects to views based off of paths.
   * @param registry
   */
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/", "/login");
    registry.addRedirectViewController("/registration", "/registration");
  }

  /**
   * Configures the view for the web application.
   * @return
   */
  @Bean
  public ViewResolver viewResolver () {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("WEB-INF/view/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
  }

  /**
   * Tell Spring where to find the resources for the web application.
   * If this is not overridden then it won't find the resources.
   * @param resourceHandlerRegistry
   */
  public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
    resourceHandlerRegistry.addResourceHandler( "/webjars/**", "/resources/**")
      .addResourceLocations("/webjars/")
      .addResourceLocations("/resources/");
  }
}

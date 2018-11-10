package com.cdm.depaul.coffeeShop;

import com.cdm.depaul.coffeeShop.entities.Customer;
import com.cdm.depaul.coffeeShop.entities.Order;
import com.cdm.depaul.coffeeShop.repositories.CustomerRepository;
import com.cdm.depaul.coffeeShop.repositories.OrderRepository;
import com.cdm.depaul.coffeeShop.services.CustomerService;
import com.cdm.depaul.coffeeShop.services.OrderService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.web.servlet.ViewResolver;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.springframework.web.servlet.view.InternalResourceViewResolver;

import java.util.logging.Logger;

/**
 *  A class that implements WebMvcConfigurer is your "ApplicationContext.xml",
 *    you register your Spring Beans here and configure your web application.
 *
 *  - It Defines callback methods to customize the Java-based configuration for Spring MVC, enabled via @EnableWebMvc.
 *
 * - @EnableWebMvc-annotated configuration classes may implement this interface
 *    to be called back and given a chance to customize the default configuration.
 *
 */

@SpringBootApplication
@EnableJpaAuditing
@EnableJpaRepositories
@EnableWebMvc
public class CoffeeShopApplication implements CommandLineRunner, WebMvcConfigurer {


  static Logger applicationLogger = Logger.getLogger(CoffeeShopApplication.class.getName());

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
   * Spring: "You can use it in static cases when there is no Java controller logic to execute before the view generates the response."
   * https://docs.spring.io/spring/docs/current/spring-framework-reference/web.html#mvc-config
   * @param registry
   */
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/", "/login");
    registry.addRedirectViewController("/registration", "/registration");
    registry.addViewController("/sweets");
    registry.addViewController("/products");
    registry.addViewController("/amenities");
    registry.addViewController("/coffeeProducts");
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



  public static void main(String[] args) {
    SpringApplication.run(CoffeeShopApplication.class, args);
  }

  @Override
  public void run(String... args) throws Exception {

  }
}

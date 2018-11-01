package com.cdm.depaul.coffeeShop.configuration;

import com.cdm.depaul.coffeeShop.entities.Customer;
import com.cdm.depaul.coffeeShop.entities.Order;
import com.cdm.depaul.coffeeShop.services.CustomerService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
//import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
//import org.springframework.security.core.userdetails.UserDetailsService;
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
//@EnableWebSecurity
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
  public CustomerService customerService() {return new CustomerService(); }


  /**
   --- Redirects to any view when invoked ---
   "Immediately forwards to a view when invoked."
   I assume that since the DispatcherServlet is the first controller to be invoked "/" then
   it will redirect to "home" view.
   */
  @Override
  public void addViewControllers(ViewControllerRegistry registry) {
    registry.addRedirectViewController("/", "login");
  }

//    /*
//        Sets which paths must be secure and which should not
//     */
//    @Override
//    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().antMatchers("/", "/home").permitAll()
//                .anyRequest().authenticated()
//                .and()
//                .formLogin()
//                .loginPage("/login")
//                .permitAll()
//                .and()
//                .logout()
//                .permitAll();
//    }
//
//    @Override
//    public UserDetailsService userDetailsServiceBean() throws Exception {
//        return super.userDetailsServiceBean();
//    }


  @Bean
  public ViewResolver viewResolver () {
    InternalResourceViewResolver viewResolver = new InternalResourceViewResolver();
    viewResolver.setPrefix("WEB-INF/view/");
    viewResolver.setSuffix(".jsp");
    return viewResolver;
  }

  /**
   * Tell Spring where to find the resources for the WebApp.
   * If this is not overridden then it won't find the resources
   * @param resourceHandlerRegistry
   */
  public void addResourceHandlers(ResourceHandlerRegistry resourceHandlerRegistry) {
    resourceHandlerRegistry.addResourceHandler( "/webjars/**", "/resources/**")
      .addResourceLocations("/webjars/")
      .addResourceLocations("/resources/");
  }
}

package com.cdm.depaul.coffeeShop.services;

import com.cdm.depaul.coffeeShop.entities.Customer;
import com.cdm.depaul.coffeeShop.repositories.CustomerRepository;
import com.cdm.depaul.coffeeShop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
//import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Optional;

@Service
@Component
@Scope("singleton") // Only one instance
public class CustomerService {

  @Autowired
  private CustomerRepository customerRepository;

  @Autowired
  private OrderRepository orderRepository;


//  @Autowired
//  private BCryptPasswordEncoder passwordEncoder;

  public CustomerService() {}

  public CustomerService (CustomerRepository customerRepository, OrderRepository orderRepository) {
    this.customerRepository = customerRepository;
    this.orderRepository = orderRepository;
  }


  public void saveCustomer (Customer customer) {
    customerRepository.save(customer);
  }

  public List<Customer> getAllCustomers () {
    return customerRepository.findAll();
  }

  public void deleteAllCustomers () { customerRepository.deleteAll(); }

  public void deleteCustomerById (Long customerId) {
    customerRepository.deleteById(customerId);
  }

  @PostConstruct
  public void initializingCustomer() {
    System.out.println("Spring Bean: CustomerService");
  }

  @PreDestroy
  public void destroy() {
    System.out.println("Destroying Spring Bean: CustomerService");
  }

  // eh???
  public Customer findByFirstAndLastAndPassword(String firstName, String lastName, String address) {
    Optional <Customer> result = customerRepository.findByFirstNameAndLastNameAAndAddress(firstName, lastName, address);
    return optionalToCustomer(result);
  }

  /**
   * This JPA function finds and updates a Customer.
   * @param id
   * @return
   */
  public Customer getOneCustomer (long id) {
    return customerRepository.getOne(id);
  }

  /**
   *
   * @param username
   * @return a Customer
   */
  public Customer getOneCustomerByUsername (String username) {
    Optional<Customer> opCustomer = customerRepository.findCustomerByUsername(username);
    return optionalToCustomer(opCustomer);
  }



  /**
   * Maps the Optional object as a Customer object.
   * @param optionalCustomer
   * @return
   */
  private Customer optionalToCustomer (Optional <Customer> optionalCustomer) {
    Customer customer = new Customer();
    if (optionalCustomer.isPresent()) {
      customer.setFirstName(optionalCustomer.get().getFirstName());
      customer.setLastName(optionalCustomer.get().getLastName());
      customer.setAddress(optionalCustomer.get().getAddress());
      customer.setCustomerId(optionalCustomer.get().getId());
      customer.setAllOrders(optionalCustomer.get().getAllOrders());
      customer.setUsername(optionalCustomer.get().getUsername());
      customer.setPassword(optionalCustomer.get().getPassword());
    }
    return customer;
  }
}

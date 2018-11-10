package com.cdm.depaul.coffeeShop.services;

import com.cdm.depaul.coffeeShop.entities.Customer;
import com.cdm.depaul.coffeeShop.repositories.CustomerRepository;
import com.cdm.depaul.coffeeShop.interfaces.iCustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
@Component
@Scope("session")
public class CustomerService implements iCustomerService, Serializable {

  private static final long serialVersionUID = 820186343653174829L;

  @Autowired
  private CustomerRepository customerRepository;


  public CustomerService (CustomerRepository customerRepository) {
    this.customerRepository = customerRepository;
  }

  @Override
  public void saveCustomer(Customer customer) {
    customerRepository.save(customer);
  }

  @Override
  public void deleteAllCustomers() {
    customerRepository.deleteAll();
  }

  @Override
  public void deleteCustomerById(Long customerId) {
    customerRepository.deleteById(customerId);
  }

  @PostConstruct
  @Override
  public void preOperation() {
    System.out.println("Generated Spring Bean: " + getClass().getName());
  }

  @PreDestroy
  @Override
  public void postOperation() {
    System.out.println("Destroying Spring Bean: " + getClass().getName());
  }

  @Override
  public List<Customer> getAllCustomers() {
    return customerRepository.findAll();
  }

  @Override
  public Customer getOneCustomerById(Long customerId) {
    return customerRepository.getOne(customerId);
  }

  @Override
  public Customer getOneCustomerByUsername(String username) {
    Optional <Customer> optionalCustomer = customerRepository.findCustomerByUsername(username);
    return optionalToCustomer(optionalCustomer);
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

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof CustomerService)) return false;
    CustomerService that = (CustomerService) o;
    return Objects.equals(customerRepository, that.customerRepository);
  }

  @Override
  public int hashCode() {
    return Objects.hash(customerRepository);
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("CustomerService{");
    sb.append("customerRepository=").append(customerRepository);
    sb.append('}');
    return sb.toString();
  }
}

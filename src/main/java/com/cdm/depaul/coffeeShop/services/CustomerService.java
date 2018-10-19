package com.cdm.depaul.coffeeShop.services;

import com.cdm.depaul.coffeeShop.entities.Customer;
import com.cdm.depaul.coffeeShop.repositories.CustomerRepository;
import com.cdm.depaul.coffeeShop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;
import java.util.List;
import java.util.Optional;

@Service
@Component
@Scope("singleton")
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private OrderRepository orderRepository;

    public CustomerService () { }

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


    public Customer findCustomer (Long customerId) {
        Optional <Customer> findOne = customerRepository.findById(customerId);
        return optionalToCustomer(findOne);
    }


    private Customer optionalToCustomer (Optional <Customer> optionalCustomer) {
        Customer customer = new Customer();
        if (optionalCustomer.isPresent()) {
            customer.setFirstName(optionalCustomer.get().getFirstName());
            customer.setLastName(optionalCustomer.get().getLastName());
            customer.setAddress(optionalCustomer.get().getAddress());
            customer.setCustomerId(optionalCustomer.get().getId());
            customer.setAllOrders(optionalCustomer.get().getAllOrders());
        }
        return customer;
    }
}

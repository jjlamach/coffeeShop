package com.cdm.depaul.coffeeShop.interfaces;

import com.cdm.depaul.coffeeShop.entities.Customer;

import java.util.List;

public interface iCustomerService {

  void saveCustomer (Customer customer);

  void deleteAllCustomers ();

  void deleteCustomerById (Long customerId);

  void preOperation();

  void postOperation();

  List<Customer> getAllCustomers ();

  Customer getOneCustomerById(Long customerId);

  Customer getOneCustomerByUsername (String username);

  Customer getOneByFirstNameAndLastNameAndPassword (String firstName, String lastName, String password);

  int hashCode();

  String toString ();

  boolean equals(Object o);
}

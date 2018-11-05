//package com.cdm.depaul.coffeeShop.services;
//
//import com.cdm.depaul.coffeeShop.entities.Customer;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.security.core.userdetails.User;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.core.userdetails.UsernameNotFoundException;
//import org.springframework.stereotype.Service;
//
//
//@Service
//public class CustomerDetailService implements UserDetailsService {
//
//  @Autowired
//  private CustomerService customerService;
//
//  @Override
//  public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
//    Customer customer = customerService.getOneCustomerByUsername(s);
//    UserDetails userDetails = new User(customer.getUsername(), customer.getPassword(), null);
//    return userDetails;
//  }
//}

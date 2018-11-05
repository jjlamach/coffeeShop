package com.cdm.depaul.coffeeShop.interfaces;

import com.cdm.depaul.coffeeShop.entities.Order;

import java.util.List;

public interface iCustomer {
  void setFirstName (String firstName);

  void setLastName (String lastName);

  void setAddress (String address);

  void addOrder (Order order);

  void setCustomerId (Long customerId);

  void setAllOrders (List<Order> orderList);

  void setPassword (String password);

  void setUsername(String username);

  String getFirstName ();

  String getPassword();

  String getLastName ();

  String getAddress();

  String getUsername();

  Long getId ();

  List <Order> getAllOrders ();

  int hashCode();

  String toString ();
}

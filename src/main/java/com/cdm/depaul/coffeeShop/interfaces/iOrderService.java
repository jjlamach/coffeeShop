package com.cdm.depaul.coffeeShop.interfaces;

import com.cdm.depaul.coffeeShop.entities.Order;

import java.util.List;

public interface iOrderService {

  void deleteOrderById (Long orderId);

  void saveOrder (Order order);

  void deleteOrder (Order order);

  Order getOneOrderById (Long orderId);

  void deleteAllOrders ();

  List<Order> getAllOrders ();

  // This is the findAllVersionTwo.
  List<Order> getAllOrdersOfCustomerById (Long customerId);

  String toString ();

  int hashCode();

  boolean equals(Object o);
}

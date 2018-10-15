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

    String getFirstName ();

    String getLastName ();

    String getAddress();

    Long getId ();

    List <Order> getAllOrders ();

    int hashCode();

    String toString ();
}

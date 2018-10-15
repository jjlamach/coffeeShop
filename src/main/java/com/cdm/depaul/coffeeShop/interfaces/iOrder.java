package com.cdm.depaul.coffeeShop.interfaces;

import com.cdm.depaul.coffeeShop.entities.Customer;

public interface iOrder {
    void setName (String name);

    void setDescription (String description);

    void setPrice (double price);

    void setCustomer (Customer customer);


    String getName ();

    String getDescription ();

    double getPrice ();

    Long getId ();

    Customer getCustomer ();

    int hashCode ();

    String toString ();
}

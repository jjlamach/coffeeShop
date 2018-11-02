package com.cdm.depaul.coffeeShop.entities;

import com.cdm.depaul.coffeeShop.interfaces.iOrder;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Objects;

/**
 * Serialization: where an object can be represented as a sequence of bytes that
 * includes the object's data as well as information about the object's type and the types of data stored in the object.
 *
 * - Can be written to a file and turn back to an object in memory again (deserialization).
 */
@Entity
@Table(name = "customer_order")
@Component
@Scope("prototype")
public class Order implements iOrder, Serializable {

  private static final long serialVersionUID = 4876487231629720215L;


  /*
      Primary Key
      customer_order add constraint FKf9abd30bhiqvugayxlpq8ryq9 foreign key (customer_id) references customer (customer_id)
   */
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "order_number")
  private Long id;

  @Column
  private String orderName;
  @Column
  private String description;
  @Column
  private double price;

  @ManyToOne(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
  @JoinColumn(name = "customer_id")
  private Customer customer;

  public Order () { }

  public Order(String orderName, String description, double price) {
    this.orderName = orderName;
    this.description = description;
    this.price = price;
  }

  @Override
  public void setName(String name) {
    this.orderName = name;
  }

  @Override
  public void setDescription(String description) {
    this.description = description;
  }

  @Override
  public void setPrice(double price) {
    this.price = price;
  }

  @Override
  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  @Override
  public String getName() {
    return orderName;
  }

  @Override
  public String getDescription() {
    return description;
  }

  @Override
  public double getPrice() {
    return price;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public Customer getCustomer() {
    return customer;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Order)) return false;
    Order order = (Order) o;
    return Double.compare(order.getPrice(), getPrice()) == 0 &&
      Objects.equals(getId(), order.getId()) &&
      Objects.equals(orderName, order.orderName) &&
      Objects.equals(getDescription(), order.getDescription()) &&
      Objects.equals(getCustomer(), order.getCustomer());
  }


  @Override
  public int hashCode() {
    return Objects.hash(getId(), orderName, getDescription(), getPrice(), getCustomer());
  }

}

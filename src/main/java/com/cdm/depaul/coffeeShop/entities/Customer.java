package com.cdm.depaul.coffeeShop.entities;

import com.cdm.depaul.coffeeShop.interfaces.iCustomer;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;
import org.springframework.web.context.annotation.SessionScope;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
/**
 * Serialization: where an object can be represented as a sequence of bytes that
 * includes the object's data as well as information about the object's type and the types of data stored in the object.
 *
 * - Can be written to a file and turn back to an object in memory again (deserialization).
 */
@Entity
@Component
//@Scope("prototype")
/* To keep track of the Customer in the session, we change the scope to @Scope("session") */
//@Scope("session")
@ApplicationScope
public class Customer implements iCustomer, Serializable {


  private static final long serialVersionUID = 870052873754135183L;

  /*
    Primary Key of the Customer table.
   */
  @NotNull
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Column(name = "customer_id")
  private Long id;

  @Column
  private String firstName;

  @Column
  private String lastName;

  @Column
  private String address;

  @Column
  private String password;

  @Column
  private String username;


  /**
   * FetchType.EAGER: load everything.
   * mappedBy: refers to the Customer field that is on the Order class.
   *          Tells Hibernate how to map this table with the other table by using the information
   *          that is at @joinColumn.
   *
   * orphanRemoval: removes a child(Order) without removing the parent (Customer)
   * A Link why cascade = {value1...value2...} does not work and why just orphanRemoval works without the cascades.
   * Link: https://stackoverflow.com/questions/16898085/jpa-hibernate-remove-entity-sometimes-not-working
   */
  // NOTE: Do not do cascade deletes! D:! It would delete the order if you delete a customer.
//  @OneToMany(cascade = {CascadeType.ALL},orphanRemoval = true, mappedBy = "customer",targetEntity = Order.class, fetch = FetchType.EAGER)


  /**
   * Customer -> many orders
   */
  @OneToMany(mappedBy = "customer", fetch = FetchType.EAGER)
  private List<Order> orderList;



  public Customer() {
    this.orderList = new ArrayList<>();
  }

  public Customer (String firstName, String lastName, String address, String password, String username) {
    this.firstName = firstName;
    this.lastName = lastName;
    this.address = address;
    this.password = password;
    this.username = username;
    this.orderList = new ArrayList<>();
  }

  @Override
  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  @Override
  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  @Override
  public void setAddress(String address) {
    this.address = address;
  }

  @Override
  public void addOrder(Order order) {
    this.orderList.add(order);
  }

  // Just for mapping. Since Hibernate will give me a generated primary key.
  @Override
  public void setCustomerId(Long customerId) {
    this.id = customerId;
  }
  // Just for mapping.
  @Override
  public void setAllOrders(List<Order> orderList) {
    this.orderList = orderList;
  }

  @Override
  public void setPassword(String password) {
    this.password = password;
  }

  @Override
  public void setUsername(String username) {
    this.username = username;
  }

  @Override
  public String getFirstName() {
    return firstName;
  }

  @Override
  public String getPassword() {
    return password;
  }

  @Override
  public String getLastName() {
    return lastName;
  }

  @Override
  public String getAddress() {
    return address;
  }

  @Override
  public String getUsername() {
    return username;
  }

  @Override
  public Long getId() {
    return id;
  }

  @Override
  public List<Order> getAllOrders() {
    return orderList;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof Customer)) return false;
    Customer customer = (Customer) o;
    return Objects.equals(getId(), customer.getId()) &&
      Objects.equals(getFirstName(), customer.getFirstName()) &&
      Objects.equals(getLastName(), customer.getLastName()) &&
      Objects.equals(getAddress(), customer.getAddress()) &&
      Objects.equals(getPassword(), customer.getPassword()) &&
      Objects.equals(getUsername(), customer.getUsername()) &&
      Objects.equals(orderList, customer.orderList);
  }

  @Override
  public int hashCode() {
    return Objects.hash(getId(), getFirstName(), getLastName(), getAddress(), getPassword(), getUsername(), orderList);
  }
}
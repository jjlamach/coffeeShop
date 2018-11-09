package com.cdm.depaul.coffeeShop.services;

import com.cdm.depaul.coffeeShop.entities.Order;
import com.cdm.depaul.coffeeShop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.web.context.annotation.ApplicationScope;

import java.util.List;

@Service
@Component
//@Scope("singleton")
@ApplicationScope
public class OrderService {

  @Autowired
  OrderRepository orderRepository;

  public OrderService() { }

  public void deleteOrderById(long id) {
    orderRepository.deleteById(id);
  }

  public void saveOrder (Order order) {
    orderRepository.save(order);
  }

  public void deleteOrder (Order order) {
    orderRepository.delete(order);
  }

  public Order getOneOrder(long id) {
    return orderRepository.getOne(id);
  }

  public void deleteAll() {
    orderRepository.deleteAll();
  }

  public List<Order> findAll(){ return orderRepository.findAll();}

  public List<Order> findAllVersion2 (long customerID) {
    return orderRepository.findAllVersion2(customerID);
  }
}

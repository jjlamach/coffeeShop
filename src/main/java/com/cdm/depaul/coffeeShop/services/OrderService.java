package com.cdm.depaul.coffeeShop.services;

import com.cdm.depaul.coffeeShop.entities.Order;
import com.cdm.depaul.coffeeShop.repositories.OrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Service
@Component
@Scope("singleton")
public class OrderService {

  @Autowired
  OrderRepository orderRepository;

  public OrderService() {

  }

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
}

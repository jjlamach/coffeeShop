package com.cdm.depaul.coffeeShop.services;

import com.cdm.depaul.coffeeShop.entities.Order;
import com.cdm.depaul.coffeeShop.repositories.OrderRepository;
import com.cdm.depaul.coffeeShop.interfaces.iOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Service
@Component
@Scope("session")
public class OrderService implements iOrderService, Serializable {

  private static final long serialVersionUID = -7234593580809212675L;

  @Autowired
  private OrderRepository orderRepository;

  public OrderService(OrderRepository orderRepository) {
    this.orderRepository = orderRepository;
  }

  @Override
  public void deleteOrderById(Long orderId) {
    orderRepository.deleteById(orderId);
  }

  @Override
  public void saveOrder(Order order) {
    orderRepository.save(order);
  }

  @Override
  public void deleteOrder(Order order) {
    orderRepository.delete(order);
  }

  @Override
  public Order getOneOrderById(Long orderId) {
    return orderRepository.getOne(orderId);
  }

  @Override
  public void deleteAllOrders() {
    orderRepository.deleteAll();
  }

  @Override
  public List<Order> getAllOrders() {
    return orderRepository.findAll();
  }

  @Override
  public List<Order> getAllOrdersOfCustomerById(Long customerId) {
    return orderRepository.findAllOrdersByCustomerId(customerId);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (!(o instanceof OrderService)) return false;
    OrderService that = (OrderService) o;
    return Objects.equals(orderRepository, that.orderRepository);
  }

  @Override
  public int hashCode() {
    return Objects.hash(orderRepository);
  }

  @Override
  public String toString() {
    final StringBuffer sb = new StringBuffer("OrderService{");
    sb.append("orderRepository=").append(orderRepository);
    sb.append('}');
    return sb.toString();
  }
}

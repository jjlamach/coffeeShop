package com.cdm.depaul.coffeeShop.repositories;

import com.cdm.depaul.coffeeShop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {
  @Override
  void deleteById(Long id);

  @Override
  void delete(Order entity);

  @Override
  Order getOne(Long aLong);

  @Override
  void deleteAll();

  @Override
  List<Order> findAll();

  @Query(value = "SELECT * FROM customer_order where customer_order.customer_id =:customerID", nativeQuery = true)
  List<Order> findAllOrdersByCustomerId(@Param("customerID") long customerID);

}

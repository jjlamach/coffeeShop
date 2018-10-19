package com.cdm.depaul.coffeeShop.repositories;

import com.cdm.depaul.coffeeShop.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderRepository extends JpaRepository <Order, Long> {}

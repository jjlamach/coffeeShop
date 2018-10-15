package com.cdm.depaul.coffeeShop.repositories;

import com.cdm.depaul.coffeeShop.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CustomerRepository extends JpaRepository<Customer, Long> {}

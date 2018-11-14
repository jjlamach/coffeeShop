package com.cdm.depaul.coffeeShop.repositories;

import com.cdm.depaul.coffeeShop.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository < Customer, Long> {
  /* Native query: these are actual SQL queries. */
  @Query(value = "SELECT * FROM customer c where c.first_name =:firstName and c.last_name =:lastName and c.password =:password LIMIT 1",
    nativeQuery = true)
  Optional <Customer> findByFirstNameAndLastNameAndPassword(@Param("firstName") String firstName,
                                                            @Param("lastName") String lastName,
                                                            @Param("password") String password);



  @Query(value = "SELECT * FROM customer where customer.customer_id =:customerId", nativeQuery = true)
  @Override
  Customer getOne(@Param("customerId") Long customerId);




  /* @Param: data binding parameter. */
  @Query(value = "SELECT * FROM customer c where c.username =:username LIMIT 1", nativeQuery = true)
  Optional<Customer> findCustomerByUsername(@Param("username") String username);

}

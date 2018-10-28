package com.cdm.depaul.coffeeShop.repositories;

import com.cdm.depaul.coffeeShop.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository <Customer, Long> {

    // Native query as in the standard SQL language?
    @Query(value = "SELECT * FROM customer c where c.first_name =:firstName and c.last_name =:lastName",
            nativeQuery = true)
    Optional <Customer> findByFirstNameAndLastName(@Param("firstName") String firstName,
                                                   @Param("lastName") String lastName);

}

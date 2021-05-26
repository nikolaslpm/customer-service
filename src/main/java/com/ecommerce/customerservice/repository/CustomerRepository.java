package com.ecommerce.customerservice.repository;

import com.ecommerce.customerservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

    Optional<Customer> getCustomerById(Long id);

}

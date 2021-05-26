package com.ecommerce.customerservice.repository;

import com.ecommerce.customerservice.domain.Customer;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRepository extends JpaRepository<Customer, Long> {

}

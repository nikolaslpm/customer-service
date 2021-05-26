package com.ecommerce.customerservice.service;

import com.ecommerce.customerservice.domain.Customer;
import com.ecommerce.customerservice.repository.CustomerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CustomerService {

    private final CustomerRepository customerRepository;

    public Customer getCustomerById(Long id) {
        return customerRepository.getCustomerById(id)
            .orElseThrow(() -> new IllegalArgumentException(String.format("Customer with id %s was not found!", id)));
    }

    public List<Customer> getAllCustomers(){
        return customerRepository.findAll();
    }
}

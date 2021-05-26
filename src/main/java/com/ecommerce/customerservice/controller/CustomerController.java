package com.ecommerce.customerservice.controller;

import com.ecommerce.customerservice.domain.Customer;
import com.ecommerce.customerservice.service.CustomerService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/v1/customers")
@RestController
public class CustomerController {

    private final CustomerService customerService;

    @GetMapping("/{customerId}")
    public ResponseEntity<Customer> getById(@PathVariable String customerId) {
        Customer customer = customerService.getCustomerById(Long.valueOf(customerId));
        return ResponseEntity.status(HttpStatus.OK).body(customer);
    }

    @GetMapping
    public ResponseEntity<List<Customer>> findAll() {
        List<Customer> customers = customerService.getAllCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(customers);
    }
}

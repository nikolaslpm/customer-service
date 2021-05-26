package com.ecommerce.customerservice.repository;

import com.ecommerce.customerservice.domain.Customer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@DataJpaTest
public class CustomerRepositoryIT {

    @Autowired
    private CustomerRepository customerRepository;

    @Test
    void shouldSaveCustomer() {
        Customer customer = Customer.builder()
            .firstName("Niko")
            .lastName("Lele")
            .email("eu@mail.com")
            .build();
        Customer savedCustomer = customerRepository.save(customer);

        assertNotNull(savedCustomer);
        assertNotNull(savedCustomer.getId());
        assertEquals(customer.getFirstName(), savedCustomer.getFirstName());
        assertEquals(customer.getLastName(), savedCustomer.getLastName());
        assertEquals(customer.getEmail(), savedCustomer.getEmail());
        assertEquals(customerRepository.count(), 1);
    }
}

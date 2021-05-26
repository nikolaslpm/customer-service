package com.ecommerce.customerservice;

import com.ecommerce.customerservice.domain.Customer;
import com.ecommerce.customerservice.repository.CustomerRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class CustomerControllerIT {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    CustomerRepository customerRepository;

    @Test
    void shouldFindAllCustomers() throws Exception {
        List<Customer> customers = List.of(
            Customer.builder().firstName("Harry").lastName("Potter").email("hog@mail.com").build(),
            Customer.builder().firstName("Hermione").lastName("Flinger").email("mione@mail.com").build()
        );
        customerRepository.save(customers.get(0));
        customerRepository.save(customers.get(1));

        MvcResult result = mockMvc.perform(get("/api/v1/customers"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(status().isOk())
            .andReturn();

        String response = result.getResponse().getContentAsString();
        System.out.println(response);
    }

    @Test
    void shouldFindById() throws Exception {
        Customer customer =
            Customer.builder().firstName("Harry").lastName("Potter").email("hog@mail.com").build();
        Long id = customerRepository.save(customer).getId();
        customer.setId(id);

        ObjectMapper objectMapper = new ObjectMapper();
        String expectedBody = objectMapper.writeValueAsString(customer);

        mockMvc.perform(get("/api/v1/customers/1"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(expectedBody))
            .andExpect(status().isOk());
    }
}

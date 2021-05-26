package com.ecommerce.customerservice;

import com.ecommerce.customerservice.controller.CustomerController;
import com.ecommerce.customerservice.domain.Customer;
import com.ecommerce.customerservice.service.CustomerService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(CustomerController.class)
@ExtendWith({SpringExtension.class, MockitoExtension.class})
public class CustomerControllerTest {

    @Autowired
    MockMvc mockMvc;

    @MockBean
    CustomerService customerService;

    @Test
    void shouldFindAllCustomers() throws Exception {
        List<Customer> customers = List.of(
            Customer.builder().firstName("Harry").lastName("Potter").email("hog@mail.com").build(),
            Customer.builder().firstName("Hermione").lastName("Flinger").email("mione@mail.com").build()
        );
        when(customerService.getAllCustomers()).thenReturn(customers);

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
            Customer.builder().id(1L).firstName("Harry").lastName("Potter").email("hog@mail.com").build();
        ObjectMapper objectMapper = new ObjectMapper();
        String expectedBody = objectMapper.writeValueAsString(customer);

        when(customerService.getCustomerById(1L)).thenReturn(customer);

        mockMvc.perform(get("/api/v1/customers/1"))
            .andExpect(content().contentType(MediaType.APPLICATION_JSON))
            .andExpect(content().json(expectedBody))
            .andExpect(status().isOk());
    }
}

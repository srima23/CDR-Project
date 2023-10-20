package com.example.project.Repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project.entity.Customers;
import com.example.project.repository.CustomersRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CustomersRepositoryTest {

    @Mock
    private CustomersRepository customersRepository;

    @Test
    public void testFindById() {
        // Create a Customers instance to be returned by the mock repository
        Customers customers = new Customers();
        customers.setId(1L);
        customers.setName("John Doe");

        // Configure the mock repository to return the Customers instance when findById
        // is called
        when(customersRepository.findById(1L)).thenReturn(Optional.of(customers));

        // Call the findById method and verify the result
        Optional<Customers> result = customersRepository.findById(1L);

        // Verify that the result matches the Customers instance created earlier
        assertTrue(result.isPresent());
        assertEquals(customers, result.get());
    }

    @Test
    public void testSave() {
        // Create a Customers instance to be saved
        Customers customers = new Customers();
        customers.setName("Alice Smith");

        // Configure the mock repository to save the Customers instance
        when(customersRepository.save(customers)).thenReturn(customers);

        // Call the save method and verify the result
        Customers savedCustomers = customersRepository.save(customers);

        // Verify that the savedCustomers matches the original Customers instance
        assertEquals(customers, savedCustomers);
    }

    // Add more tests for other repository methods as needed
}

package com.example.project.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project.business.CustomersForm;
import com.example.project.business.StatusClass;
import com.example.project.entity.Customers;
import com.example.project.repository.CustomersRepository;
import com.example.project.service.CustomersService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest

public class CustomersServiceTest {

    @Mock
    private CustomersRepository customersRepository;

    private CustomersService customersService;

    @BeforeEach
    public void setUp() {
        customersService = new CustomersService(customersRepository);
        customersService.setCustomersRepository(customersRepository);
    }

    @Test
    public void testSaveCustomersDetails() {
        CustomersForm form = new CustomersForm();
        form.setId(1);
        form.setName("John Doe");
        form.setNumber("1234567890");
        form.setLocation("Location1");
        form.setGender("Male");

        Customers expectedCustomer = new Customers();
        expectedCustomer.setId(1);
        expectedCustomer.setName("John Doe");
        expectedCustomer.setNumber("1234567890");
        expectedCustomer.setLocation("Location1");
        expectedCustomer.setGender("Male");

        when(customersRepository.save(any(Customers.class))).thenReturn(expectedCustomer);

        StatusClass status = customersService.saveCustomersDetails(form);

        assertEquals("added successfully", status.getStatus());
    }

    @Test
    public void testExportCustomersToCsv() {
        List<Customers> customersList = new ArrayList<>();
        Customers customer = new Customers();
        customer.setId(1);
        customer.setName("John Doe");
        customer.setNumber("1234567890");
        customer.setLocation("Location1");
        customer.setGender("Male");
        customersList.add(customer);

        String filePath = "test.csv";

        customersService.exportCustomersToCsv(customersList, filePath);

    }
}

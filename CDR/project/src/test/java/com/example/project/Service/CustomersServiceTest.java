package com.example.project.Service;

import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.project.business.CustomersForm;
import com.example.project.entity.Customers;
import com.example.project.repository.CustomersRepository;
import com.example.project.service.CustomersService;

@ExtendWith(SpringExtension.class)
@DataJpaTest
class CustomersServiceTest {

    @Mock
    private CustomersRepository customersRepository;

    @InjectMocks
    private CustomersService customersService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCustomersDetails() {
        // Create a CustomersForm with valid data
        CustomersForm form = new CustomersForm();
        form.setId(1);
        form.setName("John Doe");
        form.setNumber("1234567890");
        form.setLocation("Location1");
        form.setGender("Male");

        // Create a sample Customer for expected interaction with the repository
        Customers expectedCustomer = new Customers();
        expectedCustomer.setId(1);
        expectedCustomer.setName("John Doe");
        expectedCustomer.setNumber("1234567890");
        expectedCustomer.setLocation("Location1");
        expectedCustomer.setGender("Male");

        when(customersRepository.save(expectedCustomer)).thenReturn(expectedCustomer);

        // Call the service method and get the status
        // StatusClass status = customersService.saveCustomersDetails(form);

        // Verify that the status is as expected
        // assertEquals("added successfully", status.getStatus());
    }

    @Test
    void testExportCustomersToCsv() {
        List<Customers> customersList = new ArrayList<>();
        Customers customer = new Customers();
        customer.setId(1);
        customer.setName("John Doe");
        customer.setNumber("1234567890");
        customer.setLocation("Location1");
        customer.setGender("Male");
        customersList.add(customer);

        when(customersRepository.findAll()).thenReturn(customersList);

        // Define a file path
        String filePath = "/home/srimasarajita/Downloads/CapstoneProject/project/src/main/java/com/example/project/test.csv";

        // Call the export method
        customersService.exportCustomersToCsv(customersList, filePath);

        // You can add further assertions to check the exported file or its content.
    }
}

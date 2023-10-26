package com.example.project.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.project.business.CustomersForm;
import com.example.project.business.StatusClass;
import com.example.project.controller.CustomersController;
import com.example.project.service.CustomersService;

@SpringBootTest
class CustomersControllerTest {

    @MockBean
    private CustomersService customersService;

    @Autowired
    private CustomersController customersController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testSaveCustomersDetails() {
        CustomersForm customersForm = new CustomersForm();
        // Set appropriate values in customersForm

        StatusClass expectedStatus = new StatusClass();
        expectedStatus.setStatus("added successfully");

        when(customersService.saveCustomersDetails(customersForm)).thenReturn(expectedStatus);

        ResponseEntity<StatusClass> responseEntity = customersController.saveCustomersDetails(customersForm);

        // Assert the response entity and its body
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedStatus, responseEntity.getBody());

        verify(customersService, times(1)).saveCustomersDetails(customersForm);
    }

    @Test
    void testSaveCDRtoCSV() {
        CustomersForm customersForm = new CustomersForm();
        // Set appropriate values in customersForm

        StatusClass expectedStatus = new StatusClass();
        expectedStatus.setStatus("added successfully");

        when(customersService.saveCustomersDetails(customersForm)).thenReturn(expectedStatus);

        ResponseEntity<StatusClass> responseEntity = customersController.saveCDRtoCSV(customersForm);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(expectedStatus, responseEntity.getBody());
        verify(customersService, times(1)).saveCustomersDetails(customersForm);
    }
}

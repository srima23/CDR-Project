package com.example.project.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.example.project.business.CdrSmsForm;
import com.example.project.business.StatusClass;
import com.example.project.controller.CDRsmsController;
import com.example.project.service.CdrSmsService;

@SpringBootTest

class CDRsmsControllerTest {

    @MockBean
    private CdrSmsService cdrSmsService;

    @InjectMocks
    private CDRsmsController cdrSmsController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListCdrFiles() {
        // Set up any necessary mocks and data for the service
        when(cdrSmsService.listSmsCdrs()).thenReturn(cdrSmsController/* mock response */);

        // Call the controller method
        ResponseEntity<Object> responseEntity = cdrSmsController.listCdrFiles(null);

        // Assert the response
        assertNotNull(responseEntity);
        // Add more assertions if needed
    }

    @Test
    void testSaveCDRSmsData() {
        CdrSmsForm form = new CdrSmsForm(/* Set form data */);

        // Create a Status object (you can customize this based on your requirements)
        StatusClass expectedStatus = new StatusClass();
        expectedStatus.setStatus("Some status message");

        // Set up behavior to return the expected Status object
        when(cdrSmsService.saveCDRSmsData(form)).thenReturn(expectedStatus);

        // Call the controller method
        StatusClass status = cdrSmsController.saveCDRSmsData(form);

        // Assert the status or other expectations
        assertNotNull(status);
        assertEquals("Some status message", status.getStatus()); // Adjust this based on your expected status
    }

    @Test
    void testSaveCDRtoCSV() {
        CdrSmsForm form = new CdrSmsForm(/* Set form data */);

        // Create a Status object (you can customize this based on your requirements)
        StatusClass expectedStatus = new StatusClass();
        expectedStatus.setStatus("Some status message");

        // Set up behavior to return the expected Status object
        when(cdrSmsService.saveCDRSmsData(form)).thenReturn(expectedStatus);

        // Call the controller method
        StatusClass status = cdrSmsController.saveCDRtoCSV(form);

        // Assert the status or other expectations
        assertNotNull(status);
        assertEquals("Some status message", status.getStatus()); // Adjust this based on your expected status
    }

}

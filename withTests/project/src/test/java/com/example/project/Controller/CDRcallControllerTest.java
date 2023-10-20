package com.example.project.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;

import com.example.project.business.CdrCallForm;
import com.example.project.business.StatusClass;
import com.example.project.controller.CDRcallController;
import com.example.project.service.CdrCallService;
import com.example.project.service.UserService;

@SpringBootTest
class CDRcallControllerTest {

    @Mock
    private CdrCallService cdrCallService;

    @Mock
    private UserService userService;

    @InjectMocks
    private CDRcallController controller;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListCdrFiles() {
        // Mock authentication if needed
        // Authentication authentication = ...;

        // Mock behavior
        // when(cdrCallService.listCallCdrs()).thenReturn(...);

        // Call the controller method
        ResponseEntity<Object> response = controller.listCdrFiles(null);

        // Assert the response
        assertNotNull(response);
        // Add more assertions as needed
    }

    @Test
    void testSaveCDRCallData() {
        CdrCallForm form = new CdrCallForm(/* Set form data */);

        // Mock the behavior of cdrCallService.saveCDRCallData
        StatusClass expectedStatus = new StatusClass();
        expectedStatus.setStatus("added successfully");
        when(cdrCallService.saveCDRCallData(form)).thenReturn(expectedStatus);

        // Call the controller method
        StatusClass status = controller.saveCDRCallData(form);

        // Assert the status or other expectations
        assertNotNull(status);
        assertEquals("added successfully", status.getStatus());
        // Add more assertions as needed
    }

    @Test
    void testSaveCDRtoCSV() {
        CdrCallForm form = new CdrCallForm(/* Set form data */);

        // Mock behavior
        when(cdrCallService.saveCDRCallData(form)).thenReturn(new StatusClass());

        // Call the controller method
        StatusClass status = controller.saveCDRtoCSV(form);

        // Assert the status is not null
        assertNotNull(status);
    }

}

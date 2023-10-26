package com.example.project.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

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
import com.example.project.entity.CdrSms;
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
        List<CdrSms> mockCdrSmsList = new ArrayList<>(); // You need to initialize the list with your data
        when(cdrSmsService.listSmsCdrs()).thenReturn(mockCdrSmsList);

        ResponseEntity<Object> responseEntity = cdrSmsController.listCdrFiles(null);

        assertNotNull(responseEntity);
        // You can add additional assertions for the response content, if needed.
    }

    @Test
    void testSaveCDRSmsData() {
        CdrSmsForm form = new CdrSmsForm(/* Set form data */);

        StatusClass expectedStatus = new StatusClass();
        expectedStatus.setStatus("Some status message");

        // Set up behavior to return the expected Status object
        when(cdrSmsService.saveCDRSmsData(form)).thenReturn(expectedStatus);

        ResponseEntity<StatusClass> responseEntity = cdrSmsController.saveCDRSmsData(form);

        // Assert the response entity and other expectations
        assertNotNull(responseEntity);

        StatusClass status = responseEntity.getBody();
        assertNotNull(status);
        assertEquals("Some status message", status.getStatus()); // Adjust this based on your expected status
    }

    @Test
    void testSaveCDRtoCSV() {
        CdrSmsForm form = new CdrSmsForm(/* Set form data */);
        StatusClass expectedStatus = new StatusClass();
        expectedStatus.setStatus("Some status message");

        when(cdrSmsService.saveCDRSmsData(form)).thenReturn(expectedStatus);

        ResponseEntity<StatusClass> responseEntity = cdrSmsController.saveCDRtoCSV(form);

        assertNotNull(responseEntity);

        StatusClass body = responseEntity.getBody();
        assertNotNull(body);
        assertEquals("Some status message", body.getStatus()); // Adjust this based on your expected status
    }

}

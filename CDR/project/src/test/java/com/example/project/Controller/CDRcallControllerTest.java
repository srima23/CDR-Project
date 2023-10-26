package com.example.project.Controller;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.example.project.business.CdrCallForm;
import com.example.project.business.StatusClass;
import com.example.project.controller.CDRcallController;
import com.example.project.service.CdrCallService;

@SpringBootTest
class CDRcallControllerTest {

    @MockBean
    private CdrCallService cdrCallService;

    @InjectMocks
    private CDRcallController cdRcallController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testListCdrFiles() {
        ResponseEntity<Object> response = cdRcallController.listCdrFiles(null);
        assertNotNull(response);
    }

    @Test
    void testSaveCDRCallData() {
        CdrCallForm form = new CdrCallForm(/* Set form data */);

        StatusClass expectedStatus = new StatusClass();
        expectedStatus.setStatus("added successfully");

        when(cdrCallService.saveCDRCallData(form)).thenReturn(expectedStatus);

        ResponseEntity<StatusClass> responseEntity = cdRcallController.saveCDRCallData(form);

        // Use a custom Answer to handle the ResponseEntity
        // Mockito.when(cdrCallService.saveCDRCallData(form)).thenAnswer(invocation ->
        // responseEntity);

        // ResponseEntity<StatusClass> response =
        // cdRcallController.saveCDRCallData(form);

        assertNotNull(responseEntity);
        // assertEquals(HttpStatus.OK, response.getStatusCode());

        StatusClass status = responseEntity.getBody();
        assertNotNull(status);
        assertEquals("added successfully", status.getStatus());
    }

    @Test
    void testSaveCDRtoCSV() {
        CdrCallForm form = new CdrCallForm(/* Set form data */);

        when(cdrCallService.saveCDRCallData(form)).thenReturn(new StatusClass());

        ResponseEntity<StatusClass> responseEntity = cdRcallController.saveCDRtoCSV(form);

        assertNotNull(responseEntity);
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());

        StatusClass status = responseEntity.getBody();
        assertNotNull(status);
    }
}

package com.example.project.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project.business.CdrSmsForm;
import com.example.project.business.StatusClass;
import com.example.project.entity.CdrSms;
import com.example.project.repository.CdrSmsRepository;
import com.example.project.service.CdrSmsService;

@SpringBootTest

@ExtendWith(MockitoExtension.class)
public class CdrSmsServiceTest {

    @Mock
    private CdrSmsRepository cdrSmsRepository;

    @InjectMocks
    private CdrSmsService cdrSmsService;

    // ... other imports and setup

    @Test
    public void testSaveCDRSmsData() {
        // Create a CdrSmsForm instance for testing
        CdrSmsForm form = new CdrSmsForm();
        form.setDate("2023-10-18");
        form.setTime("13:45");
        form.setSmsType("TestSMS");
        form.setReceiverLoc("TestReceiverLoc");
        form.setSubscriberLoc("TestSubscriberLoc");
        form.setReceiverNum((long) 123456);
        form.setSubscriberNum((long) 789012);
        form.setStatus("Sent");

        // Mock the behavior of the repository
        when(cdrSmsRepository.save(any())).thenReturn(new CdrSms());

        // Call the service method
        StatusClass status = cdrSmsService.saveCDRSmsData(form);

        // Verify the status
        assertEquals("added successfully", status.getStatus());
    }

    @Test
    public void testExportCdrSmsToCsv() {
        // Create a list of CdrSms for testing
        List<CdrSms> cdrSmsList = new ArrayList<>();
        // Add some CdrSms instances to the list

        // Mock the behavior of the repository
        when(cdrSmsRepository.findAll()).thenReturn(cdrSmsList);

        // Define a temporary file path for testing
        String tempFilePath = "temp_cdr_sms.csv";

        // Call the CSV export method
        cdrSmsService.exportCdrSmsToCsv(cdrSmsList, tempFilePath);

        // TODO: Validate the exported CSV file content using file operations or a CSV
        // parser
    }
}

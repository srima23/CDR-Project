package com.example.project.Service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.example.project.business.CdrCallForm;
import com.example.project.business.StatusClass;
import com.example.project.entity.CdrCall;
import com.example.project.repository.CdrCallRepository;
import com.example.project.service.CdrCallService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest

class CdrCallServiceTest {

    @MockBean
    private CdrCallRepository cdrCallRepository;

    @InjectMocks
    private CdrCallService cdrCallService;

@BeforeEach
void setUp() {
when(cdrCallRepository.findAll()).thenReturn(new ArrayList<>());
}

    @Test
    void testSaveCDRCallData() {
        // Create a CdrCallForm with data
        CdrCallForm form = new CdrCallForm();
        form.setCallType("Outgoing");
        form.setDate("2023-10-19");
        form.setTime("12:07");

        // Mock the repository's save method to return a CdrCall
        when(cdrCallRepository.save(any(CdrCall.class))).thenReturn(new CdrCall());

        // Call the service method
        StatusClass status = cdrCallService.saveCDRCallData(form);

        // Verify the status

        assertEquals("added successfully", status.getStatus());

    }

    @Test
    void testExportCdrCallsToCsv() throws IOException {
        // Mock data
        List<CdrCall> cdrCallList = new ArrayList<>();
        // Add CdrCall instances to the list...

        // Mock the repository's findAll method to return the cdrCallList
        when(cdrCallRepository.findAll()).thenReturn(cdrCallList);

        // Create a temporary file for testing
        String filePath = "test.csv";

        // Call the service method to export to CSV
        cdrCallService.exportCdrCallsToCsv(cdrCallList, filePath);

        // Validate the CSV file
        // For simplicity, you can just check if the file exists
        assertTrue(new File(filePath).exists());

        // Clean up: Delete the temporary file
        Files.deleteIfExists(Paths.get(filePath));
    }
}

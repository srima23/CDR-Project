package com.example.project.Repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project.entity.CdrSms;
import com.example.project.repository.CdrSmsRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CdrSmsRepositoryTest {

    @Mock
    private CdrSmsRepository cdrSmsRepository;

    @Test
    public void testFindById() {
        // Create a CdrSms to be returned by the mock repository
        CdrSms cdrSms = new CdrSms();
        cdrSms.setUsageId(1L);
        cdrSms.setSmsType("Incoming");

        // Configure the mock repository to return the CdrSms when findById is called
        when(cdrSmsRepository.findById(1L)).thenReturn(Optional.of(cdrSms));

        // Call the findById method and verify the result
        Optional<CdrSms> result = cdrSmsRepository.findById(1L);

        // Verify that the result matches the CdrSms created earlier
        assertTrue(result.isPresent());
        assertEquals(cdrSms, result.get());
    }

    @Test
    public void testSave() {
        // Create a CdrSms to be saved
        CdrSms cdrSms = new CdrSms();
        cdrSms.setSmsType("Outgoing");

        // Configure the mock repository to save the CdrSms
        when(cdrSmsRepository.save(cdrSms)).thenReturn(cdrSms);

        // Call the save method and verify the result
        CdrSms savedCdrSms = cdrSmsRepository.save(cdrSms);

        // Verify that the savedCdrSms matches the original CdrSms
        assertEquals(cdrSms, savedCdrSms);
    }

    // Add more tests for other repository methods as needed
}

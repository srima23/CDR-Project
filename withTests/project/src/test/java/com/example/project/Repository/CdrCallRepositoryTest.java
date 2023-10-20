package com.example.project.Repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project.entity.CdrCall;
import com.example.project.repository.CdrCallRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
public class CdrCallRepositoryTest {

    @Mock
    private CdrCallRepository cdrCallRepository;

    @Test
    public void testFindById() {
        // Create a CdrCall to be returned by the mock repository
        CdrCall cdrCall = new CdrCall();
        cdrCall.setUsageId(1L);
        cdrCall.setCallType("Outgoing");

        // Configure the mock repository to return the CdrCall when findById is called
        when(cdrCallRepository.findById(1L)).thenReturn(Optional.of(cdrCall));

        // Call the findById method and verify the result
        Optional<CdrCall> result = cdrCallRepository.findById(1L);

        // Verify that the result matches the CdrCall created earlier
        assertTrue(result.isPresent());
        assertEquals(cdrCall, result.get());
    }

    @Test
    public void testSave() {
        // Create a CdrCall to be saved
        CdrCall cdrCall = new CdrCall();
        cdrCall.setCallType("Incoming");

        // Configure the mock repository to save the CdrCall
        when(cdrCallRepository.save(cdrCall)).thenReturn(cdrCall);

        // Call the save method and verify the result
        CdrCall savedCdrCall = cdrCallRepository.save(cdrCall);

        // Verify that the savedCdrCall matches the original CdrCall
        assertEquals(cdrCall, savedCdrCall);
    }

    // Add more tests for other repository methods as needed
}

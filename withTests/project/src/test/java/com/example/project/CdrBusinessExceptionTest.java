package com.example.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project.exception.CdrBusinessException;

@SpringBootTest

public class CdrBusinessExceptionTest {

    @Test
    public void testCdrBusinessExceptionMessage() {
        String errorMessage = "This is an error message.";

        try {
            // Simulate throwing the exception
            throw new CdrBusinessException(errorMessage);
        } catch (CdrBusinessException ex) {
            // Ensure the message matches the one we set
            assertEquals(errorMessage, ex.getMessage());
        }
    }
}

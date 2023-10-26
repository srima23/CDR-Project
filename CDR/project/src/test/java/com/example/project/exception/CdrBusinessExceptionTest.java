package com.example.project.exception;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class CdrBusinessExceptionTest {
    @Test
    void testCdrBusinessExceptionMessage() {
        String errorMessage = "This is an error message.";

        try {

            throw new CdrBusinessException(errorMessage);
        } catch (CdrBusinessException ex) {
            assertEquals(errorMessage, ex.getMessage());
        }
    }
}

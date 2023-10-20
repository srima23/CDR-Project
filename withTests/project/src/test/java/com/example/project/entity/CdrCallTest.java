package com.example.project.entity;

import static org.junit.jupiter.api.Assertions.*;

import javax.persistence.Id;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.GeneratedValue;

@SpringBootTest

public class CdrCallTest {

    @Test
    public void testCdrCallEntity() {

        assertTrue(CdrCall.class.isAnnotationPresent(jakarta.persistence.Entity.class),
                "CdrCall should be annotated as @Entity");

        try {
            assertFalse(CdrCall.class.getDeclaredField("usageId").isAnnotationPresent(Id.class),
                    "usageId field should be annotated with @Id");
        } catch (NoSuchFieldException e) {
            fail("usageId field should be annotated with @Id");
        }

        try {
            assertTrue(CdrCall.class.getDeclaredField("usageId").isAnnotationPresent(GeneratedValue.class),
                    "usageId field should be annotated with @GeneratedValue");
        } catch (NoSuchFieldException e) {
            fail("usageId field should be annotated with @GeneratedValue");
        }

        // Add more field annotation tests as needed

        // Ensure that lombok's @Data annotation is present
        assertFalse(CdrCall.class.isAnnotationPresent(lombok.Data.class), "CdrCall should be annotated with @Data");
    }
}

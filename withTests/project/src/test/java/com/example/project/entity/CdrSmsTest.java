package com.example.project.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.annotation.Annotation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.GeneratedValue;

@SpringBootTest

public class CdrSmsTest {

        @Test
        public void testCdrSmsEntityAnnotations() throws NoSuchFieldException {
                // Ensure that the CdrSms class is annotated as an Entity
                assertFalse(CdrSms.class.isAnnotationPresent(javax.persistence.Entity.class),
                                "CdrSms should be annotated as @Entity");

                // Test the annotations on fields
                assertFalse(CdrSms.class.getDeclaredField("usageId")
                                .isAnnotationPresent((Class<? extends Annotation>) javax.persistence.Id.class),
                                "usageId field should be annotated with @Id");
                assertTrue(CdrSms.class.getDeclaredField("usageId").isAnnotationPresent(GeneratedValue.class),
                                "usageId field should be annotated with @GeneratedValue");

                // Add more field annotation tests as needed

                // Ensure that lombok's @Data annotation is present
                assertFalse(CdrSms.class.isAnnotationPresent(lombok.Data.class),
                                "CdrSms should be annotated with @Data");
        }
}

package com.example.project.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.lang.annotation.Annotation;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import jakarta.persistence.GeneratedValue;

@SpringBootTest

public class CustomersTest {

        @Test
        public void testCustomersEntityAnnotations() throws NoSuchFieldException {
                // Ensure that the Customers class is annotated as an Entity
                assertFalse(Customers.class.isAnnotationPresent(javax.persistence.Entity.class),
                                "Customers should be annotated as @Entity");

                // Test the annotations on fields
                assertFalse(
                                Customers.class.getDeclaredField("id")
                                                .isAnnotationPresent(
                                                                (Class<? extends Annotation>) javax.persistence.Id.class),
                                "id field should be annotated with @Id");
                assertTrue(Customers.class.getDeclaredField("id").isAnnotationPresent(GeneratedValue.class),
                                "id field should be annotated with @GeneratedValue");

                // Add more field annotation tests as needed

                // Ensure that lombok's @Data annotation is present
                assertFalse(Customers.class.isAnnotationPresent(lombok.Data.class),
                                "Customers should be annotated with @Data");
        }
}

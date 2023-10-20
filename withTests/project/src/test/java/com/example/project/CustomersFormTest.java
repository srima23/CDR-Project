package com.example.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project.business.CustomersForm;

@SpringBootTest

public class CustomersFormTest {

    @Test
    public void testGetterAndSetter() {
        CustomersForm customersForm = new CustomersForm();

        // Set values using setter methods
        customersForm.setId(1);
        customersForm.setName("John Doe");
        customersForm.setNumber("1234567890");
        customersForm.setLocation("Location1");
        customersForm.setGender("Male");

        // Check values using getter methods
        assertEquals(1, customersForm.getId());
        assertEquals("John Doe", customersForm.getName());
        assertEquals("1234567890", customersForm.getNumber());
        assertEquals("Location1", customersForm.getLocation());
        assertEquals("Male", customersForm.getGender());
    }
}

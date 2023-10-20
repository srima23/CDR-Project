package com.example.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project.business.UserForm;

@SpringBootTest

public class UserFormTest {

    @Test
    public void testGetterAndSetter() {
        UserForm userForm = new UserForm();

        // Set values using setter methods
        userForm.setName("John Doe");
        userForm.setPassword("password123");
        userForm.setPasswordRepeat("password123");

        // Check values using getter methods
        assertEquals("John Doe", userForm.getName());
        assertEquals("password123", userForm.getPassword());
        assertEquals("password123", userForm.getPasswordRepeat());
    }
}

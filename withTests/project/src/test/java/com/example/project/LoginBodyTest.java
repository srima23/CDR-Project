package com.example.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project.business.LoginBody;

@SpringBootTest

class LoginBodyTest {

    @Test
    void getUsername() {
        LoginBody loginBody = new LoginBody();
        loginBody.setUsername("testUsername");
        assertEquals("testUsername", loginBody.getUsername());
    }

    @Test
    void getPassword() {
        LoginBody loginBody = new LoginBody();
        loginBody.setPassword("testPassword");
        assertEquals("testPassword", loginBody.getPassword());
    }

    @Test
    void setUsername() {
        LoginBody loginBody = new LoginBody();
        loginBody.setUsername("newUsername");
        assertEquals("newUsername", loginBody.getUsername());
    }

    @Test
    void setPassword() {
        LoginBody loginBody = new LoginBody();
        loginBody.setPassword("newPassword");
        assertEquals("newPassword", loginBody.getPassword());
    }
}

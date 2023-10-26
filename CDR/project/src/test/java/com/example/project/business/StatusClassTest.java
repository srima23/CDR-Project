package com.example.project.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

class StatusClassTest {

    @Test
    void getStatus() {
        StatusClass statusObj = new StatusClass();
        statusObj.setStatus("testStatus");
        assertEquals("testStatus", statusObj.getStatus());
    }

    @Test
    void setStatus() {
        StatusClass statusObj = new StatusClass();
        statusObj.setStatus("newStatus");
        assertEquals("newStatus", statusObj.getStatus());
    }
}

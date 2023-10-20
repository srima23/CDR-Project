package com.example.project;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project.business.CdrSmsForm;

@SpringBootTest

public class CdrSmsFormTest {

    @Test
    public void testGetterAndSetter() {
        CdrSmsForm cdrSmsForm = new CdrSmsForm();

        // Set values using setter methods
        cdrSmsForm.setSubscriberNum((long) 123);
        cdrSmsForm.setReceiverNum((long) 456);
        cdrSmsForm.setDate("2023-10-18");
        cdrSmsForm.setTime("15:30");
        cdrSmsForm.setSubscriberLoc("Location1");
        cdrSmsForm.setReceiverLoc("Location2");
        cdrSmsForm.setSmsType("Incoming");
        cdrSmsForm.setStatus("Delivered");

        // Check values using getter methods
        assertEquals(123, cdrSmsForm.getSubscriberNum());
        assertEquals(456, cdrSmsForm.getReceiverNum());
        assertEquals("2023-10-18", cdrSmsForm.getDate());
        assertEquals("15:30", cdrSmsForm.getTime());
        assertEquals("Location1", cdrSmsForm.getSubscriberLoc());
        assertEquals("Location2", cdrSmsForm.getReceiverLoc());
        assertEquals("Incoming", cdrSmsForm.getSmsType());
        assertEquals("Delivered", cdrSmsForm.getStatus());
    }
}

package com.example.project.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class CdrSmsFormTest {

    @Test
    public void testGetterAndSetter() {
        CdrSmsForm cdrSmsForm = new CdrSmsForm();

        cdrSmsForm.setSubscriberNum("7689373627");
        cdrSmsForm.setReceiverNum("625484928");
        cdrSmsForm.setDate("2023-10-18");
        cdrSmsForm.setTime("15:30");
        cdrSmsForm.setSubscriberLoc("Location1");
        cdrSmsForm.setReceiverLoc("Location2");
        cdrSmsForm.setSmsType("Incoming");
        cdrSmsForm.setStatus("Delivered");

        assertEquals("7689373627", cdrSmsForm.getSubscriberNum());
        assertEquals("625484928", cdrSmsForm.getReceiverNum());
        assertEquals("2023-10-18", cdrSmsForm.getDate());
        assertEquals("15:30", cdrSmsForm.getTime());
        assertEquals("Location1", cdrSmsForm.getSubscriberLoc());
        assertEquals("Location2", cdrSmsForm.getReceiverLoc());
        assertEquals("Incoming", cdrSmsForm.getSmsType());
        assertEquals("Delivered", cdrSmsForm.getStatus());
    }
}

package com.example.project.business;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest

public class CdrCallFormTest {

    @Test
    public void testGetterAndSetter() {
        CdrCallForm cdrCallForm = new CdrCallForm();

        // Set values using setter methods
        cdrCallForm.setSubscriberNum("7689373627");
        cdrCallForm.setReceiverNum("625484928");
        cdrCallForm.setDate("2023-10-18");
        cdrCallForm.setTime("15:30");
        cdrCallForm.setDuration(120);
        cdrCallForm.setSubscriberLoc("Location1");
        cdrCallForm.setReceiverLoc("Location2");
        cdrCallForm.setCallType("Outgoing");
        cdrCallForm.setCallStatus("Completed");
        cdrCallForm.setVoicemail(true);

        // Check values using getter methods
        assertEquals("7689373627", cdrCallForm.getSubscriberNum());
        assertEquals("625484928", cdrCallForm.getReceiverNum());
        assertEquals("2023-10-18", cdrCallForm.getDate());
        assertEquals("15:30", cdrCallForm.getTime());
        assertEquals(120, cdrCallForm.getDuration());
        assertEquals("Location1", cdrCallForm.getSubscriberLoc());
        assertEquals("Location2", cdrCallForm.getReceiverLoc());
        assertEquals("Outgoing", cdrCallForm.getCallType());
        assertEquals("Completed", cdrCallForm.getCallStatus());
        assertTrue(cdrCallForm.isVoicemail());
    }
}

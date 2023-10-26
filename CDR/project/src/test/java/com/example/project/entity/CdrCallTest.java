package com.example.project.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

class CdrCallTest {

    @Test
    void testUsageIdGetterAndSetter() {
        CdrCall cdrCall = new CdrCall();
        cdrCall.setUsageId(1L);
        assertEquals(1L, cdrCall.getUsageId());
    }

    @Test
    void testSubscriberNumGetterAndSetter() {
        CdrCall cdrCall = new CdrCall();
        cdrCall.setSubscriberNum("9287666193");
        assertEquals("9287666193", cdrCall.getSubscriberNum());
    }

    @Test
    void testReceiverNumGetterAndSetter() {
        CdrCall cdrCall = new CdrCall();
        cdrCall.setReceiverNum("9834677456");
        assertEquals("9834677456", cdrCall.getReceiverNum());
    }

    @Test
    void testDateGetterAndSetter() {
        CdrCall cdrCall = new CdrCall();
        LocalDate date = LocalDate.of(2023, 10, 15);
        cdrCall.setDate(date);
        assertEquals(date, cdrCall.getDate());
    }

    @Test
    void testTimeGetterAndSetter() {
        CdrCall cdrCall = new CdrCall();
        LocalTime time = LocalTime.of(15, 30);
        cdrCall.setTime(time);
        assertEquals(time, cdrCall.getTime());
    }

    @Test
    void testDurationGetterAndSetter() {
        CdrCall cdrCall = new CdrCall();
        cdrCall.setDuration(120);
        assertEquals(120, cdrCall.getDuration());
    }

    @Test
    void testSubscriberLocGetterAndSetter() {
        CdrCall cdrCall = new CdrCall();
        cdrCall.setSubscriberLoc("SubscriberLoc");
        assertEquals("SubscriberLoc", cdrCall.getSubscriberLoc());
    }

    @Test
    void testReceiverLocGetterAndSetter() {
        CdrCall cdrCall = new CdrCall();
        cdrCall.setReceiverLoc("ReceiverLoc");
        assertEquals("ReceiverLoc", cdrCall.getReceiverLoc());
    }

    @Test
    void testCallTypeGetterAndSetter() {
        CdrCall cdrCall = new CdrCall();
        cdrCall.setCallType("Incoming");
        assertEquals("Incoming", cdrCall.getCallType());
    }

    @Test
    void testCallStatusGetterAndSetter() {
        CdrCall cdrCall = new CdrCall();
        cdrCall.setCallStatus("Successful");
        assertEquals("Successful", cdrCall.getCallStatus());
    }

    @Test
    void testVoicemailGetterAndSetter() {
        CdrCall cdrCall = new CdrCall();
        cdrCall.setVoicemail(true);
        assertEquals(true, cdrCall.isVoicemail());
    }
}

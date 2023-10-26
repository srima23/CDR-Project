package com.example.project.entity;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.LocalTime;

import org.junit.jupiter.api.Test;

class CdrSmsTest {

        @Test
        void testUsageIdGetterAndSetter() {
                CdrSms cdrSms = new CdrSms();
                cdrSms.setUsageId(1L);
                assertEquals(1L, cdrSms.getUsageId());
        }

        @Test
        void testSubscriberNumGetterAndSetter() {
                CdrSms cdrSms = new CdrSms();
                cdrSms.setSubscriberNum("9287666193");
                assertEquals("9287666193", cdrSms.getSubscriberNum());
        }

        @Test
        void testReceiverNumGetterAndSetter() {
                CdrSms cdrSms = new CdrSms();
                cdrSms.setReceiverNum("6784563799");
                assertEquals("6784563799", cdrSms.getReceiverNum());
        }

        @Test
        void testDateGetterAndSetter() {
                CdrSms cdrSms = new CdrSms();
                LocalDate date = LocalDate.of(2023, 10, 15);
                cdrSms.setDate(date);
                assertEquals(date, cdrSms.getDate());
        }

        @Test
        void testTimeGetterAndSetter() {
                CdrSms cdrSms = new CdrSms();
                LocalTime time = LocalTime.of(15, 30);
                cdrSms.setTime(time);
                assertEquals(time, cdrSms.getTime());
        }

        @Test
        void testSubscriberLocGetterAndSetter() {
                CdrSms cdrSms = new CdrSms();
                cdrSms.setSubscriberLoc("SubscriberLoc");
                assertEquals("SubscriberLoc", cdrSms.getSubscriberLoc());
        }

        @Test
        void testReceiverLocGetterAndSetter() {
                CdrSms cdrSms = new CdrSms();
                cdrSms.setReceiverLoc("ReceiverLoc");
                assertEquals("ReceiverLoc", cdrSms.getReceiverLoc());
        }

        @Test
        void testSmsTypeGetterAndSetter() {
                CdrSms cdrSms = new CdrSms();
                cdrSms.setSmsType("Outgoing");
                assertEquals("Outgoing", cdrSms.getSmsType());
        }

        @Test
        void testStatusGetterAndSetter() {
                CdrSms cdrSms = new CdrSms();
                cdrSms.setStatus("Delivered");
                assertEquals("Delivered", cdrSms.getStatus());
        }
}

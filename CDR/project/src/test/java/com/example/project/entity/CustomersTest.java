package com.example.project.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

class CustomersTest {

        @Test
        void testIdGetterAndSetter() {
                Customers customer = new Customers();
                customer.setId(1L);
                assertEquals(1L, customer.getId());
        }

        @Test
        void testNameGetterAndSetter() {
                Customers customer = new Customers();
                customer.setName("John Doe");
                assertEquals("John Doe", customer.getName());
        }

        @Test
        void testNumberGetterAndSetter() {
                Customers customer = new Customers();
                customer.setNumber("1234567890");
                assertEquals("1234567890", customer.getNumber());
        }

        @Test
        void testLocationGetterAndSetter() {
                Customers customer = new Customers();
                customer.setLocation("Location1");
                assertEquals("Location1", customer.getLocation());
        }

        @Test
        void testGenderGetterAndSetter() {
                Customers customer = new Customers();
                customer.setGender("Male");
                assertEquals("Male", customer.getGender());
        }
}

package com.example.project.entity;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project.repository.RateRepository;

@SpringBootTest
public class RateEntityTest {

    @Autowired
    private RateRepository rateRepository;

    @Test
    public void testCreateAndRetrieveRateEntity() {
        // Create a sample Rate entity
        Rate rate = new Rate();
        rate.setCallType("SomeCallType");
        rate.setSmsType("SomeSmsType");
        rate.setRate(10);

        // Save the entity to the database
        rateRepository.save(rate);

        // Retrieve the entity from the database
        Rate retrievedRate = rateRepository.findById(rate.getId()).orElse(null);

        // Perform assertions
        assertEquals("SomeCallType", retrievedRate.getCallType());
        assertEquals("SomeSmsType", retrievedRate.getSmsType());
        assertEquals(10, retrievedRate.getRate());
    }
}

package com.example.project.Repository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.example.project.entity.Rate;
import com.example.project.repository.RateRepository;

@ExtendWith(SpringExtension.class)

@SpringBootTest

public class RateRepositoryTest {

    @Mock
    private RateRepository rateRepository;

    @Test
    public void testFindRateById() {
        // Create a sample Rate entity
        Rate rate = new Rate();
        rate.setRate(5);

        // Mock a behavior using Mockito
        when(rateRepository.findById(rate.getId())).thenReturn(Optional.of(rate));

        // Perform the test
        Optional<Rate> foundRate = rateRepository.findById(rate.getId());
        assertTrue(foundRate.isPresent());
        assertEquals(5.0, foundRate.get().getRate(), 0.01);
    }
}

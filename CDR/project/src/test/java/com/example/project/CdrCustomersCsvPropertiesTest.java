package com.example.project;

import org.junit.jupiter.api.Test;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@EnableConfigurationProperties(CdrCustomersCsvProperties.class)
@TestPropertySource(locations = "classpath:application.yml")
public class CdrCustomersCsvPropertiesTest {

    @Test
    public void testCdrCustomerCsvProperties() {
    }

    @Configuration
    public static class TestConfig {
        @Bean
        @Primary
        public CdrCustomersCsvProperties cdrCustomersCsvProperties() {
            CdrCustomersCsvProperties properties = new CdrCustomersCsvProperties();
            properties.setPath(
                    "/home/srimasarajita/Downloads/CapstoneProject/project/src/test/java/com/example/project/Customerpath.csv");
            return properties;
        }
    }

}

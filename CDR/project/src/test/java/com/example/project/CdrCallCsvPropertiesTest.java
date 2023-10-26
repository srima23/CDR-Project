package com.example.project;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.test.context.TestPropertySource;

@SpringBootTest
@EnableConfigurationProperties(CdrCallCsvProperties.class)
@TestPropertySource(locations = "classpath:application.yml") // Adjust the property file path

public class CdrCallCsvPropertiesTest {

    @Autowired
    private CdrCallCsvProperties cdrCallCsvProperties;

    @Test
    public void testCdrCallCsvProperties() {
        String path = cdrCallCsvProperties.getPath();
    }

    @Configuration
    public static class TestConfig {
        @Bean
        @Primary
        public CdrCallCsvProperties cdrCallCsvProperties() {
            CdrCallCsvProperties properties = new CdrCallCsvProperties();
            properties.setPath(
                    "/home/srimasarajita/Downloads/CapstoneProject/project/src/test/java/com/example/project/Callpath.csv");
            return properties;
        }
    }
}

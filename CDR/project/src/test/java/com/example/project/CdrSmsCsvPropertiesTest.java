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
@EnableConfigurationProperties(CdrSmsCsvProperties.class)
@TestPropertySource(locations = "classpath:application.yml")
public class CdrSmsCsvPropertiesTest {

    @Autowired
    private CdrSmsCsvProperties cdrSmsCsvProperties;

    @Test
    public void testCdrSmsCsvProperties() {
        String path = cdrSmsCsvProperties.getPath();
    }

    @Configuration
    public static class TestConfig {
        @Bean
        @Primary
        public CdrSmsCsvProperties cdrSmsCsvProperties() {
            CdrSmsCsvProperties properties = new CdrSmsCsvProperties();
            properties.setPath(
                    "/home/srimasarajita/Downloads/CapstoneProject/project/src/test/java/com/example/project/path.csv");
            return properties;
        }
    }
}

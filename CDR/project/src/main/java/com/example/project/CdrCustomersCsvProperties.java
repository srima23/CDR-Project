package com.example.project;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "cdr.customers.csv")
public class CdrCustomersCsvProperties {
    private String Path;

    public String getPath() {
        return Path;
    }

    public void setPath(String Path) {
        this.Path = Path;
    }
}

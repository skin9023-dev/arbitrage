package com.arbitrage;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;

@SpringBootApplication
@ConfigurationPropertiesScan
public class ArbitrageMonitorApplication {

    public static void main(String[] args) {
        SpringApplication.run(ArbitrageMonitorApplication.class, args);
    }
}

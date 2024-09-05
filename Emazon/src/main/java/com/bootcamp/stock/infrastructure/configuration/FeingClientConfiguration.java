package com.bootcamp.stock.infrastructure.configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yaml.snakeyaml.internal.Logger;

@Configuration
public class FeingClientConfiguration {
    @Bean
    public Logger.Level feignLoggerLevel() {
        return Logger.Level.WARNING;
    }
}

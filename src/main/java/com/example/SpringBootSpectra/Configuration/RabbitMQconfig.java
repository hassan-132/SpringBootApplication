package com.example.SpringBootSpectra.Configuration;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQconfig {
    @Bean
    public Queue kioskQueue() {
        return new Queue("KioskToSpectra");
    }

    @Bean
    public Queue webTSURecQueue() {
        return new Queue("SpectraToWebTSU");
    }

    @Bean
    public Queue webTSUSendQueue() {
        return new Queue("WebTSUToSpectra");
    }

    @Bean
    public Queue ACUQueue() {
        return new Queue("SpectraToACU");
    }
}

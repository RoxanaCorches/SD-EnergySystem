package com.example.devicesimulatorservice.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;

@Configuration
public class RabbitMQConfig {
    public static final String DEVICE_MEASUREMENTS_QUEUE = "device.measurements";

    @Bean
    public Queue deviceMeasurementsQueue() {
        return new Queue(DEVICE_MEASUREMENTS_QUEUE, true);
    }
}

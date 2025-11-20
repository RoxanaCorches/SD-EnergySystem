package com.example.monitoringservice.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;

@Configuration
public class RabbitMQConfig {
    public static final String EXCHANGE = "sync.exchange";

    //consumator, de la simulator
    public static final String DEVICE_MONITORING_QUEUE = "device.measurements";

    //consumator, de la device
    public static final String QUEUE_DEVICE_CREATED = "monitoring.device.created.queue";
    public static final String MONITORING_DEVICE_CREATED = "monitoring.deviceCreated";

    @Bean
    public TopicExchange exchange() {
        return new TopicExchange(EXCHANGE);
    }

    @Bean
    public Queue deviceMonitoringQueue() {
        return new Queue(DEVICE_MONITORING_QUEUE , true);
    }

    @Bean
    public Queue monitoringDeviceCreatedQueue() {
        return new Queue(QUEUE_DEVICE_CREATED, true);
    }

    @Bean
    public Binding bindDeviceCreated(Queue monitoringDeviceCreatedQueue, TopicExchange exchange) {
        return BindingBuilder.bind(monitoringDeviceCreatedQueue).to(exchange).with(MONITORING_DEVICE_CREATED);
    }

    @Bean
    public MessageConverter messageConverter(ObjectMapper objectMapper) {
        return new Jackson2JsonMessageConverter(objectMapper);
    }

    @Bean
    public AmqpTemplate amqpTemplate(ConnectionFactory connectionFactory, MessageConverter messageConverter) {
        RabbitTemplate rabbitTemplate = new RabbitTemplate(connectionFactory);
        rabbitTemplate.setMessageConverter(messageConverter);
        return rabbitTemplate;
    }
}

package com.pd2undav.sputnikfy.config;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;

@Configuration
public class RabbitConfig {

    // Exchanges
    public static final String SPUTNIKFY_EXCHANGE = "sputnikfy-exchange";

    @Bean
    public TopicExchange sputnikfyExchange() {
        return new TopicExchange(SPUTNIKFY_EXCHANGE);
    }

    // Queues
    @Bean
    public Queue billingQueue() {
        return QueueBuilder.durable("billing").quorum().build();
    }
    @Bean
    public Queue statisticsQueue() {
        return QueueBuilder.durable("statistics").quorum().build();
    }

    // Bindings
    @Bean
    public Binding billingBinding() {
        return BindingBuilder.bind(billingQueue()).to(sputnikfyExchange()).with("actividad.escucha");
    }
    @Bean
    public Binding statisticsBinding() {
        return BindingBuilder.bind(statisticsQueue()).to(sputnikfyExchange()).with("actividad.*");
    }
}
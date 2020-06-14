package com.pd2undav.sputnikfy.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.*;

@Configuration
public class RabbitConfig {

    // Exchanges
    @Bean
    public TopicExchange sputnikfyExchange() {
        return new TopicExchange("sputnikfy-topic");
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
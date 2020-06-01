package com.pd2undav.sputnikfy;

import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class SputnikfyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SputnikfyApplication.class, args);
	}

	// Exchanges
	@Bean
    public TopicExchange sputnikfyExchange() {
        return new TopicExchange("sput-topic");
	}

	// Queues
	@Bean
    public Queue billingQueue() {
        return new Queue("billing");
	}
	@Bean
	public Queue statisticsQueue() {
		return new Queue("statistics");
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
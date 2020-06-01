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

	@Bean
    public TopicExchange appExchange() {
        return new TopicExchange("sput-topic");
	}
	
	@Bean
    public Queue appQueueGeneric() {
        return new Queue("escuchas");
	}
	
	@Bean
    public Binding declareBindingGeneric() {
        return BindingBuilder.bind(appQueueGeneric()).to(appExchange()).with("actividad.escucha");
    }
}
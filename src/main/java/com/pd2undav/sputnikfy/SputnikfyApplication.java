package com.pd2undav.sputnikfy;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.*;

@SpringBootApplication
public class SputnikfyApplication {

	public static void main(String[] args) {
		SpringApplication.run(SputnikfyApplication.class, args);
	}

	// Exchanges
	@Bean
    public TopicExchange sputnikfyExchange() {
        return new TopicExchange("sputnikfy-topic");
	}

	// Queues
	@Bean
    public Queue billingQueue() {
		return QueueBuilder.durable("billing").quorum().build();
		//return new Queue("billing");
	}
	@Bean
	public Queue statisticsQueue() {
		return QueueBuilder.durable("statistics").quorum().build();
		//return new Queue("statistics");
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
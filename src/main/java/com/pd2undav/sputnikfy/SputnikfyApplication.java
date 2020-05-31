package com.pd2undav.sputnikfy;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.QueueBuilder;
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
	public ApplicationRunner runner(RabbitTemplate template) {
		return args -> {
			template.convertAndSend("myQueue", "Hello, world!");
		};
	}

	@Bean
	public Queue myQueue() {
		return new QueueBuilder().quorum().build().setActualName("hola");
	}

	@RabbitListener(queues = "hola")
	public void listen(String in) {
		System.out.println("Message read from myQueue : " + in);
	}

}
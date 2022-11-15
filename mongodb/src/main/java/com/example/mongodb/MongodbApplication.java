package com.example.mongodb;

import org.springframework.amqp.core.Queue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class MongodbApplication {

	static final String queueName = "PollResults";

	@Bean
	public Queue queue() {
		return new Queue(queueName);
	}



	public static void main(String[] args) {
		SpringApplication.run(MongodbApplication.class, args);
	}

}

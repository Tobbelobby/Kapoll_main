package com.example.Kapoll.Configuration;

import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {
    static final String queueName = "PollResults";

    @Bean
    public Queue queue() {
        return new Queue(queueName);
    }

    @Bean
    Jackson2JsonMessageConverter converter() {
        return new Jackson2JsonMessageConverter();
    }


}
// https://docs.spring.io/spring-amqp/docs/current/reference/html/#json-message-converter
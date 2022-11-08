package com.example.Kapoll.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitConfig {

    static final String queueName = "PollResults";
    static final String queueNamePoll = "Poll";

    @Bean
    Queue queue() {
        return new Queue(queueName);
    }
    @Bean
    Queue queuePoll(){
        return new Queue(queueNamePoll);
    }


}

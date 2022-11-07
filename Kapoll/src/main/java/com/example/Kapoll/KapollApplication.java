package com.example.Kapoll;

import com.example.Kapoll.Kapoll_db.Main;
import com.example.Kapoll.Kapoll_db.daoImplementation.KapollerDAO;
import com.example.Kapoll.Kapoll_db.daoImplementation.MainImplementation;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.listener.SimpleMessageListenerContainer;
import org.springframework.amqp.rabbit.listener.adapter.MessageListenerAdapter;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class KapollApplication {

	static KapollerDAO dao = new KapollerDAO();



	public static void main(String[] args) {
		SpringApplication.run(KapollApplication.class, args);

		Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
			public void run() {
				dao.close();
			}
		}, "Closing entity manager"));


	}

}

package com.example.Kapoll;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.stream.Stream;

@SpringBootApplication
public class KapollApplication {

	public static void main(String[] args) {
		SpringApplication.run(KapollApplication.class, args);
	}

	@Bean
	CommandLineRunner init(KapollRepository repository ) {
		return args -> {

		};
	}
}

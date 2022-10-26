package com.example.Kapoll;


import com.example.Kapoll.Kapoll_db.tables.Kapoller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
class LoadDatabase {
   /* private static final Logger log = LoggerFactory.getLogger(LoadDatabase.class);
    @Bean
    CommandLineRunner initDatabase(KapollRepository repository) {

        return args -> {
            Kapoller user1 = new Kapoller();
            user1.setFirstName("Winnie");
            user1.setLastName("The Pooh");
            user1.setUserName("The mighty bear");
            user1.setPassword("Honey123");

            log.info("Preloading " + repository.save(user1));
        };
    }*/
}

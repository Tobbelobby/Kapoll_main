package com.example.Kapoll;
import com.example.Kapoll.Kapoll_db.daoImplementation.KapollerDAO;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;


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

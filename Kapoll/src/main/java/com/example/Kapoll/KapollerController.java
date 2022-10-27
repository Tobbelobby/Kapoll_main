package com.example.Kapoll;


import com.example.Kapoll.Kapoll_db.daoImplementation.KapollerDAO;
import com.example.Kapoll.Kapoll_db.tables.Kapoller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class KapollerController {

    private final KapollerDAO kapollerDAO = new KapollerDAO();

    public KapollerController() {
    }

    @GetMapping("/Kapoller")
    List<Kapoller> getAllKapollers() {
        // Just save something so we get something in return.
        Kapoller user1 = new Kapoller();
        user1.setFirstName("Winnie");
        user1.setLastName("The Pooh");
        user1.setUserName("The mighty bear");
        user1.setPassword("Honey123");

        kapollerDAO.save(user1);

        return kapollerDAO.getAll();

    }


}

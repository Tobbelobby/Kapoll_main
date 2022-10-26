package com.example.Kapoll;
import com.google.gson.Gson;

import com.example.Kapoll.Kapoll_db.daoImplementation.KapollerDAO;
import com.example.Kapoll.Kapoll_db.daoImplementation.PollDAO;
import com.example.Kapoll.Kapoll_db.daoImplementation.PollResDAO;
import com.example.Kapoll.Kapoll_db.daoImplementation.VoterDAO;
import com.example.Kapoll.Kapoll_db.tables.Kapoller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {
    @Autowired
    private final KapollRepository repo;

    private KapollerDAO kapollerDAO = new KapollerDAO();
    private PollDAO pollDAO = new PollDAO();
    private PollResDAO pollResDAO = new PollResDAO();
    private VoterDAO voterDAO = new VoterDAO();

    public Controller(KapollRepository repo) {
        this.repo = repo;
    }


    @GetMapping("/Kapoller")
    List<Kapoller> GetAllKapollers(){
        return (List<Kapoller>) repo.findAll();

    }

  /*  @PostMapping("/Kapoller")
    String newKapoller(@RequestBody Kapoller newKapoller) {
        kapollerDAO.save(newKapoller);
        return new Gson().toJson(newKapoller);
    }*/
















}

package com.example.Kapoll;


import com.example.Kapoll.Kapoll_db.daoImplementation.KapollerDAO;
import com.example.Kapoll.Kapoll_db.daoImplementation.PollDAO;
import com.example.Kapoll.Kapoll_db.daoImplementation.PollResDAO;
import com.example.Kapoll.Kapoll_db.daoImplementation.VoterDAO;
import com.example.Kapoll.Kapoll_db.tables.Kapoller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private KapollerDAO kapollerDAO = new KapollerDAO();
    private PollDAO pollDAO = new PollDAO();
    private PollResDAO pollResDAO = new PollResDAO();
    private VoterDAO voterDAO = new VoterDAO();
    public Controller(){}

    @GetMapping("/Kapoller")
    List<Kapoller> GetAllKapollers(){

        return kapollerDAO.getAll();

    }















}

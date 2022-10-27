package com.example.Kapoll;


import com.example.Kapoll.Kapoll_db.daoImplementation.KapollerDAO;
import com.example.Kapoll.Kapoll_db.daoImplementation.PollDAO;
import com.example.Kapoll.Kapoll_db.daoImplementation.PollResDAO;
import com.example.Kapoll.Kapoll_db.daoImplementation.VoterDAO;
import com.example.Kapoll.Kapoll_db.tables.Kapoller;
import com.example.Kapoll.Kapoll_db.tables.Poll;
import com.example.Kapoll.Kapoll_db.tables.Poll_result;
import com.example.Kapoll.Kapoll_db.tables.Voters;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class Controller {

    @Autowired
    private KapollerDAO kapollerDAO = new KapollerDAO();
    private PollDAO pollDAO = new PollDAO();
    private PollResDAO pollResDAO = new PollResDAO();
    private VoterDAO voterDAO = new VoterDAO();

    //public Controller(){}

    //////////////////////// KAPOLLER
    @GetMapping("/Kapoller")
    List<Kapoller> GetAllKapollers(){
        return kapollerDAO.getAll();
    }

    @GetMapping("/Kapoller/{id}")
    Kapoller GetKapoller(@PathVariable Long id){ return kapollerDAO.get(id);}

    //FIX! test
    @PutMapping("Kapoller")
    void newKapoller(@RequestBody Kapoller newKapoller){
        kapollerDAO.addAndSave(newKapoller);
    }



    //////////////////////// POLL
    @GetMapping("/Poll")
    List<Poll> GetAllPolls(){
        return pollDAO.getAll();
    }

    @GetMapping("/Poll/{id}")
    Poll GetPoll(@PathVariable Long id){ return pollDAO.get(id);}

    @PutMapping("Poll")
    void newPoll(@RequestBody Poll newPoll){
        pollDAO.addAndSave(newPoll);
    }



    //////////////////////// POLLRESULT
    @GetMapping("/PollResult")
    List<Poll_result> GetAllPollRes(){
        return pollResDAO.getAll();
    }

    @GetMapping("/PollResult/{id}")
    Poll_result GetPollRes(@PathVariable Long id){ return pollResDAO.get(id);}

    @PutMapping("PollResult")
    void newPollRes(@RequestBody Poll_result newPollRes){
        pollResDAO.addAndSave(newPollRes);
    }



    //////////////////////// VOTERS
    @GetMapping("/Voters")
    List<Voters> GetAllVoters(){
        return voterDAO.getAll();
    }

    @GetMapping("/Voters/{id}")
    Voters GetVote(@PathVariable Long id){ return voterDAO.get(id);}

    @PutMapping("Voters")
    void newVote(@RequestBody Voters newVote){voterDAO.addAndSave(newVote);}


    //FIX - add mappings
    //PostMapping, PutMapping, DeleteMapping,














}

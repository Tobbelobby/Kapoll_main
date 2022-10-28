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

    public static boolean isNumeric(String str) {
        return str != null && str.matches("[-+]?\\d*\\.?\\d+");
    }
    public static Long convertStringToLong(String toBeConverted) {
        return Long.parseLong(toBeConverted);
    }
    //public Controller(){}

    //////////////////////// KAPOLLER
    @GetMapping("/api/Kapoller")
    List<Kapoller> GetAllKapollers(){
        return kapollerDAO.getAll();
    }

    @GetMapping("/api/Kapoller/{id}")
    Kapoller GetKapoller(@PathVariable Long id){
        return kapollerDAO.get(id);}

    //FIX! test
    @PutMapping("/api/Kapoller/{id}")
    void updateKapoller(@RequestBody Kapoller newKapoller, @PathVariable Long id) throws Exception {
        newKapoller.setId(id);
        kapollerDAO.update(newKapoller);
    }

    @PostMapping("/api/Kapoller")
    public void newKapoller(@RequestBody Kapoller newKapoller) {
        kapollerDAO.addAndSave(newKapoller);
    }

    @DeleteMapping("/api/Kapoller/{id}")
    public void deleteKapoller(@PathVariable Long id) {
        kapollerDAO.delete(kapollerDAO.get(id));
    }

    //////////////////////// POLL
    @GetMapping("/api/Poll")
    List<Poll> GetAllPolls(){
        return pollDAO.getAll();
    }

    @GetMapping("/api/Poll/{id}")
    Poll GetPoll(@PathVariable Long id){ return pollDAO.get(id);}

    @PutMapping("/apiPoll")
    void newPoll(@RequestBody Poll newPoll){
        pollDAO.addAndSave(newPoll);
    }



    //////////////////////// POLLRESULT
    @GetMapping("/api/PollResult")
    List<Poll_result> GetAllPollRes(){
        return pollResDAO.getAll();
    }

    @GetMapping("/api/PollResult/{id}")
    Poll_result GetPollRes(@PathVariable Long id){ return pollResDAO.get(id);}

    @PutMapping("/apiPollResult")
    void newPollRes(@RequestBody Poll_result newPollRes){
        pollResDAO.addAndSave(newPollRes);
    }



    //////////////////////// VOTERS
    @GetMapping("/api/Voters")
    List<Voters> GetAllVoters(){
        return voterDAO.getAll();
    }

    @GetMapping("/api/Voters/{id}")
    Voters GetVote(@PathVariable Long id){ return voterDAO.get(id);}

    @PutMapping("/apiVoters")
    void newVote(@RequestBody Voters newVote){voterDAO.addAndSave(newVote);}


    //FIX - add mappings
    //PostMapping, PutMapping, DeleteMapping,








}

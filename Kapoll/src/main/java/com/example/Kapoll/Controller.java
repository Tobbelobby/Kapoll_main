package com.example.Kapoll;


import com.example.Kapoll.Kapoll_db.daoImplementation.KapollerDAO;
import com.example.Kapoll.Kapoll_db.daoImplementation.PollDAO;
import com.example.Kapoll.Kapoll_db.daoImplementation.PollResDAO;
import com.example.Kapoll.Kapoll_db.daoImplementation.VoterDAO;
import com.example.Kapoll.Kapoll_db.tables.Kapoller;
import com.example.Kapoll.Kapoll_db.tables.Poll;
import com.example.Kapoll.Kapoll_db.tables.Poll_result;
import com.example.Kapoll.Kapoll_db.tables.Voters;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springdoc.api.annotations.ParameterObject;
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
    List<Kapoller> GetAllKapollers() {
        return kapollerDAO.getAll();
    }

    @Operation(summary = "Get a Kapoller by its id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the Kapoller",
                    content = { @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Kapoller.class)) }),
            @ApiResponse(responseCode = "400", description = "Invalid id supplied",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Kapoller not found",
                    content = @Content) })
    @GetMapping("/api/Kapoller/{id}")
    Kapoller GetKapoller(@ParameterObject @PathVariable Long id) {
        if (kapollerDAO.exist(id)) {
            return kapollerDAO.get(id);
        } else {
            throw new NotFoundException(id, "user");
        }
    }

    //FIX! test
    @PutMapping("/api/Kapoller/{id}")
    void updateKapoller(@ParameterObject @RequestBody Kapoller newKapoller, @PathVariable Long id) throws Exception {
        if (kapollerDAO.exist(id)) {
            newKapoller.setId(id);
            kapollerDAO.update(newKapoller);
        } else {
            throw new NotFoundException(id, "user");
        }

    }

    @PostMapping("/api/Kapoller")
    public void newKapoller(@ParameterObject @RequestBody Kapoller newKapoller) {
        kapollerDAO.addAndSave(newKapoller);
    }




    @DeleteMapping("/api/Kapoller/{id}")
    public void deleteKapoller(@ParameterObject @PathVariable Long id) {
        if (kapollerDAO.exist(id)) {
            kapollerDAO.delete(kapollerDAO.get(id));
        } else {
            throw new NotFoundException(id, "user");
        }

    }

    //////////////////////// POLL
    @GetMapping("/api/Poll")
    List<Poll> GetAllPolls() {
        return pollDAO.getAll();
    }

    @GetMapping("/api/Poll/{id}")
    Poll GetPoll(@ParameterObject @PathVariable Long id) {
        if (pollDAO.exist(id)) {
            return pollDAO.get(id);
        } else {
            throw new NotFoundException(id, "poll");
        }
    }

    @PostMapping("/api/Poll")
    void newPoll(@ParameterObject @RequestBody Poll newPoll) {
        pollDAO.addAndSave(newPoll);
    }

    @PutMapping("/api/Poll/{id}")
    void updatePoll(@ParameterObject @RequestBody Poll newPoll, @PathVariable Long id) throws Exception {
        if (pollDAO.exist(id)) {
            newPoll.setId(id);
            pollDAO.update(newPoll);
            kapollerDAO.update(pollDAO.get(newPoll.getId()).getOwner());
        } else {
            throw new NotFoundException(id, "poll");
        }
    }

    @DeleteMapping("/api/Poll/{id}")
    public void deletePoll(@ParameterObject @PathVariable Long id) {
        if (pollDAO.exist(id)) {
            pollDAO.delete(pollDAO.get(id));
        } else {
            throw new NotFoundException(id, "poll");
        }

    }

    //////////////////////// POLLRESULT
    @GetMapping("/api/PollResult")
    List<Poll_result> GetAllPollRes() {
        return pollResDAO.getAll();
    }

    @GetMapping("/api/PollResult/{id}")
    Poll_result GetPollRes(@ParameterObject @PathVariable Long id) {
        if (pollResDAO.exist(id)) {
            return pollResDAO.get(id);
        }else {
            throw new NotFoundException(id, "poll result");
        }
    }

    @PostMapping("/api/PollResult")
    void newPollRes(@ParameterObject @RequestBody Poll_result newPollRes) {
        pollResDAO.addAndSave(newPollRes);
    }

    @PutMapping("/api/PollResult/{id}")
    void updatePollRes(@ParameterObject @RequestBody Poll_result newPollRes, @PathVariable Long id) throws Exception {
        if (pollResDAO.exist(id)) {
            newPollRes.setId(id);
            pollResDAO.update(newPollRes);
        }else {
            throw new NotFoundException(id, "poll result");
        }
    }

    @DeleteMapping("/api/PollResult/{id}")
    void deletePollRes(@ParameterObject @PathVariable Long id) {
        if (pollResDAO.exist(id)) {
            pollResDAO.delete(id);
        }else {
            throw new NotFoundException(id, "poll result");
        }
    }


    //////////////////////// VOTERS
    @GetMapping("/api/Voters")
    List<Voters> GetAllVoters() {
        return voterDAO.getAll();
    }

    @GetMapping("/api/Voters/{id}")
    Voters GetVote(@ParameterObject @PathVariable Long id) {
        if (voterDAO.exist(id)){
            return voterDAO.get(id);
        }else {
            throw new NotFoundException(id, "voter");
        }
    }

    @PostMapping("/api/Voters")
    void newVote(@ParameterObject @RequestBody Voters newVote) {
        voterDAO.addAndSave(newVote);
    }

    @PutMapping("/api/Voters/{id}")
    void updateVoter(@ParameterObject @RequestBody Voters newVoter, @PathVariable Long id) throws Exception {
        if (voterDAO.exist(id)) {
            newVoter.setId(id);
            voterDAO.update(newVoter);
        }else {
            throw new NotFoundException(id, "voter");
        }
    }

    @DeleteMapping("/api/Voters/{id}")
    void deleteVoter(@ParameterObject @PathVariable Long id) {
        if (voterDAO.exist(id)) {
            voterDAO.delete(id);
        }else {
            throw new NotFoundException(id, "voter");
        }
    }


    //FIX - add mappings
    //PostMapping, PutMapping, DeleteMapping,


}

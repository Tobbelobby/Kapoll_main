package com.example.Kapoll;


import com.example.Kapoll.Kapoll_db.daoImplementation.KapollerDAO;
import com.example.Kapoll.Kapoll_db.daoImplementation.PollDAO;
import com.example.Kapoll.Kapoll_db.daoImplementation.PollResDAO;
import com.example.Kapoll.Kapoll_db.daoImplementation.VoterDAO;
import com.example.Kapoll.Kapoll_db.tables.Kapoller;
import com.example.Kapoll.Kapoll_db.tables.Poll;
import com.example.Kapoll.Kapoll_db.tables.Poll_result;
import com.example.Kapoll.Kapoll_db.tables.Voters;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
import java.util.List;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import static org.springframework.web.bind.annotation.RequestMethod.POST;


/**
 *
 */
@CrossOrigin(origins="http://localhost:3000", methods = {RequestMethod.GET, POST,RequestMethod.PUT,RequestMethod.DELETE, RequestMethod.OPTIONS})
@RestController
public class Controller {

    @Autowired
    private KapollerDAO kapollerDAO = new KapollerDAO();
    private PollDAO pollDAO = new PollDAO();
    private PollResDAO pollResDAO = new PollResDAO();
    private VoterDAO voterDAO = new VoterDAO();
    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
    RestTemplate restTemplate = new RestTemplate();
    private final RabbitTemplate rabbitTemplate;

    public Controller(RabbitTemplate rabbitTemplate){
        this.rabbitTemplate = rabbitTemplate;
    }


    //////////////////////// KAPOLLER

    //FIX: encode/decode url
    @CrossOrigin(origins="http://localhost:3000")
    @GetMapping("/api/Kapoller/check/{uName}")
    Boolean AccountExists(@ParameterObject @PathVariable String uName){
        return kapollerDAO.existsAccount(uName);
    }

    @CrossOrigin(origins="http://localhost:3000")
    @GetMapping("/api/Kapoller/username/{username}")
    Kapoller getUserByUsername(@ParameterObject @PathVariable String username)  {
        return GetKapoller(kapollerDAO.getKapollerIdByUsername(username));}

    @CrossOrigin(origins="http://localhost:3000")
    @GetMapping("/api/Kapoller")
    List<Kapoller> GetAllKapollers() {
        return kapollerDAO.getAll();
    }


    @CrossOrigin(origins="http://localhost:3000")
    @GetMapping("/api/Kapoller/{id}")
    Kapoller GetKapoller(@ParameterObject @PathVariable Long id) {
        if (kapollerDAO.exist(id)) {
            return kapollerDAO.get(id);
        } else {
            throw new NotFoundException(id, "user");
        }
    }
    @CrossOrigin(origins="http://localhost:3000")
    @PutMapping("/api/Kapoller/{id}")
    void updateKapoller(@ParameterObject @RequestBody Kapoller newKapoller, @PathVariable Long id) throws Exception {
        if (kapollerDAO.exist(id)) {
            newKapoller.setId(id);
            kapollerDAO.update(newKapoller);
        } else {
            throw new NotFoundException(id, "user");
        }

    }
    @CrossOrigin(origins="http://localhost:3000")
    @PostMapping("/api/Kapoller")
    public Kapoller newKapoller(@ParameterObject @RequestBody Kapoller newKapoller) {
        kapollerDAO.addAndSave(newKapoller);
        return newKapoller;
    }

    @CrossOrigin(origins="http://localhost:3000")
    @DeleteMapping("/api/Kapoller/{id}")
    public void deleteKapoller(@ParameterObject @PathVariable Long id) {
        if (kapollerDAO.exist(id)) {
            kapollerDAO.delete(kapollerDAO.get(id));
        } else {
            throw new NotFoundException(id, "user");
        }

    }

    @CrossOrigin(origins="http://localhost:3000")
    @GetMapping("/api/Poll")
    List<Poll> GetAllPolls() {
        return pollDAO.getAll();
    }

    @CrossOrigin(origins="http://localhost:3000")
    @GetMapping("/api/Poll/{id}")
    Poll GetPoll(@ParameterObject @PathVariable Long id) {
        if (pollDAO.exist(id)) {
            return pollDAO.get(id);
        } else {
            throw new NotFoundException(id, "poll");
        }
    }
    @CrossOrigin(origins="http://localhost:3000")
    @PostMapping("/api/Poll")
    Poll newPoll(@ParameterObject @RequestBody Poll newPoll) throws Exception {
        pollDAO.addAndSave(newPoll);
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String json = ow.writeValueAsString(newPoll);
        int len = json.length();
        String concatenateJson = json.substring(0, len-1);
        concatenateJson += String.format(", \"link\" : \"localhost:3000/%d\" }", newPoll.getId());
        postResultToDweet(concatenateJson);
        return newPoll;

    }
    @CrossOrigin(origins="http://localhost:3000")
    @PutMapping("/api/Poll/{id}")
    void updatePoll(@ParameterObject @RequestBody Poll newPoll, @PathVariable Long id) throws Exception {
        if (pollDAO.exist(id)) {
            newPoll.setId(id);
            pollDAO.update(newPoll);
            if(newPoll.getPoll_results() != null){
                //rabbitTemplate.convertAndSend( "","PollResults",newPoll);
            }
            kapollerDAO.update(kapollerDAO.get(pollDAO.getOwner(id)));
        } else {
            throw new NotFoundException(id, "poll");
        }
    }



    @DeleteMapping("/api/Poll/{id}")
    public void deletePoll(@ParameterObject @PathVariable Long id) throws Exception {
        if (pollDAO.exist(id)) {
            Poll poll = pollDAO.get(id);
            Kapoller owner = kapollerDAO.get(pollDAO.getOwner(id));
            pollDAO.delete(poll);
            kapollerDAO.update(owner);

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
    @PostMapping()
    ResponseEntity<String> postResultToDweet(@ParameterObject @RequestBody String data) {
        String uri = "https://dweet.io/dweet/for/Kapoll-results";
        RestTemplate restTemplate = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<String> request = new HttpEntity<>(data, headers);
        ResponseEntity<String> response = restTemplate.postForEntity(uri, request, String.class);

        return response;
    }

    @CrossOrigin(origins="http://localhost:3000")
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
    Voters getVote (@ParameterObject @PathVariable Long id) {
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



}

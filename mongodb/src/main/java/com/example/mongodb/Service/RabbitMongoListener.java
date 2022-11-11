package com.example.mongodb.Service;

import com.example.mongodb.Doucument.PollResult;
import com.example.mongodb.Repository.MongodbRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import org.springframework.stereotype.Service;



@Service
public class RabbitMongoListener {


    @Autowired
    private MongodbRepository mongoRepository;

    @RabbitListener(queues = {"PollResults"})
    public void messageReceiver(String pollResult) throws JSONException {
        System.out.println("Received: " + pollResult);
        JSONObject jsonObject= new JSONObject(pollResult );
        int yesVote = jsonObject.getInt("yesVote");
        int noVote = jsonObject.getInt("noVote");
        Long utilDate = jsonObject.getLong("utilDate");
        Long id = jsonObject.getLong("id");
        //Long pollId = jsonObject.getLong("pollid");

        mongoRepository.save(new PollResult(id,utilDate,yesVote,noVote));

    }
}

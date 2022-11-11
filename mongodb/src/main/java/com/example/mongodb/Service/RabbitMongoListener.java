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
        JSONObject resultObject= new JSONObject(pollResult );
        System.out.println(pollResult);
        int yesVote = resultObject.getInt("yesVote");
        int noVote = resultObject.getInt("noVote");
        Long utilDate = resultObject.getLong("utilDate");
        Long id = resultObject.getLong("id");
        System.out.println(yesVote);
        mongoRepository.save(new PollResult(id,utilDate,yesVote,noVote));

    }
}

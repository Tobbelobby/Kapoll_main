package com.example.mongodb.Service;
import com.example.mongodb.Doucument.PollResult;
import com.example.mongodb.Repository.MongodbRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import org.springframework.stereotype.Service;

import java.util.Arrays;


@Service
public class RabbitMongoListener {

    private PollResult pollResultdb;


    @Autowired
    private MongodbRepository mongoRepository;

    @RabbitListener(queues = {"PollResults"})
    public void messageReceiver(String pollResult) throws JSONException {
        System.out.println(pollResult);
        JSONObject resultObject= new JSONObject(pollResult);
        System.out.println(resultObject);
        JSONArray resultFromPoll = resultObject.getJSONArray("poll_results");
        System.out.println(resultFromPoll.get(0));
        mongoRepository.save(new PollResult(resultObject));



    }
}

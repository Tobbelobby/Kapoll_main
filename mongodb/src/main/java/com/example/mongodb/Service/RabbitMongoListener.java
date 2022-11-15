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
        JSONObject resultObject= new JSONObject(pollResult);
        System.out.println(resultObject);
        long kapollerId = resultObject.getLong("id");
        JSONObject resultArray = resultObject.getJSONArray("poll_results").getJSONObject(0);
        int yesVote = resultArray.getInt("yesVote");
        int noVote = resultArray.getInt("noVote");
        long pollDate = resultArray.getLong("utilDate");
        mongoRepository.save(new PollResult(kapollerId,yesVote,noVote,pollDate));



    }
}

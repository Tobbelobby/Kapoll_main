package com.example.mongodb.Service;
import com.example.mongodb.Doucument.PollResult;
import com.example.mongodb.Repository.MongodbRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;

import org.springframework.stereotype.Service;



@Service
public class RabbitMongoListener {


    @Autowired
    private MongodbRepository mongoRepository;

    @RabbitListener(queues = {"PollResults"})
    public void messageReceiver(String pollResult) throws JSONException {
        System.out.println(pollResult);
        JSONArray poll = new JSONArray(pollResult);
        JSONObject pollInfo = poll.getJSONObject(2);
        JSONObject pollResults = poll.getJSONArray(1).getJSONObject(0);
        long pollId = poll.getLong(0);
        long userId = pollInfo.getInt("id");
        int yesVote = pollResults.getInt("yesVote");
        int noVote = pollResults.getInt("noVote");
        long pollDate = pollResults.getLong("utilDate");// Should be in format MMM dd yyyy HH:mm:ss.SSS zzz but is not
        String title = pollInfo.getString("title");
        String question = pollInfo.getString("question");
        int time = pollInfo.getInt("time");
        System.out.println(pollResults);
        mongoRepository.save(new PollResult(pollId,userId, yesVote, noVote, pollDate, title, question, time));



    }
}

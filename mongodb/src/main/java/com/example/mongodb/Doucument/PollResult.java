package com.example.mongodb.Doucument;


import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("PollResult")
public class PollResult {

    @Id
    String id;

    private JSONObject poll;





    public PollResult(){}

    public PollResult(JSONObject poll){
        this.poll = poll;



    }

    @Override
    public String toString() {
        return "PollResult{" +
                "id=" + id +
                ", poll=" + poll +
                '}';
    }
}

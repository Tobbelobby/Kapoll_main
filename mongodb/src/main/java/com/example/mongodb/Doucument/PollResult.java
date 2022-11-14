package com.example.mongodb.Doucument;


import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("PollResult")
public class PollResult {

    @Id
    String id;

    private Long kapollerId;
    private int yesVotes;
    private int noVotes;
    private Long pollDate;



    public PollResult(){}

    public PollResult(Long kapollerId, int yesVotes, int noVotes, Long pollDate){
        this.kapollerId = kapollerId;
        this.yesVotes = yesVotes;
        this.noVotes = noVotes;
        this.pollDate = pollDate;
    }

    @Override
    public String toString() {
        return "PollResult{" +
                "id='" + id + '\'' +
                ", kapollerId=" + kapollerId +
                ", yesVotes=" + yesVotes +
                ", noVotes=" + noVotes +
                ", pollDate=" + pollDate +
                '}';
    }
}

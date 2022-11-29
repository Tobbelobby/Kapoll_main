package com.example.mongodb.Doucument;


import org.springframework.boot.configurationprocessor.json.JSONArray;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("PollResult")
public class PollResult {

    @Id
    String id;

    private Long pollId;
    private Long userID;
    private int yesVotes;
    private int noVotes;
    private Long pollDate;
    private String title;
    private String question;
    private int time;




    public PollResult(){}


    public PollResult(Long pollId, Long userID, int yesVotes, int noVotes, Long pollDate, String title, String question, int time){
        this.pollId = pollId;
        this.userID = userID;
        this.yesVotes = yesVotes;
        this.noVotes = noVotes;
        this.pollDate = pollDate;
        this.title = title;
        this.question = question;
        this.time = time;
    }
    @Override
    public String toString() {
        return "PollResult{" +
                "id='" + id + '\'' +
                ", pollId=" + pollId +
                ", userID=" + userID +
                ", yesVotes=" + yesVotes +
                ", noVotes=" + noVotes +
                ", pollDate=" + pollDate +
                ", title='" + title + '\'' +
                ", question='" + question + '\'' +
                ", time=" + time +
                '}';
    }



}

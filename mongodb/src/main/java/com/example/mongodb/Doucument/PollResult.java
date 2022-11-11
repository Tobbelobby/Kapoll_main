package com.example.mongodb.Doucument;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("PollResult")
public class PollResult {

    @Id
    private Long id;

    private int noVote;
    private int yesVote;
    private Long utilDate;

   // private Long pollId;


    public PollResult(){}

    public PollResult(Long id, Long utilDate, int noVote, int yesVote){
        this.id = id;
        this.utilDate = utilDate;
        this.yesVote = yesVote;
        this.noVote = noVote;
        //this.pollId = pollId;


    }


    @Override
    public String toString() {
        return "PollResult{" +
                "id='" + id + '\'' +
                ", utilDate='" + utilDate + '\'' +
                ", noVote=" + noVote +
                ", yesVote=" + yesVote +
                '}';
    }
}

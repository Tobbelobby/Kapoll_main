package com.example.Kapoll.Kapoll_db.tables;

import javax.persistence.*;

@Entity
public class Voters {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long voter_id;
    private Long poll_id;

    private int vote;

    public Long getVoter_id() {
        return voter_id;
    }
    public void setId(Long id) {this.voter_id = id;}

    //ikkje denna?
    public void setVoter_id(Long voter_id) {
        this.voter_id = voter_id;
    }

    public void setPoll_id(Long poll_id) {
        this.poll_id = poll_id;
    }

    public Long getPoll_id() {
        return poll_id;
    }

    public int getVote() {
        return vote;
    }

    public void setVote(int vote) {
        this.vote = vote;
    }
}

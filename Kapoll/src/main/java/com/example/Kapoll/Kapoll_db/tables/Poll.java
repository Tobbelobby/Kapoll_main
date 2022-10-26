package com.example.Kapoll.Kapoll_db.tables;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Poll {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;



    private String title;
    private String question;
    private int time;


    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "owner")
    @JsonBackReference
    private Kapoller owner;


    @OneToMany(fetch = FetchType.LAZY, mappedBy = "pollid")
    @JsonManagedReference
    private Set<Poll_result> poll_results;

    @OneToOne
    private Voters vote;

    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }


    public Kapoller getOwner() {
        return owner;
    }

    public void setOwner(Kapoller owner) {
        this.owner = owner;
    }

    public Set<Poll_result> getPoll_results() {
        return poll_results;
    }

    public void setPoll_results(Set<Poll_result> poll_results) {
        this.poll_results = poll_results;
    }

    public Voters getVote() {
        return vote;
    }

    public void setVote(Voters vote) {
        this.vote = vote;
    }
}

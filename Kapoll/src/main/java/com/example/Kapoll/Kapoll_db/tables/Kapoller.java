package com.example.Kapoll.Kapoll_db.tables;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.Set;

@Component
@Entity
public class Kapoller {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;
    private String userName;
    private String password;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "owner")
    @JsonManagedReference
    private Set<Poll> polls;
    public Long getId() {return id;}
    public void setId(Long id) {this.id = id;}
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Set<Poll> getPolls() {
        return polls;
    }

    public void addToPolls(Poll newPoll) {
        polls.add(newPoll);
    }

    public void removeFromPolls(Poll poll) {
        polls.remove(poll);
    }



    public void setPolls(Set<Poll> poll) {
        this.polls = poll;
    }
    
}

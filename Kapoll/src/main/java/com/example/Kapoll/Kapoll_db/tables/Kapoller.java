package com.example.Kapoll.Kapoll_db.tables;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.util.*;

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

    @OneToMany(mappedBy = "owner", cascade = CascadeType.ALL)
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

    public Set<Object> getPollIds(Set<Poll> polls) {
        Set<Object> pollIds = (Set<Object>) polls.stream().map((poll)-> poll.getId());
        return pollIds;

    }
    public void setPolls(Set<Poll> poll) {
        this.polls = poll;
    }


    @Override
    public String toString() {
        return "Kapoller{" +
                "id=" + id +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", userName='" + userName + '\'' +
                ", password='" + password + '\'' +
                ", polls=" + polls +
                '}';
    }
    
}

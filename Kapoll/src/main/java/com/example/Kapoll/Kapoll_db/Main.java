package com.example.Kapoll.Kapoll_db;


import com.example.Kapoll.Kapoll_db.tables.Kapoller;
import com.example.Kapoll.Kapoll_db.tables.Poll;
import com.example.Kapoll.Kapoll_db.tables.Poll_result;
import com.example.Kapoll.Kapoll_db.tables.Voters;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

public class Main {
    public static final String PERSISTENCE_UNIT_NAME = "Kapoller_db";

    public static void main(String[] args) {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
        EntityManager em = factory.createEntityManager();
        EntityTransaction emt = em.getTransaction();

        emt.begin();

        Kapoller user1 = new Kapoller();
        user1.setFirstName("Winnie");
        user1.setLastName("The Pooh");
        user1.setUserName("The mighty bear");
        user1.setPassword("Honey123");

        Poll poll = new Poll();
        poll.setQuestion("Is honey good?");
        poll.setTitle("The meaning of life");
        poll.setTime(10);
        poll.setOwner(user1);
        Voters vote = new Voters();
        vote.setVote(1);
        poll.setVote(vote);

        Poll_result poll_result = new Poll_result();
        poll_result.setPollid(poll);
        poll_result.setUtilDate(new Date());
        poll_result.setYesVote(vote.getVote());
        Set<Poll_result> pollResultSet = new HashSet<>();
        pollResultSet.add(poll_result);
        poll.setPoll_results(pollResultSet);



        em.persist(user1);
        em.persist(poll);
        em.persist(vote);
        em.persist(poll_result);

        emt.commit();

        em.close();
        factory.close();






        // TODO: Persist object world corresponding to the domain model of experiment 2.
    }
}

package com.example.Kapoll.Kapoll_db.daoImplementation;

import com.example.Kapoll.Kapoll_db.tables.Poll;


import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class PollDAO extends MainImplementation {



    public static final String PERSISTENCE_UNIT_NAME = "Kapoller_db";

    EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager em = factory.createEntityManager();
    EntityTransaction emt = em.getTransaction();

    List<Poll> polls = new ArrayList<>();

    public PollDAO(EntityManagerFactory entityManagerFactory, EntityManager em) {
        super(entityManagerFactory, em);
    }

    public PollDAO() {

    }

    @Override
    public Poll get(Long id) {
        return em.find(Poll.class, id);
    }

    @Override
    public List<Poll> getAll() {
        Query query = em.createQuery("SELECT p FROM Poll p");
        polls = query.getResultList();
        return polls;
    }

    @Override
    public void removeFromList(Object o) {
        polls.remove(o);
    }

    @Override
    public boolean exist(Long id) {
        if (!(get(id) == (null))) {
            return true;
        }
        else {
            return false;
        }
    }

    public Poll update(Poll poll) throws Exception {
        try {
            if (poll.getId() == null)
                throw new Exception("ID cannot be blank");

            Poll updatedPoll = get(poll.getId());
            polls.remove(updatedPoll);
            if (updatedPoll == null)
                throw new Exception("Employee not found");

            if (poll.getOwner() != null) {
                updatedPoll.setOwner(poll.getOwner());
                poll.getOwner().removeFromPolls(poll);
                poll.getOwner().addToPolls(updatedPoll);
            }

            if (poll.getTitle() != null) {
                updatedPoll.setTitle(poll.getTitle());
            }

            if (poll.getQuestion() != null) {
                updatedPoll.setQuestion(poll.getQuestion());
            }

            if (poll.getTime() > 0) {
                updatedPoll.setTime(poll.getTime());
            }

            if (poll.getPoll_results() != null){
                updatedPoll.setPoll_results(poll.getPoll_results());
            }

            if (poll.getVote() != null) {
                updatedPoll.setVote(poll.getVote());
            }
            polls.add(updatedPoll);
            save(updatedPoll);
            save(updatedPoll.getOwner());
            return updatedPoll;

        }
        catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    /*public void update(Poll poll, ArrayList<Object> params) {
        poll.setTitle((String) params.get(0));
        poll.setQuestion((String) params.get(1));
        poll.setTime((Integer) params.get(2));
        polls.add(poll);
        save(poll);

    }*/



}}

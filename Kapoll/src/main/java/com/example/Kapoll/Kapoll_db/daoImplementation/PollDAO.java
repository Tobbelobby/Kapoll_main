package com.example.Kapoll.Kapoll_db.daoImplementation;

import com.example.Kapoll.Kapoll_db.tables.Poll;
import com.example.Kapoll.Kapoll_db.tables.Poll_result;
import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


public class PollDAO extends MainImplementation {


    List<Poll> polls = new ArrayList<>();

    public PollDAO(EntityManagerFactory entityManagerFactory, EntityManager em) {
        super(entityManagerFactory, em);
    }

    public PollDAO() {}
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

    public Long getOwner(Long pollId) {
        Query query = em.createQuery("SELECT e.owner.id FROM Poll e WHERE e.id = ?1 ").setParameter(1, pollId);
        List list = query.getResultList();
        Object id = list.get(0);
        Long kapollerId = (Long)(id);
        return kapollerId;
    }

    public Poll update(Poll poll) throws Exception {
        try {
            if (poll.getId() == null)
                throw new Exception("ID cannot be blank");

            Poll updatedPoll = get(poll.getId());
            polls.remove(updatedPoll);
            if (updatedPoll == null)
                throw new Exception("Employee not found");


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
                for (Poll_result poll_result : poll.getPoll_results()) {
                    updatedPoll.addToResults(poll_result);
                }
            }
            if (poll.getVote() != null) {
                updatedPoll.setVote(poll.getVote());
            }
            polls.add(updatedPoll);
            save(updatedPoll);
            return updatedPoll;

        }
        catch (Exception ex){
            throw new Exception(ex.getMessage());
        }

}}

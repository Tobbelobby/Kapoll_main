package com.example.kapoll.Kapoll_db.daoImplementation;

import com.example.kapoll.Kapoll_db.tables.Poll_result;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

public class PollResDAO extends MainImplementation {

    public static final String PERSISTENCE_UNIT_NAME = "Kapoller_db";


    EntityManagerFactory factory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager em = factory.createEntityManager();
    EntityTransaction emt = em.getTransaction();

    List<Poll_result> pollResList = new ArrayList<>();

    @Override
    public Poll_result get(Long id) {
        return em.find(Poll_result.class, id);
    }

    @Override
    public List<Poll_result> getAll() {
        Query query = em.createQuery("SELECT r FROM Poll_result r");
        pollResList = query.getResultList();
        return pollResList;
    }

    @Override
    public void removeFromList(Object o) {
        pollResList.remove(o);
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
    public Poll_result update(Poll_result pollResult) throws Exception {
        try {
            if (pollResult.getId() == null)
                throw new Exception("ID cannot be blank");

            Poll_result updatedPollResult = get(pollResult.getId());
            pollResList.remove(updatedPollResult);
            if (updatedPollResult == null)
                throw new Exception("Employee not found");


            updatedPollResult.setNoVote(pollResult.getNoVote());
            updatedPollResult.setYesVote(pollResult.getYesVote());


            if (pollResult.getUtilDate() != null) {
                updatedPollResult.setUtilDate(pollResult.getUtilDate());
            }
            pollResList.add(updatedPollResult);
            save(updatedPollResult);
            return updatedPollResult;

        }
        catch (Exception ex){
            throw new Exception(ex.getMessage());
        }
    /*public void update(Poll_result poll_result, ArrayList<Object> params) {
        poll_result.setNoVote((Integer) params.get(0));
        poll_result.setYesVote((Integer) params.get(0));
        pollResList.add(poll_result);
        save(poll_result);

    }*/

}}

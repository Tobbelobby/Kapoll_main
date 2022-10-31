package com.example.Kapoll.Kapoll_db.daoImplementation;

import com.example.Kapoll.Kapoll_db.tables.Kapoller;
import com.example.Kapoll.Kapoll_db.tables.Poll;
import org.springframework.context.annotation.Configuration;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Stream;

@Configuration
public class KapollerDAO extends MainImplementation{
    public static final String PERSISTENCE_UNIT_NAME = "Kapoller_db";
    List<Kapoller> mainlist = new ArrayList<>();/*
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager em = entityManagerFactory.createEntityManager();
    EntityTransaction emt = em.getTransaction();*/

    public KapollerDAO(EntityManagerFactory entityManagerFactory, EntityManager em) {
        super(entityManagerFactory, em);
    }

    public KapollerDAO() {
        super();
    }


    public Kapoller update(Kapoller kapoller) throws Exception {
        try {
            if (kapoller.getId() == null)
                throw new Exception("ID cannot be blank");

            Kapoller updatedKapoller = get(kapoller.getId());
            mainlist.remove(updatedKapoller);
            if (updatedKapoller == null)
                throw new Exception("Employee not found");

            if (kapoller.getFirstName() != null) {
                updatedKapoller.setFirstName(kapoller.getFirstName());
            }

            if (kapoller.getLastName() != null) {
                updatedKapoller.setLastName(kapoller.getLastName());
            }

            if (kapoller.getPassword() != null) {
                updatedKapoller.setPassword(kapoller.getPassword());
            }

            if (kapoller.getUserName() != null) {
                updatedKapoller.setUserName(kapoller.getUserName());
            }

            if (kapoller.getPolls() != null) {
                updatedKapoller.setPolls(kapoller.getPolls());
            }
            mainlist.add(updatedKapoller);
            save(updatedKapoller);
            return updatedKapoller;

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
    }
    public Set<Object> getPollIds(Set<Poll> polls) {
        Set<Object> pollIds = (Set<Object>) polls.stream().map((poll)-> poll.getId());
        return pollIds;

    }

    @Override
    public Kapoller get(Long id) {
        return (em.find(Kapoller.class, id));
    }

    @Override
    public List<Kapoller> getAll() {
        Query query = em.createQuery("SELECT e FROM Kapoller e");
        mainlist = query.getResultList();
        return mainlist;
    }

    @Override
    public void removeFromList(Object o) {
        mainlist.remove(o);
    }
    @Override
    public boolean exist(Long id) {
        if (!(get(id) == (null))) {
            return true;
        } else {
            return false;
        }
    }


}

package com.example.Kapoll.Kapoll_db.daoImplementation;

import com.example.Kapoll.Kapoll_db.tables.Kapoller;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Configuration
public class KapollerDAO extends MainImplementation{
    public static final String PERSISTENCE_UNIT_NAME = "Kapoller_db";
    List<Kapoller> mainlist = new ArrayList<>();
    EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
    EntityManager em = entityManagerFactory.createEntityManager();
    EntityTransaction emt = em.getTransaction();






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

            if (kapoller.getPoll() != null) {
                updatedKapoller.setPoll(kapoller.getPoll());
            }
            mainlist.add(updatedKapoller);
            save(updatedKapoller);
            return updatedKapoller;

        } catch (Exception ex) {
            throw new Exception(ex.getMessage());
        }
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

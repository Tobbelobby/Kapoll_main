package com.example.kapoll.Kapoll_db.daoImplementation;

import com.example.kapoll.Kapoll_db.tables.Kapoller;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

public class KapollerDAO extends MainImplementation<Kapoller> {
  public static final String PERSISTENCE_UNIT_NAME = "Kapoller_db";
  EntityManagerFactory entityManagerFactory =
      Persistence.createEntityManagerFactory(PERSISTENCE_UNIT_NAME);
  @PersistenceContext EntityManager em = entityManagerFactory.createEntityManager();
  EntityTransaction emt = em.getTransaction();

  List<Kapoller> mainlist = new ArrayList<>();

  @Override
  public List<Kapoller> getAll() {
    Query query = em.createQuery("SELECT e FROM Kapoller e");
    mainlist = query.getResultList();
    return mainlist;
  }

  @Override
  public void removeFromList(Kapoller kapoller) {
    mainlist.remove(kapoller);
  }

  @Override
  public boolean exist(Long id) {
    return get(id) != (null);
  }

  @Override
  public Kapoller get(Long id) {
    return (em.find(Kapoller.class, id));
  }

  public Kapoller update(Kapoller kapoller) throws Exception {
    try {
      if (kapoller.getId() == null) throw new Exception("ID cannot be blank");

      Kapoller updatedKapoller = get(kapoller.getId());
      mainlist.remove(updatedKapoller);
      if (updatedKapoller == null) throw new Exception("Employee not found");

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
        updatedKapoller.setPoll(kapoller.getPolls());
      }
      mainlist.add(updatedKapoller);
      save(updatedKapoller);
      return updatedKapoller;

    } catch (Exception ex) {
      throw new Exception(ex.getMessage());
    }
  }
}
